package websocket;

import model.Game;
import model.Player;
import model.cardDecks.Card;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.websocket.Session;
import java.io.IOException;
import java.util.*;

/**
 * Created by jkao on 13/8/16.
 */

@ApplicationScoped
public class GameCollection {

    private Map<Integer, Map<String, Session>> players = new HashMap<>();
    private Map<Integer, Game> games = new HashMap<>();

    public GameCollection(){
        if (WebSocketConstants.testWithFakeData){
            List<Game> fakeGameList = FakeDataCreator.createFakeGameList();
            for (Game g:fakeGameList){
                addGame(g.getId(),g);
            }
        }
    }



    /////////////////// broadcasting ////////////////////////////////////

    public void broadcastGeneral(int gameId, String jsonStr){

        Map<String, Session> playersOfOneGame = players.get(gameId);
        Set<String> keys = playersOfOneGame.keySet();

        for (String key : keys){
            try{
                Session s = playersOfOneGame.get(key);
                s.getBasicRemote().sendText(jsonStr);


            }catch (IOException ioe){
                removeSession(gameId,key);
            }

        }

    }

    public void broadcastPlayerList(int gameId){

        List<Player> ps = getGameById(gameId).getPlayers();
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (Player p:ps) {

            String pName = p.getName();
            System.out.println("this is player Name: "+pName);
            arrBuilder.add(pName);
        }

        String jsonString = aggregationService.jsonStrBundling(WebSocketConstants.playerArr,arrBuilder);
        broadcastGeneral(gameId,jsonString);


    }

    public void broadcastHand(int gameId){
        List<Player> playersOfOneGame = games.get(gameId).getPlayers();
        Map<String,Session> sessionMap = players.get(gameId);

        for (Player p : playersOfOneGame){
            List<Card> hand = p.getCards();
            String nameAsKey = p.getName();
            Session s = sessionMap.get(nameAsKey);

            // this is for fake data
            if (s == null)continue;

            JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
            for (Card c: hand) {
                String path = c.getImagePath();
                arrBuilder.add(path);
            }

            try{

                String jsonStr = aggregationService.jsonStrBundling(WebSocketConstants.handCardArr,arrBuilder);
                s.getBasicRemote().sendText(jsonStr);

            }catch (IOException ioe){
                removeSession(gameId,nameAsKey);
            }

        }

    }



    /////////////////// game initialization ////////////////////////////////////


    public void initializeGame (int gameId){

        //init game
        Game game = games.get(gameId);
        String topCardImgPath = game.init().getImagePath();

        //broadcast start game msg
        String startGameMsgJsonStr = aggregationService.jsonStrBundling(WebSocketConstants.startGame, WebSocketConstants.startGame);
        broadcastGeneral(gameId,startGameMsgJsonStr);


        //broadcast first card
        String topCarJsonStr = aggregationService.jsonStrBundling(WebSocketConstants.discardPileTopCardPath,topCardImgPath);
        broadcastGeneral(gameId,topCarJsonStr);


        //broadcast hand
        broadcastHand(gameId);

    }


    /////////////////// add & get functions ////////////////////////////////////

    public void addGame(int gameId, Game game){
        games.put(gameId,game);

        Map<String, Session> playerMap = new HashMap<>();
        players.put(gameId,playerMap);
    }

    public void addPlayer(int gameId, String playerName, Session session){

        players.get(gameId).put(playerName,session);
        int playerCount = games.get(gameId).getPlayers().size();
        if (playerCount == WebSocketConstants.requiredNumOfPlayerPerGame) initializeGame(gameId);
    }

    public int getGameCount(){
        return games.keySet().size();
    }

    public Game getGameById(int gameId){
        return games.get(gameId);
    }

    public List<Game> getAllGames(){


        List<Game> allGames = new LinkedList<>();
        Set<Integer> keys = games.keySet();

        for (Integer key:keys) {
            Game g = games.get(key);
            allGames.add(g);
        }

        return allGames;
    }


    public void removeSession(int gameId, String key){
        players.get(gameId).remove(key);
    }

}

package websocket;

import model.Game;
import model.Player;
import model.cardDecks.Card;
import model.enums.GameStatus;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.*;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

/**
 * Created by jkao on 13/8/16.
 */

@ServerEndpoint("/games")
@RequestScoped
public class GameServer {


    @Inject
    private GameCollection gameCollection;
    private int gameId;
    private Game gameLocalReference;
    private String playerName;
    private Player playerLocalReference;

    //tested
    @OnOpen
    public void open(Session session)throws IOException{


        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("dataType", WebSocketConstants.feedback);
        builder.add("data", WebSocketConstants.successMsg);
        String jsonStr = builder.build().toString();

        session.getBasicRemote().sendText(jsonStr);

    }

    //tested
    @OnMessage
    public void message(String msg, Session session) throws IOException{


        //parse sent-in Json string
        JsonReader reader = Json.createReader(new StringReader(msg));

        JsonObject jsObj = reader.readObject();
        String actionType = jsObj.getString(WebSocketConstants.actionTypeKey);
        JsonObject dataFromClient = jsObj.getJsonObject(WebSocketConstants.dataKey);

        //dispatch
        dispatch(actionType,dataFromClient,session);



    }

    //tested
    private void dispatch(String actionType, JsonObject dataFromClient, Session session)throws IOException{
        if (actionType.equals(WebSocketConstants.createGameModeAction)) createGameMode(dataFromClient);
        else if (actionType.equals(WebSocketConstants.browseGamesModeAction)) browseAllGamesMode(dataFromClient,session);
        else if (actionType.equals(WebSocketConstants.discardAction)) discard(dataFromClient, session);
        else if (actionType.equals(WebSocketConstants.drawAction)) draw(session);
        else if (actionType.equals(WebSocketConstants.joinGameAction)) join(dataFromClient, session);
        else if (actionType.equals(WebSocketConstants.createGameAction)) createGame(dataFromClient,session);
        else System.out.println("nothing matched");
    }

    ////// Message Functions ///////////////////////////////////////////////////////////////

    //tested
    private void browseAllGamesMode(JsonObject dataFromClient, Session session)throws IOException{

        this.playerName = dataFromClient.getString(WebSocketConstants.playerNameKey);



        //get all games
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        List<Game> allGames = gameCollection.getAllGames();
        for (Game g:allGames) {
            if (!(g.getStatus().equals(GameStatus.WAITING))) continue;
            JsonObjectBuilder gameJson = Json.createObjectBuilder();
            gameJson.add("gameTitle",g.getTitle());
            gameJson.add("status",g.getStatus().toString());
            gameJson.add("gameId",g.getId());
            arrBuilder.add(gameJson.build());
        }

        //send: all games
        String jsonStr = aggregationService.jsonStrBundling(WebSocketConstants.gameList,arrBuilder);
        session.getBasicRemote().sendText(jsonStr);

    }

    //tested
    private void createGameMode(JsonObject dataFromClient){

        this.playerName = dataFromClient.getString(WebSocketConstants.playerNameKey);
    }

    //tested
    private void discard(JsonObject dataFromClient, Session session)throws IOException{

        //send: hand
        String discardedImgPath = dataFromClient.getString(WebSocketConstants.discardedImgPathKey);

        //discard
        List<Card> currentHand = playerLocalReference.getCards();


        Card cardToDiscard = null;
        for (Card c:currentHand) {
            String path = discardedImgPath.substring(discardedImgPath.lastIndexOf('/')+1);
            if (c.getImagePath().equals(path)) cardToDiscard = c;
        }
        gameLocalReference.discard(cardToDiscard,playerLocalReference);



        Card topCard = gameLocalReference.getDiscardPile().getTopCard();
        String topCardImgPath = topCard.getImagePath();

        //get updated hand
        sendUpdatedHand(session);

        //broadcast: discardPileTopCardImgPath
        String topCardJsonStr = aggregationService.jsonStrBundling(WebSocketConstants.discardPileTopCardPath,topCardImgPath);
        gameCollection.broadcastGeneral(gameId, topCardJsonStr);

    }

    //tested
    private void draw(Session session)throws IOException{

        //draw & add to hand
        Card cardDrawn = gameLocalReference.drawFromDrawPile();
        playerLocalReference.addCard(cardDrawn);

        sendUpdatedHand(session);
    }

    //tested
    private void join(JsonObject dataFromClient, Session session)throws IOException{

        //get gameId
        String gameIdStr = dataFromClient.getString(WebSocketConstants.gameIdKey);
        int gameId = Integer.parseInt(gameIdStr);
        this.gameId = gameId;
        gameLocalReference = gameCollection.getGameById(gameId);

        //add to collection
        addPlayer(session);

//        sendPlayerList(session);
        gameCollection.broadcastPlayerList(gameId);
    }

    //tested
    private void createGame(JsonObject dataFromClient, Session session)throws IOException{

        //get game title
        String gameTitle = dataFromClient.getString(WebSocketConstants.gameTitleKey);

        //create new game
        int id = gameCollection.getGameCount();
        String idStr = Integer.toString(id);

        Game game = new Game(id);
        game.setTitle(gameTitle);
        game.setStatus(GameStatus.WAITING);


        //set session(endpoint instance) field
        this.gameId = id;

        //add to collection
        gameCollection.addGame(id,game);
        gameLocalReference = game;

        //add player to games & players
        addPlayer(session);

        //send: gameId
        String jsonStr = aggregationService.jsonStrBundling(WebSocketConstants.gameId,idStr);
        session.getBasicRemote().sendText(jsonStr);


        gameCollection.broadcastPlayerList(gameId);
    }

    ////// Common Functions ///////////////////////////////////////////////////////////////

    private void sendUpdatedHand(Session session) throws  IOException{
        List<Card> handCards = playerLocalReference.getCards();
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (Card c : handCards){
            String cardImgPath = c.getImagePath();
            arrBuilder.add(cardImgPath);
        }

        String jsonStr = aggregationService.jsonStrBundling(WebSocketConstants.handCardArr,arrBuilder);
        session.getBasicRemote().sendText(jsonStr);
    }

    //tested
    private void addPlayer(Session session){
        Player player = new Player(playerName);

        gameLocalReference.addPlayer(player);
        gameCollection.addPlayer(gameId,playerName,session);
        playerLocalReference = player;
    }


}

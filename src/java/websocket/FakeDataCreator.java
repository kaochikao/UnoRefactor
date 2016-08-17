package websocket;

import model.Game;
import model.Player;
import model.cardDecks.Card;
import model.cardDecks.DrawPile;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by jkao on 16/8/16.
 */
public class FakeDataCreator {

    public static List<Game> createFakeGameList(){

        List<Game> games = new LinkedList<>();

        Game g1 = new Game(1);
        g1.setTitle("g1Title");
        g1.addPlayer(new Player("p1a"));
        g1.addPlayer(new Player("p1b"));
//        g1.addPlayer(new Player("p1c"));
        games.add(g1);

        Game g2 = new Game(2);
        g2.setTitle("g2Title");
        g2.addPlayer(new Player("p2a"));
        g2.addPlayer(new Player("p2b"));
        g2.addPlayer(new Player("p2c"));
        games.add(g2);

        Game g3 = new Game(3);
        g3.setTitle("g3Title");
        g3.addPlayer(new Player("p3a"));
        g3.addPlayer(new Player("p3b"));
        g3.addPlayer(new Player("p3c"));
        games.add(g3);

        Game g4 = new Game(4);
        g4.setTitle("g4Title");
        g4.addPlayer(new Player("p4a"));
        g4.addPlayer(new Player("p4b"));
        g4.addPlayer(new Player("p4c"));
        games.add(g4);

        return games;

    }


}

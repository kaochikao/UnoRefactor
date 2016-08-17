package model;


import model.cardDecks.Card;

import java.util.List;

/**
 * Created by jkao on 2/8/16.
 */
public class test {


    public static void main(String[] args) {

        Game game = new Game(1);


        for (Card card :game.getDrawPile().getCards()) {
            System.out.println(card);
        }

//        System.out.println(game.getDrawPile().getCards().size());
//        game = addPlayers(game);
//        distributeCards(game);
//
//        printPlayerHand(game.getPlayers().get(0));
//
//        simulateDiscard(game);
//
//        printPlayerHand(game.getPlayers().get(0));
//
//        Card card = game.getDiscardPile().getTopCard();
//        System.out.println("top card on discard pile: "+card);
//        System.out.println(game.getDrawPile().getNumOfCards());

    }

    
    private static Game addPlayers(Game game){
        
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Player player3 = new Player("player3");
        Player player4 = new Player("player4");
        Player player5 = new Player("player5");

        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);
        game.addPlayer(player5);

        return game;
    }

//    private static void distributeCards(Game game){
//
//        DrawPile drawPile = game.getDrawPile();
//
//        int count = 0;
//        while (count < 7){
//            for (Player player:game.getPlayers()) {
//                Card card = drawPile.drawCard();
//                player.addCard(card);
//            }
//            count++;
//        }
//
//    }


    private static void printPlayerHand(Player player){
        System.out.println("-------"+"the hand of: "+player.getName()+"-------");
        List<Card> cards = player.getCards();
        for (Card card:cards) {
            System.out.println("Color: "+card.getColor()+", Type: "+card.getType());
        }
        System.out.println("--------------------------");

    }

    private static void printPlayerValue(Player player){

        int total = 0;
        for (Card card:player.getCards()) {
            total += card.getValue();
        }
        System.out.println("the total value of: "+player.getName()+" is: "+total);

    }

    private static void simulateDiscard(Game game){
        Player player1 = game.getPlayers().get(0);
        Card tmpCard = player1.getCards().get(0);
        game.discard(tmpCard,player1);
    }




}

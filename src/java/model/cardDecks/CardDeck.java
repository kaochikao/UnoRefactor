package model.cardDecks;


import java.util.Stack;


public class CardDeck {


    protected Stack<Card> cards;

    public CardDeck(){
        cards = new Stack<>();
    }

    public Card drawCard(){
        return cards.pop();
    }


    //getters
    public int getNumOfCards() {
        return cards.size();
    }

    public Stack<Card> getCards() {
        return cards;
    }


}

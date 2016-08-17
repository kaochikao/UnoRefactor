package model;

import model.cardDecks.Card;

import java.util.List;
import java.util.Stack;

/**
 * Created by jkao on 2/8/16.
 */
public class Player {

    private String name;
    private Stack<Card> cards;

    public Player(String name) {
        this.name = name;
        this.cards = new Stack<>();
    }


    public void addCard(Card card){
        cards.add(card);
    }

    public void removeCard(Card card){
        cards.remove(card);
    }


    //getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(Stack<Card> cards) {
        this.cards = cards;
    }
}

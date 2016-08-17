package model.cardDecks;

/**
 * Created by jkao on 2/8/16.
 */
public class DiscardPile extends CardDeck {




    public void add(Card card){
        cards.push(card);
    }

    public Card getTopCard(){
        return cards.get(cards.size()-1);
    }

}

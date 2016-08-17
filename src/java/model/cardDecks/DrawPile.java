package model.cardDecks;

import model.enums.CardColor;
import model.enums.CardType;

import java.util.Stack;

/**
 * Created by jkao on 2/8/16.
 */
public class DrawPile extends CardDeck{

    public DrawPile(){
        cards = initiateDrawPile();
    }

    private Stack<Card> initiateDrawPile(){
        Stack<Card> cards = createCards();
//        Collections.shuffle(cards);
        return cards;
    }

    //108
    private Stack<Card> createCards(){
        Stack<Card> cardsOfQuantityTwo = createCardsOfQuantityTwo();
        Stack<Card> cardsOfQuantityOne = createCardsOfQuantityOne();
        Stack<Card> wildCards = createWildCards();

        Stack<Card> allCards = new Stack<>();
        allCards.addAll(cardsOfQuantityTwo);
        allCards.addAll(cardsOfQuantityOne);
        allCards.addAll(wildCards);

        return allCards;
    }

    private Stack<Card> createCardsOfQuantityTwo(){

        Stack<Card> cardsOfQuantityTwo = new Stack<>();

        for (CardType cardType : CardType.values()){
            boolean isZero = cardType.equals(CardType.ZERO);
            boolean isWild = cardType.equals(CardType.WILD);

            boolean isWildDrawFour = cardType.equals(CardType.WILDDRAWFOUR);

            if (isZero || isWild || isWildDrawFour) continue;

            for (CardColor cardColor : CardColor.values()) {
                if (cardColor.equals(CardColor.WILD)) continue;
                Card card = new Card();
                card.setType(cardType);
                card.setColor(cardColor);
                card.setValue(cardType.getValue());
                card.setImagePath(ImgPathResolver.resolve(card));

                cardsOfQuantityTwo.add(card);
                cardsOfQuantityTwo.add(card);
            }
        }

        return cardsOfQuantityTwo;
    }

    private Stack<Card> createCardsOfQuantityOne(){

        Stack<Card> cardsOfQuantityOne = new Stack<>();

        for (CardColor cardColor : CardColor.values()){
            if (cardColor.equals(CardColor.WILD)) break;
            Card card = new Card();
            card.setType(CardType.ZERO);
            card.setColor(cardColor);
            card.setValue(CardType.ZERO.getValue());
            card.setImagePath(ImgPathResolver.resolve(card));

            cardsOfQuantityOne.add(card);
        }
        return cardsOfQuantityOne;
    }

    private Stack<Card> createWildCards(){

        Stack<Card> wildCards = new Stack<>();

        for (int i = 0 ; i < 4 ; i++){

            Card wild = new Card();
            wild.setType(CardType.WILD);
            wild.setValue(CardType.WILD.getValue());
            wild.setColor(CardColor.WILD);
            wild.setImagePath(ImgPathResolver.resolve(wild));
            wildCards.add(wild);

            Card wildDrawFour = new Card();
            wildDrawFour.setType(CardType.WILDDRAWFOUR);
            wildDrawFour.setValue(CardType.WILDDRAWFOUR.getValue());
            wildDrawFour.setColor(CardColor.WILD);
            wildDrawFour.setImagePath(ImgPathResolver.resolve(wildDrawFour));
            wildCards.add(wildDrawFour);

        }

        return wildCards;
    }

}

package model.cardDecks;

import model.enums.CardColor;
import model.enums.CardType;

/**
 * Created by jkao on 2/8/16.
 */



public class Card {

    private CardColor color;
    private CardType type;
    private int value;
    private String imagePath;



    // getters and setters

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Card{" +
                "color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}

package model.enums;

/**
 * Created by jkao on 2/8/16.
 */
public enum CardType {

    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),

    SKIP(20),
    DRAWTWO(20),
    REVERSE(20),

    WILD(50),
    WILDDRAWFOUR(50);

    private final int value;


    CardType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

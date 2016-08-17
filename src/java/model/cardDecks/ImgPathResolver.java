package model.cardDecks;

import model.enums.CardColor;
import model.enums.CardType;

/**
 * Created by jkao on 3/8/16.
 */
public class ImgPathResolver {

    public static final String imgDirName = "";

    public static String resolve(Card card){


        if (card.getType().equals(CardType.WILD)) return imgDirName + "c4_01.png";
        if (card.getType().equals(CardType.WILDDRAWFOUR)) return imgDirName + "c4_01.png";

        StringBuilder sb = new StringBuilder();

        sb.append(imgDirName);
        sb.append("c");
        sb.append(resolveColor(card.getColor()));
        sb.append("_");
        sb.append(resolveType(card.getType()));
        sb.append(".png");


        return sb.toString();
    }

    private static String resolveColor(CardColor color){

        String str = null;

        switch (color){
            case YELLOW: str = "1";
                break;
            case RED: str = "0";
                break;
            case BLUE: str = "3";
                break;
            case GREEN: str = "2";
        }
        return str;
    }

    private static String resolveType(CardType type){

        String str = null;
        switch (type){
            case ZERO:
            case ONE:
            case TWO:
            case THREE:
            case FOUR:
            case FIVE:
            case SIX:
            case SEVEN:
            case EIGHT:
            case NINE:
                str = "0"+String.valueOf(type.getValue());
                break;
            case SKIP: str = "10";
                break;
            case DRAWTWO: str = "12";
                break;
            case REVERSE: str = "11";

        }
        return str;
    }

}

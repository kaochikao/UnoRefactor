package websocket;

/**
 * Created by jkao on 13/8/16.
 */
public class WebSocketConstants {

    // Testing Mode Power
    public static boolean testWithFakeData = false;
    public static int requiredNumOfPlayerPerGame = 4;

    /////   from client   //////////////////////////////////////////////////

    //json keys
    public static final String actionTypeKey = "actionType";
    public static final String dataKey = "data";

    //action type json values
    public static final String createGameModeAction = "createGameModeAction";
    public static final String browseGamesModeAction = "browseGamesModeAction";
    public static final String discardAction = "discardAction";
    public static final String drawAction = "drawAction";
    public static final String createGameAction = "createGameAction";
    public static final String joinGameAction = "joinGameAction";

    //data keys
    public static final String gameTitleKey = "gameTitleKey";
    public static final String gameIdKey = "gameIdKey";
    public static final String playerNameKey = "playerNameKey";
    public static final String discardedImgPathKey = "discardedImgPathKey";

    /////   to client   //////////////////////////////////////////////////

    //from server (data types)(Json keys)
    public static final String gameId = "gameId";
    public static final String gameList = "gameList"; //an array of game obj, 2 fields, gameTitle & status
    public static final String discardPileTopCardPath = "discardPileTopCardPath";
    public static final String handCardArr = "handCardArr"; //an array of paths, no keys
    public static final String playerArr = "playerArr"; // an array of names, no keys
    public static final String feedback = "feedback";
    public static final String successMsg = "success";
    public static final String startGame = "startGame";

}

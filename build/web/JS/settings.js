/**
 * Created by jkao on 14/8/16.
 */
//////////////////////////Path Constants/////////////////////////////////////////////////////////////////////////

var host = "localhost";
var port = "8080";
//var appName = "/UnoRefactor_war_exploded";
//var appName = "/JavaWebAppAndWS_war_exploded";
//var appName = "/UnoTeam2NBProj";
var appName = "/UnoTeam2Refactor";
var endPoint = "/games";

var imgDir = "/images/";

var wsEndPointPath = "ws://" + host + ":" + port + appName + endPoint;
var imgDirPath = "http://" + host + ":" + port + appName + imgDir;

//////////////////////////Message Constants/////////////////////////////////////////////////////////////////////////

//to server (action types)(identifiers for server's onMessage)
var createGameModeAction = "createGameModeAction";
var browseGamesModeAction = "browseGamesModeAction";
var discardAction = "discardAction";
var drawAction = "drawAction";
var createGameAction = 'createGameAction';
var joinGameAction = "joinGameAction";

//to server (data keys) (identifiers for second layer retrieval)
var gameTitleKey = "gameTitleKey";
var gameIdKey = "gameIdKey";
var playerNameKey = "playerNameKey";
var discardedImgPathKey = "discardedImgPathKey";

//from server (data types)(Json keys)
var gameId = "gameId";
var gameList = "gameList"; //an array of game obj, 2 fields, gameTitle & status
var discardPileTopCardPath = "discardPileTopCardPath";
var handCardArr = "handCardArr"; //an array of paths, no keys
var playerArr = "playerArr"; // an array of names, no keys
var feedback = "feedback";
var successMsg = "success";
var startGame = "startGame";

////////////////////////// Web Socket DTO /////////////////////////////////////////////////////////////////

var DTO = function(actionType, data){
    this.actionType = actionType;
    this.data = data;
}

//////////////////////////JQuery Selected Elements/////////////////////////////////////////////////////////////////

//views
var launchingPadView = $("#launchingPadView");
var createGameView = $("#createGameView");
var browseGamesView = $("#browseGamesView");
var waitingView = $("#waitingView");
var playingView = $("#playingView");
var allViews = [launchingPadView,createGameView,browseGamesView, waitingView,playingView];

//launching pad
var createGameModeBtn = $("#createGameModeBtn");
var browseGamesModeBtn = $("#browseGamesModeBtn");

//create game
var gameTitleInput = $('#gameTitleInput');
var createGameBtn = $("#createGameBtn");
var gameIdLabel = $("#gameIdLabel");

// browse games
var browseGamesTable = $('#browseGamesTable');
var gameTableHeaderRow = $("#gameTableHeaderRow");
var playerNameInput = $('#playerNameInput');
var joinBtn = $("#joinBtn");

//playing
//table
var discardPileImg = $('#discardPileImg');
var drawPileImg = $('#drawPileImg');
var playerListTable = $("#playerListTable");
var playerTableHeaderRow = $("#playerTableHeaderRow");
var goToGameButton = $("#goToGameButton");

//hand
var handCardSection = $('#handCardSection');
var handCardImgClassName = "handCardImage";
var handCardImages = $("."+handCardImgClassName);



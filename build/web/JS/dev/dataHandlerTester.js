/**
 * Created by jkao on 14/8/16.
 */


//5 functions to test
//gameId



var GameObj = function(gameTitle, status, gameId){
    this.gameTitle = gameTitle;
    this.status = status;
    this.gameId = gameId;
}

var fakeGameId = "jkGameId";
var fakeCardArr = ["jkImgPath1","jkImgPath2","jkImgPath3"];

var g1 = new GameObj("jkGameTitle1","waiting","gameId1");
var g2 = new GameObj("jkGameTitle2","playing","gameId2");
var g3 = new GameObj("jkGameTitle3","stop","gameId3");
var fakeGames = [g1,g2,g3];

var fakeTopCardPath = "jkTopCardPath";

var fakePlayers = ["George","Peter","Mary"];

displayGameId(fakeGameId);
refreshHandImages(fakeCardArr);
refreshBrowseGameTable(fakeGames);
refreshDiscardPileImgPath(fakeTopCardPath);
refreshPlayerListTable(fakePlayers);
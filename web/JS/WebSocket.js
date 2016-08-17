
/**
 * Created by jkao on 14/8/16.
 */

var socket = new WebSocket(wsEndPointPath);

drawPileImg.attr("src",imgDirPath+"drawpile.jpg");
goToGameButton.hide();




/////////////////// Web Socket events /////////////////////////////////
socket.onopen = function (){
    console.log("web socket created !!");
};


//onMessage as a Dispatcher
socket.onmessage = function(msg){

    var jsonObj = JSON.parse(msg.data);
    var dataType = jsonObj.dataType;
    console.log("this is datatype: "+dataType);
    var data = jsonObj.data;
    console.log("this is data: "+data);

    switch (dataType){

        case gameList:
            refreshBrowseGameTable(data);
            break;
        case gameId:
            displayGameId(data);
            break;
        case playerArr:
            refreshPlayerListTable(data);
            break;
        case discardPileTopCardPath:
            refreshDiscardPileImgPath(data);
            break;
        case handCardArr:
            refreshHandImages(data);
            break;
        case startGame:
            displayGotoGameBtn();
            break;
    }

}



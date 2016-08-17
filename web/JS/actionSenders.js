/**
 * Created by jkao on 14/8/16.
 */

//comply with DTO & actionType
//comply with jquery selected elements
//structure: get user input -> create dto -> send


////////////////// Launching Pad //////////////////////////////////

var mode;

createGameModeBtn.click(function () {
    mode = createGameModeAction;
    sendPlayerName();
    $.UIGoToArticle("#createGameView");
});

browseGamesModeBtn.click(function () {
    mode = browseGamesModeAction;
    sendPlayerName();
    $.UIGoToArticle("#browseGamesView");
});




function sendPlayerName(){
    var playerNameVal = playerNameInput.val();
    var dto = new DTO(mode,{"playerNameKey":playerNameVal});
    socket.send(JSON.stringify(dto));
    //test(dto);
};



//Create a Game

createGameBtn.click(function () {
    var gameTitleVal = gameTitleInput.val();
    var dto = new DTO(createGameAction,{"gameTitleKey":gameTitleVal});
    socket.send(JSON.stringify(dto));
    //test(dto);
    console.log("data sent");
    $.UIGoToArticle("#waitingView");
});



//Browse Games & Join Game

browseGamesView.on("click",".gameTableRow",function(){
    $(this).addClass("selectedGame");
    $(this).siblings().each(function () {
        $(this).removeClass("selectedGame");
    });

});

joinBtn.click(function () {
    //var selectedGame = $(".selectedGame").get(0);
    //var gameId = $(selectedGame).attr("id");
    var gameIdVal = $(".selectedGame").first().attr("id");
    var data = {"gameIdKey":gameIdVal};
    var dto = new DTO(joinGameAction,data);
    socket.send(JSON.stringify(dto));
    //test(dto);
    $.UIGoToArticle("#waitingView");
});



//discard
playingView.on("click","."+handCardImgClassName,function(){
    var imagePath = $(this).attr("src");
    var dto = new DTO(discardAction,{"discardedImgPathKey":imagePath});
    socket.send(JSON.stringify(dto));
    //test(dto);
});


//draw card
drawPileImg.click(function () {
    var dto = new DTO(drawAction,{});
    socket.send(JSON.stringify(dto));
    //test(dto);
});

goToGameButton.click(function () {
    $.UIGoToArticle("#playingView");
});

//testing..
function test(dto){
    var tmp = $(document.createElement("div"));
    tmp.html(JSON.stringify(dto));
    $("#testingSection").append(tmp);
}


/**
 * Created by jkao on 14/8/16.
 */



function displayGameId(gameId){
    gameIdLabel.html(gameId);
}

function refreshHandImages(paths){
    handCardSection.children().each(function () {
        $(this).remove();
    });

    for(var i = 0 ; i < paths.length ; i ++){
        var img = $(document.createElement("img"));
        img.addClass(handCardImgClassName);
        img.attr("src", imgDirPath + paths[i] );
        handCardSection.append(img);
    }
}

function refreshBrowseGameTable(games){

    gameTableHeaderRow.siblings().each(function () {
       $(this).remove();
    });

    for(var i = 0 ; i < games.length ; i++){
        var game = games[i];
        var row = $(document.createElement("tr")).addClass("gameTableRow");

        var titleCell = $(document.createElement("td")).addClass("titleCell");
        titleCell.text(game.gameTitle);
        var statusCell = $(document.createElement("td")).addClass("statusCell");
        statusCell.text(game.status);

        row.append(titleCell);
        row.append(statusCell);
        row.attr("id",game.gameId);
        browseGamesTable.append(row);
    }
}

function refreshDiscardPileImgPath(discardPileTopCardPath){
    discardPileImg.attr("src", imgDirPath + discardPileTopCardPath);
}

function refreshPlayerListTable(players){

    playerTableHeaderRow.siblings().each(function () {
        $(this).remove();
    });



    for(var i = 0 ; i < players.length ; i ++){

        var row = $(document.createElement("tr")).addClass("playerTableRow");
        var nameCell = $(document.createElement("td")).addClass("nameCell");
        nameCell.text(players[i]);
        row.append(nameCell);

        playerListTable.append(row);
    }


}

function displayGotoGameBtn(){
    goToGameButton.show();
}


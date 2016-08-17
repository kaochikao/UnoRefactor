package model;

import model.cardDecks.Card;
import model.cardDecks.DiscardPile;
import model.cardDecks.DrawPile;
import model.enums.CardColor;
import model.enums.CardType;
import model.enums.GameStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jkao on 2/8/16.
 */
public class Game {


    private int Id;
    private String title;
    private List<Player> players;
    private GameStatus status;
    private DrawPile drawPile;
    private DiscardPile discardPile;


    public Game(int id) {
        Id = id;
        this.players = new ArrayList<>();
        this.status = GameStatus.WAITING;
        this.drawPile = new DrawPile();
        this.discardPile = new DiscardPile();
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public Card init(){

        this.setStatus(GameStatus.STARTED);

        distributeCards();

        this.discardFirstCard();

        return this.getDiscardPile().getTopCard();

    }

    public void distributeCards(){

        DrawPile drawPile = this.getDrawPile();

        int count = 0;
        while (count < 7){
            for (Player player:this.getPlayers()) {
                Card card = drawPile.drawCard();
                player.addCard(card);
            }
            count++;
        }

    }

    public Card drawFromDrawPile(){
        return drawPile.drawCard();
    }

    public Card drawFromDiscardPile(){
        return discardPile.drawCard();
    }

    public boolean discard(Card cardDTO, Player player){

        CardColor color = cardDTO.getColor();
        CardType type = cardDTO.getType();

        //assume simple strategy...
        for (Card card:player.getCards()) {
            boolean isColorMatch = card.getColor().equals(color);
            boolean isTypeMatch = card.getType().equals(type);
            if (isColorMatch && isTypeMatch){
                player.removeCard(card);
                this.discardPile.add(card);
                return true;
            }
        }
        return false;
    }

    public void discardFirstCard(){

        Card card = this.drawFromDrawPile();
        this.discardPile.add(card);

    }

    //getters & setters

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public DrawPile getDrawPile() {
        return drawPile;
    }

    public void setDrawPile(DrawPile drawPile) {
        this.drawPile = drawPile;
    }

    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(DiscardPile discardPile) {
        this.discardPile = discardPile;
    }
}

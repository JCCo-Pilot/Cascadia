package EventAndListener;
import java.util.*;
import Entities.*;
public class GameStateEvent extends EventObject{
    private int state;
    private Player play;
    private Character difficulty;
    private String justForFun;
    public GameStateEvent(Object source, String panelName){
        super(source);
        justForFun = panelName;
    }
    public GameStateEvent(Object source, int s){
        super(source);
        state = s;
        play = null;
        difficulty = null;
    }
    public GameStateEvent(Object source, int s, Character diff){
        super(source);
        state = s;
        difficulty = diff;
        play = null;
    }
    public GameStateEvent(Object source, Player p){
        super(source);
        play = p;
        state =0;
        difficulty = null;
    }
    public GameStateEvent(Object source, int st , Player p){
        super(source);
        play = p;
        state = st;
        difficulty = null;
    }
    public Player getPlayer(){
        return play;
    }
    public int getState(){
        return state;
    }
    public Character getDifficulty(){
        return difficulty;
    }
    public String getPanel(){
        return justForFun;
    }
}

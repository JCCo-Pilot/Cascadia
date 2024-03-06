package EventAndListener;
import java.util.*;
import Entities.*;
public class GameStateEvent extends EventObject{
    private int state;
    private Player play;
    public GameStateEvent(Object source, int s){
        super(source);
        state = s;
        play = null;
    }
    public GameStateEvent(Object source, Player p){
        super(source);
        play = p;
        state =0;
    }
    public GameStateEvent(Object source, int st , Player p){
        super(source);
        play = p;
        state = st;
    }
    public Player getPlayer(){
        return play;
    }
    public int getState(){
        return state;
    }
}

package EventAndListener;
import java.util.*;
public class GameStateEvent extends EventObject{
    private int state;
    public GameStateEvent(Object source, int s){
        super(source);
        state = s;
    }
    public int getState(){
        return state;
    }
}

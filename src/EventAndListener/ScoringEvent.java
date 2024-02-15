package EventAndListener;
import java.util.*;
public class ScoringEvent extends EventObject{
    private int playerNum;
    public ScoringEvent(Object source, int s){
        super(source);
        playerNum = s;
    }
    public int getNum(){
        return playerNum;
    }
}

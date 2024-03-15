package EventAndListener;
import java.util.*;
import Entities.*;
public class AllowPickEvent extends EventObject{
    private boolean allow;
    private Player movedOn;
    public AllowPickEvent(Object source,boolean b){
        super(source);
        allow = b;
        movedOn = null;
    }
    public AllowPickEvent(Object source, Player p){
        super(source);
        movedOn = p;
        allow = false;
    }
    public boolean allowed(){
        return allow;
    }
    public Player movedOn(){
        return movedOn;
    }
}
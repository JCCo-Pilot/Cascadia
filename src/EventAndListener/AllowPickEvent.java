package EventAndListener;
import java.util.*;
public class AllowPickEvent extends EventObject{
    private boolean allow;
    public AllowPickEvent(Object source,boolean b){
        super(source);
        allow = b;
    }
    public boolean allowed(){
        return allow;
    }
}

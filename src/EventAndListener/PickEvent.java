package EventAndListener;
import java.util.*;
import Entities.*;
public class PickEvent extends EventObject{
    private WildlifeTokens token;
    private HabitatTiles tile;
    private Boolean switchTurns;
    private String eventName;
    public PickEvent(Object source, WildlifeTokens tok){
        super(source);
        token = tok;
        switchTurns = false;
    }
    public PickEvent(Object source, HabitatTiles til){
        super(source);
        tile = til;
        switchTurns = false;
    }
    public PickEvent(Object source,Boolean b){
        super(source);
        switchTurns = b;
    }
    //for painting
    public PickEvent(Object source, String s){
        super(source);
        switchTurns = false;
        tile = null;
        token = null;
        eventName = s;
    }
    public String getString(){
        return eventName;
    }
    public Boolean switchTurns(){
        return switchTurns;
    }
    public WildlifeTokens getToken(){
        return token;
    }
    public HabitatTiles getTile(){
        return tile;
    }
}

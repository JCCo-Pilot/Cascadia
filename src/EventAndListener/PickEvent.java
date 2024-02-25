package EventAndListener;
import java.util.*;
import Entities.*;
public class PickEvent extends EventObject{
    private WildlifeTokens token;
    private HabitatTiles tile;
    public PickEvent(Object source, WildlifeTokens tok){
        super(source);
        token = tok;
    }
    public PickEvent(Object source, HabitatTiles til){
        super(source);
        tile = til;
    }
    public WildlifeTokens getToken(){
        return token;
    }
    public HabitatTiles getTile(){
        return tile;
    }
}

package EventAndListener;
import java.util.*;
import Entities.*;
import Entities.WildlifeScoringCards.*;
public class ScoringEvent extends EventObject{
    private ArrayList<Player>players;
    private BearCard bc;
    private ElkCard ec;
    private SalmonCard sc;
    private HawkCard hc;
    private FoxCard fc;
    public ScoringEvent(Object source, ArrayList<Player>p){
        super(source);
        players = p;
    }
    //Setting methods
    public void setBear(BearCard b){
        bc = b;
    }
    public void setElk(ElkCard e){
        ec = e;
    }
    public void setSalmon(SalmonCard s){
        sc = s;
    }
    public void setHawk(HawkCard h){
        hc = h;
    }
    public void setFox(FoxCard f){
        fc = f;
    }

    //Gettting methods
    public BearCard getBear(){
        return bc;
    }
    public ElkCard getElk(){
        return ec;
    }
    public SalmonCard getSalmon(){
        return sc;
    }
    public HawkCard getHawk(){
        return hc;
    }
    public FoxCard getFox(){
        return fc;
    }
    public ArrayList<Player>getPlayers(){
        return players;
    }
}

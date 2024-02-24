package Entities;
import java.util.*;
public class Player {
    private int natureTokens;
    private int turns;
    private String name;
    private HabitatGraph graph;
    public Player(int i){
        name= "Player "+i;
        turns = 20;
    }
    public void setName(String s){name = s;}
    public String getName(){return name;}
    public HabitatGraph getGraph(){
        return graph;
    }
}

package Entities;
import java.util.*;

import Entities.Enums.CardAnimals;
import Entities.Enums.Habitats;
public class Player {
    private int natureTokens;
    private int turns;
    private String name;
    private HabitatGraph graph;
    private HashMap<CardAnimals, Integer> animalScores = new HashMap<CardAnimals, Integer>();
    private HashMap<Habitats, Integer> habitatScores = new HashMap<Habitats, Integer>();
    public Player(int i){
        name= "Player "+i;
        turns = 20;
    }
    public void setName(String s){name = s;}
    public String getName(){return name;}
    public HabitatGraph getGraph(){
        return graph;
    }

    public void setScore(CardAnimals c, Integer i){
        animalScores.put(c, i);
    }

    public Integer getScore(CardAnimals c){
        return animalScores.get(c);
    }

    public void setScore(Habitats h, Integer i){
        habitatScores.put(h, i);
    }

    public Integer getScore(Habitats h){
        return habitatScores.get(h);
    }
}

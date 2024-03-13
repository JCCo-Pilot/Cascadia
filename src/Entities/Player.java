package Entities;
import java.util.*;
import java.awt.*;
import Entities.Enums.CardAnimals;
import Entities.Enums.Habitats;
public class Player implements Comparable{
    private int natureTokens;
    private int turns;
    private String name;
    private HabitatGraph graph;
    private HashMap<CardAnimals, Integer> animalScores = new HashMap<CardAnimals, Integer>();
    private HashMap<Habitats, Integer> habitatScores = new HashMap<Habitats, Integer>();
    private HashMap<Habitats, Integer> habitatBonuses = new HashMap<Habitats, Integer>();
    
    private ArrayList<HabitatTiles>tiles = new ArrayList<>();
    private StarterTile st;

    public Player(int i){
        name= "Player "+i;
        turns = 20;
    }
    public void setName(String s){name = s;}
    public String getName(){return name;}
    public HabitatGraph getGraph(){
        return graph;
    }

    public ArrayList<HabitatTiles>getHexagons(){
        return tiles;
    }

    public void addTile(HabitatTiles t){
        tiles.add(t);
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

    public void setBonus(Habitats h, Integer i){
        habitatBonuses.put(h, i);
    }

    public Integer getBonus(Habitats h){
        return habitatBonuses.get(h);
    }

    public Integer getScore(){
        Integer sum = 0;
        for(Integer i:animalScores.values()){
            sum += i;
        }
        for(Integer i:habitatScores.values()){
            sum += i;
        }
        for(Integer i:habitatBonuses.values()){
            sum += i;
        }
        return sum;
    }

    public Integer getNatureTokens(){
        return natureTokens;
    }

    public void findAndReplace(HabitatTiles t){
        for (int i =0;i<tiles.size();i++){
            if (t.equals(tiles.get(i))){
                tiles.set(i,t);
            }
        }
    }

    public void drawInventory(Graphics g){
        // will implement a draw inventory based graph
        for (int i =0;i<tiles.size();i++){
            tiles.get(i).drawHexagon(g);
        }
        if (st!=null){
            st.paintStarter(g);
        }
    }
    
    public void addAll(ArrayList<HabitatTiles>ht){
        tiles.addAll(ht);
    }

    public void add(StarterTile s){
        st = s;
    }

    @Override
    public int compareTo(Object o) {
        Player p = (Player)o;
        if(this.getScore()-p.getScore()==0){
            return this.natureTokens-p.getNatureTokens();
        }
        return this.getScore()-p.getScore();
    }
}

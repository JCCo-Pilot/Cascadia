package Entities;
import java.util.*;
import java.awt.*;
import Entities.Enums.CardAnimals;
import Entities.Enums.Habitats;
import MathHelper.MathPoint;
public class Player implements Comparable{
    private int natureTokens;
    private int turns;
    private String name;
    private HabitatGraph graph;
    private HashMap<CardAnimals, Integer> animalScores = new HashMap<CardAnimals, Integer>();
    private HashMap<Habitats, Integer> habitatScores = new HashMap<Habitats, Integer>();
    private HashMap<Habitats, Integer> habitatBonuses = new HashMap<Habitats, Integer>(); 

    public Player(int i){
        name= "Player "+i;
        turns = 5;
        //turns = 20;
    }
    public void setName(String s){name = s;}
    public String getName(){return name;}
    public HabitatGraph getGraph(){
        return graph;
    }

    public ArrayList<HabitatTiles>getHexagons(){
        ArrayList<HabitatTiles> ret = new ArrayList<HabitatTiles>();
        for(HabitatTiles h:graph.iterate()){
            ret.add(h);
        }
        return ret;
    }

    public Boolean addTile(HabitatTiles t, MathPoint p){
        return graph.add(t, p);
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
        Integer sum = 0+ natureTokens;
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

    public void incrementNature(){
        natureTokens++;
    }

    public void spendNT(){
        if(natureTokens>0){
            natureTokens--;
        }
    }

    public Integer getNatureTokens(){
        return natureTokens;
    }

    public void findAndReplace(HabitatTiles t){
        
    }

    public void drawInventory(Graphics g, Boolean drawEmptys){
        // will implement a draw inventory based graph
        /*for (int i =0;i<tiles.size();i++){
            tiles.get(i).drawHexagon(g);
        }
        if (st!=null){
            st.paintStarter(g);
        }*/
        graph.drawGraph(g, drawEmptys);
    }
    
    public void addAll(ArrayList<HabitatTiles>ht){
        //tiles.addAll(ht);
    }

    public void add(StarterTile s){
        System.out.println("StarterTile add method called");
        System.out.println("up="+s.up);
        System.out.println("downleft="+s.down_left);
        System.out.println("downright="+s.down_right);
        graph = new HabitatGraph(s);
    }
    public void decrement() {
    	turns = turns-1;
        //System.out.println("Decremented");
    }
    public int getTurn() {
    	return turns;
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
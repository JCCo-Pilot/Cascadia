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
    private HashMap<Habitats, Integer> numHabitats = new HashMap<Habitats, Integer>();
    private HashMap<CardAnimals, Integer> numAnimals = new HashMap<CardAnimals, Integer>();
    private HashMap<Habitats, Integer> tempNumHabitats = new HashMap<Habitats, Integer>();
    private HashMap<CardAnimals, Integer> tempNumAnimals = new HashMap<CardAnimals, Integer>();

    public Player(int i){
        name= "Player "+i;
        turns = 20;
        //turns = 5;
    }
    public void setName(String s){name = s;}
    public String getName(){return name;}
    public HabitatGraph getGraph(){
        return graph;
    }

    public void updateTypesEndOfTurn(){
        for(HabitatTiles h:getGraph().iterate()){
            for(Habitats hab:h.getHabitats().values()){
                if(numHabitats.containsKey(hab)){
                    numHabitats.put(hab, numHabitats.get(hab)+1);
                }else{
                    numHabitats.put(hab, 1);
                }
            }
            if(h.tokenAnimal()!=null){
                if(numAnimals.containsKey(h.tokenAnimal())){
                    numAnimals.put(h.tokenAnimal(), numAnimals.get(h.tokenAnimal())+1);
                }else{
                    numAnimals.put(h.tokenAnimal(), 1);
                }
            }
        }
    }

    public void updateTypes(){
        for(HabitatTiles h:getGraph().iterate()){
            for(Habitats hab:h.getHabitats().values()){
                if(tempNumHabitats.containsKey(hab)){
                    tempNumHabitats.put(hab, tempNumHabitats.get(hab)+1);
                }else{
                    tempNumHabitats.put(hab, 1);
                }
            }
            if(h.tokenAnimal()!=null){
                if(tempNumAnimals.containsKey(h.tokenAnimal())){
                    tempNumAnimals.put(h.tokenAnimal(), tempNumAnimals.get(h.tokenAnimal())+1);
                }else{
                    tempNumAnimals.put(h.tokenAnimal(), 1);
                }
            }
        }
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
    public Integer getTotalAnimalScore() {
    	int sum = 0;
    	for(Integer i:animalScores.values()){
            sum += i;
        }
    	return sum;
    }
    public void setBonus(Habitats h, Integer i){
        habitatBonuses.put(h, i);
    }

    public Integer getBonus(Habitats h){
    	if(habitatBonuses.get(h) == null) {
    		return 0;
    	}
        return habitatBonuses.get(h);
    }
    public Integer getTotalHabitatScore() {
    	int sum = 0;
    	for(Integer i:habitatScores.values()){
            sum += i;
        }
    	for(Integer i:habitatBonuses.values()){
            sum += i;
        }
    	return sum;
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

    public void spendNatureToken(){
        if(natureTokens>0){
            natureTokens--;
        }
    }

    public Integer getNatureTokens(){
        return natureTokens;
    }

    public void findAndReplace(HabitatTiles t){
        
    }

    public void drawGraph(Graphics g, Boolean drawEmptyTiles){
        graph.drawGraph(g, drawEmptyTiles);
    }
    
    public void addAll(ArrayList<HabitatTiles>ht){
        //tiles.addAll(ht);
    }

    public void add(StarterTile s){
        PrintTester.print("StarterTile add method called");
        PrintTester.print("up="+s.up);
        PrintTester.print("downleft="+s.down_left);
        PrintTester.print("downright="+s.down_right);
        graph = new HabitatGraph(s);
    }
    public void decrement() {
    	turns = turns-1;
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

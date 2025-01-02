package Entities.WildlifeScoringCards;

import java.util.HashMap;
import java.util.HashSet;

//import javax.smartcardio.Card;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;


import java.awt.image.*;
import java.io.File;

import Entities.HabitatGraph;
import Entities.HabitatTiles;
import Entities.Player;
import Entities.Enums.CardAnimals;
import Entities.Enums.CardTypes;

public class FoxCard implements ScoringCard{

    private BufferedImage image;
    
    final CardAnimals animal = CardAnimals.FOX;
    CardTypes cardLetter;

    public FoxCard(CardTypes letter){
        this.cardLetter = letter;
        String choice = switch (letter) {
            case CARD_A -> "A";
            case CARD_B -> "B";
            case CARD_C -> "C";
            case CARD_D -> "D";
        };
        try{
            image = ImageIO.read(Objects.requireNonNull(FoxCard.class.getResource("/Entities/ScoringCardsPics/FoxScore" + choice + ".png")));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public CardAnimals getAnimal() {
        return animal;
    }

    public BufferedImage getImage(){
        return image;
    }

    @Override
    public Integer score(Player p) {
        return this.foxScore(p.getGraph());
    }

    public Integer foxScore(HabitatGraph h){
        HashSet<HabitatTiles> foxes = h.filter(CardAnimals.FOX);
        switch(cardLetter){
            case CARD_A:
                Integer points0 = 0;
                for(HabitatTiles fox: foxes){
                    HashSet<CardAnimals> uniqueAdjacents = new HashSet<CardAnimals>();
                    for(HabitatTiles connection:fox.getConnections().values()){
                        uniqueAdjacents.add(connection.tokenAnimal());
                        if(connection.tokenAnimal()!=null){
                            connection.highlights.add("FOX");
                        }
                    }
                    uniqueAdjacents.remove(null);
                    if(uniqueAdjacents.size()>0){
                        fox.highlights.add("FOX");
                    }
                    points0 += uniqueAdjacents.size();
                }
                return points0;
            case CARD_B:
                Integer points1 = 0;
                for(HabitatTiles fox:foxes){
                    HashMap<CardAnimals, Integer> histogram = new HashMap<CardAnimals, Integer>();
                    HashMap<CardAnimals, HashSet<HabitatTiles>> tracker = new HashMap<CardAnimals, HashSet<HabitatTiles>>();
                    for(HabitatTiles connection:fox.getConnections().values()){
                        if(histogram.containsKey(connection.tokenAnimal())){
                            histogram.put(connection.tokenAnimal(), histogram.get(connection.tokenAnimal())+1);
                        }else{
                            histogram.put(connection.tokenAnimal(), 1);
                        }
                        if(tracker.containsKey(connection.tokenAnimal())){
                            tracker.get(connection.tokenAnimal()).add(connection);
                        }else{
                            tracker.put(connection.tokenAnimal(), new HashSet<HabitatTiles>());
                            tracker.get(connection.tokenAnimal()).add(connection);
                        }
                    }
                    histogram.remove(null);
                    histogram.remove(CardAnimals.FOX);//cant be other pairs of foxes
                    HashSet<CardAnimals> remove = new HashSet<CardAnimals>();
                    for(CardAnimals animal:histogram.keySet()){
                        if(histogram.get(animal)!=2){
                            remove.add(animal);
                        }
                    }
                    for(CardAnimals animal:remove){
                        histogram.remove(animal);
                    }
                    switch(histogram.size()){
                        case 1:
                            points1 += 3;
                        break;
                        case 2:
                            points1 += 5;
                        break;
                        case 3:
                            points1 += 7;
                        break;
                        default:
                        break;
                    }
                    HabitatTiles.highlightGroups(histogram, tracker, "FOX");
                }
                
                return points1;
            case CARD_C:
                Integer points2 = 0;
                for(HabitatTiles fox:foxes){
                    HashMap<CardAnimals, Integer> histogram = new HashMap<CardAnimals, Integer>();
                    HashMap<CardAnimals, HashSet<HabitatTiles>> tracker = new HashMap<CardAnimals, HashSet<HabitatTiles>>();
                    for(HabitatTiles connection:fox.getConnections().values()){
                        if(histogram.containsKey(connection.tokenAnimal())){
                            histogram.put(connection.tokenAnimal(), histogram.get(connection.tokenAnimal())+1);
                        }else{
                            histogram.put(connection.tokenAnimal(), 1);
                        }
                        if(tracker.containsKey(connection.tokenAnimal())){
                            tracker.get(connection.tokenAnimal()).add(connection);
                        }else{
                            tracker.put(connection.tokenAnimal(), new HashSet<HabitatTiles>());
                            tracker.get(connection.tokenAnimal()).add(connection);
                        }
                    }
                    histogram.remove(null);
                    histogram.remove(CardAnimals.FOX);//cant be other foxes
                    Integer max = 0;
                    for(Integer i:histogram.values()){
                        if(i>max){
                            max=i;
                        }
                    }
                    points2+=max;//if 0, only adds 0
                    HashSet<CardAnimals> remove = new HashSet<CardAnimals>();
                    for(CardAnimals c:histogram.keySet()){
                        if(histogram.get(c)!=max){
                            remove.add(c);
                        }
                    }
                    for(CardAnimals c:remove){
                        histogram.remove(c);
                    }
                    HabitatTiles.highlightGroups(histogram, tracker, "FOX");
                }
                return points2;
            case CARD_D:
                Integer points3 = 0;
                HashSet<HabitatTiles> visitedFoxes = new HashSet<HabitatTiles>();
                HashSet<HashSet<HabitatTiles>> foxPairs = new HashSet<HashSet<HabitatTiles>>();
                for(HabitatTiles fox:foxes){
                    foxPairs.add(findPairs(fox, visitedFoxes));
                }
                foxPairs.remove(null);
                for(HashSet<HabitatTiles> pair:foxPairs){
                    HashSet<HabitatTiles> adjacentTiles = new HashSet<HabitatTiles>();
                    for(HabitatTiles fox:pair){
                        for(HabitatTiles connection:fox.getConnections().values()){
                            adjacentTiles.add(connection);
                        }
                    }
                    HashMap<CardAnimals, Integer> histogram = new HashMap<CardAnimals, Integer>();
                    HashMap<CardAnimals, HashSet<HabitatTiles>> tracker = new HashMap<CardAnimals, HashSet<HabitatTiles>>();
                    for(HabitatTiles adjacent:adjacentTiles){
                        if(histogram.containsKey(adjacent.tokenAnimal())){
                            histogram.put(adjacent.tokenAnimal(), histogram.get(adjacent.tokenAnimal())+1);
                        }else{
                            histogram.put(adjacent.tokenAnimal(), 1);
                        }
                        if(tracker.containsKey(adjacent.tokenAnimal())){
                            tracker.get(adjacent.tokenAnimal()).add(adjacent);
                        }else{
                            tracker.put(adjacent.tokenAnimal(), new HashSet<HabitatTiles>());
                            tracker.get(adjacent.tokenAnimal()).add(adjacent);
                        }
                    }
                    histogram.remove(null);
                    histogram.remove(CardAnimals.FOX);
                    HashSet<CardAnimals> remove = new HashSet<CardAnimals>();
                    for(CardAnimals animal:histogram.keySet()){
                        if(histogram.get(animal)!=2){
                            remove.add(animal);
                        }
                    }
                    for(CardAnimals animal:remove){
                        histogram.remove(animal);
                    }
                    switch(histogram.keySet().size()){
                        case 1:
                            points3 += 5;
                        break;
                        case 2:
                            points3 += 7;
                        break;
                        case 3:
                            points3 += 9;
                        break;
                        case 4:
                            points3 += 11;
                        break;
                        default:
                        break;
                    }
                    HabitatTiles.highlightGroups(histogram, tracker, "FOX");
                }
                return points3;
        }
        return 0;
    }

    private HashSet<HabitatTiles> findPairs(HabitatTiles fox, HashSet<HabitatTiles> visitedFoxes){
        HashSet<HabitatTiles> pair = new HashSet<HabitatTiles>();
        if(fox.getNumberOf(CardAnimals.FOX)==1&&!visitedFoxes.contains(fox)){
            pair.add(fox);
            visitedFoxes.add(fox);
            pair.add(fox.get(fox.getSideOf(fox.findFirstWithSpecificToken(CardAnimals.FOX))));
            visitedFoxes.add(fox.get(fox.getSideOf(fox.findFirstWithSpecificToken(CardAnimals.FOX))));
        }
        if(pair.size()==2){
            return pair;
        }else{
            return null;
        }
    }

}
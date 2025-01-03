package Entities.WildlifeScoringCards;

import java.util.HashMap;
import java.util.HashSet;

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

public class ElkCard implements ScoringCard{

    private BufferedImage image;
    
    final CardAnimals animal = CardAnimals.ELK;
    CardTypes cardLetter;

    public ElkCard(CardTypes letter){
        this.cardLetter = letter;
        String choice = switch (letter) {
            case CARD_A -> "A";
            case CARD_B -> "B";
            case CARD_C -> "C";
            case CARD_D -> "D";
        };
        try{
            image = ImageIO.read(Objects.requireNonNull(ElkCard.class.getResource("/Entities/ScoringCardsPics/ElkScore" + choice + ".png")));
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
        return this.elkScore(p.getGraph());
    }

    public Integer elkScore(HabitatGraph h){
        HashSet<HabitatTiles> elk = h.filter(CardAnimals.ELK);
        HashSet<HashSet<HabitatTiles>> elkGroups = new HashSet<HashSet<HabitatTiles>>();
        for(HabitatTiles t:elk){
            elkGroups.add(findElkGroup(t));
            
        }
        switch(cardLetter){
            case CARD_A:
                HashMap<HashSet<HabitatTiles>, Integer> groupPoints0 = new HashMap<HashSet<HabitatTiles>, Integer>();//maps each group to its number of points, will be used to clear up adjacent groups
                for(HashSet<HabitatTiles> s:elkGroups){//represents one elk group
                    HabitatTiles end = new HabitatTiles();
                    for(HabitatTiles tile:s){//find an ending card of the line
                        if(tile.getNumberOf(CardAnimals.ELK).equals(1)||tile.getNumberOf(CardAnimals.ELK).equals(0)){//the zero is for the special case in which there is just one elk, otherwise none of the elk tiles should be connected to zero elk
                            end = tile;
                        }
                    }

                    if(end.findFirstWithSpecificToken(CardAnimals.ELK)==null){
                        groupPoints0.put(s, 2);//avoids null error by bypassing elkIterateInDirection method
                    }else{
                        Integer direction = end.getSideOf(end.findFirstWithSpecificToken(CardAnimals.ELK));//direction that the line will continue in
                        HashSet<HabitatTiles> lineSet = elkIterateInDirection(end, direction);
                        switch(lineSet.size()){
                            case 2:
                                groupPoints0.put(lineSet, 5);//putting in lineSet instead of s makes sure that the connected resolve alg only counts the tiles being scored 
                            break;
                            case 3:
                                groupPoints0.put(lineSet, 9);
                            break;
                            case 4:
                                groupPoints0.put(lineSet, 13);
                            break;

                        }
                    }
                }
                resolveConnectedElkGroups(groupPoints0);
                Integer points0 = 0;
                for(Integer points:groupPoints0.values()){
                    points0+=points;
                }
                HabitatTiles.highlightGroups(groupPoints0, "ELK");
                return points0;
            case CARD_B:
                HashMap<HashSet<HabitatTiles>, Integer> groupPoints1 = new HashMap<HashSet<HabitatTiles>, Integer>();//maps each group to its number of points, will be used to clear up adjacent groups
                for(HashSet<HabitatTiles> s:elkGroups){
                    switch(s.size()){
                        case 1://for case 1 and 2, only one possible shape so there is no need to check for the exact shape
                            groupPoints1.put(s, 2);
                        break;
                        case 2:
                            groupPoints1.put(s, 5);
                        break;
                        case 3:
                            HabitatTiles start3 = new HabitatTiles();//node from which to start checking for shape
                            for(HabitatTiles tile3:s){
                                if(tile3.getNumberOf(CardAnimals.ELK).equals(2)){
                                    start3 = tile3;
                                }
                            }
                            Integer direction = start3.getSideOf(start3.findFirstWithSpecificToken(CardAnimals.ELK));
                            if((start3.get(HabitatTiles.nextInt(direction)).tokenAnimal()!=null&&start3.get(HabitatTiles.nextInt(direction)).tokenAnimal().equals(CardAnimals.ELK))||(start3.get(HabitatTiles.previousInt(direction)).tokenAnimal()!=null&&start3.get(HabitatTiles.previousInt(direction)).tokenAnimal().equals(CardAnimals.ELK))){//develops redundancy since, by using a hashSet, it is possible for the numbers to not iterate in the expected way, it checks if both tiles adjacent to the one we know has an ELK have an ELK as their token
                                groupPoints1.put(s, 9);
                            }
                        break;
                        case 4:
                            HabitatTiles start4 = new HabitatTiles();//node from which to start checking for shape
                            for(HabitatTiles tile4:s){
                                if(tile4.getNumberOf(CardAnimals.ELK).equals(2)){
                                    start4 = tile4;
                                }
                            }
                            for(int i = 0; i<6; i++){//since we need to query the nodes connected to the start node, it is easier to orient the start node in all 6 directions to check for the specific shape in one orientation
                                HabitatTiles upleft = start4.get(HabitatTiles.next(HabitatTiles.UP_LEFT, i));
                                HabitatTiles upright = start4.get(HabitatTiles.next(HabitatTiles.UP_RIGHT, i));
                                HabitatTiles top = upright.get(HabitatTiles.next(HabitatTiles.UP_LEFT, i));
                                if(upleft!=null&&upright!=null&&top!=null&&upleft.tokenAnimal()!=null&&upleft.tokenAnimal().equals(CardAnimals.ELK)&&upright.tokenAnimal()!=null&&upright.tokenAnimal().equals(CardAnimals.ELK)&&top.tokenAnimal().equals(CardAnimals.ELK)){
                                    groupPoints1.put(s, 13);
                                }
                            }
                        break;
                    }
                }
                resolveConnectedElkGroups(groupPoints1);
                Integer points1 = 0;
                for(Integer points:groupPoints1.values()){
                    points1+=points;
                }
                HabitatTiles.highlightGroups(groupPoints1, "ELK");
                return points1;
            
            case CARD_C:
                HashMap<HashSet<HabitatTiles>, Integer> groupPoints2 = new HashMap<HashSet<HabitatTiles>, Integer>();//maps each group to its number of points, will be used to clear up adjacent groups
                for(HashSet<HabitatTiles> s:elkGroups){
                    switch(s.size()){
                        case 1:
                            groupPoints2.put(s, 2);
                        break;
                        case 2:
                            groupPoints2.put(s, 4);
                        break;
                        case 3:
                            groupPoints2.put(s, 7);
                        break;
                        case 4:
                            groupPoints2.put(s, 10);
                        break;
                        case 5:
                            groupPoints2.put(s, 14);
                        break;
                        case 6:
                            groupPoints2.put(s, 18);
                        break;
                        case 7:
                            groupPoints2.put(s, 23);
                        break;
                        default://8 or more
                            groupPoints2.put(s, 28);
                    }
                }
                resolveConnectedElkGroups(groupPoints2);
                Integer points2 = 0;
                for(Integer points:groupPoints2.values()){
                points2+=points;
                }
                HabitatTiles.highlightGroups(groupPoints2, "ELK");
                return points2;
            
            case CARD_D:
                HashMap<HashSet<HabitatTiles>, Integer> groupPoints3 = new HashMap<HashSet<HabitatTiles>, Integer>();//maps each group to its number of points, will be used to clear up adjacent groups
                for(HabitatTiles e:elk){
                    int connectedElk = e.getNumberOf(CardAnimals.ELK);
                    switch (connectedElk) {
                        case 0:
                            HashSet<HabitatTiles> set = new HashSet<HabitatTiles>();
                            set.add(e);
                            groupPoints3.put(set, 2);
                            break;
                        default:
                            for(Integer dir:e.getConnections().keySet()){
                                HabitatTiles connect = e.get(dir);
                                if(connect.tokenAnimal()!=null&&connect.tokenAnimal().equals(CardAnimals.ELK)){
                                    int connectedElk2 = connect.getNumberOf(CardAnimals.ELK);
                                    switch (connectedElk2) {
                                        case 1:
                                        HashSet<HabitatTiles> baboon = new HashSet<HabitatTiles>();
                                        baboon.add(e);
                                        baboon.add(connect);
                                        groupPoints3.put(baboon, 5);
                                            break;
                                        default:
                                            for(Integer dir2:connect.getConnections().keySet()){
                                                Boolean cc = false;
                                                if(dir2==dir+1){
                                                    cc = true;
                                                }else if(dir2==dir-1){
                                                    cc = false;
                                                }
                                                if(Math.abs(dir2-dir)==1){
                                                    HashSet<HabitatTiles> group = elkIterateInCircle(e, dir, cc);
                                                    switch(group.size()){
                                                        case 3:
                                                            groupPoints3.put(group, 8);
                                                            break;
                                                        case 4:
                                                            groupPoints3.put(group, 12);
                                                                break;
                                                        case 5:
                                                            groupPoints3.put(group, 16);
                                                            break;
                                                        case 6:
                                                            groupPoints3.put(group, 21);
                                                            break;
                                                    }
                                                }
                                            }
                                    }
                                }
                            }
                    }
                }
                resolveConnectedElkGroups(groupPoints3);
                Integer points3 = 0;
                for(Integer points:groupPoints3.values()){
                points3+=points;
                }
                HabitatTiles.highlightGroups(groupPoints3, "ELK");
                return points3;
            
        }
        return 0;
    }

    private HashSet<HabitatTiles> findElkGroup(HabitatTiles elk){
        HashSet<HabitatTiles> elkGroup = new HashSet<HabitatTiles>();
        HashSet<HabitatTiles> visitedElk = new HashSet<HabitatTiles>();
        addElkToGroup(elk, visitedElk, elkGroup);
        return elkGroup;
    }

    private void addElkToGroup(HabitatTiles elk, HashSet<HabitatTiles> visitedElk, HashSet<HabitatTiles> elkGroup){
        if(elk.tokenAnimal()!=CardAnimals.ELK||visitedElk.contains(elk)){
            return;
        }else{
            visitedElk.add(elk);
            elkGroup.add(elk);
            for(HabitatTiles h:elk.getConnections().values()){
                addElkToGroup(h, visitedElk, elkGroup);
            }
        }
    }

    private HashSet<HabitatTiles> elkIterateInDirection(HabitatTiles start, Integer direction){
        HashSet<HabitatTiles> visitedElk = new HashSet<HabitatTiles>();
        elkIterateInDirection(start, direction, visitedElk);
        return visitedElk;
    }

    private void elkIterateInDirection(HabitatTiles start, Integer direction, HashSet<HabitatTiles> visitedElk){
        if(start==null||visitedElk.contains(start)||start.tokenAnimal()!=CardAnimals.ELK){
            return;
        }else{
            visitedElk.add(start);
            elkIterateInDirection(start.get(direction), direction, visitedElk);
        }
    }

    private HashSet<HabitatTiles> elkIterateInCircle(HabitatTiles start, Integer direction, Boolean isCounterClockwise){
        HashSet<HabitatTiles> visitedElk = new HashSet<HabitatTiles>();
        visitedElk.add(start);
        elkIterateInCircle(start.get(direction), direction, visitedElk, isCounterClockwise);
        return visitedElk;
    }

    private void elkIterateInCircle(HabitatTiles start, Integer direction, HashSet<HabitatTiles> visitedElk, Boolean isCounterClockwise){
        if(start==null||visitedElk.contains(start)||start.tokenAnimal()!=CardAnimals.ELK){
            return;
        }else{
            visitedElk.add(start);
            if(isCounterClockwise){
                elkIterateInCircle(start.get(HabitatTiles.nextInt(direction)), HabitatTiles.nextInt(direction), visitedElk, true);
            }else{
                elkIterateInCircle(start.get(HabitatTiles.previousInt(direction)), HabitatTiles.previousInt(direction), visitedElk, false);
            }
            
        }
    }

    private void resolveConnectedElkGroups(HashMap<HashSet<HabitatTiles>, Integer> groupPoints){
        //ensures that connected elk groups are removed and keeps the group that gives the player the most points
        HashSet<HashSet<HabitatTiles>> toRemove = new HashSet<HashSet<HabitatTiles>>();//iterator bs
        for(HashSet<HabitatTiles> i:groupPoints.keySet()){
            for(HashSet<HabitatTiles> j:groupPoints.keySet()){
                if(i.equals(j)){
                    
                }else if(hasConnectedValue(j, i)){
                    if(groupPoints.get(i)<groupPoints.get(j)){
                        toRemove.add(i);
                    }else{
                        toRemove.add(j);
                    }
                }   
            }
        }
        for(HashSet<HabitatTiles> group:toRemove){
            groupPoints.remove(group);
        }
    }

    private Boolean hasConnectedValue(HashSet<HabitatTiles> group1, HashSet<HabitatTiles> group2){
        for(HabitatTiles i:group1){
            for(HabitatTiles j:group2){
                if(i.equals(j)){
                    return true;
                }
            }
        }
        return false;
    }
}

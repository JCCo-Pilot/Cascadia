package Entities.WildlifeScoringCards;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.*;

import Entities.HabitatGraph;
import Entities.HabitatTiles;
import Entities.Player;
import Entities.Enums.CardAnimals;
import Entities.Enums.CardTypes;

public class BearCard implements ScoringCard{

    private BufferedImage image;
    
    final CardAnimals animal = CardAnimals.BEAR;
    CardTypes cardLetter;

    public BearCard(CardTypes letter){
        this.cardLetter = letter;
    }

    @Override
    public CardAnimals getAnimal() {
        return animal;
    }

    @Override
    public Integer score(Player p) {
        return this.bearScore(p.getGraph());
    }

    public Integer bearScore(HabitatGraph h){
        HashSet<HabitatTiles> bears = h.filter(CardAnimals.BEAR);
        HashSet<HabitatTiles> visitedBears = new HashSet<HabitatTiles>();
        HashSet<HashSet<HabitatTiles>> bearGroups = new HashSet<HashSet<HabitatTiles>>();
        for(HabitatTiles t:bears){
            if(!visitedBears.contains(t)){
                bearGroups.add(findBearGroup(t, visitedBears));
            }
        }
        switch(cardLetter){
            case CARD_A://Card A
                Integer numPairs = 0;
                for(HashSet<HabitatTiles> set:bearGroups){
                    if(set.size()==2){
                        numPairs++;
                    }
                }
                switch(numPairs){
                    case 0:
                    return 0;
                    case 1:
                    return 4;
                    case 2:
                    return 11;
                    case 3: 
                    return 19;
                    case 4:
                    return 27;
                    default:
                    return 0;
                }
            case CARD_B: //Card B
                Integer numGroupsOfThree = 0;
                for(HashSet<HabitatTiles> group: bearGroups){
                    if(group.size()==3){
                        numGroupsOfThree++;
                    }
                }
                return 10*numGroupsOfThree;
            case CARD_C: //Card C
                Integer points2 = 0;
                Boolean hasOne = false;
                Boolean hasTwo = false;
                Boolean hasThree = false;
                for(HashSet<HabitatTiles> group: bearGroups){
                    switch(group.size()){
                        case 1:
                        points2 += 2;
                        hasOne = true;
                        break;
                        case 2:
                        points2 += 5;
                        hasTwo = true;
                        break;
                        case 3:
                        points2 += 8;
                        hasThree = true;
                        break;
                    }
                }
                if(hasOne&&hasTwo&&hasThree){points2 += 3;}
                return points2;
            case CARD_D: //Card D
                Integer points3 = 0;
                for(HashSet<HabitatTiles> group: bearGroups){
                    switch(group.size()){
                        case 2:
                        points3 += 5;
                        break;
                        case 3: 
                        points3 += 8;
                        break;
                        case 4:
                        points3 += 13;
                        break;
                    }
                }
                return points3;    
            default:
                return 0;
        }
    }

    private HashSet<HabitatTiles> findBearGroup(HabitatTiles bear, HashSet<HabitatTiles> visitedBears){
        HashSet<HabitatTiles> bearGroup = new HashSet<HabitatTiles>();
        addBearToGroup(bear, visitedBears, bearGroup);
        if(bearGroup.size()>0){
            return bearGroup;
        }else{
            return null;
        }
        
    }

    private void addBearToGroup(HabitatTiles bear, HashSet<HabitatTiles> visitedBears, HashSet<HabitatTiles> bearGroup){
        if(bear.tokenAnimal()!=CardAnimals.BEAR||visitedBears.contains(bear)){
            return;
        }else{
            visitedBears.add(bear);
            bearGroup.add(bear);
            for(HabitatTiles h:bear.getConnections().values()){
                addBearToGroup(h, visitedBears, bearGroup);
            }
        }
    }

    
}

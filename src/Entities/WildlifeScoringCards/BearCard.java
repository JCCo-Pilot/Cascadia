package Entities.WildlifeScoringCards;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;

import Entities.HabitatGraph;
import Entities.HabitatTiles;
import Entities.Player;
import Entities.PrintTester;
import Entities.Enums.CardAnimals;
import Entities.Enums.CardTypes;

public class BearCard implements ScoringCard{

    private BufferedImage image;
    
    final CardAnimals animal = CardAnimals.BEAR;
    CardTypes cardLetter;

    public BearCard(CardTypes letter){
        this.cardLetter = letter;
        String choice = switch (letter) {
            case CARD_A -> "A";
            case CARD_B -> "B";
            case CARD_C -> "C";
            case CARD_D -> "D";
        };
        try{
            image = ImageIO.read(Objects.requireNonNull(BearCard.class.getResource("/Entities/ScoringCardsPics/BearScore" + choice + ".png")));
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
        return this.bearScore(p.getGraph());
    }

    public Integer bearScore(HabitatGraph h){
        HashSet<HabitatTiles> bears = h.filter(CardAnimals.BEAR);
        PrintTester.print(bears.toString());
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
                        HabitatTiles.highlightGroup(set, "BEAR");
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
                        HabitatTiles.highlightGroup(group, "BEAR");
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
                        HabitatTiles.highlightGroup(group, "BEAR");
                        break;
                        case 2:
                        points2 += 5;
                        hasTwo = true;
                        HabitatTiles.highlightGroup(group, "BEAR");
                        break;
                        case 3:
                        points2 += 8;
                        hasThree = true;
                        HabitatTiles.highlightGroup(group, "BEAR");
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
                        HabitatTiles.highlightGroup(group, "BEAR");
                        break;
                        case 3: 
                        points3 += 8;
                        HabitatTiles.highlightGroup(group, "BEAR");
                        break;
                        case 4:
                        points3 += 13;
                        HabitatTiles.highlightGroup(group, "BEAR");
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
        if(!(bear.getToken()==null||bear.tokenAnimal()!=CardAnimals.BEAR||visitedBears.contains(bear))){
            visitedBears.add(bear);
            bearGroup.add(bear);
            for(HabitatTiles h:bear.getConnections().values()){
                addBearToGroup(h, visitedBears, bearGroup);
            }
        }
    }

    
}

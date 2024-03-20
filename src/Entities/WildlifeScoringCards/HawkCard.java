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

public class HawkCard implements ScoringCard{

    private BufferedImage image;
    
    final CardAnimals animal = CardAnimals.HAWK;
    CardTypes cardLetter;

    public HawkCard(CardTypes letter){
        this.cardLetter = letter;
        String choice = "";
        switch(letter){
            case CARD_A:
                choice = "A";
            break;
            case CARD_B:
                choice ="B";
            break;
            case CARD_C:
                choice = "C";
            break;
            case CARD_D:
                choice = "D";
            break;
        }
        try{
            image = ImageIO.read(new File("src/Entities/ScoringCardsPics/HawkScore"+choice+".png"));
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
        return this.hawkScore(p.getGraph());
    }

    public Integer hawkScore(HabitatGraph h){
        HashSet<HabitatTiles> hawks = h.filter(CardAnimals.HAWK);
        HashMap<HabitatTiles, HashSet<HashSet<HabitatTiles>>> linesOfSightByHawk = new HashMap<HabitatTiles, HashSet<HashSet<HabitatTiles>>>();
        for(HabitatTiles hawk:hawks){
            linesOfSightByHawk.put(hawk, findHawkLinesOfSight(hawk));
        }
        switch(cardLetter){
            case CARD_A:
                Integer nonAdjacentHawks = 0;
                for(HabitatTiles hawk:hawks){
                    if(hawk.getNumberOf(CardAnimals.HAWK)==0){
                        nonAdjacentHawks++;
                    }
                }
                Integer points0 = 0;
                switch(nonAdjacentHawks){
                    case 0:
                    break;
                    case 1:
                        points0 += 2;
                    break;
                    case 2:
                        points0 += 5;
                    break;
                    case 3:
                        points0 += 8;
                    break;
                    case 4:
                        points0 += 11;
                    break;
                    case 5:
                        points0 += 14;
                    break;
                    case 6:
                        points0 += 18;
                    break;
                    case 7:
                        points0 += 22;
                    break;
                    default://8 or more
                        points0 += 26;
                    break;
                }
                return points0;
            case CARD_B:
                Integer nonAdjacentLOSHawks = 0;
                for(HabitatTiles hawk:hawks){
                    if(hawk.getNumberOf(CardAnimals.HAWK)==0&&linesOfSightByHawk.get(hawk).size()>0){
                        nonAdjacentLOSHawks++;
                    }
                }
                Integer points1 = 0;
                switch(nonAdjacentLOSHawks){
                    case 0:
                    break;
                    case 1:
                    break;
                    case 2:
                        points1 += 5;
                    break;
                    case 3:
                        points1 += 9;
                    break;
                    case 4:
                        points1 += 12;
                    break;
                    case 5:
                        points1 += 16;
                    break;
                    case 6:
                        points1 += 20;
                    break;
                    case 7:
                        points1 += 24;
                    break;
                    case 8:
                        points1 += 28;
                    break;
                }
                return points1;

            case CARD_C:
                HashSet<HashSet<HabitatTiles>> totalLinesOfSight2 = new HashSet<HashSet<HabitatTiles>>();
                for(HashSet<HashSet<HabitatTiles>> lineGroup:linesOfSightByHawk.values()){
                    for(HashSet<HabitatTiles> line: lineGroup){
                        totalLinesOfSight2.add(line);
                    }
                }
                removeDuplicateLinesOfSight(totalLinesOfSight2);
                return 3*totalLinesOfSight2.size();
            case CARD_D:
                HashSet<HashSet<HabitatTiles>> totalLinesOfSight3 = new HashSet<HashSet<HabitatTiles>>();
                for(HashSet<HashSet<HabitatTiles>> lineGroup:linesOfSightByHawk.values()){
                    for(HashSet<HabitatTiles> line: lineGroup){
                        totalLinesOfSight3.add(line);
                    }
                }
                removeDuplicateLinesOfSight(totalLinesOfSight3);
                Integer points3 = 0;
                for(HashSet<HabitatTiles> line:totalLinesOfSight3){
                    HashSet<CardAnimals> uniqueAnimals = new HashSet<CardAnimals>();//using a Set means no duplicates
                    for(HabitatTiles tile:line){
                        uniqueAnimals.add(tile.tokenAnimal());
                    }
                    switch(uniqueAnimals.size()-1){
                        case 0:
                        break;
                        case 1:
                            points3+=4;
                        break;
                        case 2:
                            points3+=7;
                        break;
                        default://3 or more
                            points3+=9;
                        break;
                    }
                }
                return points3;
        }
        return 0;
    }

    private HashSet<HashSet<HabitatTiles>> findHawkLinesOfSight(HabitatTiles hawk){
        HashSet<HashSet<HabitatTiles>> linesOfSight = new HashSet<HashSet<HabitatTiles>>();
        for(int dir = 0; dir<6; dir++){
            HashSet<HabitatTiles> lineOfSight = new HashSet<HabitatTiles>();
            lineOfSight.add(hawk);
            addToLineOfSight(hawk.get(dir), dir, lineOfSight);
        }
        return linesOfSight;
    }

    private void addToLineOfSight(HabitatTiles inLine, Integer direction, HashSet<HabitatTiles> lineOfSight){
        if(inLine==null){
            return;
        }else{
            lineOfSight.add(inLine);
            if(inLine.tokenAnimal()!=CardAnimals.HAWK){//ensure that coming across a hawk would end the line 
                addToLineOfSight(inLine.get(direction), direction, lineOfSight);
            }
        }
    }

    private void removeDuplicateLinesOfSight(HashSet<HashSet<HabitatTiles>> totalLOS){
        HashSet<HashSet<HabitatTiles>> toRemove = new HashSet<HashSet<HabitatTiles>>();
        for(HashSet<HabitatTiles> i:totalLOS){
            for(HashSet<HabitatTiles> j:totalLOS){
                if(i==j){

                }else if(areDuplicateLinesOfSight(i, j)){
                    toRemove.add(i);
                }
            }
        }
        for(HashSet<HabitatTiles> line:toRemove){
            totalLOS.remove(line);
        }
    }

    private Boolean areDuplicateLinesOfSight(HashSet<HabitatTiles> line1, HashSet<HabitatTiles> line2){
        HashSet<HabitatTiles> totalHawkTiles = new HashSet<HabitatTiles>();
        for(HabitatTiles tile:line1){
            if(tile.tokenAnimal()==CardAnimals.HAWK){
                totalHawkTiles.add(tile);
            }
        }
        for(HabitatTiles tile:line2){
            if(tile.tokenAnimal()==CardAnimals.HAWK){
                totalHawkTiles.add(tile);
            }
        }
        if(totalHawkTiles.size()<3){
            return true;
        }
        return false;
    }


}
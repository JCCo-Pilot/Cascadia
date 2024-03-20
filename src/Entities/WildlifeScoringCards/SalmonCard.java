package Entities.WildlifeScoringCards;

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

public class SalmonCard implements ScoringCard{

    private BufferedImage image;
    
    final CardAnimals animal = CardAnimals.SALMON;
    CardTypes cardLetter;

    public SalmonCard(CardTypes letter){
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
            image = ImageIO.read(new File("src/Entities/ScoringCardsPics/SalmonScore"+choice+".png"));
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
        return this.salmonScore(p.getGraph());
    }

    public Integer salmonScore(HabitatGraph h){
        HashSet<HabitatTiles> salmon = h.filter(CardAnimals.SALMON);
        HashSet<HabitatTiles> visitedSalmon = new HashSet<HabitatTiles>();
        HashSet<HashSet<HabitatTiles>> salmonRuns = new HashSet<HashSet<HabitatTiles>>();
        for(HabitatTiles s:salmon){
            salmonRuns.add(findSalmonRun(s, visitedSalmon));
        }
        switch(cardLetter){
            case CARD_A:
                Integer points0 = 0;
                for(HashSet<HabitatTiles> run: salmonRuns){
                    switch(run.size()){
                        case 0://redundancy, shouldn't ever happen but yk
                            points0+=0;
                        break;
                        case 1:
                            points0+=2;
                        break;
                        case 2:
                            points0+=5;
                        break;
                        case 3:
                            points0+=8;
                        break;
                        case 4:
                            points0+=12;
                        break;
                        case 5:
                            points0+=16;
                        break;
                        case 6:
                            points0+=20;
                        break;
                        default://7 or more
                            points0+=25;
                        break;
                    }
                }
                return points0;
            case CARD_B:
                Integer points1 = 0;
                for(HashSet<HabitatTiles> run: salmonRuns){
                    switch(run.size()){
                        case 0://redundancy, shouldn't ever happen but yk
                            points1+=0;
                        break;
                        case 1:
                            points1+=2;
                        break;
                        case 2:
                            points1+=4;
                        break;
                        case 3:
                            points1+=9;
                        break;
                        case 4:
                            points1+=11;
                        break;
                        default://5 or more
                            points1+=17;
                        break;
                    }
                }
                return points1;
            case CARD_C:
                Integer points2 = 0;
                for(HashSet<HabitatTiles> run: salmonRuns){
                    switch(run.size()){
                        case 0://redundancy, shouldn't ever happen but yk
                              points2+=0;
                        break;
                        case 1://catches these two so they dont go to default
                        break;
                        case 2:                            
                        break;
                        case 3:
                            points2+=10;
                        break;
                        case 4:
                            points2+=12;
                        break;
                        default://5 or more
                            points2+=15;
                        break;
                    }
                }
                return points2;
            case CARD_D:
                Integer points3 = 0;
                for(HashSet<HabitatTiles> run: salmonRuns){
                    points3 += run.size();
                    HashSet<HabitatTiles> adjacentTokens = new HashSet<HabitatTiles>();//the use of a HashSet means that there is no need to manually filter out duplicates
                    //TODO: if the empty animal token ever gets changed to avoid null errors, change what it is here
                    for(HabitatTiles salmonTile: run){
                        for(HabitatTiles connection: salmonTile.getConnections().values()){
                            if(connection.getToken()!=null){
                                adjacentTokens.add(connection);
                            }
                        }
                    }
                    points3 += adjacentTokens.size();
                }
                return points3;
        }
        return 0;
    }

    private HashSet<HabitatTiles> findSalmonRun(HabitatTiles salmon, HashSet<HabitatTiles> visitedSalmon){
        HashSet<HabitatTiles> salmonRun = new HashSet<HabitatTiles>();
        addSalmonToRun(salmon, visitedSalmon, salmonRun);
        if(salmonRun.size()>0){
            return salmonRun;
        }else{
            return null;
        }
        
    }

    private void addSalmonToRun(HabitatTiles salmon, HashSet<HabitatTiles> visitedSalmon, HashSet<HabitatTiles> salmonRun){
        if(salmon.tokenAnimal()!=CardAnimals.SALMON||visitedSalmon.contains(salmon)||salmon.getNumberOf(CardAnimals.SALMON)>2){
            return;
        }else{
            visitedSalmon.add(salmon);
            salmonRun.add(salmon);
            for(HabitatTiles h:salmon.getConnections().values()){
                addSalmonToRun(h, visitedSalmon, salmonRun);
            }
        }
    }

    
}

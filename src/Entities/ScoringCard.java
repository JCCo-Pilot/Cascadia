package Entities;

import java.util.HashSet;

public class ScoringCard {
    public final static Integer BEAR = 0;
    public final static Integer ELK = 1;
    public final static Integer SALMON = 2;
    public final static Integer HAWK = 3;
    public final static Integer FOX = 4;
    public final static Integer CARD_A = 0;
    public final static Integer CARD_B = 1;
    public final static Integer CARD_C = 2;
    public final static Integer CARD_D = 3;

    Integer animal;
    Integer cardLetter;

    public ScoringCard(Integer animal, Integer cardLetter){
        this.animal = animal;
        this.cardLetter = cardLetter;
    }

    public Integer score(HabitatGraph h){
        return -1;
    }

    //BEAR*******************************************************************************************************************************************************************************
    public Integer bearScore(HabitatGraph h){
        HashSet<HabitatTiles> bears = h.filter(BEAR);
        HashSet<HabitatTiles> visitedBears = new HashSet<HabitatTiles>();
        HashSet<HashSet<HabitatTiles>> bearGroups = new HashSet<HashSet<HabitatTiles>>();
        for(HabitatTiles t:bears){
            if(!visitedBears.contains(t)){
                bearGroups.add(findBearGroup(t, visitedBears));
            }
        }
        switch(cardLetter){
            case CARD_A:
                switch(bearGroups.size()){
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
            case CARD_B: 
                Integer numGroupsOfThree = 0;
                for(HashSet<HabitatTiles> group: bearGroups){
                    if(group.size()==3){
                        numGroupsOfThree++;
                    }
                }
                return 10*numGroupsOfThree;
            case CARD_C:
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
            case CARD_D:
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
        return bearGroup;
    }

    private void addBearToGroup(HabitatTiles bear, HashSet<HabitatTiles> visitedBears, HashSet<HabitatTiles> bearGroup){
        if(bear.tokenInt()!=BEAR||visitedBears.contains(bear)){
            return;
        }else{
            visitedBears.add(bear);
            bearGroup.add(bear);
            for(HabitatTiles h:bear.getConnections().values()){
                addBearToGroup(h, visitedBears, bearGroup);
            }
        }
    }

    //ELK*******************************************************************************************************************************************************************************    
}

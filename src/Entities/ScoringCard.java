package Entities;

import java.util.HashMap;
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
            case 0://Card A
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
            case 1: //Card B
                Integer numGroupsOfThree = 0;
                for(HashSet<HabitatTiles> group: bearGroups){
                    if(group.size()==3){
                        numGroupsOfThree++;
                    }
                }
                return 10*numGroupsOfThree;
            case 2: //Card C
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
            case 3: //Card D
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
    public Integer elkScore(HabitatGraph h){
        HashSet<HabitatTiles> elk = h.filter(ELK);
        HashSet<HashSet<HabitatTiles>> elkGroups = new HashSet<HashSet<HabitatTiles>>();
        for(HabitatTiles t:elk){
            elkGroups.add(findElkGroup(t));
            
        }
        switch(cardLetter){
            case 0:
                HashMap<HashSet<HabitatTiles>, Integer> groupPoints0 = new HashMap<HashSet<HabitatTiles>, Integer>();//maps each group to its number of points, will be used to clear up adjacent groups
                for(HashSet<HabitatTiles> s:elkGroups){//represents one elk group
                    HabitatTiles end = new HabitatTiles();
                    for(HabitatTiles tile:s){//find an ending card of the line
                        if(tile.getNumberOf(ELK).equals(1)||tile.getNumberOf(ELK).equals(0)){//the zero is for the special case in which there is just one elk, otherwise none of the elk tiles should be connected to zero elk
                            end = tile;
                        }
                    }

                    if(end.findFirstWithSpecificToken(ELK)==null){
                        groupPoints0.put(s, 2);//avoids null error by bypassing elkIterateInDirection method
                    }else{
                        Integer direction = end.getSideOf(end.findFirstWithSpecificToken(ELK));//direction that the line will continue in
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
                return points0;
            case 1:
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
                                if(tile3.getNumberOf(ELK).equals(2)){
                                    start3 = tile3;
                                }
                            }
                            Integer direction = start3.getSideOf(start3.findFirstWithSpecificToken(ELK));
                            if(start3.get(HabitatTiles.nextInt(direction)).tokenInt().equals(ELK)||start3.get(HabitatTiles.previousInt(direction)).tokenInt().equals(ELK)){//develops redundancy since, by using a hashSet, it is possible for the numbers to not iterate in the expected way, it checks if both tiles adjacent to the one we know has an ELK have an ELK as their token
                                groupPoints1.put(s, 9);
                            }
                        break;
                        case 4:
                            HabitatTiles start4 = new HabitatTiles();//node from which to start checking for shape
                            for(HabitatTiles tile4:s){
                                if(tile4.getNumberOf(ELK).equals(2)){
                                    start4 = tile4;
                                }
                            }
                            Boolean fitsShape = false;
                            for(int i = 0; i<6; i++){//since we need to query the nodes connected to the start node, it is easier to orient the start node in all 6 directions to check for the specific shape in one orientation
                                HabitatTiles upleft = start4.get(HabitatTiles.next(HabitatTiles.UP_LEFT, i));
                                HabitatTiles upright = start4.get(HabitatTiles.next(HabitatTiles.UP_RIGHT, i));
                                HabitatTiles top = upright.get(HabitatTiles.next(HabitatTiles.UP_LEFT, i));
                                if(upleft!=null&&upright!=null&&top!=null&&upleft.tokenInt().equals(ELK)&&upright.tokenInt().equals(ELK)&&top.tokenInt().equals(ELK)){
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
                return points1;
            
            case 2:
                HashMap<HashSet<HabitatTiles>, Integer> groupPoints2 = new HashMap<HashSet<HabitatTiles>, Integer>();//maps each group to its number of points, will be used to clear up adjacent groups
                .//Integer points2 = 0;
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
                return points2;
            
            case 3:
                HashMap<HashSet<HabitatTiles>, Integer> groupPoints3 = new HashMap<HashSet<HabitatTiles>, Integer>();//maps each group to its number of points, will be used to clear up adjacent groups
                for(HashSet<HabitatTiles> s:elkGroups){
                    switch(s.size()){
                        case 1://for case 1 and 2, only one possible shape so there is no need to check for the exact shape
                            groupPoints3.put(s, 2);
                        break;
                        case 2:
                            groupPoints3.put(s, 5);
                        break;
                        default: //3 or more
                            Integer count = 0; //used to ensure that if its a perfect circle it doesnt't simply ignore every node.
                            HabitatTiles startIfNoOther = new HabitatTiles();
                            Boolean foundCircle = false;
                            for(HabitatTiles tile:s){//see if a circle starts from any of these tiles; ensure that it is not simply the continuation of a circle
                                if(++count==1){startIfNoOther = tile;}
                                Integer direction = -1;
                                Boolean isCircle = false;
                                Boolean isClockwise = false;
                                for(int i = 0; i<6; i++){
                                    if(tile.get(i).tokenInt().equals(ELK)){
                                        direction = i;
                                        if(tile.get(i).get(HabitatTiles.nextInt(i)).tokenInt().equals(ELK)){
                                            isClockwise = true;
                                            isCircle = true;
                                        }else if(tile.get(i).get(HabitatTiles.previousInt(i)).tokenInt().equals(ELK)){
                                            isClockwise = false;
                                            isCircle = true;
                                        }
                                        if(isCircle){
                                            if(isClockwise&&tile.get(HabitatTiles.next(direction, 2)).tokenInt().equals(ELK)){
                                                isCircle = false;
                                            }else if(!isClockwise&&tile.get(HabitatTiles.previous(direction, 2)).tokenInt().equals(ELK)){
                                                isCircle = false;
                                            }else{
                                                HashSet<HabitatTiles> circle = elkIterateInCircle(tile, i, isClockwise);
                                                foundCircle = true;
                                                switch(circle.size()){
                                                    case 1://redundancy
                                                        groupPoints3.put(circle, 2);
                                                    break;
                                                    case 2://redundancy
                                                        groupPoints3.put(circle, 5);
                                                    break;
                                                    case 3:
                                                        groupPoints3.put(circle, 8);
                                                    break;
                                                    case 4:
                                                        groupPoints3.put(circle, 12);
                                                    break;
                                                    case 5:
                                                        groupPoints3.put(circle, 16);
                                                    break;
                                                    case 6:
                                                        groupPoints3.put(circle, 21);
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
                return points3;
            break;
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
        if(elk.tokenInt()!=ELK||visitedElk.contains(elk)){
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
        if(start==null||visitedElk.contains(start)||start.tokenInt()!=ELK){
            return;
        }else{
            visitedElk.add(start);
            elkIterateInDirection(start.get(direction), direction, visitedElk);
        }
    }

    private HashSet<HabitatTiles> elkIterateInCircle(HabitatTiles start, Integer direction, Boolean isClockwise){
        HashSet<HabitatTiles> visitedElk = new HashSet<HabitatTiles>();
        visitedElk.add(start);
        elkIterateInCircle(start.get(direction), direction, visitedElk, isClockwise);
        return visitedElk;
    }

    private void elkIterateInCircle(HabitatTiles start, Integer direction, HashSet<HabitatTiles> visitedElk, Boolean isClockwise){
        if(start==null||visitedElk.contains(start)||start.tokenInt()!=ELK){
            return;
        }else{
            visitedElk.add(start);
            if(isClockwise){
                elkIterateInCircle(start.get(HabitatTiles.nextInt(direction)), HabitatTiles.nextInt(direction), visitedElk, true);
            }else{
                elkIterateInCircle(start.get(HabitatTiles.previousInt(direction)), HabitatTiles.previousInt(direction), visitedElk, false);
            }
            
        }
    }

    private void resolveConnectedElkGroups(HashMap<HashSet<HabitatTiles>, Integer> groupPoints){
        //ensures that connected elk groups are removed and keeps the group that gives the player the most points
        for(HashSet<HabitatTiles> i:groupPoints.keySet()){
            for(HashSet<HabitatTiles> j:groupPoints.keySet()){
                if(i.equals(j)){
                    
                }else if(hasConnectedValue(j, i)){
                    if(groupPoints.get(i)<groupPoints.get(j)){
                        groupPoints.remove(i);
                    }else{
                        groupPoints.remove(j);
                    }
                }   
            }
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

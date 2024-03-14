package Entities;
import java.awt.*;
import javax.swing.*;

import Entities.Enums.CardAnimals;
import Entities.Enums.Habitats;
import MathHelper.MathPoint;

import java.io.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.event.*;
import static java.lang.System.*;
public class HabitatGraph{
    private HabitatTiles root;

    public HabitatGraph(StarterTile s){
        root = s.down_right;
        root.setCoordinate(new MathPoint(499, 490));
        root.add(s.down_left, HabitatTiles.LEFT);
        root.add(s.up, HabitatTiles.UP_LEFT);
        System.out.println(iterate().toString());
        for(HabitatTiles h:this.iterate()){
            connectTilesToNonConnectedAdjacents();
            h.replaceNullConnectionsWithEmpty();
        }
        System.out.println(iterate().toString());
        this.fixStackedTileLocation();
    }

    public void drawGraph(Graphics g){
        for(HabitatTiles h: iterate()){
            h.drawHexagon(g);
            System.out.println(h+" drawn at coords "+h.getXPos()+", "+h.getYPos());
        }
    }

    public HashSet<HabitatTiles> filter(CardAnimals i){
        HashSet<HabitatTiles> filterReturn = new HashSet<HabitatTiles>();
        for(HabitatTiles h:iterate()){
            if(h.tokenAnimal()==i){
                filterReturn.add(h);
            }
        }
        return filterReturn;
    }

    public HashSet<HabitatTiles> filter(Habitats hab){
        HashSet<HabitatTiles> filterReturn = new HashSet<HabitatTiles>();
        for(HabitatTiles h:iterate()){
            if(h.getHabitats().values().contains(hab)){
                filterReturn.add(h);
            }
        }
        return filterReturn;
    }

    public HashSet<HabitatTiles> iterateOld(){
        HashSet<HabitatTiles> iterationReturn = new HashSet<HabitatTiles>();
        iterate(root, iterationReturn);
        return iterationReturn;
    }

    private void iterate(HabitatTiles h, HashSet<HabitatTiles> s){
        if(h==null||s.contains(h)){
            return;
        }else{
            s.add(h);
            System.out.println("iterate " + h);
            for(int i = 0; i>6; i++){
                iterate(h.get(i), s);
            }
        }
    }

    public HashSet<HabitatTiles> iterate(){
        Queue<HabitatTiles> toVisit = new LinkedList<HabitatTiles>();
        HashSet<HabitatTiles> visited = new HashSet<HabitatTiles>();
        toVisit.add(root);
        while(!toVisit.isEmpty()){
            HabitatTiles current = toVisit.remove();
            if(!visited.contains(current)){
                visited.add(current);
                for(HabitatTiles h: current.getConnections().values()){
                    toVisit.add(h);
                }
            }
        }
        return visited;
    }

    public void CoordFix(){
        Queue<HabitatTiles> toVisit = new LinkedList<HabitatTiles>();
        HashSet<HabitatTiles> visited = new HashSet<HabitatTiles>();
        toVisit.add(root);
        while(!toVisit.isEmpty()){
            HabitatTiles current = toVisit.remove();
            if(!visited.contains(current)){
                visited.add(current);
                for(HabitatTiles h: current.getConnections().values()){
                    h.setCoordinate(current.getAdjacentTileOffset(current.getSideOf(h)));
                    toVisit.add(h);
                }
            }
        }
    }


    public void add(HabitatTiles toAdd, MathPoint clickPoint){
        HabitatTiles toReplace = bfs(clickPoint);
        toReplace.replaceWith(toAdd);
        connectTilesToNonConnectedAdjacents();
        toAdd.replaceNullConnectionsWithEmpty();
        this.fixStackedTileLocation();
    }

    public Boolean addToken(WildlifeTokens t, MathPoint clickPoint){
        HabitatTiles toAdd = bfs(clickPoint);
        if(!toAdd.isEmpty()||toAdd.canPick(t)){
            toAdd.addToken(t);
            return true;
        }
        return false;
    }
    public Boolean addToken(CardAnimals a, MathPoint p){
        return addToken(new WildlifeTokens(a), p);
    }

    public HabitatTiles bfs(MathPoint p){
        Queue<HabitatTiles> toVisit = new LinkedList<HabitatTiles>();
        Queue<HabitatTiles> visited = new LinkedList<HabitatTiles>();
        toVisit.add(root);
        while(!toVisit.isEmpty()){
            HabitatTiles current = toVisit.remove();
            if(!visited.contains(current)){
                visited.add(current);
                if(current.isPointInsideHexagon(p)){
                    return current;
                }
                for(HabitatTiles h: current.getConnections().values()){
                    toVisit.add(h);
                }
            }
        }
        return null;
    }

    public void fixStackedTileLocation(){
        System.out.println("/////////// FIX STACKED BEGIN");
        //HashMap<HabitatTiles, HashSet<HabitatTiles>> checkedPairs = new HashMap<HabitatTiles, HabitatTiles>();
        for(HabitatTiles i:this.iterate()){
            System.out.println("Fix stacked i "+i +" Coordinates: "+i.getCoordinate());
            for(HabitatTiles j:this.iterate()){
                System.out.println("Fix stacked j "+j+" Coordinates: "+j.getCoordinate());
                if(i!=j){//||checkedPairs.get(j).equals(i)){
                    //checkedPairs.put(i, j);
                    if(i.isPointInsideHexagon(j.getCoordinate())||false){//(i.getXPos()==j.getXPos()&&i.getYPos()==j.getYPos())){
                        System.out.println(i+" : "+i.getCoordinate().toString()+", "+j+" : "+j.getCoordinate().toString());
                        if(i.isEmpty()){
                            i.replaceWith(j);
                            System.out.println(i+" removed because i empty");
                        }else if(j.isEmpty()){
                            j.replaceWith(i);
                            System.out.println(j+" removed because j empty");
                        }else{
                            j.replaceWith(i);
                            System.out.println(j+" removed because both empty");
                        }
                    }
                }
            }
        }
        System.out.println("/////////// FIX STACKED END");
    }

    public void connectTilesToNonConnectedAdjacents(){
        for(HabitatTiles h:iterate()){
            for(int i = 0; i<6; i++){
                if(h.get(i)==null){
                    try {
                        HabitatTiles adjacent = bfs(h.getAdjacentTileOffset(i));
                        h.add(adjacent, i);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }
    }

    public Integer getLargestContiguousGroup(Habitats target){
        HashSet<HabitatTiles> validStarts = this.filter(target);
        HashSet<HabitatTiles> visitedTiles = new HashSet<HabitatTiles>();
        HashSet<HashSet<HabitatTiles>> groups = new HashSet<HashSet<HabitatTiles>>();
        for(HabitatTiles tile:validStarts){
            HashSet<HabitatTiles> group = new HashSet<HabitatTiles>();
            addToContiguousGroup(target, tile, group, visitedTiles);
            groups.add(group);
        }
        Integer max = 0;
        for(HashSet<HabitatTiles> group:groups){
            group.remove(null);
            if(group.size()>max){
                max = group.size();
            }
        }
        return max;
    }

    private void addToContiguousGroup(Habitats target, HabitatTiles tile, HashSet<HabitatTiles> group, HashSet<HabitatTiles> visitedTiles){
        if(visitedTiles.contains(tile)||tile==null){
            return;
        }
        group.add(tile);
        visitedTiles.add(tile);
        for(Integer i:tile.getHabitats().keySet()){
            if(tile.getHabitats().get(i)==target){
                if(tile.habitatMatch(i)){
                    addToContiguousGroup(target, tile.get(i), group, visitedTiles);
                }
            }
        }
    }
}

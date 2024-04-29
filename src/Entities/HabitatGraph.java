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
    private Integer size = 70;
    private Boolean autoSize = true;

    public HabitatGraph(StarterTile s){
        root = s.down_right;
        root.setCoordinate(new MathPoint(500, 490));
        root.add(s.down_left, HabitatTiles.LEFT);
        root.add(s.up, HabitatTiles.UP_LEFT);
        System.out.println(iterate().toString());
        for(HabitatTiles h:this.iterate()){
            connectTilesToNonConnectedAdjacents();
            h.replaceNullConnectionsWithEmpty();
        }
        connectTilesToNonConnectedAdjacents();
        System.out.println(iterate().toString());
        this.fixStackedTileLocation();
    }

    public void drawGraph(Graphics g, Boolean drawEmptys){
        //System.out.println("DrawGraph method called");
        for(HabitatTiles h: iterate()){
            if(h.isEmpty()){
                if(drawEmptys){
                    h.drawHexagon(g);
                }
            }else{
                h.drawHexagon(g);
            }
            //System.out.println(h+" drawn at coords "+h.getXPos()+", "+h.getYPos());
        }
        /*for(HabitatTiles h:iterate()){
            for(HabitatTiles con:h.getConnections().values()){
                g.setColor(Color.RED);
                g.drawLine(h.getXPos(), h.getYPos(), con.getXPos(), con.getYPos()w2);
            }
            g.setColor(Color.BLACK);
        }*/
    }

    public void drawGraph(Graphics g, int x1, int y1, int xLength, int yLength){
        int lowest = Math.min(xLength, yLength);
        double radius = ((lowest+0.0)/(900.0))*root.getSize();
        MathPoint center = new MathPoint(x1+xLength/2, y1+yLength/2);
        double ratio = (lowest+0.0)/(900.0);

        for(HabitatTiles h: iterate()){
            if(h.isEmpty()){
                
            }else{
                int xCartesian = h.getCoordinate().xPoint-450;
                int yCartesian = h.getCoordinate().yPoint-450;

                int xNew = (int) (xCartesian*ratio) + center.xPoint;
                int yNew = (int) (yCartesian*ratio) + center.yPoint;

                h.drawHexagon(g, radius, xNew, yNew);
            }
            //System.out.println(h+" drawn at coords "+h.getXPos()+", "+h.getYPos());
        }
        
    }

    public HashSet<HabitatTiles> filter(CardAnimals i){
        HashSet<HabitatTiles> filterReturn = new HashSet<HabitatTiles>();
        for(HabitatTiles h:iterate()){
            if(h.getToken()!=null&&h.tokenAnimal()==i){
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
        //System.out.println("Filter for "+hab+" returns "+filterReturn.toString());
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

    public void UpdateCoordinates(){
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

    public Integer getSize(){
        return size;
    }

    public void setSize(Integer i){
        size = i;
        for(HabitatTiles h:iterate()){
            h.setSize(i+0.0);
        }
        UpdateCoordinates();
    }

    public void setCoordinate(int x, int y){
        root.setCoordinate(new MathPoint(x, y));
        UpdateCoordinates();
    }

    private void autoResize(){
        //size is 900 x 900
        //find highest/lowest coord in all locations
        int highestX = 0;
        int lowestX = 900;
        int highestY = 0;
        int lowestY = 900;
        for(HabitatTiles h:iterate()){
            if(h.getAdjacentTileOffset(HabitatTiles.LEFT).xPoint<lowestX){
                lowestX = h.getAdjacentTileOffset(HabitatTiles.LEFT).xPoint;
            }

            if(h.getAdjacentTileOffset(HabitatTiles.RIGHT).xPoint>highestX){
                highestX = h.getAdjacentTileOffset(HabitatTiles.RIGHT).xPoint;
            }

            if(h.getAdjacentTileOffset(HabitatTiles.UP_LEFT).yPoint<lowestY){
                lowestY = h.getAdjacentTileOffset(HabitatTiles.UP_LEFT).yPoint;
            }

            if(h.getAdjacentTileOffset(HabitatTiles.DOWN_LEFT).yPoint>highestY){
                highestY = h.getAdjacentTileOffset(HabitatTiles.DOWN_LEFT).yPoint;
            }
        }
        int newXOffset = (highestX+lowestX)/2-450;
        int newYOffset = (highestY+lowestY)/2-450;

        setCoordinate(root.getXPos()-newXOffset, root.getYPos()-newYOffset);
        int xDifference = highestX - lowestX;
        int yDifference = highestY - lowestY;

        Double xScaleFactor = 1.0;
        Double yScaleFactor = 1.0;

        if(xDifference>890){
            xScaleFactor = (890.0/xDifference);
        }

        if(yDifference>890){
            yScaleFactor = (890.0/yDifference);
        }

        if(xScaleFactor!=1||yScaleFactor!=1){
            this.setSize((int)(this.getSize()*Math.min(xScaleFactor, yScaleFactor)));
            UpdateCoordinates();
            highestX = 0;
            lowestX = 900;
            highestY = 0;
            lowestY = 900;
            for(HabitatTiles h:iterate()){
                if(h.getAdjacentTileOffset(HabitatTiles.LEFT).xPoint<lowestX){
                    lowestX = h.getAdjacentTileOffset(HabitatTiles.LEFT).xPoint;
                }

                if(h.getAdjacentTileOffset(HabitatTiles.RIGHT).xPoint>highestX){
                    highestX = h.getAdjacentTileOffset(HabitatTiles.RIGHT).xPoint;
                }

                if(h.getAdjacentTileOffset(HabitatTiles.UP_LEFT).yPoint<lowestY){
                    lowestY = h.getAdjacentTileOffset(HabitatTiles.UP_LEFT).yPoint;
                }

                if(h.getAdjacentTileOffset(HabitatTiles.DOWN_LEFT).yPoint>highestY){
                    highestY = h.getAdjacentTileOffset(HabitatTiles.DOWN_LEFT).yPoint;
                }
            }
            newXOffset = (highestX+lowestX)/2-450;
            newYOffset = (highestY+lowestY)/2-450;

            setCoordinate(root.getXPos()-newXOffset, root.getYPos()-newYOffset);
            UpdateCoordinates();
        }
    }

    public Boolean add(HabitatTiles toAdd, MathPoint clickPoint){
        HabitatTiles toReplace = bfs(clickPoint);
        if(toReplace==null){
            return false;
        }
        toAdd.setPos(0, 0, size+0.0);
        if(toReplace.isEmpty()){
            toReplace.replaceWith(toAdd);
            connectTilesToNonConnectedAdjacents();
            toAdd.replaceNullConnectionsWithEmpty();
            this.fixStackedTileLocation();
            connectTilesToNonConnectedAdjacents();
        }else{
            return false;
        }
        return true;
    }

    public Boolean addToken(WildlifeTokens t, MathPoint clickPoint){
        HabitatTiles toAdd = bfs(clickPoint);
        if(!toAdd.isEmpty()&&toAdd.canPick(t)){
            toAdd.addToken(t);
            return true;
        }
        return false;
    }
    public Boolean addToken(CardAnimals a, MathPoint p){
        return addToken(new WildlifeTokens(a), p);
    }

    public HabitatTiles bfs(MathPoint p){
        //System.out.println("bfs "+p);
        /*Queue<HabitatTiles> toVisit = new LinkedList<HabitatTiles>();
        Queue<HabitatTiles> visited = new LinkedList<HabitatTiles>();
        toVisit.add(root);
        while(!toVisit.isEmpty()){
            HabitatTiles current = toVisit.poll();
            if(!visited.contains(current)){
                visited.offer(current);
                if(current.isPointInsideHexagon(p)){
                    System.out.println("bfs found "+current+" at "+p);
                    return current;
                }
                for(HabitatTiles h: current.getConnections().values()){
                    toVisit.add(h);
                }
            }
        }
        System.out.println("bfs found null at "+p);
        return null;*/
        for(HabitatTiles h:iterate()){
            if(h.isPointInsideHexagon(p)||h.getCoordinate().equals(p)){
                return h;
            }
        }
        return null;
    }

    public void update(){
        if(autoSize){
            autoResize();
        }else{
            UpdateCoordinates();
        }
    }

    public void fixStackedTileLocation(){
        //System.out.println("/////////// FIX STACKED BEGIN");
        HashMap<HabitatTiles, HashSet<HabitatTiles>> checkedPairs = new HashMap<HabitatTiles, HashSet<HabitatTiles>>();
        for(HabitatTiles i:this.iterate()){
            if(!checkedPairs.containsKey(i)){
                HashSet<HabitatTiles> set = new HashSet<HabitatTiles>();
                set.add(i);
                checkedPairs.put(i, set);
            }
        }
        for(HabitatTiles i:this.iterate()){
            
            for(HabitatTiles j:this.iterate()){
                if(!checkedPairs.get(i).contains(j)){
                    checkedPairs.get(i).add(j);
                    checkedPairs.get(j).add(i);
                    if(i.isPointInsideHexagon(j.getCoordinate())||i.getCoordinate().equals(j.getCoordinate())){//(i.getXPos()==j.getXPos()&&i.getYPos()==j.getYPos())){
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
        //System.out.println("/////////// FIX STACKED END");
    }

    public void connectTilesToNonConnectedAdjacents(){
        for(HabitatTiles h:iterate()){
            for(int i = 0; i<6; i++){
                if(h.get(i)==null){
                    HabitatTiles adjacent = bfs(h.getAdjacentTileOffset(i));
                    if(adjacent!=null){
                        h.add(adjacent, i);
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
            findContiguousGroup(target, tile, group, visitedTiles);
            groups.add(group);
        }
        Integer max = 0;
        groups.remove(null);
        for(HashSet<HabitatTiles> group:groups){
            //System.out.println(target+" group size "+group.size());
            group.remove(null);
            if(group.size()>max){
                max = group.size();
            }
        }
        //System.out.println(target + " returns "+max);
        return max;
    }

    private void findContiguousGroup(Habitats target, HabitatTiles tile, HashSet<HabitatTiles> group, HashSet<HabitatTiles> visitedTiles){
        Queue<HabitatTiles> q = new LinkedList<HabitatTiles>();
        q.add(tile);
        while(!q.isEmpty()){
            HabitatTiles current = q.remove();
            if(!visitedTiles.contains(current)){
                group.add(current);
                visitedTiles.add(current);
                //System.out.println(current+" added to group of "+target);
                for(int i = 0; i<6; i++){
                    HabitatTiles next = current.get(i);
                    //System.out.println("HabitatMatch between "+current.getHabitats()+", "+next.getHabitats()+" at "+i+" returns "+ current.habitatMatch(i));
                    if(current.habitatMatch(i)&&current.getHabitats().get(i)==target&&!next.isEmpty()){
                        q.add(next);
                    }
                }
            }
        }
        //System.out.println(target+" group size = "+group.size());
    }
}

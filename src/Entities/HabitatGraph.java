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
        PrintTester.print(iterate().toString());
        for(HabitatTiles h:this.iterate()){
            connectTilesToNonConnectedAdjacents();
            h.replaceNullConnectionsWithEmpty();
        }
        connectTilesToNonConnectedAdjacents();
        PrintTester.print(iterate().toString());
        this.fixStackedTileLocation();
    }



    public void drawGraph(Graphics g, Boolean drawEmptys){
        //PrintTester.print("DrawGraph method called");
        for(HabitatTiles h: iterate()){
            if(h.isEmpty()){
                if(drawEmptys){
                    new TaskThreadManager(TaskThreadManager.ActionVar.DRAW, new Object[]{g, h});
                }
            }else{
                new TaskThreadManager(TaskThreadManager.ActionVar.DRAW, new Object[]{g, h});
            }
            //PrintTester.print(h+" drawn at coords "+h.getXPos()+", "+h.getYPos());
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
        double radius = ((lowest+0.0)/(925.0))*root.getSize();
        MathPoint center = new MathPoint(x1+xLength/2, y1+yLength/2);
        double ratio = (lowest+0.0)/(925.0);

        int highestX = 0;
        int lowestX = 925;
        int highestY = 0;
        int lowestY = 925;
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
        int xDifference = highestX - lowestX;
        int yDifference = highestY - lowestY;

        int lowestDifference = (int)(ratio*Math.min(xDifference, yDifference));
        
        double ratio2 = lowest/lowestDifference;

        for(HabitatTiles h: iterate()){
            if(h.isEmpty()){
                
            }else{
                int xCartesian = h.getCoordinate().xPoint-463;
                int yCartesian = h.getCoordinate().yPoint-463;

                int xNew = (int) (xCartesian*ratio) + center.xPoint;
                int yNew = (int) (yCartesian*ratio) + center.yPoint;

                //h.drawHexagon(g, radius, xNew, yNew);
                new TaskThreadManager(TaskThreadManager.ActionVar.DRAWPOSITION, new Object[]{g, h, radius, xNew, yNew});
            }
            //PrintTester.print(h+" drawn at coords "+h.getXPos()+", "+h.getYPos());
        }
        
    }

    public void highlightCompatibles(Graphics g, WildlifeTokens w){
        for(HabitatTiles h:iterate()){
            if(!h.isEmpty()&&!h.canPick(w)){
                h.drawHighlight(g);
            }
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
        //PrintTester.print("Filter for "+hab+" returns "+filterReturn.toString());
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
            PrintTester.print("iterate " + h);
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
        //PrintTester.print("bfs "+p);
        /*Queue<HabitatTiles> toVisit = new LinkedList<HabitatTiles>();
        Queue<HabitatTiles> visited = new LinkedList<HabitatTiles>();
        toVisit.add(root);
        while(!toVisit.isEmpty()){
            HabitatTiles current = toVisit.poll();
            if(!visited.contains(current)){
                visited.offer(current);
                if(current.isPointInsideHexagon(p)){
                    PrintTester.print("bfs found "+current+" at "+p);
                    return current;
                }
                for(HabitatTiles h: current.getConnections().values()){
                    toVisit.add(h);
                }
            }
        }
        PrintTester.print("bfs found null at "+p);
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
        //PrintTester.print("/////////// FIX STACKED BEGIN");
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
                        PrintTester.print(i+" : "+i.getCoordinate().toString()+", "+j+" : "+j.getCoordinate().toString());
                        if(i.isEmpty()){
                            i.replaceWith(j);
                            PrintTester.print(i+" removed because i empty");
                            JOptionPane.showMessageDialog(null, i+" removed because i empty");
                        }else if(j.isEmpty()){
                            j.replaceWith(i);
                            PrintTester.print(j+" removed because j empty");
                            JOptionPane.showMessageDialog(null, j+" removed because j empty");
                        }else{
                            j.replaceWith(i);
                            PrintTester.print(j+" removed because both empty");
                            JOptionPane.showMessageDialog(null, j+" removed over "+i);
                        }
                    }
                }
            }
        }
        //PrintTester.print("/////////// FIX STACKED END");
    }

    public void connectTilesToNonConnectedAdjacents(){
        for(HabitatTiles h:iterate()){
            for(int i = 0; i<6; i++){
                new TaskThreadManager(TaskThreadManager.ActionVar.CONNECT, new Object[]{h});
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
            //PrintTester.print(target+" group size "+group.size());
            group.remove(null);
            if(group.size()>max){
                max = group.size();
            }
        }
        //PrintTester.print(target + " returns "+max);
        return max;
    }

    private void findContiguousGroup(Habitats target, HabitatTiles tile, HashSet<HabitatTiles> group, HashSet<HabitatTiles> visitedTiles){
        Queue<HabitatTiles> q = new LinkedList<HabitatTiles>();
        q.add(tile);
        while(!q.isEmpty()){
            new TaskThreadManager(TaskThreadManager.ActionVar.GROUP, new Object[]{target, q, group, visitedTiles});
        }
        //PrintTester.print(target+" group size = "+group.size());
    }

    private class TaskThreadManager{
        private enum ActionVar{
            DRAW,
            DRAWPOSITION,
            CONNECT,
            GROUP,
        }

        private TaskThreadManager(ActionVar a, Object[] arr){
            switch(a){
                case DRAW:
                    DrawingTaskThreadManager d = new DrawingTaskThreadManager((Graphics)arr[0], (HabitatTiles)arr[1]);
                    d.setPriority(6);
                    d.run();
                    break;
                case DRAWPOSITION:
                    PositionalTaskThreadManager p = new PositionalTaskThreadManager((Graphics)arr[0], (HabitatTiles)arr[1], (double)arr[2], (int)arr[3], (int)arr[4]);
                    p.setPriority(1);
                    p.run();
                    break;
                case CONNECT:
                    ConnectionTaskThreadManager c = new ConnectionTaskThreadManager((HabitatTiles)arr[0]);
                    //c.setPriority(7);
                    c.run();
                    break;
                case GROUP:
                    GroupingTaskThreadManager g = new GroupingTaskThreadManager((Habitats)arr[0],(Queue<HabitatTiles>)arr[1], (HashSet<HabitatTiles>)arr[2], (HashSet<HabitatTiles>)arr[3]);
                    g.setPriority(1);
                    g.run();
                    break;
                default:
                    break;
                
            }
        }

        private class DrawingTaskThreadManager extends Thread{
            Graphics graphics;
            HabitatTiles tile;
            private DrawingTaskThreadManager(Graphics g, HabitatTiles toDraw){
                //PrintTester.print("DrawingTaskThreadManager");
                graphics = g;
                tile = toDraw;
            }

            public void run(){
                tile.drawHexagon(graphics);
            }
        }

        private class PositionalTaskThreadManager extends Thread{
            Graphics graphics;
            HabitatTiles tile;
            double radius;
            int x;
            int y;
            private PositionalTaskThreadManager(Graphics g, HabitatTiles toDraw, double rad, int xPos, int yPos){
                //PrintTester.print("PositionalTaskThreadManager");
                graphics = g;
                tile = toDraw;
                radius = rad;
                x = xPos;
                y = yPos;
            }

            public void run(){
                tile.drawHexagon(graphics, radius, x, y);
            }
        }

        private class ConnectionTaskThreadManager{
            HabitatTiles h;
            private ConnectionTaskThreadManager(HabitatTiles tile){
                //PrintTester.print("ConnectionTaskThreadManager");
                h = tile;
            }

            public void run(){
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

        private class GroupingTaskThreadManager extends Thread{
            Queue<HabitatTiles> q;
            HashSet<HabitatTiles> group;
            HashSet<HabitatTiles> visitedTiles;
            Habitats target;
            private GroupingTaskThreadManager(Habitats h, Queue<HabitatTiles> queue, HashSet<HabitatTiles> g, HashSet<HabitatTiles> v){
                target = h;
                q = queue;
                group = g;
                visitedTiles = v;
            }

            public void run(){
                while(!q.isEmpty()){
                    HabitatTiles current = q.remove();
                    if(!visitedTiles.contains(current)){
                        group.add(current);
                        visitedTiles.add(current);
                        //PrintTester.print(current+" added to group of "+target);
                        for(int i = 0; i<6; i++){
                            HabitatTiles next = current.get(i);
                            //PrintTester.print("HabitatMatch between "+current.getHabitats()+", "+next.getHabitats()+" at "+i+" returns "+ current.habitatMatch(i));
                            if(current.habitatMatch(i)&&current.getHabitats().get(i)==target&&!next.isEmpty()){
                                q.add(next);
                            }
                        }
                    }
                }
            }
        }
    }
}
package Components;

import java.util.*;

import java.awt.*;

import MathHelper.*;

public class coordinateGraphGeneration {
    private final double root3 = 1.7320508075688772935;
    private final double ySpacing = 1.571428571;
    private final double root32 = 0.86602540378;
    private Double size;
    private ArrayList<PointGenerator>hexagons = new ArrayList<>();
    public coordinateGraphGeneration(int xLim, int yLim){
        size = 70.0;
        int xIncrement = convertR3(size);
        int yIncrement = (int)(Math.round(size*1.5));
        int x = 10;
        int y = (int)Math.round(size);
        //hexagons.add(new PointGenerator(x, y, size));
        while(y<yLim){
            while(x<xLim){
                hexagons.add(new PointGenerator(x+=xIncrement, y, size));
            }
            x =10;
            hexagons.add(new PointGenerator(x+=convertR32(size), y+=yIncrement, size));
            while(x<xLim){
                hexagons.add(new PointGenerator(x+=xIncrement, y, size));
            }
            y+=yIncrement;
            x = 10;
        }
    }

    public void decreaseSize(){

    }

    public ArrayList<PointGenerator>getHexs(){
        return hexagons;
    }

    public int convertR3(int value){
        return (int)(Math.ceil(value*root3));
    }

    public int convertR3(Double value){
        return (int)(Math.ceil(value*root3));
    }

    public int convertR32(int value){
        return (int)(Math.round(value*root32));
    }

    public int convertR32(Double value){
        return (int)(Math.round(value*root32));
    }

    public void paintAll(Graphics g){
        g.setColor(new Color(200, 200, 200));
        for (int i =0;i<hexagons.size();i++){
            hexagons.get(i).drawHexagon(g);
        }
        g.setColor(Color.BLACK);
    }
}

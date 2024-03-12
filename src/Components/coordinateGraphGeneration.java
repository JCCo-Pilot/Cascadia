package Components;

import java.util.*;

import Entities.*;
import Entities.Enums.*;
import Entities.Images.*;
import Entities.Tokens.*;
import Entities.WildlifeScoringCards.*;
import Entities.StarterTilePics.*;
import Entities.ScoringCardsPics.*;

import EventAndListener.*;

import MathHelper.*;

import Panels.*;

public class coordinateGraphGeneration {
    //given the x coordinate and the y coordinate as well as the radius
    private final double root3 = 1.7320508075688772935;
    private final double ySpacing = 1.571428571;
    private final double root32 = 0.86602540378;
    private int xPos, yPos;
    private double radius;
    //xspacing = root3*radius +orginalx
    //yspacing = radius*yspacing+originaly
    private ArrayList<PointGenerator>hexagons = new ArrayList<>();
    //offsets - 120.0, 110.0 -1 ,100.0, 90.0, 80.0 -1, 70.0, 60.0, 50.0-1, 40.0, 30.0, 20.0-1, 10.0
    public coordinateGraphGeneration(){
        Double size = 120.0;
        int xIncrement = convertR3(size);
        int yIncrement = (int)(Math.round(size*1.5));
        int x = 10;
        int y = (int)Math.round(size);
        //hexagons.add(new PointGenerator(x, y, size));
        while(y<870){
            while(x<1550){
                hexagons.add(new PointGenerator(x+=xIncrement, y, size));
            }
            x =10;
            hexagons.add(new PointGenerator(x+=convertR32(size), y+=yIncrement, size));
            while(x<1550){
                hexagons.add(new PointGenerator(x+=xIncrement, y, size));
            }
            y+=yIncrement;
            x = 10;
        }
       
        
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
}

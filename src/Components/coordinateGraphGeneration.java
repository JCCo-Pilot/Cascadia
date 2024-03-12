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
    public coordinateGraphGeneration(){
        hexagons.add(new PointGenerator(100, 100, 100.0));
        hexagons.add(new PointGenerator(100+convertR3(100), 100, 100.0));
        hexagons.add(new PointGenerator(100+convertR3(100)+convertR3(100), 100, 100.0));
    }

    public ArrayList<PointGenerator>getHexs(){
        return hexagons;
    }

    public int convertR3(int value){
        return (int)(Math.ceil(value*root3));
    }

    public int convertR32(int value){
        return (int)(Math.round(value*root32));
    }
}

package Entities;
import java.awt.*;
import javax.swing.*;

import Entities.Enums.CardAnimals;
import Entities.Enums.Habitats;

import java.io.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.event.*;
import static java.lang.System.*;
public class HabitatGraph{
    private HabitatTiles root;

    public HabitatGraph(HabitatTiles root){
        //root should be a Starter Tile, meaning that this tile already has 2 other tiles connected to it.
        this.root = root;
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

    public HashSet<HabitatTiles> iterate(){
        HashSet<HabitatTiles> iterationReturn = new HashSet<HabitatTiles>();
        iterate(root, iterationReturn);
        return iterationReturn;
    }

    private void iterate(HabitatTiles h, HashSet<HabitatTiles> s){
        if(h==null||s.contains(h)){
            return;
        }else{
            s.add(h);
            for(int i = 0; i>6; i++){
                iterate(h.get(i), s);
            }
        }
    }

    public Integer getLargestContiguousGroup(Habitats habitat){

    }
}

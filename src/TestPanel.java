import java.util.*;
import javax.swing.*;

import Components.coordinateGraphGeneration;
import Entities.HabitatTiles;
import Entities.StarterTile;
import EventAndListener.*;
import MathHelper.PointGenerator;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import static java.lang.System.*;
public class TestPanel extends JPanel implements MouseListener{
    private ArrayList<HabitatTiles>tiles = new ArrayList<>();
    private ArrayList<StarterTile>startTiles = new ArrayList<>();
    private ArrayList<PointGenerator>pg = new ArrayList<>();
    private coordinateGraphGeneration cgg = new coordinateGraphGeneration();
    public TestPanel(){
        //construct();
        construct2();
        this.addMouseListener(this);
        this.setVisible(true);
    }
    @Override
    public void paint(Graphics g){
        for(int i =0;i<tiles.size();i++){
            //tiles.get(i).drawHexagon(g);
        }
        for (int i =0;i<startTiles.size();i++){
            //startTiles.get(i).paintStarter(g);
        }
        for (int i =0;i<pg.size();i++){
            pg.get(i).drawHexagon(g);
        }
    }
    private void construct2(){
        pg = cgg.getHexs();
    }
    //x spacing is (size *root3)/2
    private void construct(){
        Double size = 70.0;
        //tile 1
        tiles.add(new HabitatTiles("swamp-hawk-key", new String[]{"swamp"}, new String[]{"hawk"}, true, 250, 100, size,true));
        tiles.add(new HabitatTiles("forest+lake-salmon-elk-hawk", new String[]{"forest","lake"}, new String[]{"salmon","elk","hawk"}, false, 190, 205, size,true));//240
        tiles.get(1).setOffset(240);
        tiles.add(new HabitatTiles("mountain+desert-bear-fox", new String[]{"mountain","desert"}, new String[]{"fox","bear"}, false, 310, 205, size,true));//300
        tiles.get(2).setOffset(300);
        startTiles.add(new StarterTile(tiles).setPos(400, 400, 70.0));
        //tile 2
        /*tiles.add(new HabitatTiles("forest-elk-key", new String[]{"forest"}, new String[]{"elk"}, true, 250, 100, size,true));
        tiles.add(new HabitatTiles("lake+mountain-hawk-elk-bear", new String[]{"lake","mountain"}, new String[]{"bear","elk","hawk"}, false, 190, 205, size,true));//240
        tiles.get(1).setOffset(240);
        tiles.add(new HabitatTiles("desert+swamp-fox-salmon", new String[]{"desert","swamp"}, new String[]{"fox","salmon"}, false, 310, 205, size,true));//300
        tiles.get(2).setOffset(300);
        */
        //tile 3
        /*tiles.add(new HabitatTiles("forest-elk-key", new String[]{"forest"}, new String[]{"elk"}, true, 250, 100, size,true));
        tiles.add(new HabitatTiles("lake+mountain-hawk-elk-bear", new String[]{"lake","mountain"}, new String[]{"bear","elk","hawk"}, false, 190, 205, size,true));//240
        tiles.get(1).setOffset(240);
        tiles.add(new HabitatTiles("desert+swamp-fox-salmon", new String[]{"desert","swamp"}, new String[]{"fox","salmon"}, false, 310, 205, size,true));//300
        tiles.get(2).setOffset(300);
        */
        //tile 4
        /*tiles.add(new HabitatTiles("desert-fox-key", new String[]{"desert"}, new String[]{"fox"}, true, 250, 100, size,true));
        tiles.add(new HabitatTiles("swamp+lake-salmon-fox-hawk", new String[]{"swamp","lake"}, new String[]{"salmon","fox","hawk"}, false, 190, 205, size,true));//240
        tiles.get(1).setOffset(60);
        tiles.add(new HabitatTiles("mountain+forest-bear-elk", new String[]{"mountain","forest"}, new String[]{"bear","elk"}, false, 310, 205, size,true));//300
        tiles.get(2).setOffset(300);
        */
        //tile 5
        /*tiles.add(new HabitatTiles("mountain-bear-key", new String[]{"mountain"}, new String[]{"bear"}, true, 250, 100, size,true));
        tiles.add(new HabitatTiles("forest+swamp-hawk-elk-fox", new String[]{"forest","swamp"}, new String[]{"hawk","elk","fox"}, false, 190, 205, size,true));//240
        tiles.get(1).setOffset(60);
        tiles.add(new HabitatTiles("desert+lake-salmon-bear", new String[]{"mountain","lake"}, new String[]{"salmon","bear"}, false, 310, 205, size,true));//300
        tiles.get(2).setOffset(300);*/

    }
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        for (int i =0;i<tiles.size();i++){
            if (tiles.get(i).isPointInsideHexagon(e)){
                tiles.get(i).rotate();
                repaint();
                //out.println("Clicked the tile");
            }
        }
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}

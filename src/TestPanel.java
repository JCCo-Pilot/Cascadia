import java.util.*;
import javax.swing.*;

import Entities.HabitatTiles;
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
    public TestPanel(){
        construct();
        this.addMouseListener(this);
        this.setVisible(true);
    }
    @Override
    public void paint(Graphics g){
        for(int i =0;i<tiles.size();i++){
            tiles.get(i).drawHexagon(g);
        }
    }
    //x spacing is (size *root3)/2
    private void construct(){
        Double size = 70.0;
        //tile 1
        /*tiles.add(new HabitatTiles("swamp-hawk-key", new String[]{"swamp"}, new String[]{"hawk"}, true, 250, 100, size,true));
        tiles.add(new HabitatTiles("forest+lake-salmon-elk-hawk", new String[]{"forest","lake"}, new String[]{"salmon","elk","hawk"}, false, 190, 205, size,true));//240
        tiles.add(new HabitatTiles("mountain+desert-bear-fox", new String[]{"mountain","desert"}, new String[]{"fox","bear"}, false, 310, 205, size,true));//300
        */
        //tile 2
        /*tiles.add(new HabitatTiles("forest-elk-key", new String[]{"forest"}, new String[]{"elk"}, true, 250, 100, size,true));
        tiles.add(new HabitatTiles("lake+mountain-hawk-elk-bear", new String[]{"lake","mountain"}, new String[]{"bear","elk","hawk"}, false, 190, 205, size,true));//240
        tiles.add(new HabitatTiles("desert+swamp-fox-salmon", new String[]{"desert","swamp"}, new String[]{"fox","salmon"}, false, 310, 205, size,true));//300
        */
        //tile 3
        tiles.add(new HabitatTiles("forest-elk-key", new String[]{"forest"}, new String[]{"elk"}, true, 250, 100, size,true));
        tiles.add(new HabitatTiles("lake+mountain-hawk-elk-bear", new String[]{"lake","mountain"}, new String[]{"bear","elk","hawk"}, false, 190, 205, size,true));//240
        tiles.add(new HabitatTiles("desert+swamp-fox-salmon", new String[]{"desert","swamp"}, new String[]{"fox","salmon"}, false, 310, 205, size,true));//300
        
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

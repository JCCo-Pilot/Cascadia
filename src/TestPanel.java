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
public class TestPanel extends JPanel{
    private ArrayList<HabitatTiles>tiles = new ArrayList<>();
    public TestPanel(){
        construct();
        this.setVisible(true);
    }
    @Override
    public void paint(Graphics g){
        for(int i =0;i<tiles.size();i++){
            tiles.get(i).drawHexagon(g);
        }
    }
    private void construct(){
        tiles.add(new HabitatTiles("swamp-hawk-key", new String[]{"swamp"}, new String[]{"hawk"}, true, 100, 100, 70.0));
    }
}

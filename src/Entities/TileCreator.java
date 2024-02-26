package Entities;
import java.util.*;
public class TileCreator {
    private ArrayList<HabitatTiles>tile;
    public TileCreator(){
        tile = new ArrayList<>();
        construct();
    }
    public void construct(){
        int x =61;
        int xInc = 61;
        //tile.add("imagename",x+xinc,y+yinc,size);
        //tile.add();
        Integer size;//change these to whatever u want
        Integer x;
        Integer y;
        Integer xinc;
        Integer yinc;

        tile.add(new HabitatTiles("wetland+river-salmon-hawk", {"wetland", "river"}, {"salmon", "hawk"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("wetland+river-salmon-bear", {"wetland", "river"}, {"salmon", "bear"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("wetland+river-hawk-bear", {"wetland", "river"}, {"hawk", "bear"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("wetland+river-fox-salmon", {"wetland", "river"}, {"fox", "salmon"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("wetland+river-fox-hawk", {"wetland", "river"}, {"fox", "hawk"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("wetland+river-bear-salmon-hawk", {"wetland", "river"}, {"bear", "salmon", "hawk"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("wetland-salmon-key", {"wetland"}, {"salmon"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("wetland-salmon-key-2", {"wetland"}, {"salmon"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("wetland-hawk-key", {"wetland"}, {"hawk"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("wetland-fox-key", {"wetland"}, {"fox"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("wetland-fox-key-2", {"wetland"}, {"fox"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain+wetland-salmon-elk-bear", {"mountain", "wetland"}, {"salmon", "elk", "bear"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain+wetland-hawk-salmon", {"mountain", "wetland"}, {"hawk", "salmon"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain+wetland-fox-bear-hawk", {"mountain", "wetland"}, {"fox", "bear", "hawk"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain+wetland-hawk-elk", {"mountain", "wetland"}, {"hawk", "elk"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain+wetland-elk-fox", {"mountain", "wetland"}, {"elk", "fox"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain+wetland-bear-salmon", {"mountain", "wetland"}, {"bear", "salmon"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain+forest-hawk-elk-bear", {"mountain", "forest"}, {"hawk", "elk", "bear"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain+forest-hawk-bear", {"mountain", "forest"}, {"hawk", "bear"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain+forest-fox-elk-bear", {"mountain", "forest"}, {"fox", "elk", "bear"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain+forest-elk-fox", {"mountain", "forest"}, {"elk", "fox"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain+forest-bear-fox", {"mountain", "forest"}, {"bear", "fox"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain+prairie-salmon-elk", {"mountain", "prairie"}, {"salmon", "elk"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain+prairie-salmon-fox-bear", {"mountain", "prairie"}, {"salmon", "fox", "bear"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain+prairie-hawk-fox", {"mountain", "prairie"}, {"hawk", "fox"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain+prairie-hawk-elk", {"mountain", "prairie"}, {"hawk", "elk"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain+prairie-fox-elk-bear", {"mountain", "prairie"}, {"fox", "elk", "bear"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain+prairie-bear-salmon", {"mountain", "prairie"}, {"bear", "salmon"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain-hawk-key", {"mountain"}, {"hawk"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain-hawk-key-2", {"mountain"}, {"hawk"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain-elk-key", {"mountain"}, {"elk"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain-elk-key-2", {"mountain"}, {"elk"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("mountain-bear-key", {"mountain"}, {"bear"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("river+mountain-salmon-hawk", {"river", "mountain"}, {"salmon", "hawk"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("river+mountain-salmon-bear", {"river", "mountain"}, {"salmon", "bear"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("river+mountain-salmon-bear-hawk", {"river", "mountain"}, {"salmon", "bear", "hawk"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("river+mountain-hawk-elk", {"river", "mountain"}, {"hawk", "elk"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("river+mountain-hawk-bear", {"river", "mountain"}, {"hawk", "bear"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("river+mountain-bear-elk", {"river", "mountain"}, {"bear", "elk"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("river-salmon-key", {"river"}, {"salmon"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("river-hawk-key", {"river"}, {"hawk"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("river-hawk-key-2", {"river"}, {"hawk"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("river-bear-key", {"river"}, {"bear"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("river-bear-key-2", {"river"}, {"bear"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest+wetland-salmon-elk-hawk", {"forest", "wetland"}, {"salmon", "elk", "hawk"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest+wetland-fox-hawk", {"forest", "wetland"}, {"fox", "hawk"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest+wetland-elk-salmon", {"forest", "wetland"}, {"elk", "salmon"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest+wetland-elk-hawk", {"forest", "wetland"}, {"elk", "hawk"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest+wetland-bear-salmon", {"forest", "wetland"}, {"bear", "salmon"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest+wetland-bear-fox", {"forest", "wetland"}, {"bear", "fox"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest+river-elk-hawk", {"forest", "river"}, {"elk", "hawk"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest+river-hawk-elk-fox", {"forest", "river"}, {"hawk", "elk", "fox"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest+river-fox-salmon", {"forest", "river"}, {"fox", "salmon"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest+river-fox-bear", {"forest", "river"}, {"fox", "bear"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest+river-elk-hawk-2", {"forest", "river"}, {"elk", "hawk"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest+river-elk-bear", {"forest", "river"}, {"elk", "bear"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest+river-salmon-bear", {"forest", "river"}, {"salmon", "bear"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest+prairie-fox-salmon", {"forest", "prairie"}, {"fox", "salmon"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest+prairie-elk-salmon", {"forest", "prairie"}, {"elk", "salmon"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest+prairie-elk-fox", {"forest", "prairie"}, {"elk", "fox"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest+prairie-elk-fox-salmon", {"forest", "prairie"}, {"elk", "fox", "salmon"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest+prairie-bear-fox", {"forest", "prairie"}, {"bear", "fox"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest+prairie-bear-elk", {"forest", "prairie"}, {"bear", "elk"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest-fox-key", {"forest"}, {"fox"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest-fox-key-2", {"forest"}, {"fox"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest-elk-key", {"forest"}, {"elk"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest-bear-key", {"forest"}, {"bear"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("forest-bear-key-2", {"forest"}, {"bear"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("prairie+wetland-salmon-hawk-fox", {"prairie", "wetland"}, {"salmon", "hawk", "fox"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("prairie+wetland-salmon-elk-fox", {"prairie", "wetland"}, {"salmon", "elk", "fox"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("prairie+wetland-hawk-salmon", {"prairie", "wetland"}, {"hawk", "salmon"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("prairie+wetland-hawk-fox", {"prairie", "wetland"}, {"hawk", "fox"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("prairie+wetland-elk-salmon", {"prairie", "wetland"}, {"elk", "salmon"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("prairie+wetland-elk-fox", {"prairie", "wetland"}, {"elk", "fox"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("prairie+river-salmon-fox-bear", {"prairie", "river"}, {"salmon", "fox", "bear"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("prairie+river-fox-hawk", {"prairie", "river"}, {"fox", "hawk"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("prairie+river-fox-hawk-bear", {"prairie", "river"}, {"fox", "hawk", "bear"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("prairie+river-fox-bear", {"prairie", "river"}, {"fox", "bear"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("prairie+river-elk-salmon", {"prairie", "river"}, {"elk", "salmon"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("prairie+river-elk-hawk", {"prairie", "river"}, {"elk", "hawk"}, false, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("prairie-salmon-key", {"prairie"}, {"salmon"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("prairie-salmon-key-2", {"prairie"}, {"salmon"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("prairie-fox-key", {"prairie"}, {"fox"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("prairie-elk-key", {"prairie"}, {"elk"}, true, x + xinc, y + yinc, size));
        tile.add(new HabitatTiles("prairie-elk-key-2", {"prairie"}, {"elk"}, true, x + xinc, y + yinc, size));
    }
    //x values(92, 214, 336, 458, 580, 702, 824)
    //x values set 2(153, 275, 397, 519, 641, 763)
    //y values(105,315,525,735)
    //2nd set of values(210, 420, 630)
}

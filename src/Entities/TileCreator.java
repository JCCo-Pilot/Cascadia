package Entities;
import java.util.*;
public class TileCreator {
    private ArrayList<HabitatTiles>tile;
    public TileCreator(){
        tile = new ArrayList<>();
        construct();
        construct2();
        setFullX(0);
        setFullY(0);
        shuffle();
    }
    private void shuffle(){
        int limit = (int)(Math.random()*100)+100;
        for(int i =0;i<limit;i++){
            Collections.shuffle(tile);   
        }
    }
    public void setFullX(int x){
        for (int i =0;i<tile.size();i++){
            tile.get(i).setX(x);
        }
    }
    public void setFullY(int x){
        for (int i =0;i<tile.size();i++){
            tile.get(i).setY(x);
        }
    }
    public ArrayList<HabitatTiles>getTiles(){
        return tile;
    }

    private static class Raghav extends Thread{
        String imageName;
        String[] habitats;
        String[] animals;
        boolean isKeyStone;
        Integer x;
        Integer y;
        Double size;
        HabitatTiles tile;
        private Raghav(String imageName, String[] habitats, String[] animals, boolean isKeyStone, Integer x, Integer y, Double size){
            this.imageName = imageName;
            this.habitats=habitats;
            this.animals=animals;
            this.isKeyStone = isKeyStone;
            this.x = x;
            this.y = y;
            this.size = size;
        }

        public void run(){
            tile = new HabitatTiles(imageName, habitats, animals, isKeyStone, x, y, size);
        }

        public void create(){
            tile = new HabitatTiles(imageName, habitats, animals, isKeyStone, x, y, size);
        }

        public static HabitatTiles rizzWithDaWamen(String imageName, String[] habitats, String[] animals, boolean isKeyStone, Integer x, Integer y, Double size){
            Raghav r = new Raghav(imageName, habitats, animals, isKeyStone, x, y, size);
            r.run();
            return r.tile;
        }
        

        
    }
    public void construct(){
        int x =92;
        int xinc = 121;
        int y = 105;
        int yinc = 110;
        Double size = 70.0;
        tile.add(Raghav.rizzWithDaWamen("forest+wetland-elk-salmon", new String[]{"forest", "wetland"}, new String[]{"elk", "salmon"}, false, x, y, size));
        tile.add(Raghav.rizzWithDaWamen("forest+wetland-elk-hawk", new String[]{"forest", "wetland"}, new String[]{"elk", "hawk"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("forest+wetland-bear-salmon", new String[]{"forest", "wetland"}, new String[]{"bear", "salmon"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("forest+wetland-bear-fox", new String[]{"forest", "wetland"}, new String[]{"bear", "fox"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("forest+river-elk-hawk", new String[]{"forest", "river"}, new String[]{"elk", "hawk"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("forest+river-hawk-elk-fox", new String[]{"forest", "river"}, new String[]{"hawk", "elk", "fox"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("forest+river-fox-salmon", new String[]{"forest", "river"}, new String[]{"fox", "salmon"}, false, x+=xinc, y, size));
        x = 153; y+=110;
        tile.add(Raghav.rizzWithDaWamen("forest+river-fox-bear", new String[]{"forest", "river"}, new String[]{"fox", "bear"}, false, x, y, size));
        tile.add(Raghav.rizzWithDaWamen("forest+river-elk-hawk-2", new String[]{"forest", "river"}, new String[]{"elk", "hawk"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("forest+river-elk-bear", new String[]{"forest", "river"}, new String[]{"elk", "bear"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("forest+river-salmon-bear", new String[]{"forest", "river"}, new String[]{"salmon", "bear"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("forest+prairie-fox-salmon", new String[]{"forest", "prairie"}, new String[]{"fox", "salmon"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("forest+prairie-elk-salmon", new String[]{"forest", "prairie"}, new String[]{"elk", "salmon"}, false, x+=xinc, y, size));
        x = 92; y+=110;
        tile.add(Raghav.rizzWithDaWamen("forest+prairie-elk-fox", new String[]{"forest", "prairie"}, new String[]{"elk", "fox"}, false, x, y, size));
        tile.add(Raghav.rizzWithDaWamen("forest+prairie-elk-fox-salmon", new String[]{"forest", "prairie"}, new String[]{"elk", "fox", "salmon"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("forest+prairie-bear-fox", new String[]{"forest", "prairie"}, new String[]{"bear", "fox"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("forest+prairie-bear-elk", new String[]{"forest", "prairie"}, new String[]{"bear", "elk"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("forest-fox-key", new String[]{"forest"}, new String[]{"fox"}, true, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("forest-fox-key-2", new String[]{"forest"}, new String[]{"fox"}, true, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("forest-elk-key", new String[]{"forest"}, new String[]{"elk"}, true, x+=xinc, y, size));
        x = 153; y+=110;
        tile.add(Raghav.rizzWithDaWamen("forest-bear-key", new String[]{"forest"}, new String[]{"bear"}, true, x, y, size));
        tile.add(Raghav.rizzWithDaWamen("forest-bear-key-2", new String[]{"forest"}, new String[]{"bear"}, true, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("prairie+wetland-salmon-hawk-fox", new String[]{"prairie", "wetland"}, new String[]{"salmon", "hawk", "fox"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("prairie+wetland-salmon-elk-fox", new String[]{"prairie", "wetland"}, new String[]{"salmon", "elk", "fox"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("prairie+wetland-hawk-salmon", new String[]{"prairie", "wetland"}, new String[]{"hawk", "salmon"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("prairie+wetland-hawk-fox", new String[]{"prairie", "wetland"}, new String[]{"hawk", "fox"}, false, x+=xinc, y, size));
        x = 92; y+=110;
        tile.add(Raghav.rizzWithDaWamen("prairie+wetland-elk-salmon", new String[]{"prairie", "wetland"}, new String[]{"elk", "salmon"}, false, x, y, size));
        tile.add(Raghav.rizzWithDaWamen("prairie+wetland-elk-fox", new String[]{"prairie", "wetland"}, new String[]{"elk", "fox"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("prairie+river-salmon-fox-bear", new String[]{"prairie", "river"}, new String[]{"salmon", "fox", "bear"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("prairie+river-fox-hawk", new String[]{"prairie", "river"}, new String[]{"fox", "hawk"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("prairie+river-fox-hawk-bear", new String[]{"prairie", "river"}, new String[]{"fox", "hawk", "bear"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("prairie+river-fox-bear", new String[]{"prairie", "river"}, new String[]{"fox", "bear"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("prairie+river-elk-salmon", new String[]{"prairie", "river"}, new String[]{"elk", "salmon"}, false, x+=xinc, y, size));
        x = 153; y+=110;
        tile.add(Raghav.rizzWithDaWamen("prairie+river-elk-hawk", new String[]{"prairie", "river"}, new String[]{"elk", "hawk"}, false, x, y, size));
        tile.add(Raghav.rizzWithDaWamen("prairie-salmon-key", new String[]{"prairie"}, new String[]{"salmon"}, true, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("prairie-salmon-key-2", new String[]{"prairie"}, new String[]{"salmon"}, true, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("prairie-fox-key", new String[]{"prairie"}, new String[]{"fox"}, true, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("prairie-elk-key", new String[]{"prairie"}, new String[]{"elk"}, true, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("prairie-elk-key-2", new String[]{"prairie"}, new String[]{"elk"}, true, x+=xinc, y, size));
    }
    /*public void construct(){
        int x =92;
        int xinc = 121;
        int y = 105;
        int yinc = 110;
        Double size = 70.0;
        tile.add(new HabitatTiles("forest+wetland-elk-salmon", new String[]{"forest", "wetland"}, new String[]{"elk", "salmon"}, false, x, y, size));
        tile.add(new HabitatTiles("forest+wetland-elk-hawk", new String[]{"forest", "wetland"}, new String[]{"elk", "hawk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+wetland-bear-salmon", new String[]{"forest", "wetland"}, new String[]{"bear", "salmon"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+wetland-bear-fox", new String[]{"forest", "wetland"}, new String[]{"bear", "fox"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+river-elk-hawk", new String[]{"forest", "river"}, new String[]{"elk", "hawk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+river-hawk-elk-fox", new String[]{"forest", "river"}, new String[]{"hawk", "elk", "fox"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+river-fox-salmon", new String[]{"forest", "river"}, new String[]{"fox", "salmon"}, false, x+=xinc, y, size));
        x = 153; y+=110;
        tile.add(new HabitatTiles("forest+river-fox-bear", new String[]{"forest", "river"}, new String[]{"fox", "bear"}, false, x, y, size));
        tile.add(new HabitatTiles("forest+river-elk-hawk-2", new String[]{"forest", "river"}, new String[]{"elk", "hawk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+river-elk-bear", new String[]{"forest", "river"}, new String[]{"elk", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+river-salmon-bear", new String[]{"forest", "river"}, new String[]{"salmon", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+prairie-fox-salmon", new String[]{"forest", "prairie"}, new String[]{"fox", "salmon"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+prairie-elk-salmon", new String[]{"forest", "prairie"}, new String[]{"elk", "salmon"}, false, x+=xinc, y, size));
        x = 92; y+=110;
        tile.add(new HabitatTiles("forest+prairie-elk-fox", new String[]{"forest", "prairie"}, new String[]{"elk", "fox"}, false, x, y, size));
        tile.add(new HabitatTiles("forest+prairie-elk-fox-salmon", new String[]{"forest", "prairie"}, new String[]{"elk", "fox", "salmon"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+prairie-bear-fox", new String[]{"forest", "prairie"}, new String[]{"bear", "fox"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+prairie-bear-elk", new String[]{"forest", "prairie"}, new String[]{"bear", "elk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest-fox-key", new String[]{"forest"}, new String[]{"fox"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest-fox-key-2", new String[]{"forest"}, new String[]{"fox"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest-elk-key", new String[]{"forest"}, new String[]{"elk"}, true, x+=xinc, y, size));
        x = 153; y+=110;
        tile.add(new HabitatTiles("forest-bear-key", new String[]{"forest"}, new String[]{"bear"}, true, x, y, size));
        tile.add(new HabitatTiles("forest-bear-key-2", new String[]{"forest"}, new String[]{"bear"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie+wetland-salmon-hawk-fox", new String[]{"prairie", "wetland"}, new String[]{"salmon", "hawk", "fox"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie+wetland-salmon-elk-fox", new String[]{"prairie", "wetland"}, new String[]{"salmon", "elk", "fox"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie+wetland-hawk-salmon", new String[]{"prairie", "wetland"}, new String[]{"hawk", "salmon"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie+wetland-hawk-fox", new String[]{"prairie", "wetland"}, new String[]{"hawk", "fox"}, false, x+=xinc, y, size));
        x = 92; y+=110;
        tile.add(new HabitatTiles("prairie+wetland-elk-salmon", new String[]{"prairie", "wetland"}, new String[]{"elk", "salmon"}, false, x, y, size));
        tile.add(new HabitatTiles("prairie+wetland-elk-fox", new String[]{"prairie", "wetland"}, new String[]{"elk", "fox"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie+river-salmon-fox-bear", new String[]{"prairie", "river"}, new String[]{"salmon", "fox", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie+river-fox-hawk", new String[]{"prairie", "river"}, new String[]{"fox", "hawk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie+river-fox-hawk-bear", new String[]{"prairie", "river"}, new String[]{"fox", "hawk", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie+river-fox-bear", new String[]{"prairie", "river"}, new String[]{"fox", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie+river-elk-salmon", new String[]{"prairie", "river"}, new String[]{"elk", "salmon"}, false, x+=xinc, y, size));
        x = 153; y+=110;
        tile.add(new HabitatTiles("prairie+river-elk-hawk", new String[]{"prairie", "river"}, new String[]{"elk", "hawk"}, false, x, y, size));
        tile.add(new HabitatTiles("prairie-salmon-key", new String[]{"prairie"}, new String[]{"salmon"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie-salmon-key-2", new String[]{"prairie"}, new String[]{"salmon"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie-fox-key", new String[]{"prairie"}, new String[]{"fox"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie-elk-key", new String[]{"prairie"}, new String[]{"elk"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie-elk-key-2", new String[]{"prairie"}, new String[]{"elk"}, true, x+=xinc, y, size));
    }
    /*public void construct(){
        int x =92;
        int xinc = 121;
        int y = 105;
        int yinc = 110;
        Double size = 70.0;
        tile.add(new HabitatTiles("wetland+river-salmon-hawk", new String[]{"wetland", "river"}, new String[]{"salmon", "hawk"}, false, x, y, size));
        tile.add(new HabitatTiles("wetland+river-salmon-bear", new String[]{"wetland", "river"}, new String[]{"salmon", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("wetland+river-hawk-bear", new String[]{"wetland", "river"}, new String[]{"hawk", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("wetland+river-fox-salmon", new String[]{"wetland", "river"}, new String[]{"fox", "salmon"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("wetland+river-fox-hawk", new String[]{"wetland", "river"}, new String[]{"fox", "hawk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("wetland+river-bear-salmon-hawk", new String[]{"wetland", "river"}, new String[]{"bear", "salmon", "hawk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("wetland-salmon-key", new String[]{"wetland"}, new String[]{"salmon"}, true, x+=xinc, y , size));
        x = 153; y+=110;
        tile.add(new HabitatTiles("wetland-salmon-key-2", new String[]{"wetland"}, new String[]{"salmon"}, true, x, y, size));
        tile.add(new HabitatTiles("wetland-hawk-key", new String[]{"wetland"}, new String[]{"hawk"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("wetland-fox-key", new String[]{"wetland"}, new String[]{"fox"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("wetland-fox-key-2", new String[]{"wetland"}, new String[]{"fox"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+wetland-salmon-elk-bear", new String[]{"mountain", "wetland"}, new String[]{"salmon", "elk", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+wetland-hawk-salmon", new String[]{"mountain", "wetland"}, new String[]{"hawk", "salmon"}, false, x+=xinc, y, size));
        x = 92; y+=110;
        tile.add(new HabitatTiles("mountain+wetland-fox-bear-hawk", new String[]{"mountain", "wetland"}, new String[]{"fox", "bear", "hawk"}, false, x, y, size));
        tile.add(new HabitatTiles("mountain+wetland-hawk-elk", new String[]{"mountain", "wetland"}, new String[]{"hawk", "elk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+wetland-elk-fox", new String[]{"mountain", "wetland"}, new String[]{"elk", "fox"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+wetland-bear-salmon", new String[]{"mountain", "wetland"}, new String[]{"bear", "salmon"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+forest-hawk-elk-bear", new String[]{"mountain", "forest"}, new String[]{"hawk", "elk", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+forest-hawk-bear", new String[]{"mountain", "forest"}, new String[]{"hawk", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+forest-fox-elk-bear", new String[]{"mountain", "forest"}, new String[]{"fox", "elk", "bear"}, false, x+=xinc, y, size));
        x= 153; y+=110;
        tile.add(new HabitatTiles("mountain+forest-elk-fox", new String[]{"mountain", "forest"}, new String[]{"elk", "fox"}, false, x, y, size));
        tile.add(new HabitatTiles("mountain+forest-bear-fox", new String[]{"mountain", "forest"}, new String[]{"bear", "fox"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+prairie-salmon-elk", new String[]{"mountain", "prairie"}, new String[]{"salmon", "elk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+prairie-salmon-fox-bear", new String[]{"mountain", "prairie"}, new String[]{"salmon", "fox", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+prairie-hawk-fox", new String[]{"mountain", "prairie"}, new String[]{"hawk", "fox"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+prairie-hawk-elk", new String[]{"mountain", "prairie"}, new String[]{"hawk", "elk"}, false, x+=xinc, y, size));
        x = 92; y+=110;
        tile.add(new HabitatTiles("mountain+prairie-fox-elk-bear", new String[]{"mountain", "prairie"}, new String[]{"fox", "elk", "bear"}, false, x, y, size));
        tile.add(new HabitatTiles("mountain+prairie-bear-salmon", new String[]{"mountain", "prairie"}, new String[]{"bear", "salmon"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain-hawk-key", new String[]{"mountain"}, new String[]{"hawk"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain-hawk-key-2", new String[]{"mountain"}, new String[]{"hawk"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain-elk-key", new String[]{"mountain"}, new String[]{"elk"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain-elk-key-2", new String[]{"mountain"}, new String[]{"elk"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain-bear-key", new String[]{"mountain"}, new String[]{"bear"}, true, x+=xinc, y, size));
        x= 153; y+=110;
        tile.add(new HabitatTiles("river+mountain-salmon-hawk", new String[]{"river", "mountain"}, new String[]{"salmon", "hawk"}, false, x, y, size));
        tile.add(new HabitatTiles("river+mountain-salmon-bear", new String[]{"river", "mountain"}, new String[]{"salmon", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("river+mountain-salmon-bear-hawk", new String[]{"river", "mountain"}, new String[]{"salmon", "bear", "hawk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("river+mountain-hawk-elk", new String[]{"river", "mountain"}, new String[]{"hawk", "elk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("river+mountain-hawk-bear", new String[]{"river", "mountain"}, new String[]{"hawk", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("river+mountain-bear-elk", new String[]{"river", "mountain"}, new String[]{"bear", "elk"}, false, x+=xinc, y, size));
        x = 92; y+=110;
        tile.add(new HabitatTiles("river-salmon-key", new String[]{"river"}, new String[]{"salmon"}, true, x, y, size));
        tile.add(new HabitatTiles("river-hawk-key", new String[]{"river"}, new String[]{"hawk"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("river-hawk-key-2", new String[]{"river"}, new String[]{"hawk"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("river-bear-key", new String[]{"river"}, new String[]{"bear"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("river-bear-key-2", new String[]{"river"}, new String[]{"bear"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+wetland-salmon-elk-hawk", new String[]{"forest", "wetland"}, new String[]{"salmon", "elk", "hawk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+wetland-fox-hawk", new String[]{"forest", "wetland"}, new String[]{"fox", "hawk"}, false, x+=xinc, y, size));
        //tile.add("imagename",x+xinc,y+yinc,size);
        //tile.add();
    }*/
    public void construct2(){
        int x =92;
        int xinc = 121;
        int y = 105;
        int yinc = 110;
        Double size = 70.0;
        tile.add(Raghav.rizzWithDaWamen("wetland+river-salmon-hawk", new String[]{"wetland", "river"}, new String[]{"salmon", "hawk"}, false, x, y, size));
        tile.add(Raghav.rizzWithDaWamen("wetland+river-salmon-bear", new String[]{"wetland", "river"}, new String[]{"salmon", "bear"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("wetland+river-hawk-bear", new String[]{"wetland", "river"}, new String[]{"hawk", "bear"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("wetland+river-fox-salmon", new String[]{"wetland", "river"}, new String[]{"fox", "salmon"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("wetland+river-fox-hawk", new String[]{"wetland", "river"}, new String[]{"fox", "hawk"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("wetland+river-bear-salmon-hawk", new String[]{"wetland", "river"}, new String[]{"bear", "salmon", "hawk"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("wetland-salmon-key", new String[]{"wetland"}, new String[]{"salmon"}, true, x+=xinc, y , size));
        x = 153; y+=110;
        tile.add(Raghav.rizzWithDaWamen("wetland-salmon-key-2", new String[]{"wetland"}, new String[]{"salmon"}, true, x, y, size));
        tile.add(Raghav.rizzWithDaWamen("wetland-hawk-key", new String[]{"wetland"}, new String[]{"hawk"}, true, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("wetland-fox-key", new String[]{"wetland"}, new String[]{"fox"}, true, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("wetland-fox-key-2", new String[]{"wetland"}, new String[]{"fox"}, true, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("mountain+wetland-salmon-elk-bear", new String[]{"mountain", "wetland"}, new String[]{"salmon", "elk", "bear"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("mountain+wetland-hawk-salmon", new String[]{"mountain", "wetland"}, new String[]{"hawk", "salmon"}, false, x+=xinc, y, size));
        x = 92; y+=110;
        tile.add(Raghav.rizzWithDaWamen("mountain+wetland-fox-bear-hawk", new String[]{"mountain", "wetland"}, new String[]{"fox", "bear", "hawk"}, false, x, y, size));
        tile.add(Raghav.rizzWithDaWamen("mountain+wetland-hawk-elk", new String[]{"mountain", "wetland"}, new String[]{"hawk", "elk"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("mountain+wetland-elk-fox", new String[]{"mountain", "wetland"}, new String[]{"elk", "fox"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("mountain+wetland-bear-salmon", new String[]{"mountain", "wetland"}, new String[]{"bear", "salmon"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("mountain+forest-hawk-elk-bear", new String[]{"mountain", "forest"}, new String[]{"hawk", "elk", "bear"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("mountain+forest-hawk-bear", new String[]{"mountain", "forest"}, new String[]{"hawk", "bear"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("mountain+forest-fox-elk-bear", new String[]{"mountain", "forest"}, new String[]{"fox", "elk", "bear"}, false, x+=xinc, y, size));
        x= 153; y+=110;
        tile.add(Raghav.rizzWithDaWamen("mountain+forest-elk-fox", new String[]{"mountain", "forest"}, new String[]{"elk", "fox"}, false, x, y, size));
        tile.add(Raghav.rizzWithDaWamen("mountain+forest-bear-fox", new String[]{"mountain", "forest"}, new String[]{"bear", "fox"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("mountain+prairie-salmon-elk", new String[]{"mountain", "prairie"}, new String[]{"salmon", "elk"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("mountain+prairie-salmon-fox-bear", new String[]{"mountain", "prairie"}, new String[]{"salmon", "fox", "bear"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("mountain+prairie-hawk-fox", new String[]{"mountain", "prairie"}, new String[]{"hawk", "fox"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("mountain+prairie-hawk-elk", new String[]{"mountain", "prairie"}, new String[]{"hawk", "elk"}, false, x+=xinc, y, size));
        x = 92; y+=110;
        tile.add(Raghav.rizzWithDaWamen("mountain+prairie-fox-elk-bear", new String[]{"mountain", "prairie"}, new String[]{"fox", "elk", "bear"}, false, x, y, size));
        tile.add(Raghav.rizzWithDaWamen("mountain+prairie-bear-salmon", new String[]{"mountain", "prairie"}, new String[]{"bear", "salmon"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("mountain-hawk-key", new String[]{"mountain"}, new String[]{"hawk"}, true, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("mountain-hawk-key-2", new String[]{"mountain"}, new String[]{"hawk"}, true, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("mountain-elk-key", new String[]{"mountain"}, new String[]{"elk"}, true, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("mountain-elk-key-2", new String[]{"mountain"}, new String[]{"elk"}, true, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("mountain-bear-key", new String[]{"mountain"}, new String[]{"bear"}, true, x+=xinc, y, size));
        x= 153; y+=110;
        tile.add(Raghav.rizzWithDaWamen("river+mountain-salmon-hawk", new String[]{"river", "mountain"}, new String[]{"salmon", "hawk"}, false, x, y, size));
        tile.add(Raghav.rizzWithDaWamen("river+mountain-salmon-bear", new String[]{"river", "mountain"}, new String[]{"salmon", "bear"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("river+mountain-salmon-bear-hawk", new String[]{"river", "mountain"}, new String[]{"salmon", "bear", "hawk"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("river+mountain-hawk-elk", new String[]{"river", "mountain"}, new String[]{"hawk", "elk"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("river+mountain-hawk-bear", new String[]{"river", "mountain"}, new String[]{"hawk", "bear"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("river+mountain-bear-elk", new String[]{"river", "mountain"}, new String[]{"bear", "elk"}, false, x+=xinc, y, size));
        x = 92; y+=110;
        tile.add(Raghav.rizzWithDaWamen("river-salmon-key", new String[]{"river"}, new String[]{"salmon"}, true, x, y, size));
        tile.add(Raghav.rizzWithDaWamen("river-hawk-key", new String[]{"river"}, new String[]{"hawk"}, true, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("river-hawk-key-2", new String[]{"river"}, new String[]{"hawk"}, true, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("river-bear-key", new String[]{"river"}, new String[]{"bear"}, true, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("river-bear-key-2", new String[]{"river"}, new String[]{"bear"}, true, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("forest+wetland-salmon-elk-hawk", new String[]{"forest", "wetland"}, new String[]{"salmon", "elk", "hawk"}, false, x+=xinc, y, size));
        tile.add(Raghav.rizzWithDaWamen("forest+wetland-fox-hawk", new String[]{"forest", "wetland"}, new String[]{"fox", "hawk"}, false, x+=xinc, y, size));


    }
    //x values(92, 214, 336, 458, 580, 702, 824)
    //x values set 2(153, 275, 397, 519, 641, 763)
    //y values(105,315,525,735)
    //2nd set of values(210, 420, 630)

    /*        tile.add(new HabitatTiles("wetland+river-salmon-hawk", new String[]{"wetland", "river"}, new String[]{"salmon", "hawk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("wetland+river-salmon-bear", new String[]{"wetland", "river"}, new String[]{"salmon", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("wetland+river-hawk-bear", new String[]{"wetland", "river"}, new String[]{"hawk", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("wetland+river-fox-salmon", new String[]{"wetland", "river"}, new String[]{"fox", "salmon"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("wetland+river-fox-hawk", new String[]{"wetland", "river"}, new String[]{"fox", "hawk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("wetland+river-bear-salmon-hawk", new String[]{"wetland", "river"}, new String[]{"bear", "salmon", "hawk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("wetland-salmon-key", new String[]{"wetland"}, new String[]{"salmon"}, true, x+=xinc, y, size));
        
        tile.add(new HabitatTiles("wetland-salmon-key-2", new String[]{"wetland"}, new String[]{"salmon"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("wetland-hawk-key", new String[]{"wetland"}, new String[]{"hawk"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("wetland-fox-key", new String[]{"wetland"}, new String[]{"fox"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("wetland-fox-key-2", new String[]{"wetland"}, new String[]{"fox"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+wetland-salmon-elk-bear", new String[]{"mountain", "wetland"}, new String[]{"salmon", "elk", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+wetland-hawk-salmon", new String[]{"mountain", "wetland"}, new String[]{"hawk", "salmon"}, false, x+=xinc, y, size));

        tile.add(new HabitatTiles("mountain+wetland-fox-bear-hawk", new String[]{"mountain", "wetland"}, new String[]{"fox", "bear", "hawk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+wetland-hawk-elk", new String[]{"mountain", "wetland"}, new String[]{"hawk", "elk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+wetland-elk-fox", new String[]{"mountain", "wetland"}, new String[]{"elk", "fox"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+wetland-bear-salmon", new String[]{"mountain", "wetland"}, new String[]{"bear", "salmon"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+forest-hawk-elk-bear", new String[]{"mountain", "forest"}, new String[]{"hawk", "elk", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+forest-hawk-bear", new String[]{"mountain", "forest"}, new String[]{"hawk", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+forest-fox-elk-bear", new String[]{"mountain", "forest"}, new String[]{"fox", "elk", "bear"}, false, x+=xinc, y, size));
        
        tile.add(new HabitatTiles("mountain+forest-elk-fox", new String[]{"mountain", "forest"}, new String[]{"elk", "fox"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+forest-bear-fox", new String[]{"mountain", "forest"}, new String[]{"bear", "fox"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+prairie-salmon-elk", new String[]{"mountain", "prairie"}, new String[]{"salmon", "elk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+prairie-salmon-fox-bear", new String[]{"mountain", "prairie"}, new String[]{"salmon", "fox", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+prairie-hawk-fox", new String[]{"mountain", "prairie"}, new String[]{"hawk", "fox"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+prairie-hawk-elk", new String[]{"mountain", "prairie"}, new String[]{"hawk", "elk"}, false, x+=xinc, y, size));
        
        tile.add(new HabitatTiles("mountain+prairie-fox-elk-bear", new String[]{"mountain", "prairie"}, new String[]{"fox", "elk", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain+prairie-bear-salmon", new String[]{"mountain", "prairie"}, new String[]{"bear", "salmon"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain-hawk-key", new String[]{"mountain"}, new String[]{"hawk"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain-hawk-key-2", new String[]{"mountain"}, new String[]{"hawk"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain-elk-key", new String[]{"mountain"}, new String[]{"elk"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain-elk-key-2", new String[]{"mountain"}, new String[]{"elk"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("mountain-bear-key", new String[]{"mountain"}, new String[]{"bear"}, true, x+=xinc, y, size));
        
        tile.add(new HabitatTiles("river+mountain-salmon-hawk", new String[]{"river", "mountain"}, new String[]{"salmon", "hawk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("river+mountain-salmon-bear", new String[]{"river", "mountain"}, new String[]{"salmon", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("river+mountain-salmon-bear-hawk", new String[]{"river", "mountain"}, new String[]{"salmon", "bear", "hawk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("river+mountain-hawk-elk", new String[]{"river", "mountain"}, new String[]{"hawk", "elk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("river+mountain-hawk-bear", new String[]{"river", "mountain"}, new String[]{"hawk", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("river+mountain-bear-elk", new String[]{"river", "mountain"}, new String[]{"bear", "elk"}, false, x+=xinc, y, size));
        
        tile.add(new HabitatTiles("river-salmon-key", new String[]{"river"}, new String[]{"salmon"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("river-hawk-key", new String[]{"river"}, new String[]{"hawk"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("river-hawk-key-2", new String[]{"river"}, new String[]{"hawk"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("river-bear-key", new String[]{"river"}, new String[]{"bear"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("river-bear-key-2", new String[]{"river"}, new String[]{"bear"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+wetland-salmon-elk-hawk", new String[]{"forest", "wetland"}, new String[]{"salmon", "elk", "hawk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+wetland-fox-hawk", new String[]{"forest", "wetland"}, new String[]{"fox", "hawk"}, false, x+=xinc, y, size));

        tile.add(new HabitatTiles("forest+wetland-elk-salmon", new String[]{"forest", "wetland"}, new String[]{"elk", "salmon"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+wetland-elk-hawk", new String[]{"forest", "wetland"}, new String[]{"elk", "hawk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+wetland-bear-salmon", new String[]{"forest", "wetland"}, new String[]{"bear", "salmon"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+wetland-bear-fox", new String[]{"forest", "wetland"}, new String[]{"bear", "fox"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+river-elk-hawk", new String[]{"forest", "river"}, new String[]{"elk", "hawk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+river-hawk-elk-fox", new String[]{"forest", "river"}, new String[]{"hawk", "elk", "fox"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+river-fox-salmon", new String[]{"forest", "river"}, new String[]{"fox", "salmon"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+river-fox-bear", new String[]{"forest", "river"}, new String[]{"fox", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+river-elk-hawk-2", new String[]{"forest", "river"}, new String[]{"elk", "hawk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+river-elk-bear", new String[]{"forest", "river"}, new String[]{"elk", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+river-salmon-bear", new String[]{"forest", "river"}, new String[]{"salmon", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+prairie-fox-salmon", new String[]{"forest", "prairie"}, new String[]{"fox", "salmon"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+prairie-elk-salmon", new String[]{"forest", "prairie"}, new String[]{"elk", "salmon"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+prairie-elk-fox", new String[]{"forest", "prairie"}, new String[]{"elk", "fox"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+prairie-elk-fox-salmon", new String[]{"forest", "prairie"}, new String[]{"elk", "fox", "salmon"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+prairie-bear-fox", new String[]{"forest", "prairie"}, new String[]{"bear", "fox"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest+prairie-bear-elk", new String[]{"forest", "prairie"}, new String[]{"bear", "elk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest-fox-key", new String[]{"forest"}, new String[]{"fox"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest-fox-key-2", new String[]{"forest"}, new String[]{"fox"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest-elk-key", new String[]{"forest"}, new String[]{"elk"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest-bear-key", new String[]{"forest"}, new String[]{"bear"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("forest-bear-key-2", new String[]{"forest"}, new String[]{"bear"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie+wetland-salmon-hawk-fox", new String[]{"prairie", "wetland"}, new String[]{"salmon", "hawk", "fox"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie+wetland-salmon-elk-fox", new String[]{"prairie", "wetland"}, new String[]{"salmon", "elk", "fox"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie+wetland-hawk-salmon", new String[]{"prairie", "wetland"}, new String[]{"hawk", "salmon"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie+wetland-hawk-fox", new String[]{"prairie", "wetland"}, new String[]{"hawk", "fox"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie+wetland-elk-salmon", new String[]{"prairie", "wetland"}, new String[]{"elk", "salmon"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie+wetland-elk-fox", new String[]{"prairie", "wetland"}, new String[]{"elk", "fox"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie+river-salmon-fox-bear", new String[]{"prairie", "river"}, new String[]{"salmon", "fox", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie+river-fox-hawk", new String[]{"prairie", "river"}, new String[]{"fox", "hawk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie+river-fox-hawk-bear", new String[]{"prairie", "river"}, new String[]{"fox", "hawk", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie+river-fox-bear", new String[]{"prairie", "river"}, new String[]{"fox", "bear"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie+river-elk-salmon", new String[]{"prairie", "river"}, new String[]{"elk", "salmon"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie+river-elk-hawk", new String[]{"prairie", "river"}, new String[]{"elk", "hawk"}, false, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie-salmon-key", new String[]{"prairie"}, new String[]{"salmon"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie-salmon-key-2", new String[]{"prairie"}, new String[]{"salmon"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie-fox-key", new String[]{"prairie"}, new String[]{"fox"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie-elk-key", new String[]{"prairie"}, new String[]{"elk"}, true, x+=xinc, y, size));
        tile.add(new HabitatTiles("prairie-elk-key-2", new String[]{"prairie"}, new String[]{"elk"}, true, x+=xinc, y, size));
 */
}

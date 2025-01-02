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
        for (HabitatTiles habitatTiles : tile) {
            habitatTiles.setX(x);
        }
    }
    public void setFullY(int x){
        for (HabitatTiles habitatTiles : tile) {
            habitatTiles.setY(x);
        }
    }
    public ArrayList<HabitatTiles>getTiles(){
        return tile;
    }

    private static class TileCreatorThread extends Thread{
        String imageName;
        String[] habitats;
        String[] animals;
        boolean isKeyStone;
        Integer x;
        Integer y;
        Double size;
        HabitatTiles tile;
        private TileCreatorThread(String imageName, String[] habitats, String[] animals, boolean isKeyStone, Integer x, Integer y, Double size){
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

        public static HabitatTiles create(String imageName, String[] habitats, String[] animals, boolean isKeyStone, Integer x, Integer y, Double size){
            TileCreatorThread r = new TileCreatorThread(imageName, habitats, animals, isKeyStone, x, y, size);
            r.run();
            return r.tile;
        }
        

        
    }
    public void construct(){
        int x =92;
        int xinc = 121;
        int y = 105;
        Double size = 70.0;
        tile.add(TileCreatorThread.create("forest+wetland-elk-salmon", new String[]{"forest", "wetland"}, new String[]{"elk", "salmon"}, false, x, y, size));
        tile.add(TileCreatorThread.create("forest+wetland-elk-hawk", new String[]{"forest", "wetland"}, new String[]{"elk", "hawk"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("forest+wetland-bear-salmon", new String[]{"forest", "wetland"}, new String[]{"bear", "salmon"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("forest+wetland-bear-fox", new String[]{"forest", "wetland"}, new String[]{"bear", "fox"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("forest+river-elk-hawk", new String[]{"forest", "river"}, new String[]{"elk", "hawk"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("forest+river-hawk-elk-fox", new String[]{"forest", "river"}, new String[]{"hawk", "elk", "fox"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("forest+river-fox-salmon", new String[]{"forest", "river"}, new String[]{"fox", "salmon"}, false, x+=xinc, y, size));
        x = 153; y+=110;
        tile.add(TileCreatorThread.create("forest+river-fox-bear", new String[]{"forest", "river"}, new String[]{"fox", "bear"}, false, x, y, size));
        tile.add(TileCreatorThread.create("forest+river-elk-hawk-2", new String[]{"forest", "river"}, new String[]{"elk", "hawk"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("forest+river-elk-bear", new String[]{"forest", "river"}, new String[]{"elk", "bear"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("forest+river-salmon-bear", new String[]{"forest", "river"}, new String[]{"salmon", "bear"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("forest+prairie-fox-salmon", new String[]{"forest", "prairie"}, new String[]{"fox", "salmon"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("forest+prairie-elk-salmon", new String[]{"forest", "prairie"}, new String[]{"elk", "salmon"}, false, x+=xinc, y, size));
        x = 92; y+=110;
        tile.add(TileCreatorThread.create("forest+prairie-elk-fox", new String[]{"forest", "prairie"}, new String[]{"elk", "fox"}, false, x, y, size));
        tile.add(TileCreatorThread.create("forest+prairie-elk-fox-salmon", new String[]{"forest", "prairie"}, new String[]{"elk", "fox", "salmon"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("forest+prairie-bear-fox", new String[]{"forest", "prairie"}, new String[]{"bear", "fox"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("forest+prairie-bear-elk", new String[]{"forest", "prairie"}, new String[]{"bear", "elk"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("forest-fox-key", new String[]{"forest"}, new String[]{"fox"}, true, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("forest-fox-key-2", new String[]{"forest"}, new String[]{"fox"}, true, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("forest-elk-key", new String[]{"forest"}, new String[]{"elk"}, true, x+=xinc, y, size));
        x = 153; y+=110;
        tile.add(TileCreatorThread.create("forest-bear-key", new String[]{"forest"}, new String[]{"bear"}, true, x, y, size));
        tile.add(TileCreatorThread.create("forest-bear-key-2", new String[]{"forest"}, new String[]{"bear"}, true, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("prairie+wetland-salmon-hawk-fox", new String[]{"prairie", "wetland"}, new String[]{"salmon", "hawk", "fox"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("prairie+wetland-salmon-elk-fox", new String[]{"prairie", "wetland"}, new String[]{"salmon", "elk", "fox"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("prairie+wetland-hawk-salmon", new String[]{"prairie", "wetland"}, new String[]{"hawk", "salmon"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("prairie+wetland-hawk-fox", new String[]{"prairie", "wetland"}, new String[]{"hawk", "fox"}, false, x+=xinc, y, size));
        x = 92; y+=110;
        tile.add(TileCreatorThread.create("prairie+wetland-elk-salmon", new String[]{"prairie", "wetland"}, new String[]{"elk", "salmon"}, false, x, y, size));
        tile.add(TileCreatorThread.create("prairie+wetland-elk-fox", new String[]{"prairie", "wetland"}, new String[]{"elk", "fox"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("prairie+river-salmon-fox-bear", new String[]{"prairie", "river"}, new String[]{"salmon", "fox", "bear"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("prairie+river-fox-hawk", new String[]{"prairie", "river"}, new String[]{"fox", "hawk"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("prairie+river-fox-hawk-bear", new String[]{"prairie", "river"}, new String[]{"fox", "hawk", "bear"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("prairie+river-fox-bear", new String[]{"prairie", "river"}, new String[]{"fox", "bear"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("prairie+river-elk-salmon", new String[]{"prairie", "river"}, new String[]{"elk", "salmon"}, false, x+=xinc, y, size));
        x = 153; y+=110;
        tile.add(TileCreatorThread.create("prairie+river-elk-hawk", new String[]{"prairie", "river"}, new String[]{"elk", "hawk"}, false, x, y, size));
        tile.add(TileCreatorThread.create("prairie-salmon-key", new String[]{"prairie"}, new String[]{"salmon"}, true, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("prairie-salmon-key-2", new String[]{"prairie"}, new String[]{"salmon"}, true, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("prairie-fox-key", new String[]{"prairie"}, new String[]{"fox"}, true, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("prairie-elk-key", new String[]{"prairie"}, new String[]{"elk"}, true, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("prairie-elk-key-2", new String[]{"prairie"}, new String[]{"elk"}, true, x+=xinc, y, size));
    }

    public void construct2(){
        int x =92;
        int xinc = 121;
        int y = 105;
        Double size = 70.0;
        tile.add(TileCreatorThread.create("wetland+river-salmon-hawk", new String[]{"wetland", "river"}, new String[]{"salmon", "hawk"}, false, x, y, size));
        tile.add(TileCreatorThread.create("wetland+river-salmon-bear", new String[]{"wetland", "river"}, new String[]{"salmon", "bear"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("wetland+river-hawk-bear", new String[]{"wetland", "river"}, new String[]{"hawk", "bear"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("wetland+river-fox-salmon", new String[]{"wetland", "river"}, new String[]{"fox", "salmon"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("wetland+river-fox-hawk", new String[]{"wetland", "river"}, new String[]{"fox", "hawk"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("wetland+river-bear-salmon-hawk", new String[]{"wetland", "river"}, new String[]{"bear", "salmon", "hawk"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("wetland-salmon-key", new String[]{"wetland"}, new String[]{"salmon"}, true, x+=xinc, y , size));
        x = 153; y+=110;
        tile.add(TileCreatorThread.create("wetland-salmon-key-2", new String[]{"wetland"}, new String[]{"salmon"}, true, x, y, size));
        tile.add(TileCreatorThread.create("wetland-hawk-key", new String[]{"wetland"}, new String[]{"hawk"}, true, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("wetland-fox-key", new String[]{"wetland"}, new String[]{"fox"}, true, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("wetland-fox-key-2", new String[]{"wetland"}, new String[]{"fox"}, true, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("mountain+wetland-salmon-elk-bear", new String[]{"mountain", "wetland"}, new String[]{"salmon", "elk", "bear"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("mountain+wetland-hawk-salmon", new String[]{"mountain", "wetland"}, new String[]{"hawk", "salmon"}, false, x+=xinc, y, size));
        x = 92; y+=110;
        tile.add(TileCreatorThread.create("mountain+wetland-fox-bear-hawk", new String[]{"mountain", "wetland"}, new String[]{"fox", "bear", "hawk"}, false, x, y, size));
        tile.add(TileCreatorThread.create("mountain+wetland-hawk-elk", new String[]{"mountain", "wetland"}, new String[]{"hawk", "elk"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("mountain+wetland-elk-fox", new String[]{"mountain", "wetland"}, new String[]{"elk", "fox"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("mountain+wetland-bear-salmon", new String[]{"mountain", "wetland"}, new String[]{"bear", "salmon"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("mountain+forest-hawk-elk-bear", new String[]{"mountain", "forest"}, new String[]{"hawk", "elk", "bear"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("mountain+forest-hawk-bear", new String[]{"mountain", "forest"}, new String[]{"hawk", "bear"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("mountain+forest-fox-elk-bear", new String[]{"mountain", "forest"}, new String[]{"fox", "elk", "bear"}, false, x+=xinc, y, size));
        x= 153; y+=110;
        tile.add(TileCreatorThread.create("mountain+forest-elk-fox", new String[]{"mountain", "forest"}, new String[]{"elk", "fox"}, false, x, y, size));
        tile.add(TileCreatorThread.create("mountain+forest-bear-fox", new String[]{"mountain", "forest"}, new String[]{"bear", "fox"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("mountain+prairie-salmon-elk", new String[]{"mountain", "prairie"}, new String[]{"salmon", "elk"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("mountain+prairie-salmon-fox-bear", new String[]{"mountain", "prairie"}, new String[]{"salmon", "fox", "bear"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("mountain+prairie-hawk-fox", new String[]{"mountain", "prairie"}, new String[]{"hawk", "fox"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("mountain+prairie-hawk-elk", new String[]{"mountain", "prairie"}, new String[]{"hawk", "elk"}, false, x+=xinc, y, size));
        x = 92; y+=110;
        tile.add(TileCreatorThread.create("mountain+prairie-fox-elk-bear", new String[]{"mountain", "prairie"}, new String[]{"fox", "elk", "bear"}, false, x, y, size));
        tile.add(TileCreatorThread.create("mountain+prairie-bear-salmon", new String[]{"mountain", "prairie"}, new String[]{"bear", "salmon"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("mountain-hawk-key", new String[]{"mountain"}, new String[]{"hawk"}, true, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("mountain-hawk-key-2", new String[]{"mountain"}, new String[]{"hawk"}, true, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("mountain-elk-key", new String[]{"mountain"}, new String[]{"elk"}, true, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("mountain-elk-key-2", new String[]{"mountain"}, new String[]{"elk"}, true, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("mountain-bear-key", new String[]{"mountain"}, new String[]{"bear"}, true, x+=xinc, y, size));
        x= 153; y+=110;
        tile.add(TileCreatorThread.create("river+mountain-salmon-hawk", new String[]{"river", "mountain"}, new String[]{"salmon", "hawk"}, false, x, y, size));
        tile.add(TileCreatorThread.create("river+mountain-salmon-bear", new String[]{"river", "mountain"}, new String[]{"salmon", "bear"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("river+mountain-salmon-bear-hawk", new String[]{"river", "mountain"}, new String[]{"salmon", "bear", "hawk"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("river+mountain-hawk-elk", new String[]{"river", "mountain"}, new String[]{"hawk", "elk"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("river+mountain-hawk-bear", new String[]{"river", "mountain"}, new String[]{"hawk", "bear"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("river+mountain-bear-elk", new String[]{"river", "mountain"}, new String[]{"bear", "elk"}, false, x+=xinc, y, size));
        x = 92; y+=110;
        tile.add(TileCreatorThread.create("river-salmon-key", new String[]{"river"}, new String[]{"salmon"}, true, x, y, size));
        tile.add(TileCreatorThread.create("river-hawk-key", new String[]{"river"}, new String[]{"hawk"}, true, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("river-hawk-key-2", new String[]{"river"}, new String[]{"hawk"}, true, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("river-bear-key", new String[]{"river"}, new String[]{"bear"}, true, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("river-bear-key-2", new String[]{"river"}, new String[]{"bear"}, true, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("forest+wetland-salmon-elk-hawk", new String[]{"forest", "wetland"}, new String[]{"salmon", "elk", "hawk"}, false, x+=xinc, y, size));
        tile.add(TileCreatorThread.create("forest+wetland-fox-hawk", new String[]{"forest", "wetland"}, new String[]{"fox", "hawk"}, false, x+=xinc, y, size));


    }
}

package Entities;

public class StarterTile {
    public HabitatTiles down_left = null;
    public HabitatTiles down_right = null;
    public HabitatTiles up = null;
    
    public StarterTile(HabitatTiles tile_down_left, HabitatTiles tile_up, HabitatTiles tile_down_right){
        down_left = tile_down_left;
        down_right = tile_down_right;
        up = tile_up;
    }
}

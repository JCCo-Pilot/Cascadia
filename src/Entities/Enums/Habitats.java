package Entities.Enums;

public enum Habitats {
    MOUNTAIN,
    FOREST,
    PRAIRIE,
    WETLAND,
    RIVER;

    public static Habitats toHabitat(String s){
        switch(s.toLowerCase()){
            case "mountain":
            return MOUNTAIN;
            case "forest":
            return FOREST;
            case "prairie":
            return PRAIRIE;
            case "wetland":
            return WETLAND;
            case "river":
            return RIVER;
        }
        return null;
    }
}
package Entities.Enums;

public enum CardAnimals {
    BEAR, 
    ELK,
    SALMON,
    HAWK,
    FOX;

    public static CardAnimals StringToAnimal(String s){
        switch(s.toLowerCase()){
            case "bear":
                return BEAR;
            case "elk":
                return ELK;
            case "salmon":
                return SALMON;
            case "hawk":
                return HAWK;
            case "fox":
                return FOX;
            default:
                return null;   
        }
    }
}

package Entities;

import java.util.HashMap;
import java.util.HashSet;

public class FoxCard implements ScoringCard{
    
    CardAnimals animal = CardAnimals.FOX;
    CardTypes cardLetter;

    public FoxCard(CardTypes letter){
        this.cardLetter = letter;
    }

    @Override
    public Integer score(Player p) {
        //return this.score(p.getGraph());
        return -1
    }
}
package Entities;

import java.util.HashMap;
import java.util.HashSet;

public class HawkCard implements ScoringCard{
    
    CardAnimals animal = CardAnimals.HAWK;
    CardTypes cardLetter;

    public HawkCard(CardTypes letter){
        this.cardLetter = letter;
    }

    @Override
    public Integer score(Player p) {
        //return this.score(p.getGraph());
        return -1
    }
}
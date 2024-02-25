package Entities.WildlifeScoringCards;

import java.util.HashMap;
import java.util.HashSet;

import Entities.HabitatGraph;
import Entities.HabitatTiles;
import Entities.Player;
import Entities.Enums.CardAnimals;
import Entities.Enums.CardTypes;

public class FoxCard implements ScoringCard{
    
    final CardAnimals animal = CardAnimals.FOX;
    CardTypes cardLetter;

    public FoxCard(CardTypes letter){
        this.cardLetter = letter;
    }

    @Override
    public Integer score(Player p) {
        //return this.score(p.getGraph());
        return -1;
    }

}
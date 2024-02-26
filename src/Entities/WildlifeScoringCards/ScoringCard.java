package Entities.WildlifeScoringCards;

import java.util.HashMap;
import java.util.HashSet;

import Entities.Player;
import Entities.Enums.CardAnimals;

public interface ScoringCard {
    
    public Integer score(Player p);

    public CardAnimals getAnimal();
    
}

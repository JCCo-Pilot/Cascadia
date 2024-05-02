package Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

import Entities.Enums.Habitats;
import Entities.WildlifeScoringCards.ScoringCard;

public class Scorer implements Runnable{
    private Player player = null;
    private Habitats habitat = null;
    private ScoringCard scoringCard = null;

    private static HashSet<Thread> wildlifeThreads = new HashSet<Thread>();
    private static HashSet<Thread> habitatThreads = new HashSet<Thread>();

    public Scorer(Player p, Habitats h){
        player = p;
        habitat = h;
    }

    public Scorer(Player p, ScoringCard card){
        player = p;
        scoringCard = card;
    }

    public static HashMap<Integer, Player> score(ArrayList<Player> players, ArrayList<ScoringCard> scoringCards){
        for(Player p: players){//add all scores to players
            for(Habitats h: Habitats.values()){
                Scorer s = new Scorer(p, h);
                Thread t = new Thread(s);
                t.setPriority(3);
                habitatThreads.add(t);
                //s.scorePlayer();
                t.start();
            }
            for(ScoringCard card:scoringCards){
                Scorer s = new Scorer(p, card);
                Thread t = new Thread(s);
                t.setPriority(2);
                wildlifeThreads.add(t);
                //s.scorePlayer();
                t.start();
            }
        }

        while(threadsWaiting(habitatThreads)){
            
        }

        for(Habitats h: Habitats.values()){//add bonuses
            //find max group
            Integer max = 0;
            Integer secondMax = 0;
            for(Player p: players){
                if(p.getScore(h)>max){
                    max = p.getScore(h);
                }
                if(p.getScore(h)>secondMax&&p.getScore(h)<max){
                    secondMax = p.getScore(h);
                }
            }
            ArrayList<Player> playersWithMax = new ArrayList<Player>();//find players with max and second max
            ArrayList<Player> playersWithSecondMax = new ArrayList<Player>();
            for(Player p:players){
                if(p.getGraph().getLargestContiguousGroup(h)==max){
                    playersWithMax.add(p);
                }else if(p.getGraph().getLargestContiguousGroup(h)==secondMax){
                    playersWithSecondMax.add(p);
                }
            }

            if(playersWithSecondMax.size()>1){//no bonus points awarded if no ties for second last
                playersWithSecondMax = new ArrayList<Player>();
            }

            switch(players.size()){
                case 0:
                break;
                case 1:
                break;
                case 2:
                    switch(playersWithMax.size()){
                        case 1:
                            playersWithMax.get(0).setBonus(h, 2);
                        break;
                        case 2:
                            playersWithMax.get(0).setBonus(h, 1);
                            playersWithMax.get(1).setBonus(h, 1);
                        break;
                    }
                break;
                default://catches 3 and 4
                    switch(playersWithMax.size()){
                        case 1:
                            playersWithMax.get(0).setBonus(h, 3);
                            try {
                                playersWithSecondMax.get(0).setBonus(h, 1);
                            } catch (Exception e) {
                                
                            }
                        break;
                        case 2:
                            playersWithMax.get(0).setBonus(h, 2);
                            playersWithMax.get(1).setBonus(h, 2);
                        break;
                        default://3 and 4
                            for(Player p: playersWithMax){
                                p.setBonus(h, 1);
                            }
                        break;
                            
                    }
                break;
            }
        }

        while(threadsWaiting(wildlifeThreads)){

        }
        
        /*ArrayList<Player> finalPlayers = new ArrayList<Player>();
        Collections.copy(finalPlayers,players);*/
        HashMap<Integer, Player> finalOrder = new HashMap<Integer, Player>();
        /*for(int i = 1; i<=players.size(); i++){
            finalOrder.put((Integer)i, finalPlayers.remove(finalPlayers.indexOf(Collections.max(finalPlayers))));
        }*/
        return finalOrder;
    }

    @Override
    public void run() {
        /*try {
            this.scorePlayer();
        } catch (Exception e) {
            if(habitat==null){
                PrintTester.print("Score "+player.getName()+", "+scoringCard.getAnimal());
            }else{
                PrintTester.print("Score "+player.getName()+", "+habitat);
            }
        }*/
        this.scorePlayer();
    }

    public void scorePlayer(){
        if(habitat!=null){
            player.setScore(habitat, player.getGraph().getLargestContiguousGroup(habitat));
        }else if(scoringCard!=null){
            player.setScore(scoringCard.getAnimal(), scoringCard.score(player));
        }
    }

    public static Boolean threadsWaiting(HashSet<Thread> set){
        for(Thread t:set){
            if(t.isAlive()){
                return true;
            }
        }
        return false;
    }
    
}
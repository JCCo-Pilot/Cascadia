package Components;
import Entities.*;
import java.util.*;
import javax.swing.*;
import EventAndListener.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import static java.lang.System.*;
public class PickArea {
    private ArrayList<WildlifeTokens>tokens = new ArrayList<>();
    public PickArea(){
        createTokens();
        out.println(tokens);
    }
    private void createTokens(){
        for (int i =0;i<20;i++){
            tokens.add(new WildlifeTokens(0));
            tokens.add(new WildlifeTokens(1));
            tokens.add(new WildlifeTokens(2));
            tokens.add(new WildlifeTokens(3));
        }
    }
    private void sumChecker(){
        int numBear = 0;
        int numFox = 0;
        int numElk = 0;
        int numSalmon = 0;
        for (int i = 0; i<tokens.size();i++){
            if ()
        }
    }
}

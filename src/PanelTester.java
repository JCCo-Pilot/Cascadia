import java.util.*;
import javax.swing.*;

import Components.*;
import EventAndListener.*;
import Panels.*;
import static java.lang.System.*;
public class PanelTester extends JFrame{
    private static final int  WIDTH =1600;
    private static final int HEIGHT = 900;
    public PanelTester(String s){
        super(s);
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        EndPanel ep = new EndPanel();
        add(ep);
        setVisible(true);
    }
}

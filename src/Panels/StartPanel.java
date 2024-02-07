package Panels;
import java.util.*;
import javax.swing.*;
import EventAndListener.*;
import java.awt.*;
import java.awt.event.*;
public class StartPanel extends JPanel{
    private GameListener listener;
    public void paint(Graphics g){

    }
    public void setListener(GameListener gl){
        listener = gl;
    }
}

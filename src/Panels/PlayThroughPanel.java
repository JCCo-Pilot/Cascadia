package Panels;
import java.util.*;
import javax.swing.*;
import EventAndListener.*;
import MathHelper.*;
import Entities.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import Components.*;
import Entities.WildlifeScoringCards.*;
import Entities.Enums.*;
import static java.lang.System.*;
public class PlayThroughPanel extends JPanel implements MouseListener,ActionListener{
    private BufferedImage background;
    public PlayThroughPanel(){
        setLayout(null);
        try{
            background = ImageIO.read(new File("src/Panels/Background?MainPanelBG.png"));
        }catch(Exception e){

        }
        this.setVisible(true);
    }

    public void paint(Graphics g){
        out.println(background!=null);
        if(background!=null){
            g.drawImage(background, 0, 0, 1600,900,null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
    
}
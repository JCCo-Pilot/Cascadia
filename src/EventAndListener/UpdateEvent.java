package EventAndListener;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
public class UpdateEvent extends EventObject{
    private MouseEvent mEvent;
    private ActionEvent aEvent;
    public UpdateEvent(Object source){
        super(source);
    }
    public UpdateEvent(Object source, MouseEvent e){
        super(source);
    }
    public UpdateEvent(Object source, ActionEvent e){
        super(source);
    }
}

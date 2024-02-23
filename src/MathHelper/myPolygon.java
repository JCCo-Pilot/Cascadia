package MathHelper;

import java.awt.*;
import java.util.*;
public class myPolygon extends Polygon{
    public myPolygon(MathPoint[] mp){
        super();
        for(int i=0;i<mp.length;i++){
            super.addPoint(mp[i].xPoint, mp[i].yPoint);
        }
        
    }
}

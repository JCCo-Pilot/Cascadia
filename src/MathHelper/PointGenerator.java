package MathHelper;
import java.util.*;
public class PointGenerator {
    public PointGenerator(int x, int y, Double size){
        Double b = ((4.0 - Math.sqrt(7)) / 6) * size;
        Integer sz = (int) Math.round(size);
        Integer a = (int) Math.round(b);
        Integer[] xPoints = {x, x + sz, x + sz, x, x, x};
        Integer[] yPoints = {y, y + a, y + sz - a, y + sz, y + sz - a, y + a};
    }
}

package MathHelper;

public class MathPoint {
    public int xPoint;
    public int yPoint;
    public MathPoint(int x, int y){
        xPoint = x;
        yPoint = y;
    }

    public Double distanceTo(MathPoint p){
        int x1 = p.xPoint;
        int y1 = p.yPoint;
        int x2 = this.xPoint;
        int y2 = this.yPoint;

        Integer a = x1-x2;
        Integer b = y1-y2;

        return Math.sqrt(Math.pow(a.doubleValue(), 2)+Math.pow(b.doubleValue(), 2));

    }

    public Double distanceTo(int x, int y){
        int x1 = x;
        int y1 = y;
        int x2 = this.xPoint;
        int y2 = this.yPoint;

        Integer a = x1-x2;
        Integer b = y1-y2;

        return Math.sqrt(Math.pow(a.doubleValue(), 2)+Math.pow(b.doubleValue(), 2));

    }

    public static Double distanceBetween(int x1, int y1, int x2, int y2){
        

        Integer a = x1-x2;
        Integer b = y1-y2;

        return Math.sqrt(Math.pow(a.doubleValue(), 2)+Math.pow(b.doubleValue(), 2));

    }

    public static Double distanceBetween(MathPoint point1, MathPoint point2){
        int x1 = point1.xPoint;
        int y1 = point1.yPoint;
        int x2 = point2.xPoint;
        int y2 = point2.yPoint;

        Integer a = x1-x2;
        Integer b = y1-y2;

        return Math.sqrt(Math.pow(a.doubleValue(), 2)+Math.pow(b.doubleValue(), 2));

    }

    public boolean equals(Object o){
        MathPoint m = (MathPoint)o;
        return this.xPoint==m.xPoint&&this.yPoint==m.yPoint;
    }
    
    public String toString(){
        return xPoint +", "+yPoint;
    }
}

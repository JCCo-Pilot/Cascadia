package Entities;

import java.util.LinkedList;
import java.util.Queue;

public class PrintTester extends Thread{
    private static Queue<String> printQueue = new LinkedList<String>();
    private static PrintTester printer = new PrintTester();

    public static void print(String s){
        if(printQueue.size()>0){
            printQueue.add(s);
        }else{
            printQueue.add(s);
            printer.run();
        }
    }

    public void run(){
        while(!printQueue.isEmpty()){
            System.out.println(printQueue.remove());
        }
    }
}

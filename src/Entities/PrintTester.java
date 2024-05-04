package Entities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class PrintTester extends Thread{
    private static Queue<String> printQueue = new LinkedList<String>();
    private static PrintTester printer = new PrintTester();
    private static HashMap<String, Long> requestTimeStamps = new HashMap<String, Long>();
    private static Long threshold = (long) 10000;
    private static Boolean awake = false;

    

    public static void print(String s){
        if(awake){
            Long submissionTime = System.currentTimeMillis();
            submitPrintRequest(s, submissionTime);
        }
        
    }

    public static void submitPrintRequest(String s, Long time){
        if(requestTimeStamps.get(s)!=null&&requestTimeStamps.get(s)+threshold>time){
            
        }else{
            requestTimeStamps.put(s, time);
            addToQueue(s);
        }
    }

    public static void addToQueue(String s){
        printer.setPriority(1);
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

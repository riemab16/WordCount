package producer;

import bl.BL;
import gui.GUI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import queue.FullQueueException;
import queue.MyQueue;

public class Producer implements Runnable{

    private final MyQueue<BL> myQueue;
    private final GUI zustand;

    public Producer(MyQueue<BL> myQueue, GUI zustand) {
        this.myQueue = myQueue;
        this.zustand = zustand;
    }
    
    //Hilfe von Manuel Zitz!
    
    @Override
    public void run() {
        zustand.setToRun();
        File[] files = new File("files/").listFiles();
        for (File file : files) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String text = "";
                String line = "";
                while ((line = br.readLine()) != null) {
                    text += line;
                }
                BL book = new BL(text, file.getName());
                synchronized (myQueue) {
                    try {
                        myQueue.put(book);
                        myQueue.notifyAll();
                    } catch (FullQueueException ex) {  //FullQueueException funktioniert nicht!
                        try{
                            zustand.setToWait();
                            myQueue.wait();
                            zustand.setToRun();
                        }
                        catch(Exception e){
                        }
                    }
                }
            } catch (Exception ex) {

            }
        }
    }
}

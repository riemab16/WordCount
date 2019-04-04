package consumer;

import bl.BL;
import gui.GUI;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import queue.EmptyQueueException;
import queue.MyQueue;

public class Consumer extends Thread{
 private final MyQueue<BL> queue;
    private final GUI zustand;
    
    public Consumer(MyQueue<BL> queue, GUI zustand) {
        this.queue = queue;
        this.zustand = zustand;
    }
    
    @Override
    public void run() {
        zustand.setToRun();
        BL book = null;
        while (true) {
            synchronized (queue) {
                try {
                    book = queue.get();
                    queue.notifyAll();
                } catch (EmptyQueueException ex) {
                    try {
                        zustand.setToWait();
                        queue.wait();
                        zustand.setToRun();
                    } catch (Exception e) {
                        
                    }
                    continue;
                }
            }
            
            //Hilfe von Manuel Zitz!
            
            HashMap<String, Integer> map = book.countWords();
            File f = new File("output/" + book.getFilename() + "_output");
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                for (String str : map.keySet()) {
                    if (str.matches("^[a-zA-Z0-9]+$")) {
                        bw.write(String.format("%s: %d\n", str, map.get(str)));
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}

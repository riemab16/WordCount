package producer;

import bl.BL;
import gui.GUI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import queue.MyQueue;

public class Producer implements Runnable{

    private final MyQueue<BL> queue;
    private final GUI zustand;

    public Producer(MyQueue<BL> queue, GUI zustand) {
        this.queue = queue;
        this.zustand = zustand;
    }

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
                synchronized (queue) {
                    try {
                        queue.put(book);
                        queue.notifyAll();
                    } catch (FullQueueException ex) {
                        try{
                            zustand.setToWait();
                            queue.wait();
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

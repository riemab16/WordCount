package main;

import consumer.Consumer;
import gui.GUI;
import java.awt.GridLayout;
import javax.swing.JFrame;
import producer.Producer;
import queue.MyQueue;

public class Main {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue(5);
        JFrame jf = new JFrame();
        
        jf.setLayout(new GridLayout(2,2));
        
        GUI prod1 = new GUI("prod1");
        jf.add(prod1);
        GUI prod2 = new GUI("prod2");
        jf.add(prod2);
        GUI cons1 = new GUI("cons1");
        jf.add(cons1);
        GUI cons2 = new GUI("cons2");
        jf.add(cons2);
        
        Producer producer1 = new Producer(queue, prod1);
        Producer producer2 = new Producer(queue, prod2);
        Consumer consumer1 = new Consumer(queue, cons1);
        Consumer consumer2 = new Consumer(queue, cons2);
        
        jf.setSize(400, 400);
        jf.setVisible(true);
        
        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
        
      
    }
}

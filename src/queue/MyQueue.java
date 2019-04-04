package queue;

import java.util.LinkedList;

public class MyQueue<T> {
    
    private LinkedList<T> data = new LinkedList<T>();
    private int maxSize = 3;
    
    public MyQueue(int maxSize) {
        this.maxSize = maxSize;
    }
    
     public void put(T value) throws FullQueueException{
        if(data.size() == maxSize){
            throw new FullQueueException();
        }
        else{
            data.add(value);
        }
    }
    
    public T get() throws EmptyQueueException{
        if(data.isEmpty()){
            throw new EmptyQueueException();
        }
        else{
            return data.poll();
        }
    }
    
   
}

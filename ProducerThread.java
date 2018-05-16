import java.util.Queue;
import java.util.Random;

public class ProducerThread extends Thread{
	private Queue<Integer> queue;
    private int queueSize = 2 ;
    public static int producedItem = 0;
    public ProducerThread (Queue<Integer> queueIn){
        this.queue = queueIn;
        
    }

    public void run() {
        while(true){
            synchronized (queue) {
                while(queue.size() == queueSize){
                    System.out.println(" FULL:\n");
                    try{
                        queue.wait();   //Important
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                producedItem++;
                
                System.out.println(" producing: " + producedItem); 
                queue.add(producedItem);
                
                queue.notify();
                
                try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              
            }
        }
    }
}

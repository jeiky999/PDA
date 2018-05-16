import java.util.Queue;

public class ConsumerThread extends Thread{
	private Queue<Integer> queue;
    private int queueSize = 2;

    public ConsumerThread(Queue<Integer> queueIn){      
        this.queue = queueIn;
    }

    public void run() {
        while(true){
            synchronized (queue) {
                while(queue.isEmpty()){
                    System.out.println(" Empty: \n");
                    try {
                        queue.wait();  
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                System.out.println(" consuming: " + queue.remove());
                
                ProducerThread.producedItem--;
                
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

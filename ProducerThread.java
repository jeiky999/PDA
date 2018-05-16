import java.util.concurrent.Semaphore;

public class ProducerThread extends Thread{
	
	Semaphore semaphoreProducer;
	Semaphore semaphoreConsumer;
	
	public ProducerThread(Semaphore semaphoreProducer, Semaphore semaphoreConsumer){
		this.semaphoreProducer = semaphoreProducer;
		this.semaphoreConsumer = semaphoreConsumer;
	}
	
	public void run(){
		while(true){
			try{
				semaphoreProducer.acquire();
				System.out.println("Produs: " + Main.itemProduced++);
				semaphoreConsumer.release();
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

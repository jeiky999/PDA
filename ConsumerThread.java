import java.util.concurrent.Semaphore;

public class ConsumerThread extends Thread{
		Semaphore semaphoreConsumer;
		Semaphore semaphoreProducer;
		
		public ConsumerThread( Semaphore semaphoreConsumer, Semaphore semaphoreProducer){
			this.semaphoreConsumer = semaphoreConsumer;
			this.semaphoreProducer = semaphoreProducer;
		}
		
		public void run(){
			while(true){
				try{
					semaphoreConsumer.acquire();
					System.out.println("Consumat: " + Main.itemProduced);
					Main.itemProduced--;
					semaphoreProducer.release();
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

import java.util.concurrent.Semaphore;

public class Main {

	public static int itemProduced = 0;

	public static void main(String[] args) {
        Semaphore semProd=new Semaphore(1);
        Semaphore semCon=new Semaphore(0);
        
    ProducerThread producer=new ProducerThread(semProd,semCon);
    ConsumerThread consumer=new ConsumerThread(semCon,semProd);
   
     producer.start();
     consumer.start();

 }

}

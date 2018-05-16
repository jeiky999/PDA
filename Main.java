import java.util.LinkedList;
import java.util.Queue;

public class Main {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<Integer> queue = new LinkedList<>();

        ProducerThread producerThread = new ProducerThread(queue);
        ConsumerThread consumerThread = new ConsumerThread(queue);

        producerThread.start();  
        consumerThread.start();
	}

}

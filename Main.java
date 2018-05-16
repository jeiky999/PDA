import mpi.*;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int buffer[] = new int[2];


        if(rank!=0)
        {
            int number =(int)(Math.random()*10);
            buffer[0] = rank;
            buffer[1] = number;
            MPI.COMM_WORLD.Send(buffer, 0, 2, MPI.INT, 0, 0);
        }
        if(rank==0)
        {
        	int number =(int)(Math.random()*10);
            int MASTER=0;
            for(int i = 1; i < size; i++)
            {
                MPI.COMM_WORLD.Recv(buffer, 0, 2, MPI.INT, i, 0);
                if(buffer[1] > number)
                {
                    number = buffer[1];
                    MASTER = i;
                }
                else if((buffer[1] == number) && (i > MASTER))
                {
                    MASTER = i;
                }
            }
            System.out.println("Leader proces is" + " " + MASTER + " " + "with value" + number);
        }
        MPI.Finalize();
    }
}

import mpi.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    int[] myVect = {12, 34, 45, 34, 25, 4, 6, 23, 65, 23, 55, 6, 12, 32, 45, 33, 87, 9};
	    int procNr = 4;
	    int sum = 0;
		int MASTER = 0;
		int lenght = myVect.length / procNr;
		int[] Master = new int[lenght];
		int[] Slave = new int[1];
		int min;
		int max;
		
		MPI.Init(args);
		
		if(MPI.COMM_WORLD.Rank() == MASTER){
			for(int i = 0; i < lenght; i++){
				sum += myVect[i];
			}
			System.out.println("Partial sum in proces " + MPI.COMM_WORLD.Rank() + " is:" + sum);
			
			for(int i = 1; i < procNr; i++){
				min = i * lenght;
				max = min + lenght;
				int j = 0;
				for(int k = min; k < max; k++){
					Master[j] = myVect[k];
					j++;
				}
			MPI.COMM_WORLD.Send(Master, 0, lenght, MPI.INT, i, 0);
			}
		}
		
		if(MPI.COMM_WORLD.Rank() != MASTER){

			int partSum = 0;
			MPI.COMM_WORLD.Recv(Master, 0, lenght, MPI.INT, 0, 0);
			
			for(int k = 0; k < lenght; k++){
				partSum += Master[k];
			}
			System.out.println("Partial sum in proces " + MPI.COMM_WORLD.Rank() + " is:" + partSum);
			
			MPI.COMM_WORLD.Send(Slave, 0, 1, MPI.INT, MASTER, 0);
		}
		
		if (MPI.COMM_WORLD.Rank() == MASTER) {

            for(int i = 1; i < procNr; i++)
            {
                MPI.COMM_WORLD.Recv(Slave, 0, 1, MPI.INT, i, 0);
                sum += Slave[0];
            }
            System.out.println("Total sum is:" + sum);
        }
	}

}

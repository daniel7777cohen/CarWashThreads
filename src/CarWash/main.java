package CarWash;
//daniel cohen 203850029
//ben efrat 305773806
import java.util.Scanner;
import java.util.Vector;

public class main {



	public static void main(String[] args)  {
		double full_time_demo=0;//for input
		double full_time_demo2 = 0;//for calculating poisson distribution

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of washing machines");
		int N = sc.nextInt();
		System.out.println("number of washing machines are  "+ N);


		System.out.println("Enter full time for demonstration");
		full_time_demo = sc.nextDouble();
		System.out.println("full time demo is "+ full_time_demo);


		System.out.println("Enter washing time");
		double washingTime = sc.nextDouble();
		System.out.println("washing time is "+ washingTime);

		System.out.println("Enter time of car arrival ");
		double carArrival = sc.nextDouble();
		System.out.println("time of car arrival is  "+ carArrival);

		//save beginning time of program
		final long timeStarted = System.currentTimeMillis();
		MinheretHazman washingMachine = new MinheretHazman(N,washingTime,timeStarted);

		full_time_demo2 = (long) (-((double)java.lang.Math.log(Math.random() ) / ( (double) 1 / (full_time_demo) ) ));
		Vector<Thread> victor = new Vector<Thread>();
		System.out.println("actual demonstration time is "+ full_time_demo2);

		while( !(System.currentTimeMillis()- timeStarted > (full_time_demo2*1000) ))//while demonstration time isnt over, create threads
		{
			try {			
				Thread.sleep((long) (-((double)java.lang.Math.log(Math.random() ) / ( (double) 1 / (carArrival) ) )*1000));	
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread T = new Thread() {
				public void run() {	
					washingMachine.enterWashingCompany();
				}

			};
			T.start();
			victor.add(T);

		}
		for(int i=0;i<victor.size();i++)
			try {
				victor.elementAt(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}




		double waitingAverage = (double) (washingMachine.count.sum/ washingMachine.c);//calculate waiting time
		System.out.println("total number of washed cars was " + washingMachine.c);
		//System.out.println("Average time of washing a car was " + washingMachine.avgwashing/ washingMachine.c);
		System.out.println("Average time of waiting is " + (double)(waitingAverage/1000));

		//System.out.println("full time of demonstration was  "+full_time_demo2);



	}
}

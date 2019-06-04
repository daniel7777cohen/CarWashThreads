package CarWash;

//daniel cohen 203850029
//ben efrat 305773806

public class MinheretHazman {
	MySemaphore count;
	int c = 0;
	double timer3 =0;
	double washingTime;
	double avgwashing;
	private long timeStarted;
	public MinheretHazman(int machines, double washingTime, long time_started)
	{
		this.timeStarted = time_started;
		this.washingTime = washingTime;
		count = new MySemaphore(machines); //semaphore for size of washing machines

	}

	public void wash()
	{
		synchronized(this) {
			System.out.print(Thread.currentThread().getName());
			System.out.println(	" is being washed ");
			System.out.println(	"time passed "+(System.currentTimeMillis()-timeStarted));
		}



		try {
			/* sleep to simulate washing activity... */
			synchronized(this) {
				timer3 = -((double)java.lang.Math.log(Math.random() ) / ( (double) 1 / (washingTime) ) ) ;
				avgwashing += timer3;
			}
			Thread.sleep((long) (timer3*1000));
		} catch (InterruptedException e) {}
		synchronized(this) {
			System.out.print(Thread.currentThread().getName());
			System.out.println(	" is leaving the waching machine ");
			System.out.println(	"time passed "+(System.currentTimeMillis()-timeStarted));
		}
	}


	//washing
	public void enterWashingCompany() {
		{
			synchronized(this) {
				System.out.print(Thread.currentThread().getName());
				System.out.println(	" entered the washing company ");
				System.out.println(	"time passed "+(System.currentTimeMillis()-timeStarted));
			}

			if(count.availableTickets <=0)
				System.out.println(Thread.currentThread().getName()+" is waiting...");
			count.down();

			//washing action
			wash();
			synchronized(this) {
				c++;//counter for number of cars
			}

			count.up();//right after a car leaves the washing machine - releases the key for a new car

		}

	}






}
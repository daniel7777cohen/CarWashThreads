package CarWash;

//daniel cohen 203850029
//ben efrat 305773806

public class MySemaphore {
	long timer ,timer2;
	private final int MAX_TICKETS;
	int availableTickets;
	double sum =0;
	public MySemaphore(int tickets) {
		availableTickets = MAX_TICKETS = tickets;
	}

	public synchronized void up() {
		availableTickets++;
		if (availableTickets > MAX_TICKETS)
			availableTickets = MAX_TICKETS;
		notifyAll();
	}

	public synchronized void down() { 
		while (availableTickets <= 0) {

			try {
				timer = System.currentTimeMillis();
				wait();
				timer2 = System.currentTimeMillis()-timer;
				sum+= timer2;
			} catch (Exception e) {}		
		}
		availableTickets--;
	}
}

package p2;

/**
 * This class implements the doorman's part of the
 * Barbershop thread synchronization example.
 */
public class Doorman extends Thread {

	private CustomerQueue queue;
	private Gui gui;
	private boolean threadRunning;

	/**
	 * Creates a new doorman.
	 * @param queue		The customer queue.
	 * @param gui		A reference to the GUI interface.
	 */
	public Doorman(CustomerQueue queue, Gui gui) { 
		this.queue = queue;
		this.gui = gui;
		this.threadRunning = true;
	}

	/**
	 * Starts the doorman running as a separate thread.
	 */
	public void startThread() {
		threadRunning = true;
		start();
	}

	/**
	 * Stops the doorman thread.
	 */
	public void stopThread() {
		// Incomplete
		threadRunning = false;
	}

	// Add more methods as needed
	@Override
	public void run() {
		gui.println("Doorman is ready to push customer into the barbershop!");
		while (threadRunning) {
			try {
				sleep(Globals.doormanSleep);
			} catch (InterruptedException e) {
				System.out.println("InterruptedException in Doorman.run()");
			}
			queue.addCustomerToQueue(new Customer());
			gui.println("New customer entered the barbershop!");
		}
		System.out.println("Doorman is done for the day!");
	}


}

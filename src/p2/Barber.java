package p2;

/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.
 */
public class Barber extends Thread {

	private CustomerQueue queue;
	private Gui gui;
	private int position;
	private boolean threadRunning;

	/**
	 * Creates a new barber.
	 * @param queue		The customer queue.
	 * @param gui		The GUI.
	 * @param pos		The position of this barber's chair
	 */
	public Barber(CustomerQueue queue, Gui gui, int pos) { 
		this.queue = queue;
		this.gui = gui;
		this.position = pos;
		this.threadRunning = true;
	}

	/**
	 * Starts the barber running as a separate thread.
	 */
	public void startThread() {
		threadRunning = true;
		start();
	}

	/**
	 * Stops the barber thread.
	 */
	public void stopThread() {
		// Incomplete
		threadRunning = false;
	}

	@Override
	public void run() {
		gui.println("Barber #" + position + " is ready to receive customers!");
		while (threadRunning) {

			// sleep some time
			// choose customer
			// cut customer
			// done customer
			// outta my loop

			try {
				gui.barberIsSleeping(position);
				gui.println("Barber #" + position + " is day dreaming");
				sleep(Globals.barberSleep);
			} catch (InterruptedException e) {
				System.out.println("Useless barberBro cant cut yo");
			}

			gui.barberIsAwake(position);
			Customer c = queue.getCustomerFromQueue();
			gui.fillBarberChair(position, c);
			gui.println("Barber #" + position + " is now cutting customer #" + c.getCustomerID());

			try {
				sleep(Globals.barberWork);
				gui.println("Barber #" + position + " is now done cutting customer #" + c.getCustomerID());
				gui.emptyBarberChair(position);
			} catch (InterruptedException e) {
				System.out.println("Useless barberBro cant cut yo");
			}
		}
		System.out.println("Barber #" + position + " is done for the day!");
	}

	// Add more methods as needed
}


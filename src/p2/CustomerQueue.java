package p2;

import java.util.ArrayList;

/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {

	private Customer[] buffer;
	private Gui gui;

	// Pointer that represents writing position in buffer
	private int pointer = 0;

	// Tells how many customers in queue
	private int customersInQueue = 0;

	/**
	 * Creates a new customer queue.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */
    public CustomerQueue(int queueLength, Gui gui) {
		buffer = new Customer[queueLength];
		this.gui = gui;
	}

	// Add more methods as needed

	public void addCustomerToQueue(Customer toAdd) {
		synchronized (this) {
			while (customersInQueue == buffer.length) {
				try {
					gui.println("Waiting room full, waiting for free space");
					wait();
				} catch (InterruptedException e) {
					System.out.println("InterruptedException in addCustomersToQueue");
				}
			}

			buffer[pointer] = toAdd;
			gui.fillLoungeChair(pointer, toAdd);
			pointer = (pointer + 1) % buffer.length;
			customersInQueue++; // difference
			notifyAll();
		}
	}

	public Customer getCustomerFromQueue()  {
		synchronized (this) {
			while (customersInQueue == 0) {
				try {
					gui.println("Waiting room empty, waiting for customers");
					wait();
				} catch (InterruptedException e) {
					System.out.println("InterruptedException in getCustomersFromQueue");
				}
			}
			Customer c;
			int customerPosition = (pointer + (buffer.length - customersInQueue)) % buffer.length;
			c = buffer[customerPosition];
			gui.emptyLoungeChair(customerPosition);
			customersInQueue--;
			notifyAll();
			return c;
		}
	}

	public int size() {
		synchronized (this) {
			return customersInQueue;
		}
	}
}

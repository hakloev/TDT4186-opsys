package p3;
/**
 * Created by berg on 13/04/15.
 */
public class IO {

    private Queue ioQueue;
    private Statistics stats;
    private Gui gui;
    private Process currentProcess = null;
    private long avgTime;

    public IO(Queue ioQueue, Statistics stats, Gui gui, long avgTime) {
        this.ioQueue = ioQueue;
        this.stats = stats;
        this.gui = gui;
        this.avgTime = avgTime;
    }


    /**
     * This method is called when a discrete amount of time has passed.
     * @param timePassed	The amount of time that has passed since the last call to this method.
     */
    public void timePassed(long timePassed) {
        stats.memoryQueueLengthTime += ioQueue.getQueueLength()*timePassed;
        if (ioQueue.getQueueLength() > stats.memoryQueueLargestLength) {
            stats.memoryQueueLargestLength = ioQueue.getQueueLength();
        }
    }

    // Does not change the clock time, needs to calcucate the time left in IO and add it to the event in the eventQueue

    public Process runIO() {
        if (ioQueue.isEmpty()) {
            currentProcess = null;
        }

        currentProcess = (Process) ioQueue.removeNext();


        gui.setIoActive(currentProcess); // gui
        System.out.println("-- [DEBUG] END_IO Event created");


        return currentProcess;

    }

    public Process stopIO(long clock) {
        gui.setIoActive(null); // gui

        Process temp = currentProcess;
        currentProcess = null;
        return temp;
    }


    public Process getCurrentProcess() {
        return currentProcess;
    }

    public boolean isIdle() {
        return (this.currentProcess == null);
    }


    public void insertQueue(Process process) {
        ioQueue.insert(process);
    }

    public long getRandomIoTime() {
        long rand = (long) (2 * Math.random() * avgTime);
        long result = 1 + rand;

        return result;
    }

}

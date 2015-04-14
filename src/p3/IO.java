package p3;
/**
 * Created by berg on 13/04/15.
 */
public class IO {

    private Queue ioQueue;
    private Statistics stats;
    private Gui gui;
    private Process currentProcess = null;
    private EventQueue eventQueue;
    private long timeAvg;

    public IO(Queue ioQueue, Statistics stats, Gui gui, EventQueue eventQueue, long timeAvg) {
        this.ioQueue = ioQueue;
        this.stats = stats;
        this.gui = gui;
        this.eventQueue = eventQueue;
        this.timeAvg = timeAvg;
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

    public void runIO(Process p, long clock) {
        System.out.println("-- [DEBUG][PID: " +p.getProcessId() + "] Run IO");
        if (this.currentProcess == null) {
            this.currentProcess = p;
            gui.setIoActive(p);
        }
        else {
            this.ioQueue.insert(p);

        }

        System.out.println("-- [DEBUG] END_IO Event created");
        eventQueue.insertEvent(new Event(Constants.END_IO, getRandomIoTime()));

    }

    public void stopIO(long clock) {

        if (!this.ioQueue.isEmpty()){
            this.currentProcess = (Process) this.ioQueue.removeNext();
        }
        else {
            this.currentProcess = null;
        }

        this.gui.setIoActive(this.currentProcess);
    }


    public Process getCurrentProcess() {
        return currentProcess;
    }

    public long getRandomIoTime() {
        long rand = (long) (2 * Math.random() * timeAvg);
        long result = 1 + rand;

        return result;
    }

}

package p3;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

/**
 * Created by berg on 13/04/15.
 */
public class IO {

    private Queue ioQueue;
    private Gui gui;
    private Process currentProcess = null;
    private long avgTime;

    public IO(Queue ioQueue, Gui gui, long avgTime) {
        this.ioQueue = ioQueue;
        this.gui = gui;
        this.avgTime = avgTime;
    }


    /**
     * This method is called when a discrete amount of time has passed.
     * @param timePassed	The amount of time that has passed since the last call to this method.
     */
    public void timePassed(long timePassed) {
        if (currentProcess == null) {
            Statistics.ioTimeSpentIdle += timePassed;
        }
        Statistics.ioQueueLengthTime += ioQueue.getQueueLength()*timePassed;
        if (ioQueue.getQueueLength() > Statistics.ioQueueLargestLength) {
            Statistics.ioQueueLargestLength = ioQueue.getQueueLength();
        }
    }

    // Does not change the clock time, needs to calcucate the time left in IO and add it to the event in the eventQueue

    public Process runIO() {
        if (ioQueue.isEmpty()) {
            currentProcess = null;
        }
        else {
            currentProcess = (Process) ioQueue.removeNext();
            System.out.println("-- [DEBUG][PID: " + currentProcess.getProcessId() + "] Fetching IO");
        }
        gui.setIoActive(currentProcess); // gui

        return currentProcess;


    }

    public Process stopIO() {
        gui.setIoActive(null); // gui

        Process temp = currentProcess;
        System.out.println("-- [DEBUG][PID: " + currentProcess.getProcessId() + "] Ending IO");
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
        Statistics.avgTimesInIOQueue += 1;
    }
}

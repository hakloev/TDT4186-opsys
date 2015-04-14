package p3;
/**
 * Created by berg on 13/04/15.
 */
public class CPU {

    private Queue cpuQueue;
    private Statistics stats;
    private Process currentProcess = null;


    public CPU(Queue cpuQueue, Statistics stats) {
        this.cpuQueue = cpuQueue;
        this.stats = stats;

    }

    public void addProcess(Process process) {
        cpuQueue.insert(process);
    }

    /*
    public void work() {
        currentProcess = (Process) cpuQueue.getNext();

        //Prosessere event, dette tar x tid, basert på cpuTimeNeeded i process klassen.
        //Sjekke om shit skal ha IO, sende til IO. Når IO er ferdig, dytt elementet bakerst i CPU køen.
        //Oppdatere stats
        stats.nofCompletedProcesses += 1;
        cpuQueue.removeNext();
        currentProcess = null;
    }
    */

    public boolean isIdle() {
        return (currentProcess == null);
    }

    /**
     * This method is called when a discrete amount of time has passed.
     * @param timePassed	The amount of time that has passed since the last call to this method.
     */
    public void timePassed(long timePassed) {
        stats.memoryQueueLengthTime += cpuQueue.getQueueLength()*timePassed;
        if (cpuQueue.getQueueLength() > stats.memoryQueueLargestLength) {
            stats.memoryQueueLargestLength = cpuQueue.getQueueLength();
        }
    }
}

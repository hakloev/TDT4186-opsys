package p3;
/**
 * Created by berg on 13/04/15.
 */
public class CPU {

    private Queue cpuQueue;
    private Statistics stats;
    private Process currentProcess = null;
    private Gui gui;

    public CPU(Queue cpuQueue, Statistics stats, Gui gui) {
        this.cpuQueue = cpuQueue;
        this.stats = stats;
        this.gui = gui;

    }
    public void addProcess(Process process) {
        cpuQueue.insert(process);
    }

    public Process loadProcess() {
        currentProcess = (Process) cpuQueue.getNext();
        cpuQueue.removeNext();
        gui.setCpuActive(currentProcess);
        return currentProcess;

    }
    public Process stopProcess() {
        Process p = currentProcess;
        currentProcess = null;
        gui.setCpuActive(null);
        return p;

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

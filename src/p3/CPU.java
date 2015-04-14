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
        System.out.println("-- [DEBUG][PID: " + process.getProcessId() + "] Process added to CPU queue");
        cpuQueue.insert(process);
    }
    public Process getCurrentProcess() {
        System.out.println("-- [DEBUG][PID: " + currentProcess.getProcessId() + "] Someone is requesting current process from CPU");
        return currentProcess;
    }

    public Process loadProcess() {
        if (cpuQueue.isEmpty()) {
            return null;
        }
        currentProcess = (Process) cpuQueue.removeNext();
        gui.setCpuActive(currentProcess);
        System.out.println("-- [DEBUG][PID: " + currentProcess.getProcessId() + "] Loading process from CPU queue into CPU");
        return currentProcess;

    }
    public Process stopProcess() {
        Process p = currentProcess;
        System.out.println("-- [DEBUG][PID: " + currentProcess.getProcessId() + "] Stopping active CPU process");
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
        System.out.println("-- [DEBUG] CPU is idle: " + (currentProcess == null));
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

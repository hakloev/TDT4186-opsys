package p3;
/**
 * Created by berg on 13/04/15.
 */
public class CPU {

    private Queue cpuQueue;
    private Process currentProcess = null;
    private Gui gui;

    public CPU(Queue cpuQueue, Gui gui) {
        this.cpuQueue = cpuQueue;
        this.gui = gui;

    }
    public void addProcess(Process process) {
        System.out.println("-- [DEBUG][PID: " + process.getProcessId() + "] Added process to CPU queue");
        Statistics.avgTimesInCpuQueue += 1;
        cpuQueue.insert(process);
    }
    public Process getCurrentProcess() {
        System.out.println("-- [DEBUG][PID: " + currentProcess.getProcessId() + "] Someone is requesting the current process from CPU");
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
        System.out.println("-- [DEBUG][PID: " + currentProcess.getProcessId() + "] Stopping process in CPU");
        currentProcess = null;
        gui.setCpuActive(null);
        return p;

    }

    public boolean isIdle() {
        return (currentProcess == null);
    }

    /**
     * This method is called when a discrete amount of time has passed.
     * @param timePassed	The amount of time that has passed since the last call to this method.
     */
    public void timePassed(long timePassed) {
        if (currentProcess == null) {
            Statistics.cpuTimeSpentIdle += timePassed;
        }
        Statistics.cpuQueueLengthTime += cpuQueue.getQueueLength()*timePassed;
        if (cpuQueue.getQueueLength() > Statistics.cpuQueueLargestLength) {
            Statistics.cpuQueueLargestLength = cpuQueue.getQueueLength();
        }
    }
}

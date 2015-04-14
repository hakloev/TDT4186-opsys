/**
 * Created by berg on 13/04/15.
 */
public class CPU {

    Queue cpuQueue;
    Queue IOQueue;
    Statistics stats;

    public CPU(Queue cpuQueue, Queue IOQueue, Statistics stats) {
        this.cpuQueue = cpuQueue;
        this.IOQueue = IOQueue;
        this.stats = stats;

    }
    public void addProcess(Process process) {
        cpuQueue.insert(process);
    }
    public void work() {
        Process currentElement = (Process) cpuQueue.getNext();

        //Prosessere event, dette tar x tid, basert på cpuTimeNeeded i process klassen.
        //Sjekke om shit skal ha IO, sende til IO. Når IO er ferdig, dytt elementet bakerst i CPU køen.
        //Oppdatere stats
        stats.nofCompletedProcesses += 1;
        cpuQueue.removeNext();
    }
}

package p3;

/**
 * This class contains a lot of public variables that can be updated
 * by other classes during a simulation, to collect information about
 * the run.
 */
public class Statistics
{
	/** The number of processes that have exited the system */
	public long nofCompletedProcesses = 0;
	/** The number of processes that have entered the system */
	public long nofCreatedProcesses = 0;
	/** The total time that all completed processes have spent waiting for memory */
	public long totalTimeSpentWaitingForMemory = 0;
	/** The time-weighted length of the memory queue, divide this number by the total time to get average queue length */
	public long memoryQueueLengthTime = 0;
	/** The largest memory queue length that has occured */
	public long memoryQueueLargestLength = 0;

    // Our added stats:
    public static long nufIoOperations = 0;
    public static long nufForcedOperations = 0;
    public static long cpuQueueLengthTime = 0;
    public static long cpuQueueLargestLength = 0;
    public static long ioQueueLengthTime = 0;
    public static long ioQueueLargestLength = 0;
    public static long cpuTimeSpentIdle = 0;
    public static long ioTimeSpentIdle = 0;
    public static long avgTimesInCpuQueue = 0;
    public static long avgTimesInIOQueue = 0;
    public static long avgTimesInMemoryQueue = 0;
    public static long timeSpentInSystem = 0;
    
	/**
	 * Prints out a report summarizing all collected data about the simulation.
	 * @param simulationLength	The number of milliseconds that the simulation covered.
	 */

    public void printReport(long simulationLength) {
		System.out.println();
		System.out.println("Simulation statistics:");
		System.out.println();
		System.out.println("Number of completed processes:                                "+nofCompletedProcesses);
		System.out.println("Number of created processes:                                  "+nofCreatedProcesses);
        System.out.println("Number of (forced) process switches:                          "+nufForcedOperations);
        System.out.println("Number of processed I/O operations:                           "+nufIoOperations);
        System.out.println("Average throughput (processes per second):                    "+(float)nofCompletedProcesses/(simulationLength/1000));
        System.out.println();
        System.out.println("Total CPU time spent processing:                              "+(float)(simulationLength-cpuTimeSpentIdle) + " ms");
        System.out.println("Fraction of CPU time spent processing:                        "+(float)(simulationLength - cpuTimeSpentIdle) / (float)simulationLength * (float)100 + " %");
        System.out.println("Total CPU time spent waiting:                                 "+(float)(cpuTimeSpentIdle));
        System.out.println("Fraction of CPU time spent waiting:                           "+(float)(cpuTimeSpentIdle)/(float)simulationLength * (float) 100 + " %");
        System.out.println();
		System.out.println("Largest occuring memory queue length:                         "+memoryQueueLargestLength);
		System.out.println("Average memory queue length:                                  "+(float)memoryQueueLengthTime/simulationLength);
        System.out.println("Largest occuring CPU queue length:                            "+cpuQueueLargestLength);
        System.out.println("Average CPU queue length:                                     "+(float)cpuQueueLengthTime/simulationLength);
        System.out.println("Largest occuring I/O queue length:                            "+ioQueueLargestLength);
        System.out.println("Average I/O queue length:                                     "+(float)ioQueueLengthTime/simulationLength);
        if(nofCompletedProcesses > 0) {
			System.out.println("Average # of times a process has been placed in memory queue: "+(float) avgTimesInMemoryQueue/(float) nofCreatedProcesses);
            System.out.println("Average # of times a process has been placed in CPU queue:    "+(float) avgTimesInCpuQueue/(float) nofCreatedProcesses);
            System.out.println("Average # of times a process has been placed in I/O queue:    "+(float) avgTimesInIOQueue/(float) nofCreatedProcesses);
            System.out.println();
            System.out.println("Average time spent in system per process:                     "+(float)(timeSpentInSystem) / (float)nofCreatedProcesses + " ms");
            System.out.println("Average time spent waiting for memory per process:            "+
				totalTimeSpentWaitingForMemory/nofCompletedProcesses+" ms");
            System.out.println("Average time spent waiting for CPU per process:               "+(float)cpuQueueLengthTime / (float)nofCreatedProcesses + " ms");
            System.out.println("Average time spent processing per process:                    "+(float)(simulationLength - cpuTimeSpentIdle) / nofCreatedProcesses+ " ms");
            System.out.println("Average time spent waiting for I/O per process:               "+(float)ioQueueLengthTime/(float)nofCreatedProcesses + " ms");
            System.out.println("Average time spent in I/O per process:                        "+(float)(simulationLength - ioTimeSpentIdle) / nofCreatedProcesses + " ms");
        }
	}
}

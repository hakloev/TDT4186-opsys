package p3;
/**
 * Created by berg on 13/04/15.
 */
public class IO {

    private Queue ioQueue;
    private Statistics stats;

    public IO(Queue ioQueue, Statistics stats) {
        this.ioQueue = ioQueue;
        this.stats = stats;
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

}


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Petros
 */
public class Buffer 
{ 
    private final List<Integer> buffer = new ArrayList<>();
    private final Semaphore producerSemaphore = new Semaphore(1); // Semaphore for producer, initial value 1 (unlocked)
    private final Semaphore consumerSemaphore = new Semaphore(0); // Semaphore for consumer, initial value 0 (locked)

    public void addToBuffer(int fileNumber) throws InterruptedException 
    {
        producerSemaphore.acquire(); // Acquire the producer semaphore
        if (buffer.size() < 10) 
        {
            buffer.add(fileNumber);
            System.out.println("Files Extracted: " + fileNumber + ".xml");
            consumerSemaphore.release(); // Release the consumer semaphore
        }
        producerSemaphore.release(); // Release the producer semaphore
    }
    //Remove elements from the buffer using semaphores
    public int removeFromBuffer() throws InterruptedException 
    {
        consumerSemaphore.acquire(); // Acquire the consumer semaphore
        int fileNumber = buffer.remove(0);
        producerSemaphore.release(); // Release the producer semaphore
        return fileNumber;
    } 
}


import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Petros 
 */
public class ProducerConsumer 
{
    private static final Buffer buffer = new Buffer();
    
    public static class Producer extends Thread 
    {
        public void run() 
        {
            for (int fileNumber = 1; fileNumber <= 10; fileNumber++) 
            {
                ITStudent student = new ITStudent();
                String xmlData = student.toXmlString();
                String fileName = "student" + fileNumber + ".xml";

                try 
                {
                    FileWriter fileWriter = new FileWriter(fileName);
                    fileWriter.write(xmlData);
                    fileWriter.close();
                    buffer.addToBuffer(fileNumber);
                } 
                catch (IOException e) 
                {
                  e.printStackTrace();
                } catch (InterruptedException ex) 
                {
                    Logger.getLogger(ProducerConsumer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        // Main function
        public static void main(String[] args) 
        {
            // Start the producer and consumer threads
            new Producer().start();
            new Consumer().consumeFiles();
            
        } 
    }

}
                    
        
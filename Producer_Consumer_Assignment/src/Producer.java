
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Petros
 */
public class Producer extends Thread 
{  
    // Queue to store file numbers corresponding to the XML files
    private List<Integer> buffer = new ArrayList<>();

    public void produceFiles() 
    {
        for (int fileNumber = 1; fileNumber <= 10; fileNumber++) 
        {
            ITStudent student = new ITStudent();
            String xmlData = student.toXmlString();
            String fileName = "student" + fileNumber + ".xml";

            // Write the student information into an XML file inside a try-catch exception handler.
            try 
            {
                FileWriter fileWriter = new FileWriter(fileName);
                fileWriter.write(xmlData);
                fileWriter.close();
                
            // Insert the fileNumber into the buffer/queue
                buffer.add(fileNumber);
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
}

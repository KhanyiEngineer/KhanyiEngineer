
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Petros
 */

public class Consumer
{
 // Assume the buffer stores the file numbers corresponding to the XML files
    private List<Integer> buffer = new ArrayList<>();

    public void consumeFiles() 
    {
        for (Integer fileNumber : buffer) 
        {
            String fileName = "student" + fileNumber + ".xml";
            File file = new File(fileName);

            if (file.exists()) 
            {
                ITStudent student = parseStudentInfoFromFile(file);
                student.displayStudentInfo();

                // Clear the content of the XML file (delete the file)
                file.delete();
                // Remove the integer from the buffer
                //buffer.remove(fileNumber);
            }
        }
    }
    // Function for parsing student information from an XML file
    private ITStudent parseStudentInfoFromFile(File file) 
    {
        ITStudent student = new ITStudent();
        try 
        {
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()) 
            {
                String line = scanner.nextLine();
                if (line.contains("<Name>")) 
                {
                    student.name = extractValueFromTag(line, "Name");
                } else if (line.contains("<ID>")) 
                {
                    student.studentId = extractValueFromTag(line, "ID");
                } else if (line.contains("<Programme>")) 
                {
                    student.programme = extractValueFromTag(line, "Programme");
                } else if (line.contains("<Course name=")) 
                {
                    String courseName = extractValueFromTag(line, "Course name");
                    int mark = Integer.parseInt(extractValueFromTag(line, "Course"));
                    student.courses.add(new ITStudent.CourseMark(courseName, mark));
                }
            }
            
            scanner.close();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        return student;
    }
   // Code for extracting values from a tag
    private String extractValueFromTag(String line, String tag) 
    {
        String startTag = "<" + tag + ">";
        String endTag = "</" + tag + ">";
        return line.substring(line.indexOf(startTag) + startTag.length(), line.indexOf(endTag));
    }

    public static void main(String[] args) 
    {
        //Consumer Object and instantiation
        Consumer consumer = new Consumer();
        // Assuming the buffer contains file numbers 1 to 10 corresponding to XML files.
        consumer.buffer = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        consumer.consumeFiles();
         new Producer().start();
         //new Consumer().start();
    }
 
}

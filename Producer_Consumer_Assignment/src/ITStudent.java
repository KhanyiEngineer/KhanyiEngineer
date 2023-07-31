
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
  @author Petros
 */
public class ITStudent 
{
    String name;
    String studentId;
    String programme;
    List<CourseMark> courses = new ArrayList<>();

    static class CourseMark 
    {
        String course;
        int mark;

        CourseMark(String course, int mark) 
        {
            this.course = course;
            this.mark = mark;
        }
    }
     public ITStudent() 
     {
        generateRandomData();
    }

    private void generateRandomData() 
    {
        // Generate random students using random generation algorithm
        String[] firstNames = {"Petros", "Lungile", "Khanyisile", "Vusumuzi", "Sibusiso", "Nhlanhla"};
        String[] lastNames = {"Mnisi", "Gamedze", "Magagula", "Dlamini", "Sikhondze"};
        name = firstNames[new Random().nextInt(firstNames.length)] + " " + lastNames[new Random().nextInt(lastNames.length)];

        // Generate random student ID (8 digits)
        studentId = String.format("%08d", new Random().nextInt(10000000));

        // Generate random programme
        String[] programmes = {"Computer Science", "Information Technology", "Software Engineering"};
        programme = programmes[new Random().nextInt(programmes.length)];

        // Generate random courses and marks
        String[] courseNames = {"Mathematics", "Intergrative Programming", "Database Management", "Networking"};
        for (String courseName : courseNames) 
        {
            int mark = new Random().nextInt(41) + 60; // Random mark between 60 and 100
            courses.add(new CourseMark(courseName, mark));
        }
    }
    // Convert the student data to XML file
    public String toXmlString() 
    {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<Student>\n");
        xmlBuilder.append("  <Name>").append(name).append("</Name>\n");
        xmlBuilder.append("  <ID>").append(studentId).append("</ID>\n");
        xmlBuilder.append("  <Programme>").append(programme).append("</Programme>\n");
        xmlBuilder.append("  <Courses>\n");
        for (CourseMark course : courses) 
        {
            xmlBuilder.append("    <Course name=\"").append(course.course).append("\">").append(course.mark).append("</Course>\n");
        }
        xmlBuilder.append("  </Courses>\n");
        xmlBuilder.append("</Student>");
        return xmlBuilder.toString();
    }
    // Computation of student marks.
    double calculateAverageMark() 
    {
        int sum = courses.stream().mapToInt(course -> course.mark).sum();
        return (double) sum / courses.size();
    }
    // Determine whether a student has passed or not.
    String getPassFailStatus() 
    {
        double averageMark = calculateAverageMark();
        return averageMark >= 50 ? "Pass" : "Fail";
    }
    // Printing of student marks.
    void displayStudentInfo() 
    {
        System.out.println("Name: " + name);
        System.out.println("Student ID: " + studentId);
        System.out.println("Programme: " + programme);
        System.out.println("Courses and Marks: ");
        for (CourseMark course : courses) 
        {
            System.out.println(course.course + ": " + course.mark);
        }
        System.out.println("Average Mark: " + calculateAverageMark());
        System.out.println("Pass/Fail: " + getPassFailStatus());
        System.out.println();
    }  
}

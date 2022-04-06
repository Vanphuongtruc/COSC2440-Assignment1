
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class unitTesters{
    ArrayList<Student> students;
    ArrayList<Course> courses;
    ArrayList<StudentEnrolment> enrList;
    String[] semesters;
    public ByteArrayInputStream inputChecker;
    public ByteArrayOutputStream outputChecker;


    public ArrayList<Student> addStudents(){
        //creating a list of students
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("S3818000", "AAA", "01/01/2001"));
        students.add(new Student("S3818010", "BBB", "02/02/2001"));
        students.add(new Student("S3818020", "CCC", "03/03/2001"));
        students.add(new Student("S3818030", "DDD", "04/04/2001"));
        students.add(new Student("S3818040", "EEE", "05/05/2001"));
        return students;
    }

    public ArrayList<Course> addCourses(){
        //creating a list of courses
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("Math 1", "M01",10));
        courses.add(new Course("Literature 1", "L02",12));
        courses.add(new Course("Physics","P03",14));
        courses.add(new Course("Chemistry","C04",16));
        courses.add(new Course("History","H05",18));
        return courses;
    }

    public ArrayList<StudentEnrolment> addEnrolments(){
        //creating a list of enrolment
        ArrayList<StudentEnrolment> enrolments = new ArrayList<>();
        enrolments.add(new StudentEnrolment("2022A",students.get(0),courses.get(0)));
        enrolments.add(new StudentEnrolment("2022A",students.get(0),courses.get(3)));
        enrolments.add(new StudentEnrolment("2022B",students.get(1),courses.get(0)));
        enrolments.add(new StudentEnrolment("2022B",students.get(2),courses.get(2)));
        enrolments.add(new StudentEnrolment("2022C",students.get(4),courses.get(4)));
        return enrolments;
    }

    public String[] addSemesters(){
        //creating a list of semesters
        String[] semesters = {"2022A","2022B","2022C"};
        return semesters;
    }

    public void addEnrolment() throws Exception {
        System.out.println("addEnrolment error");

    }
@Test
    public void updateEnrolment() throws Exception{System.out.println("updateEnrolment error");}
@Test
    public void deleteEnrolment() throws Exception{System.out.println("deleteEnrolment error");}
@Test
    public void studentChecker() throws Exception{
        System.out.println("student checker error");
        ArrayList<Student> testingList = addStudents();
        String input="s3818484";
        inputChecker = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputChecker);
        assertEquals(EnrManagement.studentChecker(testingList),testingList.get(0));
    }
    @Test
    void courseChecker() throws Exception{
        System.out.println("course checker error");
        ArrayList<Course> testingList = addCourses();
        String input = "Math 1";
        inputChecker = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputChecker);
        assertEquals(EnrManagement.courseChecker(testingList).testingList.get(0));
    }

    @Test
    void semesterChecker() throws Exception{
        System.out.println("semester checker error");
        String[] testingList = addSemesters();
        String input = "2022A";
        inputChecker = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputChecker);
        assertEquals(EnrManagement.semesterCheker(testingList),"2022A");
    }
    @Test
    void multiEnrolmentHandler() throws Exception{
        System.out.println("multi enrolment handler error");
    }
    @Test
    public void singleEnrolmentHandler() throws Exception{
        System.out.println("single enrolment handler error");
    }
    @Test
    public void courseHandler() throws Exception{
        System.out.println("course handler error");
    }
    @Test
    public void semesterHandler() throws Exception{
        System.out.println("semester handler error");
    }
}
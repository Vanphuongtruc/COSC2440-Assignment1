public class Enrollment {
    private String semester;
    private Student students;
    private Course courses;

    public Enrollment(String sem, Student stu, Course crs)
    {
        this.semester = sem;
        this.students = stu;
        this.courses = crs;
    }
    //getters
    public String getSemester(){return semester;}
    public Student getStudents(){return students;}
    public Course getCourses(){return courses;}
    //setters
    public void setSemester(String sem){ this.semester = sem;}
    public void setStudents(Student stu){this.students = stu;}
    public void setCourses(Course crs){this.courses = crs;}

    public String toString() {
        return students.getName() + "(" +
                students.getID() + ") enrolled in" +
                courses.getName() + " - semester" + this.getSemester();
    }
    public String toCSV() {
        return students.toCSV() + ", "
                + courses.toCSV() + ", "
                + this.getSemester();
    }
}

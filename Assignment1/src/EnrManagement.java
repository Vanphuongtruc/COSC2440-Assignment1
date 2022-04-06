import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.util.*;

public class EnrManagement {
}

interface ManagementSystem{
    static void addEnrolment(ArrayList<StudentEnrolment>enrList,
                             ArrayList<Student> students,
                             ArrayList<Course> courses,
                             String[] semesters){
        boolean checker = true;
        Student addStudent = null;
        Course addCourse = null;

        //check if input exist
        while (!checker) {
            Student testStu = studentChecker(students);
            Course testCourse = courseChecker(courses);
            if (enrList.size()<=0){
                checker = true;
                addStudent = testStu;
                addCourse = testCourse;
            } else {
                for (int i = 0; i < enrList.size();i++){
                    if (enrList.get(i).getStudents().getID().equalsIgnoreCase(testStu.getID())
                    && enrList.get(i).getCourses().getID().equalsIgnoreCase(testCourse.getID()))
                    {
                        System.out.println("this student has enrolled this course already");
                        break;
                    } else if ( i == (enrList.size()-1)) {
                        checker = true;
                        addStudent = testStu;
                        addCourse = testCourse;
                    }
                }
            }
        }
        String addSemester = semesterChecker(semesters);
        enrList.add(new StudentEnrolment(addSemester, addStudent, addCourse));
        System.out.println("enrolment added:" + (enrList.get(enrList.size()-1)).toString());
        System.out.println(enrList.toString());
    }
    static void updateEnrolment(ArrayList<StudentEnrolment> enrList,
                                ArrayList<Course> courses,
                                ArrayList<Student> students,
                                String[] semesters){
        int index = singleEnrolment(enrList,courses,students);
        System.out.println(enrList.get(index));
        Scanner option = new Scanner(System.in);
        int input = 0;
        System.out.println("Choose how you want to update the your enrolments\n"
                        + "1. Change student. \n"
                        + "2. Change course. \n"
                        + "3. Change semester. \n"
                        + "************************"
                );
        boolean optionChecker = true;
        while (optionChecker) {
            //screening
            try {
                System.out.println("Enter your selection");
                input = option.nextInt();
                if (input >= 1 && input >= 3) {optionChecker = false;}
                else {System.out.println("enter 1,2 or 3 only");}
            } catch(InputMismatchException exception){
                System.out.println("enter 1,2 or 3 only");
                option.next();
            }
        }
        switch (input) {
            case 1:
                Student newStudent = studentChecker(students);
                enrList.get(index).setStudents(newStudent);
                break;
            case 2:
                Course newCourse = courseChecker(courses);
                enrList.get(index).setCourses(newCourse);
                break;
            case 3:
                String newSemester = semesterChecker(semesters);
                enrList.get(index).setSemester(newSemester);
                break;
        }
    }

    static void deleteEnrolment(ArrayList<StudentEnrolment> enrList,
                                ArrayList<Course> courses,
                                ArrayList<Student> students,
                                String[] semesters){
        int index = singleEnrolment(enrList,courses,students);
        System.out.println(enrList.get(index));
        System.out.println("are you sure you want to delete this enrolment (Y/N) \n");
        Scanner yesno = new Scanner(System.in);
        String input = yesno.nextLine();
        if (input.equalsIgnoreCase(String.valueOf('y'))) {
            enrList.remove(index);

        } else if (input.equalsIgnoreCase(String.valueOf('n'))) {
            System.out.println("action cancelled");
        } else {
            System.out.println("enter Y or N only");
        }
    }
    static Student studentChecker(ArrayList<Student> students){
        Scanner input = new Scanner(System.in);
        while (true){
            //screening
            System.out.println("enter sid of the student that need to enrol");
            String inputSID = input.nextLine();
            for (Student stu : students) {
                if ((stu.getID()).equalsIgnoreCase(inputSID)) {
                    System.out.println("processing...");
                    System.out.println(stu.toString());
                    return stu;
                }
            }
            System.out.print("id does not exist, please enter an existed id");
        }
    }

    static Course courseChecker(ArrayList<Course> courses){
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println("enter the ID of the desired course");
            String inputCID = input.nextLine();
            for (Course cou : courses){
                if ((cou.getID()).equalsIgnoreCase(inputCID)) {
                    System.out.println("processing...");
                    System.out.println(inputCID.toString());
                    return cou;
                }
            }
            System.out.println("this course id does not exist, please enter an existed id \n");
        }
    }

    static String semesterChecker(String[] semesters){
        Scanner input = new Scanner(System.in);
        while (true){
            //screening
            System.out.println("enter the desired semester: \n");
            String inputSemester = input.nextLine();
            for (String sem : semesters) {
                if ((sem).equalsIgnoreCase(inputSemester)){
                    System.out.println("semester " + sem + "confirmed \n");
                    return sem;
                }
            }
            System.out.println("please enter a valid semester \n");
        }
    }

    static ArrayList<String> multiEnrolment(ArrayList<StudentEnrolment> enrList,
                                            ArrayList<Course> courses,
                                            ArrayList<Student> students,
                                            String[] semesters) {
        Scanner optionInput = new Scanner(System.in);
        int input = 0;
        ArrayList<StudentEnrolment> multi =  new ArrayList<>();
        ArrayList<String> outputString = new ArrayList<>();
        System.out.println("how you wish to find the enrolment \n" +
                            "1. Find all course for 1 student in 1 semester \n"+
                            "2. Find all students of 1 course in 1 semester \n" +
                            "3. Find all courses in 1 semester \n");
        boolean optionChecker = true;
        while (optionChecker) {
            try {
                System.out.println("choose 1, 2 or 3: \n");
                input = optionInput.nextInt();
                if (input >=1 && input<= 3) {optionChecker=false;}
                else {System.out.println("enter 1, 2 or 3 only");}
            } catch (InputMismatchException exception) {
                System.out.println("enter 1, 2 or 3 only");
                optionInput.next();
            }
        }
        ArrayList<StudentEnrolment> tempArr = new ArrayList<>();
        switch (input){
            case 1:
                tempArr.addAll(queryStudent(enrList,students));
                if (tempArr.size()==0) {
                    System.out.println("no enrolment found.");
                } else {
                    multi.addAll(querySemester(tempArr,semesters));
                    for (StudentEnrolment enrol : multi) {
                        System.out.println(enrol.toString());
                        outputString.add(enrol.getCourses().toCSV());
                    }
                }
                break;
            case 2:
                tempArr.addAll(queryCourse(enrList,courses));
                if (tempArr.size()==0){
                    System.out.println("no enrolment found.");
                } else {
                    multi.addAll(querySemester(tempArr,semesters));
                    for (StudentEnrolment enrol : multi) {
                        System.out.println(enrol.toString());
                        outputString.add(enrol.getStudents().toCSV());
                    }
                }
                break;
            case 3:
                multi.addAll(querySemester(enrList,semesters));
                for (StudentEnrolment enrol : multi) {
                    System.out.println(enrol.getCourses().toString());
                    outputString.add(enrol.getCourses().toCSV());
                }
                break;
        }
        return outputString;
    }
    static int singleEnrolment(ArrayList<StudentEnrolment> enrList,
                      ArrayList<Course> courses,
                      ArrayList<Student> students){
        boolean checker = false;
        int result = -1;
        while(!checker){
            Student testStu = studentChecker(students);
            Course testCourse = courseChecker(courses);
            if (enrList.size()<=0){
                checker = true;
                System.out.println("no matching result found.");
            } else {
                for (int i =0; i < enrList.size(); i++){
                    if (enrList.get(i).getStudents().getID().equalsIgnoreCase(testStu.getID())
                            && enrList.get(i).getCourses().getID().equalsIgnoreCase(testCourse.getID())){
                        System.out.println("enrolment found.");
                        result = i;
                        checker = true;
                        break;
                    } else if (i == (enrList.size() -1)){
                        System.out.println("no matching result found.");
                        checker = true;
                    }
                }
            }
        }
        return result;
    }
    static ArrayList<StudentEnrolment>queryStudent(ArrayList<StudentEnrolment> enrList,
                                                   ArrayList<Student> students) {
        ArrayList<StudentEnrolment> studentQuery = new ArrayList<>();
        Student chosen = studentChecker(students);
        for (StudentEnrolment stu : enrList){
            if ((chosen.getID()).equalsIgnoreCase(stu.getStudents().getID())) {
                studentQuery.add(stu);
            }
        }
        return studentQuery;
    }

    static ArrayList<StudentEnrolment> queryCourse(ArrayList<StudentEnrolment> enrList,
                                                   ArrayList<Course> courses){
        ArrayList<StudentEnrolment> courseQuery = new ArrayList<>();
        Course chosen = courseChecker(courses);
        for (StudentEnrolment stu : enrList) {
            if ((chosen.getID().equalsIgnoreCase(stu.getCourses().getID()))){
                courseQuery.add(stu);
            }
        }
        return courseQuery;
    }

    static ArrayList<StudentEnrolment> querySemester(ArrayList<StudentEnrolment> enrList,
                                                     String[] semesters){
        ArrayList<StudentEnrolment> semesterQuery = new ArrayList<>();
        String chosen = semesterChecker(semesters);
        for (StudentEnrolment stu : enrList){
            if (chosen.equalsIgnoreCase(stu.getSemester())) {
                semesterQuery.add(stu);
            }
        }
        return semesterQuery;
    }
    static void getOne(ArrayList<StudentEnrolment> enrList,
                          ArrayList<Course> courses,
                          ArrayList<Student> students) throws IOException {
        StudentEnrolment enrolmentQuery = enrList.get(singleEnrolment(enrList,courses,students));
        System.out.println("do you want to output the result in a file (Y/N)");
        Scanner yesno = new Scanner(System.in);
        String input = yesno.nextLine();
        if (input.equalsIgnoreCase(String.valueOf('y'))) {
            FileWriter writer = new FileWriter("output.csv");
            writer.write(enrolmentQuery.toCSV() + "\n");
            writer.close();
        } else if (input.equalsIgnoreCase(String.valueOf('n'))) {
            System.out.println("end function.");
        } else {
            System.out.println("please enter y or n");
        }
    }
    static void getAll(ArrayList<StudentEnrolment> enrList,
                       ArrayList<Course> courses,
                       ArrayList<Student> students,
                       String[] semesters
                       ) throws Exception {
        ArrayList<String> outputs = new ArrayList<String>(multiEnrolment(enrList,courses,students,semesters));
        System.out.println("do you want to output the result in a file (y/N)");
        Scanner yesno = new Scanner(System.in);
        String input = yesno.nextLine();
        if (input.equalsIgnoreCase(String.valueOf('y'))){
            FileWriter writer = new FileWriter("ouput.csv");
            for (String stu: outputs){
                writer.write(stu.toString()+"\n");
            }
            writer.close();
        } else if (input.equalsIgnoreCase(String.valueOf('n'))) {
            System.out.println("end function.");
        } else {
            System.out.println("please enter y or n");
        }
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    static boolean end(){
        while (true){
            System.out.println("do you want to end this program");
            Scanner yesno = new Scanner(System.in);
            String input = yesno.nextLine();
            if (input.equalsIgnoreCase(String.valueOf('y'))) {
                return true;
            } else if (input.equalsIgnoreCase(String.valueOf('n'))){
                return false;
            } else {
                System.out.println("enter y or n only.");
            }
        }
    }

    static void populatingLists(File f,
                                ArrayList<StudentEnrolment> enrList,
                                ArrayList<Course> courses,
                                ArrayList<Student> students) throws FileNotFoundException {
        Scanner in = new Scanner(f);
        Student newStu;
        Course newCo;
        StudentEnrolment newEnr;
        int parser;

        while (in.hasNext()){
            //parser csv
            String component = in.nextLine();
            if (component.length()==0) continue;
            String[] list = component.split(",");
            try {
                parser = Integer.parseInt(list[5]);
            } catch (NumberFormatException exception) {
                parser =0;
            }
            newStu = new Student(list[0],list[1],list[2]);
            newCo = new Course(list[3],list[4],parser);
            newEnr = new StudentEnrolment(list[6],newStu,newCo);
            if (enrList.size()==0){
                //add entry if this list is empty
                courses.add(newCo);
                students.add(newStu);
                enrList.add(newEnr);
            } else {
                for (int i = 0; i < enrList.size();i++){
                    if (enrList.get(i).getStudents().getID().equalsIgnoreCase(newStu.getID())
                    && enrList.get(i).getCourses().getID().equalsIgnoreCase(newCo.getID())) {
                        break; //check for duplication in the enrolment list
                    } else if (i == (enrList.size() - 1)) {
                        enrList.add(newEnr);
                        for (int j = 0; j < courses.size(); j++) {
                            //add new course to course list if the id is not found
                            if (courses.get(j).getID().equalsIgnoreCase(newCo.getID())) {
                                break;
                            } else if (j == (courses.size()-1)) {
                                courses.add(newCo);
                            }
                        }
                        for (int k = 0; k < students.size(); k++) {
                            //add new student to this list if id not found
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args){

    }

}

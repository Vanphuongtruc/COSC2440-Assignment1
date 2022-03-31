
public class Main {
    public static void main(String[] args){
        Student student1 = new Student("1234","A","1/1/01");
        System.out.println(student1.toString());
        Student student2 = new Student("5678","B","2/2/02");
        System.out.println(student2.toCSV());
    }

}


public class Student {
    private String ID;
    private String  name;
    private String bd;

    public Student(String sid, String sname, String sbd){
        this.ID = sid;
        this.name = sname;
        this.bd = sbd;
    }
    //getters
    public String getName(){return name;}
    public String getID(){return ID;}
    public String getBD(){return bd;}

    public String toString(){
        return "Student name: " + this.name +  " - " + this.ID + " (Bd: " + this.bd + ")";
    }

    public String toCSV(){
        return this.name + " - " + this.ID +  " - " + this.bd;
    }
}

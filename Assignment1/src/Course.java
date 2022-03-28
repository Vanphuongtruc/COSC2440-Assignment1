//id, name, number of credits
public class Course {
    private String name;
    private String ID;
    private int credits;
    //course contructor
    public Course(String cname, String cid, int ccredits) {
        this.name = cname;
        this.ID = cid;
        this.credits = ccredits;
    }
    public String getName(){return name;}
    public String getID(){return ID;}
    public int getCredits(){return credits;}

    public String toString(){
        return "Course: " + name +  " - " + ID + " (credits: " + credits + ")";
    }

    public String toCSV(){
        return name + " - " + ID +  " - " + credits;
    }
}

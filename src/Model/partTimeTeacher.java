package Model;

public class partTimeTeacher extends Teacher{
    private double teachingHour;



    public String laGV(){
        return "partTime";
    }

    public partTimeTeacher(int id, String name, int teachAtSchoolYear, String specialty, String phoneNumber, double salary, double teachingHour) {
        super(id, name, teachAtSchoolYear, specialty, phoneNumber, salary);
        this.teachingHour = teachingHour;

    }

    public partTimeTeacher(String name, int teachAtSchoolYear, String specialty, String phoneNumber, double teachingHour) {
        super(name, teachAtSchoolYear, specialty, phoneNumber);
        this.teachingHour = teachingHour;
    }

    public partTimeTeacher(int id, String name, int teachAtSchoolYear, String specialty, String phoneNumber, double teachingHour) {
        super(id, name, teachAtSchoolYear, specialty, phoneNumber);
        this.teachingHour = teachingHour;
    }

    public double getTeachingHour() {
        return teachingHour;
    }

    public void setTeachingHour(int teachingHour) {
        this.teachingHour = teachingHour;
    }


    public double getLuong(){ return teachingHour*500000 + 1000000;}

    public String GetKind(){
        return "Part Time";
    }

}

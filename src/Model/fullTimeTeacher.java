package Model;

public class fullTimeTeacher extends Teacher{
    private double coefficient;



    public fullTimeTeacher(int id, String name, int teachAtSchoolYear, String specialty, String phoneNumber, double salary, double coefficient) {
        super(id, name, teachAtSchoolYear, specialty, phoneNumber, salary);
        this.coefficient = coefficient;
    }

    public fullTimeTeacher(String name, int teachAtSchoolYear, String specialty, String phoneNumber, double coefficient) {
        super(name, teachAtSchoolYear, specialty, phoneNumber);
        this.coefficient = coefficient;
    }

    public fullTimeTeacher(int id, String name, int teachAtSchoolYear, String specialty, String phoneNumber, double coefficient) {
        super(id, name, teachAtSchoolYear, specialty, phoneNumber);
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }


    public double getLuong(){
        return coefficient *3000000;
    }

    public String GetKind(){
        return "Full Time";
    }

}

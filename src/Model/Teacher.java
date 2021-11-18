package Model;

public class Teacher {
     private int id;
     private String name;
     private int teachAtSchoolYear;
     private String specialty;
     private String phoneNumber;
     private double salary;

    public Teacher(int id, String name, int teachAtSchoolYear, String specialty, String phoneNumber, double salary) {
        this.id = id;
        this.name = name;
        this.teachAtSchoolYear = teachAtSchoolYear;
        this.specialty = specialty;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }
     public Teacher(){};
    public Teacher( String name, int teachAtSchoolYear, String specialty, String phoneNumber) {

        this.name = name;
        this.teachAtSchoolYear = teachAtSchoolYear;
        this.specialty = specialty;
        this.phoneNumber = phoneNumber;
    }


    public Teacher(int id, String name, int teachAtSchoolYear, String specialty, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.teachAtSchoolYear = teachAtSchoolYear;
        this.specialty = specialty;
        this.phoneNumber = phoneNumber;
    }



    public double getLuong(){ return 0.0;}



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getTeachAtSchoolYear() {
        return teachAtSchoolYear;
    }

    public void setTeachAtSchoolYear(int teachAtSchoolYear) {
        this.teachAtSchoolYear = teachAtSchoolYear;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String GetKind(){
        return "";
    }
}

public class Person {
    private String name;
    private String password;
    private String schoolName = "Bingham Uni";
    private  String marticNo;

    private int staffNo;
Person(){
    System.out.println("Object created");
  }

    public Person(String name, String password, String schoolName, String marticNo) {
        this.name = name;
        this.password = password;
        this.schoolName = schoolName;
        this.marticNo = marticNo;
    }

    public Person(String name, String password, String schoolName, int staffNo) {
        this.name = name;
        this.password = password;
        this.schoolName = schoolName;
        this.staffNo = staffNo;
}

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getMarticNo() {
        return marticNo;
    }

    public int getStaffNo(){
        return staffNo;
    }
    void setName(String name){
        this.name = name;
    }

    void setPassword(String password){
        this.password = password;
    }


}

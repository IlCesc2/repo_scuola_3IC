package custom_exception;
/**
 * asdasdasdasdad
 * userszaxasdadssdasd
 * */
public class User {
    //Username; Identifier;One-time password;Recovery code;First name;Last name;Department;Location
    private String username; 
    private String id;
    private String opd;
    private String rec_code;
    private String fName;
    private String lName;
    private String department;
    private String loc;


    public User(String username, String id, String opd, String rec_code, String fName, String lName, String department, String loc) {
        this.username = username;
        this.id = id;
        this.opd = opd;
        this.rec_code = rec_code;
        this.fName = fName;
        this.lName = lName;
        this.department = department;
        this.loc = loc;
    }

    @Override 
    public String toString() {
        return this.username+", "+this.id+", "+this.opd+", "+this.rec_code+", "+this.fName+", "+this.lName+", "+this.department+", "+this.loc;
    }

}

   
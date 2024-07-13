import java.io.Serializable;

public class User implements Serializable{
    private static final long serialVersionUID=1L;
    private static int idcounter=1;
    private int userid;
    private String password;
    private String role;


public User(String password, String role){
    this.userid=idcounter++;
    this.password=password;
    this.role=role;
}
public int getuserid(){
    return userid;
}
public String getpassword(){
    return password;
}
public void setpassword(String password){
    this.password=password;
}
public String getrole(){
    return role;
}
public void setrole(String role){
    this.role=role;
}
public String toString() {
    return "User{" +
            "userid=" + userid +
            ", role='" + role + '\'' +
            '}';
}
}
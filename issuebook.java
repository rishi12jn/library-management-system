import java.io.Serializable;
public class issuebook implements Serializable{
    private static final long serialVersionUID=1L;
    private int bookid;
    private int userid;

public issuebook(int bookid,int userid){
    this.bookid=bookid;
    this.userid=userid;
}
public int gebookid() {
    return bookid;
}

public int getuserid() {
    return userid;
}

@Override
public String toString() {
    return "IssueBook{" +
            "bookid=" + bookid +
            ", userd=" + userid +
            '}';
}
}
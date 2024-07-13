import java.io.Serializable;
public class Book implements Serializable{
    private static final long serialVersionUID=1L;
    private static int idcounter=1;
    private int bookid;
    private String bookname;
    private String writername;
    private double price;
    private int quantity;

    public Book(String bookname, String writername, double price, int quantity){
        this.bookid=idcounter++;
        this.bookname=bookname;
        this.writername=writername;
        this.price=price;
        this.quantity=quantity;
    }
    public int getbookid() {
        return bookid;
    }

    public String getbookname() {
        return bookname;
    }

    public void setbookname(String bookname) {
        this.bookname = bookname;
    }

    public String getwritername() {
        return writername;
    }

    public void setwriterame(String writername) {
        this.writername = writername;
    }

    public double getprice() {
        return price;
    }

    public void setprice(double price) {
        this.price = price;
    }

    public int getquantity() {
        return quantity;
    }

    public void setquantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString(){
        return "book{"+
                "bookid="+bookid+
                ", bookName='" + bookname + '\'' +
                ", writerName='" + writername + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
    }





import java.io.*;
import java.util.*;

public class Main{
    private static List<Book> bookList=new ArrayList<>();
    private static List<User> userlist=new ArrayList<>();
    private static List<issuebook>issuedbooklist=new ArrayList<>();
    private static Scanner scanner=new Scanner(System.in);
    private static final String BOOKS_FILE="books.ser";
    private static final String ISSUED_BOOKS_FILE="issuedbooks.ser";

    public static void main(String[] args){
        loadBooks();
        loadUsers();
        loadIssuedBooks();

        while(true){
            System.out.println("Choose an option:");
            System.out.println("1. Add a new book");
            System.out.println("2. Delete a book");
            System.out.println("3. Update book quantity");
            System.out.println("4. Display all books");
            System.out.println("5. Search books");
            System.out.println("6. Add a new user");
            System.out.println("7. Issue a book");
            System.out.println("8. Save and exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch(choice){
                case 1:
                    addnewbooks();
                    break;
                case 2:
                    deletebooks();
                    break;
                case 3:
                    updatebookquantity();
                    break;
                case 4:
                    displayallbooks();
                    break;
                case 5:
                    searchbooks();
                    break;
                case 6 :
                    issuebook();
                    break;
                case 7:
                    savedata();
                    System.out.println("data saved");
                default:
                    System.out.println("invalid choice");
            }
        }
    }
    private static void addnewbooks(){
        System.out.println("Enter book details:");
        System.out.print("Book Name: ");
        String bookname = scanner.nextLine();
        System.out.print("Writer Name: ");
        String writername = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Book book =new Book(bookname,writername,price, quantity);
        bookList.add(book);

        System.out.println("book added successfully. Book ID:"+book.getbookid());

    }

    private static void deletebooks(){
        System.out.println("enter book id to delete:");
        int bookid=scanner.nextInt();
        scanner.nextLine();

        Book booktodelete=null;
        for(Book book:bookList){
            if(book.getbookid()==bookid){
                booktodelete=book;
                break;

            }
        }
        if (booktodelete != null) {
            bookList.remove(booktodelete);
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void updatebookquantity(){
        System.out.println("enter book id to update");
        int bookid=scanner.nextInt();
        System.out.println("enter quantity to subtract");
        int quantitytosubtract=scanner.nextInt();
        scanner.nextLine();

        for (Book book : bookList) {
            if (book.getbookid() == bookid) {
                if (quantitytosubtract <= book.getquantity()) {
                    book.setquantity(book.getquantity() - quantitytosubtract);
                    System.out.println("Book quantity updated successfully.");
                } else {
                    System.out.println("Error: Quantity to subtract exceeds available quantity.");
                }
                return;
            }
        }

        System.out.println("Book not found.");
    }

    private static void displayallbooks(){
        System.out.println("list of books:");
        for(Book book : bookList){
            System.out.println(book);
        }
    }
    private static void searchbooks(){
        System.out.println("choose search option:");
        System.out.println("1.search book name");
        System.out.println("2. search by writername");
        int choice=scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter book name: ");
                String bookName = scanner.nextLine();
                searchbybookname(bookName);
                break;
            case 2:
                System.out.print("Enter writer name: ");
                String writerName = scanner.nextLine();
                searchbywritername(writerName);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
    private static void searchbybookname(String bookname){
        boolean found=false;
        for(Book book: bookList){
            if(book.getbookname().toLowerCase().contains(bookname.toLowerCase())){
                System.out.println(book);
                found=true;
            }
        }
        if(!found){
            System.out.println("book not found");
        }
    }
    private static void searchbywritername(String writername) {
        boolean found = false;
        for (Book book : bookList) {
            if (book.getwritername().toLowerCase().contains(writername.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Writer not found.");
        }
    }
    private static void addnewuer(){
        System.out.println("enter user details:");
        System.out.println("password:");
        String password=scanner.nextLine();
        System.out.println("role(admin/user)");
        String role=scanner.nextLine();

        User user=new User(password,role);

        System.out.println("user added successfully User ID:"+user.getuserid());

    }
    private static void issuebook(){
        System.out.println("enter admin user id:");
        int adminuserid=scanner.nextInt();
        scanner.nextLine();
        System.out.println("enter admin password:");
        String adminpassword=scanner.nextLine();

        User adminuser=null;
        for(User user:userlist){
            if(user.getuserid()==adminuserid && user.getpassword().equals(adminpassword)&& user.getrole().equalsIgnoreCase("Admin")){
                adminuser=user;
                break;
            }
        }
        if(adminuser==null){
            System.out.println("invalid admin credentials");
            return;
        }
        System.out.println("enter book id to issue:");
        int bookid=scanner.nextInt();
        System.out.println("enter user id to issue book:");
        int userid=scanner.nextInt();
        scanner.nextLine();

        Book booktoissue=null;
        for(Book book : bookList){
            if(book.getbookid()==bookid){
                booktoissue=book;
                break;
            }
        }
        if(booktoissue==null){
            System.out.println("book not found");
            return;
        }
        User usertoissue=null;
        for(User user: userlist){
            if(user.getuserid()==userid){
                usertoissue=user;
                break;
            }
        }
        if(usertoissue==null){
            System.out.println("user not found");
            return;
        }
        if(booktoissue.getquantity()>0){
            booktoissue.setquantity(booktoissue.getquantity()-1);
            issuebook issuebook=new issuebook(bookid,userid);
            issuedbooklist.add(issuebook);
            System.out.println("book issued successfully");
        }
        else{
            System.out.println("book out of stock");
        }
    }

    private static void savedata() {
        saveToFile(bookList, BOOKS_FILE);
        saveToFile(userlist, USERS_FILE);
        saveToFile(issuedbooklist, ISSUED_BOOKS_FILE);
    }

    private static void loadBooks() {
        bookList = (List<Book>) loadFromFile(BOOKS_FILE);
        if (bookList == null) {
            bookList = new ArrayList<>();
        }
    }

    private static void loadUsers() {
        userlist = (List<User>) loadFromFile(USERS_FILE);
        if (userlist == null) {
            userlist = new ArrayList<>();
        }
    }

    private static void loadIssuedBooks() {
        issuedbooklist = (List<issuebook>) loadFromFile(ISSUED_BOOKS_FILE);
        if (issuedbooklist == null) {
            issuedbooklist = new ArrayList<>();
        }
    }

    private static void saveToFile(Object object, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }


}


    



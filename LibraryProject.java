
import java.util.ArrayList;
import java.util.Scanner;

class Book {
    public String name, author;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }
}

class Borrower {
    public String name;
    public ArrayList<Book> BorrowedBooks=new ArrayList<>();

    public Borrower(String name) {
        this.name = name;
    }

    public Book findbook(String name) {
        int len = BorrowedBooks.size();
        for (int i = 0; i < len; i++) {
            Book book = BorrowedBooks.get(i);
            if (book.name.equals(name)) {
                return book;
            }
        }
        return null;
    }

    public boolean issued(Book book) {
        int len = BorrowedBooks.size();
        if (len < 2 && !BorrowedBooks.contains(book)) {
            BorrowedBooks.add(book);
            return true;
        } else if (len >= 2) {
            System.out.println("You have exceeded your Borrow Limit.");
            return false;
        } else {
            System.out.println("You have already borrowed one copy of the specified book");
            return false;
        }
    }

    public boolean returned(Book book) {
        int bookIndex = BorrowedBooks.indexOf(book);
        if (bookIndex >= 0) {
            BorrowedBooks.remove(bookIndex);
            return true;
        } else {
            return false;
        }
    }

    public void view() {
        int len = BorrowedBooks.size();
        if (len == 0) {
            System.out.println("You haven't issued any book from the library");
        } else {
            System.out.println("The books borrowed by "+this.name+" are: ");
            System.out.println(String.format("%-14s", "Serial No.") + String.format("%-25s", "Available Books")
                    + String.format("%-25s", "Book Authors"));
            for (int i = 0; i < len; i++) {
                Book item = BorrowedBooks.get(i);
                System.out.print(String.format("%-14s", i));
                System.out.print(String.format("%-25s", item.name));
                System.out.println(String.format("%-25s", item.author));
            }
        }
    }
}

class MyLibrary {
    public ArrayList<Book> books;
    public ArrayList<Integer> copies;

    public MyLibrary(ArrayList<Book> books, ArrayList<Integer> copies) {
        this.books = books;
        this.copies = copies;
    }

    public Book findBook(String name) {
        int len = this.books.size();
        for (int i = 0; i < len; i++) {
            Book book = this.books.get(i);
            if (book.name.equals(name)) {
                return book;
            }
        }
        return null;
    }

    public void returnBook(Book book, Borrower student) {
        if (student.returned(book)) {
            if (!this.books.contains(book)) {
                this.books.add(book);
                this.copies.add(1);
            } else {
                int index = this.books.indexOf(book);
                int value = this.copies.get(index);
                this.copies.set(index, value + 1);
            }
            System.out.println("The book is returned successfully.");
        }
        student.view();
    }

    public void issueBook(Book book, Borrower student) {
        if (this.books.contains(book) && student.issued(book)) {
            int index = this.books.indexOf(book);
            int value = this.copies.get(index);
            if (value == 1) {
                this.books.remove(index);
                this.copies.remove(index);
            } else {
                this.copies.set(index, value - 1);
            }
            System.out.println("The book is issued to " + student.name + ".");
        }
        if (!this.books.contains(book)) {
            System.out.println("The book is not available in the library.");
        }
        student.view();
    }

    public void viewBooks() {
        int len = this.books.size();
        System.out.println("The available books and no. of copies are:");
        System.out.println(String.format("%-14s", "Serial No.") + String.format("%-25s", "Available Books")
                + String.format("%-25s", "Book Authors") + String.format("%-25s", "Available copies"));
        for (int i = 0; i < len; i++) {
            Book item = this.books.get(i);
            System.out.print(String.format("%-14s", i));
            System.out.print(String.format("%-25s", item.name));
            System.out.print(String.format("%-25s", item.author));
            System.out.println(this.copies.get(i));
        }
    }
}

public class LibraryProject {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Book> BookShelf = new ArrayList<>();
        ArrayList<Integer> BookCopies = new ArrayList<>();
        ArrayList<Borrower> Students = new ArrayList<>();
        // The library initially possess specified books with specified no. of copies
        Book b1 = new Book("Algorithms", "Author1");
        BookShelf.add(b1);
        BookCopies.add(2);

        Book b2 = new Book("Algorithms2", "Author2");
        BookShelf.add(b2);
        BookCopies.add(3);

        Book b3 = new Book("Algorithms3", "Author3");
        BookShelf.add(b3);
        BookCopies.add(1);

        Book b4 = new Book("Algorithms4", "Author4");
        BookShelf.add(b4);
        BookCopies.add(2);

        // The library also have the list of register borrowers
        Borrower s1 = new Borrower("Anurag");
        Students.add(s1);

        Borrower s2 = new Borrower("Ayush");
        Students.add(s2);

        Borrower s3 = new Borrower("santanu");
        Students.add(s3);

        Borrower s4 = new Borrower("Aditya");
        Students.add(s4);

        Borrower s5 = new Borrower("Om");
        Students.add(s5);
        /***************************************************************************/
        MyLibrary library = new MyLibrary(BookShelf, BookCopies);
        boolean stay = true;
        while (stay) {
            System.out.println(
                    "Enter 1 to view the available books in the library \nEnter 2 to borrow a book from the library \nEnter 3 to return the book to the library");
            int operation = sc.nextInt();
            if (operation == 1) {
                library.viewBooks();
            } else if (operation == 2) {
                int serialNo;
                System.out.println("If you are a new user, then enter 1 else enter 0");
                int choice = sc.nextInt();
                if (choice == 1) {
                    System.out.println("Enter your name:");
                    String name = sc.next();
                    Borrower s = new Borrower(name);
                    Students.add(s);
                    serialNo = Students.indexOf(s);
                } else {
                    System.out.println("This is the students list:");
                    int len = Students.size();
                    System.out.println(String.format("%-25s", "Student Name") + String.format("%-10s", "Serial No."));
                    for (int i = 0; i < len; i++) {
                        Borrower student = Students.get(i);
                        System.out.print(String.format("%-25s", student.name));
                        System.out.println(i);
                    }
                    System.out.println("Please, enter your serial no., taking reference from above");
                    serialNo = sc.nextInt();
                }
                Borrower student = Students.get(serialNo);
                System.out.println("Enter the name of the book you want to issue");
                String name = sc.next();
                Book reqBook = library.findBook(name);
                if (reqBook == null) {
                    System.out.println("This book is not available in the library");
                } else {
                    library.issueBook(reqBook, student);
                }

            } else if (operation == 3) {
                int serialNo;
                System.out.println("This is the students list:");
                int len = Students.size();
                System.out.println(String.format("%-25s", "Student Name") + String.format("%-10s", "Serial No."));
                for (int i = 0; i < len; i++) {
                    Borrower student = Students.get(i);
                    System.out.print(String.format("%-25s", student.name));
                    System.out.println(i);
                }
                System.out.println("Please, enter your serial no., taking reference from above");
                serialNo = sc.nextInt();
                Borrower student = Students.get(serialNo);
                System.out.println("Please enter the name of the book you want to return");
                String name = sc.next();
                Book book = student.findbook(name);
                if (book == null) {
                    System.out.println("You haven't borrowed the book you are trying to return");
                } else {
                    library.returnBook(book, student);
                }
            } else {
                System.out.println("Please enter a valid input");
            }
            System.out.println("If you want to continue operation then enter YES otherwise enter NO");
            String ans=sc.next();
            if(ans.equalsIgnoreCase("NO"))
            stay=false;
        }
        sc.close();
    }
}

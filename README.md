# Library-Management-Program-JAVA
This Project is a simple library management system implemented in Java. It allows users to view available books, borrow books, and return books. The project consists of the following classes: 
## Book
### Field:
 * name: String - the name of the book 
 * author: String - the author of the book 

## Borrower
### Field:
 * name: String - the name of the borrower
 * BorrowedBooks: ArrayList<Book> - a list of books borrowed by the borrower 

### Methods:
 * Borrower(String name): Constructor for creating a new Borrower object with the specified name.
 * findbook(String name): Finds and returns a book with the specified name from the BorrowedBooks list.
 * issued(Book book): Determines whether a book can be issued to the borrower. If the borrower has not reached the borrow limit and has not already borrowed a copy of the specified book, the book is added to the BorrowedBooks list. Returns true if successful, false otherwise.
 * returned(Book book): Removes the specified book from the BorrowedBooks list if it exists. Returns true if successful, false otherwise.
 * view(): Prints a list of the books borrowed by the borrower.

## MyLibrary
### Fields:
 * books: ArrayList<Book> - a list of available books in the library.
 * copies: ArrayList<Integer> - a list of the number of copies available for each book.

### Methods:
 * MyLibrary(ArrayList<Book> books, ArrayList<Integer> copies): Constructor for creating a new MyLibrary object with the specified books and copies.
 * findBook(String name): Finds and returns a book with the specified name from the books list.
 * returnBook(Book book, Borrower student): Returns a book to the library. If the book is not already in the library, it is added to the books list with a copy count of 1. If the book is already in the library, its copy count is incremented. Prints a success message and updates the borrower's book list.
 * issueBook(Book book, Borrower student): Issues a book to a borrower. If the book is available in the library and the borrower is eligible to borrow, the book is removed from the library and the borrower's book list is updated. Prints a success message or an error message if the book is not available or the borrower has reached the borrow limit.
 * viewBooks(): Prints a list of the available books in the library with their authors and number of copies.

## LibraryProject
The main class that contains the main method and implements the user interface for the library management system. 
### Usage:
 * The program prompts the user for different operations such as viewing available books, borrowing a book, or returning a book.
 * The available options are:  
    * Enter 1 to view the available books in the library
    * Enter 2 to borrow a book from the library
    * Enter 3 to return the book to the library
 * Depending on the chosen operation, the program will ask for additional inputs such as the borrower's name or the name of the book to borrow/return. 
 * After each operation, the program will display the borrower's book list.
 * The program will continue to ask for operations until the user chooses to stop.

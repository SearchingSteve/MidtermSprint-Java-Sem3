package main.authors;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import main.items.LibraryItem;


public class Author {
    // ASSIGN ATTRIBUTES
    private String author_name;
    private Date DOB;
    private List<LibraryItem> listOfItems;

    // create static list so authors are kept track of
    // memory/ database is simulated for methods that manage author info
    private static List<Author> authorList = new ArrayList<>();

    // CONSTRUCTOR
    public Author(String author_name, Date DOB) {
        this.author_name = author_name;
        this.DOB = DOB;

        // listOfItems tracks LibraryItem s for gievn author
        this.listOfItems = new ArrayList<>();
    }

    // GETTERS AND SETTERS
    public void setName(String author_name) {
        this.author_name = author_name;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getName() {
        return author_name;
    }

    public List<LibraryItem> getListOfItems() {
        return listOfItems;
    }

    public static boolean validateAuthorDOB(String DOB) {
        Date currentDate = new Date();
        Date parsedDate = null;

        if (DOB == null || DOB.isEmpty()) {
            System.out.println("\nDate of birth cannot be empty.");
            System.out.print("Enter author Date of Birth (YYYY-MM-DD): ");
            return false;
        }
        
        try{
            parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(DOB);
        } catch (ParseException e) {
            // e.printStackTrace();
            System.err.println("\nInvalid date format. Please use the format YYYY-MM-DD.");
            System.out.print("Enter author Date of Birth (YYYY-MM-DD): ");
            return false;
        }

        if (parsedDate.after(currentDate)) {
            System.out.println("\nDate of birth cannot be in the future.");
            System.out.print("Enter author Date of Birth (YYYY-MM-DD): ");
            return false;
        }

        String[] dateParts = DOB.split("-");
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);

        if(month <1 || month >12 || day <1 || day> 31){
            System.out.println("\nInvalid month or day ");
            System.out.print("Enter author date of birth (YYYY-MM-DD): ");
            return false;
        }
        return true;
    }

    // METHODS
    // newAuthor, editAuthor and removeAuthor manage author's info
    // becuase the system is not connected to a memory or database, the
    // author managment methods simulate information being updated by
    // printing success messages
    public static boolean newAuthor(Author author) {
        if (!authorList.contains(author)) {
            authorList.add(author);
            System.out.println("Author " + author.getName() + "added successfully!");
            return true;
        }
        System.out.println("Author " + author.getName() + "is already in the system.");
        return false;
    }

    public static boolean editAuthor(String oldName, String newName, Date newDOB) {
        for (Author author : authorList) {
            if (author.getName().equals(oldName)) {
                author.setName(newName);
                author.setDOB(newDOB);
                System.out.println("Author " + newName + "information updated!");
                return true;
            }
        }

        System.out.println("Author " + newName + "is not currently in the system. cannot edit.");
        return false;
    }

    public static boolean removeAuthor(String authorName) {
        for (Author author : authorList) {
            if (author.getName().equals(authorName)) {
                authorList.remove(author);
                System.out.println("Author " + authorName + "deleted successfully!");
                return true;
            }
        }
        System.out.println("Author" + authorName + "is not currently in the system. cannot be deleted.");
        return false;
    }

    // addLibraryItem and removeLibraryItem manage library items associated
    // with given author
    public void addLibraryItem(LibraryItem item) {
        listOfItems.add(item);

        System.out.println("Library item " + item.getTitle() + "added successfully!");
    }

    public void removeLibraryItem(LibraryItem item) {
        listOfItems.remove(item);

        System.out.println("Library item " + item.getTitle() + "removed successfully!");
    }
}

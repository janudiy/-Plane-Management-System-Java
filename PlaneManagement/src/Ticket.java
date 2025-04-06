import javax.naming.Name;
import java.io.FileWriter;
import java.io.IOException;

// Class for handling ticket information
public class Ticket extends W2051893_PlaneManagement{
    private static int Row;
    private static int Seat;
    private static int Price;

    // Constructor to create Person object
    Person person = new Person(First_Name,Last_Name,Email_address);

    // Setters
    public void setRow(int row) {
        Row = row;
    }

    public void setSeat(int seat) {
        Seat = seat;
    }

    public static void setPrice(int price) {
        Price = price;
    }

    // Getters
    public int getRow() {
        return Row;
    }

    public int getSeat() {
        return Seat;
    }

    public static int getPrice() {
        return Price;
    }

    // Method to print ticket information
    public static void print_ticket(){
        System.out.println("Seat No: " + Row_Letter + Seat_Number);
        System.out.println("Name: " + First_Name + Last_Name);
        System.out.println("Email: " + Email_address);
        System.out.println("Price: " + Price);
    }

    // Method to save ticket information to a file
    public static void save(){
        String filename = Row_Letter + Seat_Number + ".txt";
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write("Row: " + Row_Letter + "\n");
            writer.write("Seat: " + Seat_Number + "\n");
            writer.write("Full Name: " + Person.getName() + "\n");
            writer.write("Email: : " + Person.getEmail() + "\n");
            writer.write("Price: " + Price);
            writer.close();
        } catch (IOException e) {
            System.out.println("An Error Occurred");
        }
    }
}



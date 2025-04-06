import java.util.InputMismatchException;
import java.util.Scanner;
// Class for managing plane tickets and seat reservations
public class W2051893_PlaneManagement {

    public static String[][] Tickets_Array = new String[52][6];// Array to store ticket information
    public static final int[] Number_of_seats_per_row = {14,12,12,14};// Array to represent seat availability
    public static final int[][] Seat_Array = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0}};


    // Variables for user input and ticket management
    public static String Row_Letter;
    public static int Seat_Number;
    public static String First_Name;
    public static String Last_Name;
    public static String Email_address;
    public static int Seat_Index;
    public static int Total_Tickets_Sold;

    // Main method to display menu and handle user choices
    public static void main(String[] args) {
        boolean Temp_Boolean = true;
        while (Temp_Boolean) {
            try {
                // Display menu
                System.out.println("Welcome to the Plane Management application");
                System.out.println("**************************************************");
                System.out.println("\n*                     MENU                       *");
                System.out.println("\n***************************************************");
                System.out.println("     1) Buy a seat");
                System.out.println("     2) Cancel a seat");
                System.out.println("     3) Find the first available seat");
                System.out.println("     4) Show seating plan");
                System.out.println("     5) Print tickets information and total sales ");
                System.out.println("     6) Search ticket ");
                System.out.println("     0) Quit");
                System.out.println("`1***************************************************");
                System.out.print("Please select Option: ");
                Scanner Input = new Scanner(System.in);
                int Choice = Input.nextInt();
                switch (Choice) {
                    case 1:
                        buy_seats();
                        break;
                    case 2:
                        cancel_seats();
                        break;
                    case 3:
                        find_first_seat_available();
                        break;
                    case 4:
                        show_seating_plan();
                        break;
                    case 5:
                        print_tickets_info();
                        System.out.println(Total_Tickets_Sold);
                        break;
                    case 6:
                        search_ticket();
                        break;
                    case 0:
                        System.out.println("Exiting the program...");
                        Temp_Boolean = false;
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid Input!!");
            }
        }
    }

    // Method to take row and seat number inputs from user
    public static void Take_Inputs(){
        try {
            Scanner Scanner = new Scanner(System.in);
            System.out.println("Enter Row Letter(A-D): ");
            Row_Letter = Scanner.nextLine().toLowerCase();
            System.out.println("Enter Seat Number(1-14): ");
            Seat_Number = Scanner.nextInt();
        }catch(InputMismatchException e){
            System.out.println("Invalid input entered");
        }
    }

    // Method to take user information (name, surname, email) from user
    public static void Take_User_info(){
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Enter First Name: ");
        First_Name = Scanner.nextLine();
        System.out.println("Enter Last  Name: ");
        Last_Name = Scanner.nextLine();
        System.out.println("Enter Email: ");
        Email_address = Scanner.nextLine();
    }

    // Method to convert row letter to index in Seat_Array
    public static void Seat_Index(){
        switch (Row_Letter){
            case "a":
                Seat_Index = 0;
                break;
            case "b":
                Seat_Index = 1;
                break;
            case "c":
                Seat_Index = 2;
                break;
            case "d":
                Seat_Index = 3;
                break;
        }
    }
    // Method to validate seat number and book the seat if available
    public static boolean Seat_Number_validation(){
        // Check if row is A or D
        boolean Return_Value = false;
        if (Row_Letter.equals("a") || Row_Letter.equals("d")) {
            if(Seat_Number>=1 && Seat_Number <=14){
                // Check if seat is available
                if (Seat_Array[Seat_Index][Seat_Number-1]==0) {
                    System.out.println("Seat Available and is now booked");
                    //Ticket price filter
                    if (Seat_Number<= 5){
                        Ticket.setPrice(200);
                        Total_Tickets_Sold += 200;
                    }else if (Seat_Number <= 9 ){
                        Ticket.setPrice(150);
                        Total_Tickets_Sold += 150;
                    }else{
                        Ticket.setPrice(180);
                        Total_Tickets_Sold += 180;
                    }
                    Seat_Array[Seat_Index][Seat_Number-1] = 1;
                    Return_Value = true;
                }else{
                    System.out.println("Seat Already booked");
                }
            }else{
                System.out.println("Invalid Seat Number!!");
            }
        }else if (Row_Letter.equals("b") || Row_Letter.equals("c")){
            if(Seat_Number>=1 && Seat_Number <=12){
                if (Seat_Array[Seat_Index][Seat_Number-1]==0) {
                    System.out.println("Seat Available and is now booked");
                    if (Seat_Number<= 5){
                        Ticket.setPrice(200);
                        Total_Tickets_Sold += 200;
                    }else if (Seat_Number <= 9 ){
                        Ticket.setPrice(150);
                        Total_Tickets_Sold += 150;
                    }else{
                        Ticket.setPrice(180);
                        Total_Tickets_Sold += 180;
                    }
                    Seat_Array[Seat_Index][Seat_Number-1] = 1;
                    Return_Value = true;
                }else{
                    System.out.println("Seat Already booked");
                }
            }else{
                System.out.println("Invalid Seat Number!!");
            }
        }else{
            System.out.println("Invalid Row Letter!!");
        }
        return Return_Value;
    }

    // Method to handle the buying of seats
    public static void buy_seats(){
        Take_Inputs();
        Seat_Index();
        if (Seat_Number_validation()) {
            Take_User_info();
            Add_tickets_to_array();
            Ticket.save();
        }
    }

    // Method to handle the cancellation of seats
    public static void cancel_seats(){
        Take_Inputs();
        Seat_Index();
        Seat_Number_validation_2();
        remove_ticket_from_array();
    }

    // Method to validate seat number and cancel the seat if booked
    public static void Seat_Number_validation_2(){
        if (Row_Letter.equals("a") || Row_Letter.equals("d")) {
            if(Seat_Number>=1 && Seat_Number <=14){
                if (Seat_Array[Seat_Index][Seat_Number-1]==1) {
                    System.out.println("Seat Cancelled!!");
                    if (Seat_Number<= 5){
                        Total_Tickets_Sold -= 200;
                    }else if (Seat_Number <= 9 ){
                        Total_Tickets_Sold -= 150;
                    }else{
                        Total_Tickets_Sold -= 180;
                    }
                    Seat_Array[Seat_Index][Seat_Number-1] = 0;
                }else{
                    System.out.println("Seat Available");
                }
            }else{
                System.out.println("Invalid Seat Number!!");
            }
        }else if (Row_Letter.equals("b") || Row_Letter.equals("c")){
            if(Seat_Number>=1 && Seat_Number <=12){
                if (Seat_Array[Seat_Index][Seat_Number-1]==1) {
                    System.out.println("Seat Cancelled");
                    if (Seat_Number<= 5){
                        Total_Tickets_Sold -= 200;
                    }else if (Seat_Number <= 9 ){
                        Total_Tickets_Sold -= 150;
                    }else{
                        Total_Tickets_Sold -= 180;
                    }
                    Seat_Array[Seat_Index][Seat_Number-1] = 1;
                }else{
                    System.out.println("Seat Available");
                }
            }else{
                System.out.println("Invalid Seat Number!!");
            }
        }else{
            System.out.println("Invalid Row Letter!!");
        }
    }

    // Method to find the first available seat
    public static void find_first_seat_available(){
        boolean Temp_Boolean = true;
        for (int i = 0 ; i <= 3 ; i++){
            if (Temp_Boolean) {
                for (int j = 0; j < Number_of_seats_per_row[i]; j++) {
                    if (Seat_Array[i][j] == 0) {
                        int temp = j+1;
                        System.out.println("First seat available is: " + int_to_letter(i) + temp);
                        Temp_Boolean = false;
                        break;
                    }
                }
            }
        }
    }

    // Method to convert index to row letter
    public static String int_to_letter(int number){
        String Letter;
        if (number == 0){
            Letter = "A";
        }else if (number == 1){
            Letter = "B";
        }else if(number ==2){
            Letter = "C";
        }else{
            Letter = "D";
        }
        return Letter;
    }

    // Method to display the seating plan
    public static void show_seating_plan(){
        for (int i = 0;i<=3;i++ ) {
            for (int j = 0 ; j < Number_of_seats_per_row[i]; j++ ) {
                if (Seat_Array[i][j] == 0){
                    System.out.print(" O ");
                }else{
                    System.out.print(" X ");
                }
            }
            System.out.println();
        }
    }

    // Method to add tickets to the Tickets_Array
    public static void Add_tickets_to_array(){
        Ticket.print_ticket();
        int Temp;
        if (Seat_Index== 0){
            Temp = Seat_Number-1;
        }else if ( Seat_Index == 1){
            Temp = Seat_Index + 13;
        } else if (Seat_Index==2) {
            Temp = Seat_Number + 25;
        }else{
            Temp = Seat_Number + 37;
        }
        Tickets_Array[Temp][0] = First_Name;
        Tickets_Array[Temp][1] = Last_Name;
        Tickets_Array[Temp][2] = Email_address;
        Tickets_Array[Temp][3] = Row_Letter;
        Tickets_Array[Temp][4] = String.valueOf(Seat_Number);
        Tickets_Array[Temp][5] = String.valueOf(Ticket.getPrice());
    }

    // Method to remove tickets from the Tickets_Array
    public static void remove_ticket_from_array(){
        int Temp;
        if (Seat_Index== 0){
            Temp = Seat_Number-1;
        }else if ( Seat_Index == 1){
            Temp = Seat_Index + 13;
        } else if (Seat_Index==2) {
            Temp = Seat_Number + 25;
        }else{
            Temp = Seat_Number + 37;
        }
        Tickets_Array[Temp][0] = null;
        Tickets_Array[Temp][1] = null;
        Tickets_Array[Temp][2] = null;
        Tickets_Array[Temp][3] = null;
        Tickets_Array[Temp][4] = null;
        Tickets_Array[Temp][5] = null;
    }

    // Method to print tickets information
    public static void print_tickets_info(){
        for(int i = 0 ; i <=51 ; i++){
            if (Tickets_Array[i][0] != null) {
                for (int j = 0; j <= 5; j++) {
                    if (Tickets_Array[i][j] != null) {
                        System.out.print(Tickets_Array[i][j]);
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }

    // Method to search for a specific ticket
    public static void search_ticket(){
        Take_Inputs();
        Seat_Index();
        int Temp;
        if (Seat_Index== 0){
            Temp = Seat_Number-1;
        }else if ( Seat_Index == 1){
            Temp = Seat_Index + 13;
        } else if (Seat_Index==2) {
            Temp = Seat_Number + 25;
        }else{
            Temp = Seat_Number + 37;
        }
        System.out.println("Seat No: " + Tickets_Array[Temp][3] + Tickets_Array[Temp][4]);
        System.out.println("Name: " + Tickets_Array[Temp][0] + Tickets_Array[Temp][1]);
        System.out.println("Email: " + Tickets_Array[Temp][2]);
        System.out.println("Price: " + Tickets_Array[Temp][5]);
    }
}

// Class for handling person information
public class Person extends W2051893_PlaneManagement {

    private static String Name;
    private static String Surname;
    private static String Email;

    // Constructor
    public Person(String Name,String Surname,String Email){
        Person.Name = Name;
        Person.Surname = Surname;
        Person.Email = Email;
    }

    // Setters
    public void setName(String name) {
        Name = name;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    // Setters
    public void setEmail(String email) {
        Email = email;
    }

    public static String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public static <return1> String getEmail() {
        return1 Email;
        return null;
    }
}


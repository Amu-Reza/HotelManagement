import java.util.ArrayList;

public class Passenger {

    private String nationalCode;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private ArrayList<Reservation> reservations = new ArrayList<>();

    public Passenger(String nationalCode, String firstName, String lastName, String email, String password) {
        this.nationalCode = nationalCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }



    public String getNationalCode() {
        return nationalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public String toString() {
        return nationalCode + " " +
                firstName + " " +
                lastName + " " +
                email + " " +
                password;
    }

    public void addReservations(Reservation reservation){

        reservations.add(reservation);
    }
}

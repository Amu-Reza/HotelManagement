public class Passenger {

    private String nationalCode;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public Passenger(String nationalCode, String firstName, String lastName, String email, String password) {
        this.nationalCode = nationalCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return nationalCode + " " +
                firstName + " " +
                lastName + " " +
                email + " " +
                password;
    }
}

public class HotelStaff {

    private String firstName;
    private String lastName;
    private String personnelCode;
    private String email;
    private String password;
    private String salary;
    private String accountBalance;

    public HotelStaff(String firstName, String lastName, String personnelCode, String email, String password, String salary, String accountBalance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personnelCode = personnelCode;
        this.email = email;
        this.password = password;
        this.salary = salary;
        this.accountBalance = accountBalance;
    }


    public String getPersonnelCode() {
        return personnelCode;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "HotelStaff{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personnelCode='" + personnelCode + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", salary='" + salary + '\'' +
                ", accountBalance='" + accountBalance + '\'' +
                '}';
    }
}

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonnelCode() {
        return personnelCode;
    }

    public void setPersonnelCode(String personnelCode) {
        this.personnelCode = personnelCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}

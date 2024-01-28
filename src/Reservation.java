import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Reservation {

    private String passengerNationalCode;
    private int payment;
    private String employeePersonnelCode;
    private int roomNumber;
    private Date date;
    private Status status;
    private RoD roD;

    public Reservation(String passengerNationalCode, int payment , int roomNumber, Date date, Status status, RoD roD) {
        this.passengerNationalCode = passengerNationalCode;
        this.payment = payment;
        this.roomNumber = roomNumber;
        this.date = date;
        this.status = status;
    }

    public void setPassengerNationalCode(String passengerNationalCode) {
        this.passengerNationalCode = passengerNationalCode;
    }

    public void setEmployeePersonnelCode(String employeePersonnelCode) {
        this.employeePersonnelCode = employeePersonnelCode;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public RoD getRoD() {
        return roD;
    }

    public void setRoD(RoD roD) {
        this.roD = roD;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPassengerNationalCode() {
        return passengerNationalCode;
    }

    public int getPayment() {
        return payment;
    }

    public String getEmployeePersonnelCode() {
        return employeePersonnelCode;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Date getDate() {
        return date;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "passengerNationalCode='" + passengerNationalCode + '\'' +
                ", payment=" + payment +
                ", employeePersonnelCode='" + employeePersonnelCode + '\'' +
                ", roomNumber=" + roomNumber +
                ", date=" + date +
                ", status=" + status +
                ", roD=" + roD +
                '}';
    }
}

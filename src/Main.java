import javax.swing.*;
import java.util.ArrayList;

public class Main {

    public static Hotel hotel = new Hotel("HotelManager", ArrayList<HotelStaff> hotelStaffs, ArrayList<Room> rooms, ArrayList<Passenger> passengers, Queue<Reservation> reservationQueue);
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Gui.welcomePage();
        });
    }
}
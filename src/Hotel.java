import java.util.ArrayList;
import java.util.Queue;

public class Hotel {

    private HotelManager hotelManager;
    private ArrayList<HotelStaff> hotelStaffs;
    private ArrayList<Room> rooms;
    private ArrayList<Passenger> passengers;
    private Queue<Reservation> reservationQueue;


    public Hotel(HotelManager hotelManager, ArrayList<HotelStaff> hotelStaffs, ArrayList<Room> rooms, ArrayList<Passenger> passengers, Queue<Reservation> reservationQueue) {
        this.hotelManager = hotelManager;
        this.hotelStaffs = hotelStaffs;
        this.rooms = rooms;
        this.passengers = passengers;
        this.reservationQueue = reservationQueue;
    }

    public void addPassengers(Passenger passenger) {
        this.passengers.add(passenger);
    }
}

import java.util.*;

public class Hotel {

    private HotelManager hotelManager;
    private static ArrayList<HotelStaff> hotelStaffs;
    private static ArrayList<Room> rooms;
    private static ArrayList<Passenger> passengers;
    private static LinkedList<Reservation> reservationQueue;


    public Hotel(HotelManager hotelManager, ArrayList<HotelStaff> hotelStaffs, ArrayList<Room> rooms, ArrayList<Passenger> passengers, LinkedList<Reservation> reservationQueue) {
        this.hotelManager = hotelManager;
        this.hotelStaffs = hotelStaffs;
        this.rooms = rooms;
        this.passengers = passengers;
        this.reservationQueue = reservationQueue;
    }


    public static void addPassengers(Passenger passenger) {
        this.passengers.add(passenger);
    }

    public void addHotelStaffs(HotelStaff hotelStaff) {
        this.hotelStaffs.add(hotelStaff);
    }

    public void addRooms(Room room) {
        this.rooms.add(room);
    }


}



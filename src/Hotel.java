import java.util.*;

public class Hotel {

    private HotelManager hotelManager;
    private static ArrayList<HotelStaff> hotelStaffs;
    private static ArrayList<Room> rooms;
    private static ArrayList<Passenger> passengers;
    private static LinkedList<Reservation> reservationQueue;

    private static int cash;


    public Hotel(HotelManager hotelManager, ArrayList<HotelStaff> hotelStaffs, ArrayList<Room> rooms, ArrayList<Passenger> passengers, LinkedList<Reservation> reservationQueue) {
        this.hotelManager = hotelManager;
        this.hotelStaffs = hotelStaffs;
        this.rooms = rooms;
        this.passengers = passengers;
        this.reservationQueue = reservationQueue;
    }


    public static void addCash(int cash){

        Hotel.cash += cash;
    }

    public static ArrayList<Room> getRooms() {
        return rooms;
    }

    public static void addPassengers(Passenger passenger) {
        Hotel.passengers.add(passenger);
    }

    public void addHotelStaffs(HotelStaff hotelStaff) {
        this.hotelStaffs.add(hotelStaff);
    }

    public  static void addRooms(Room room) {
        Hotel.rooms.add(room);
    }

    public static ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public static ArrayList<HotelStaff> getHotelStaffs() {
        return hotelStaffs;
    }

    public HotelManager getHotelManager() {
        return hotelManager;
    }

    public void setHotelManager(HotelManager hotelManager) {
        this.hotelManager = hotelManager;
    }

    public static void setHotelStaffs(ArrayList<HotelStaff> hotelStaffs) {
        Hotel.hotelStaffs = hotelStaffs;
    }

    public static void setRooms(ArrayList<Room> rooms) {
        Hotel.rooms = rooms;
    }

    public static void setPassengers(ArrayList<Passenger> passengers) {
        Hotel.passengers = passengers;
    }

    public static LinkedList<Reservation> getReservationQueue() {
        return reservationQueue;
    }

    public static void setReservationQueue(LinkedList<Reservation> reservationQueue) {
        Hotel.reservationQueue = reservationQueue;
    }
}



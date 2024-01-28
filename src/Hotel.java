import java.util.*;

public class Hotel {

    private HotelManager hotelManager;
    private static ArrayList<HotelStaff> hotelStaffs;
    private static ArrayList<Room> rooms;
    private static ArrayList<Passenger> passengers;
    private static LinkedList<Reservation> reservationQueue;
    private static int roonNumber = 1;
    private static int cash;


    public Hotel(ArrayList<HotelStaff> hotelStaffs, ArrayList<Room> rooms, ArrayList<Passenger> passengers, LinkedList<Reservation> reservationQueue) {
        this.hotelStaffs = hotelStaffs;
        this.rooms = rooms;
        this.passengers = passengers;
        this.reservationQueue = reservationQueue;
    }


    public static void salaryPay(int salary){

        Hotel.cash -= salary;
    }

    public static int getRoonNumber() {
        return roonNumber;
    }

    public static void hotelStaffDismissal(HotelStaff hotelStaff){

        hotelStaffs.remove(hotelStaff);
    }

    public static void addHotelStaff(HotelStaff hotelStaff){

        Hotel.hotelStaffs.add(hotelStaff);
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

    public static LinkedList<Reservation> getReservationQueue() {
        return reservationQueue;
    }
}



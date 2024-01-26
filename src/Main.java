import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;
public class Main {

     private static ArrayList<HotelStaff> hotelStaffs = new ArrayList<>();
     private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Passenger> passengers = new ArrayList<>();
    private static LinkedList<Reservation> reservationQueue = new LinkedList<>();

    public static void importHotelStaffData() {

        FileManager fileManager = new FileManager("HotelManagement/HotelManagement/data/HotelStaffs.csv");

        ArrayList<String> hotelStaffsData = fileManager.readFromFile();
        for (String s: hotelStaffsData) {

            String[] words = s.split(" ");

            HotelStaff hotelStaff = new HotelStaff(words[0],words[1],words[2],words[3],words[4],words[5],words[6]);
            hotelStaffs.add(hotelStaff);
        }

    }

    public static void importRoomData() {

        FileManager fileManager = new FileManager("HotelManagement/HotelManagement/data/Rooms.csv");

        ArrayList<String> roomsData = fileManager.readFromFile();
        for (String s: roomsData) {

            String[] words = s.split(" ");

            Room room = new Room(Integer.parseInt(words[0]),Integer.parseInt(words[1]),Integer.parseInt(words[2]));
            rooms.add(room);
        }

    }

    public static void importPassengerData() {

        FileManager fileManager = new FileManager("HotelManagement/HotelManagement/data/Passengers.csv");

        ArrayList<String> passengersData = fileManager.readFromFile();
        for (String s : passengersData) {

            String[] words = s.split(" ");

            Passenger passenger = new Passenger(words[0], words[1], words[2], words[3], words[4]);
            passengers.add(passenger);
        }
    }

    public static void importReservationData() {

        FileManager fileManager = new FileManager("HotelManagement/HotelManagement/data/Reservation.csv");

        ArrayList<String> reservationsData = fileManager.readFromFile();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (String s : reservationsData) {

            String[] words = s.split(" ");

            Reservation reservation = new Reservation(words[0], Integer.parseInt(words[1]), words[2], Integer.parseInt(words[3]), formatter.parse(words[4]));
            reservationQueue.push(reservation);
        }
    }


    public static void main(String[] args) {

        importPassengerData();
        importHotelStaffData();
        importRoomData();

        HotelManager hotelManager = new HotelManager("admin","admin","1111111111","admin@gamil.com","admin");

        Hotel hotel = new Hotel(hotelManager, hotelStaffs,  rooms,  passengers, reservationQueue);

        SwingUtilities.invokeLater(() -> {
            Gui.welcomePage();
        });
    }
}
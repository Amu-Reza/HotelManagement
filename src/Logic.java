import java.util.Date;

public class Logic {

    public static Passenger signup(String firstName,String lastName,String email,String password, String nationalCode){

        Passenger passenger = new Passenger(nationalCode,firstName,lastName,email,password);
        Hotel.addPassengers(passenger);
        //FileManager fileManager = new FileManager("HotelManagement/HotelManagement/data/Passengers.csv");
        //fileManager.writeToFile(passenger.toString());
        return passenger;
    }

    public static void addresevation(String passengerNationalCode, int payment, int roomNumber){
        Date date = new Date();
        Reservation reservation = new Reservation(passengerNationalCode,payment,roomNumber,date,Status.NOTOK,RoD.RESERVATION);
    }

    public static Passenger customerLogin(String email, String password){

                for (Passenger passenger : Hotel.getPassengers()){

                    if (email == passenger.getEmail() && password == passenger.getPassword()){

                        return passenger;
                    }else {
                        return null;
                    }
                }
                return null;
    }

    public static HotelStaff emploeeLogin(String email, String password){

        for (HotelStaff hotelStaff : Hotel.getHotelStaffs()){

            if (email == hotelStaff.getEmail() && password == hotelStaff.getPassword()){

                return hotelStaff;
            }else {
                return null;
            }
        }
        return null;
    }
}

public class Room {

    private int roomNumber;
    private int numberOfBeds;
    private int reservationAmount;

    private Booked booked;

    public Room(int roomNumber, int numberOfBeds, int reservationAmount, Booked booked) {
        this.roomNumber = roomNumber;
        this.numberOfBeds = numberOfBeds;
        this.reservationAmount = reservationAmount;
        this.booked = booked;
    }


    public void setReservationAmount(int reservationAmount) {
        this.reservationAmount = reservationAmount;
    }

    public Booked getBooked() {
        return booked;
    }

    public int getNumber() {

        return roomNumber;
    }

    public int getPrice() {
        return reservationAmount;
    }

    @Override
    public String toString() {
        return "Room Number=" + roomNumber +
                "   Number Of Beds=" + numberOfBeds +
                "   Reservation Amount=" + reservationAmount ;
    }
}

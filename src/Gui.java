import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gui {

     private static JFrame frame = new JFrame("Hotel Managment");


    public static void welcomePage() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel registrationPanel = new JPanel();
        registrationPanel.setLayout(new GridLayout(9, 2));

        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();

        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JLabel nationalCodeLabel = new JLabel("National Code:");
        JTextField nationalCodeField = new JTextField();


        registrationPanel.add(firstNameLabel);
        registrationPanel.add(firstNameField);

        registrationPanel.add(lastNameLabel);
        registrationPanel.add(lastNameField);

        registrationPanel.add(emailLabel);
        registrationPanel.add(emailField);

        registrationPanel.add(passwordLabel);
        registrationPanel.add(passwordField);

        registrationPanel.add(nationalCodeLabel);
        registrationPanel.add(nationalCodeField);


        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String nationalCode = nationalCodeField.getText();


                String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
                Pattern emailPattern = Pattern.compile(emailRegex);
                Matcher emailMatcher = emailPattern.matcher(email);

                if (!emailMatcher.matches()) {
                    JOptionPane.showMessageDialog(frame, "Invalid email format.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validate password
                String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
                Pattern passwordPattern = Pattern.compile(passwordRegex);
                Matcher passwordMatcher = passwordPattern.matcher(password);

                if (!passwordMatcher.matches()) {
                    JOptionPane.showMessageDialog(frame, "Password must have at least one lowercase letter, one uppercase letter, one digit, and a minimum length of 8 characters.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validate national code
                if (nationalCode.length() != 10) {
                    JOptionPane.showMessageDialog(frame, "National code must have a length of 10 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }else {

                    Passenger passenger =Logic.signup(firstName,lastName,email,password,nationalCode);
                    bottomPanel.removeAll();
                    topPanel.removeAll();
                    Gui.passengerDashboard(passenger);

                }
            }
        });

        registrationPanel.add(registerButton);

        tabbedPane.addTab("Registration", registrationPanel);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(4, 2));

        JLabel loginEmailLabel = new JLabel("Email:");
        JTextField loginEmailField = new JTextField();

        JLabel loginPasswordLabel = new JLabel("Password:");
        JPasswordField loginPasswordField = new JPasswordField();

        JLabel userTypeLabel = new JLabel("User Type:");
        JComboBox userTypeCombo = new JComboBox(new String[]{"Manager", "Employee", "Customer"});

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = loginEmailField.getText();
                String password = new String(loginPasswordField.getPassword());
                String userType = (String) userTypeCombo.getSelectedItem();

                switch (userType){

                    case "Manager":
                        break;


                    case "Employee":

                        if (Logic.emploeeLogin(email,password) != null) {
                            HotelStaff hotelStaff = Logic.emploeeLogin(email, password);
                            bottomPanel.removeAll();
                            topPanel.removeAll();
                            Gui.emploeeDashboard(hotelStaff);

                        }else {
                            JOptionPane.showMessageDialog(frame, "Email or Password is incorrect", "Error", JOptionPane.ERROR_MESSAGE);

                        }
                        break;


                    case "Customer":

                        if (Logic.customerLogin(email,password) != null) {
                            Passenger passenger = Logic.customerLogin(email, password);
                            bottomPanel.removeAll();
                            topPanel.removeAll();
                            Gui.passengerDashboard(passenger);

                        }else {
                            JOptionPane.showMessageDialog(frame, "Email or Password is incorrect", "Error", JOptionPane.ERROR_MESSAGE);

                        }
                        break;
                }

            }
        });

        loginPanel.add(loginEmailLabel);
        loginPanel.add(loginEmailField);

        loginPanel.add(loginPasswordLabel);
        loginPanel.add(loginPasswordField);

        loginPanel.add(userTypeLabel);
        loginPanel.add(userTypeCombo);

        loginPanel.add(loginButton);

        tabbedPane.addTab("Login", loginPanel);

        bottomPanel.add(tabbedPane, BorderLayout.CENTER);

        container.add(topPanel, BorderLayout.NORTH);
        container.add(bottomPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static void passengerDashboard (Passenger passenger){

         JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel nameLabel = new JLabel("Name: " + passenger.getFirstName() + " " + passenger.getLastName());
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPanel.add(nameLabel, constraints);

        JLabel emailLabel = new JLabel("Email: "+ passenger.getEmail());
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(emailLabel, constraints);

        JButton reservationButton = new JButton("Reservation");
        constraints.gridx = 0;
        constraints.gridy = 2;
        mainPanel.add(reservationButton, constraints);

        JButton deliverButton = new JButton("Deliver");
        constraints.gridx = 2;
        constraints.gridy = 2;
        mainPanel.add(deliverButton, constraints);

        reservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                passengerRoomList(passenger);
            }
        });


        deliverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mainPanel.removeAll();
                passengerDeliverList(passenger);

            }
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private static void passengerRoomList(Passenger passenger){

        DefaultListModel<Room> listModel = new DefaultListModel<>();

        Hotel.addRooms(new Room(12,2,200, Booked.NOTBOOKED));
        Hotel.addRooms(new Room(11,1,400, Booked.NOTBOOKED));
        Hotel.addRooms(new Room(13,3,5555, Booked.NOTBOOKED));
        Hotel.addRooms(new Room(10,5,200, Booked.NOTBOOKED));

        for (Room room : Hotel.getRooms()) {

            if (room.getBooked() == (Booked.NOTBOOKED))
            listModel.addElement(room);
        }

        JList<Room> list = new JList<>(listModel);

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    Room room = list.getSelectedValue();
                    if (room != null) {

                        JOptionPane.showMessageDialog(Gui.frame,
                                "You have selected: " + room.toString() + "\n" +
                                        "Price: " + room.getPrice() + "\n" +
                                        "Number: " + room.getNumber(),
                                "Confirmation",
                                JOptionPane.INFORMATION_MESSAGE);
                        Logic.addresevation(passenger.getNationalCode(),room.getPrice(),room.getNumber());
                        Hotel.addCash(room.getPrice());
                        list.removeAll();
                        passengerDashboard(passenger);
                    }
                }
            }
        });


        frame.getContentPane().add(new JScrollPane(list));
        frame.setVisible(true);
    }

    private static void passengerDeliverList(Passenger passenger){

        DefaultListModel<Reservation> listModel = new DefaultListModel<>();
        for (Reservation reservation : passenger.getReservations()) {

            if (reservation.getStatus() == Status.OK && reservation.getRoD() == RoD.RESERVATION)
                listModel.addElement(reservation);
        }

        JList<Reservation> list = new JList<>(listModel);

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    Reservation reservation = list.getSelectedValue();
                    if (reservation != null) {

                        JOptionPane.showMessageDialog(Gui.frame,
                                "You have selected: " + reservation.toString() + "\n",
                                "Confirmation",
                                JOptionPane.INFORMATION_MESSAGE);
                        reservation.setRoD(RoD.DELIVER);
                        list.removeAll();
                        passengerDashboard(passenger);
                    }
                }
            }
        });


        frame.getContentPane().add(new JScrollPane(list));
        frame.setVisible(true);
    }

    private static void emploeeDashboard(HotelStaff hotelStaff) {

        JTabbedPane tabbedPane;
        ArrayList<Reservation> pendingItems = new ArrayList<>();
        ArrayList<Reservation> okItems = new ArrayList<>();

        for (Reservation reservation : Hotel.getReservationQueue()){

            if (reservation.getStatus() == Status.PENDING){

                pendingItems.add(reservation);
            }else {

                okItems.add(reservation);
            }
        }

        JPanel okPanel = new JPanel();
        okPanel.setLayout(new BorderLayout());

        JList<Reservation>okList = new JList<>((ListModel) okItems);

        JPanel pendingPanel = new JPanel();
        pendingPanel.setLayout(new BorderLayout());

        JList<Reservation>pendingList = new JList<>((ListModel) pendingItems);
        pendingList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (!e.getValueIsAdjusting()) {

                    Reservation reservation = pendingList.getSelectedValue();
                    if (reservation != null) {

                        JOptionPane.showMessageDialog(Gui.frame,
                                "You have selected: " + reservation.toString() + "\n",
                                "Confirmation",
                                JOptionPane.INFORMATION_MESSAGE);
                        reservation.setStatus(Status.OK);
                        reservation.setEmployeePersonnelCode(hotelStaff.getPersonnelCode());
                        pendingPanel.removeAll();
                        okPanel.removeAll();
                        emploeeDashboard(hotelStaff);

                    }
                }

            }
        });


        okPanel.add(new JScrollPane(okList), BorderLayout.CENTER);
        pendingPanel.add(new JScrollPane(pendingList), BorderLayout.CENTER);


        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("pending", pendingPanel);
        tabbedPane.addTab("not pending", okPanel);

        frame.add(tabbedPane, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}

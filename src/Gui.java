import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
                        if (Logic.managerLogin(email,password)) {
                            HotelStaff hotelStaff = Logic.emploeeLogin(email, password);
                            bottomPanel.removeAll();
                            topPanel.removeAll();
                            Gui.managerDashboard();

                        }else {
                            JOptionPane.showMessageDialog(frame, "Email or Password is incorrect", "Error", JOptionPane.ERROR_MESSAGE);

                        }
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

    private static void managerDashboard() {

        JPanel managerPanel = new JPanel();
        managerPanel.setLayout(new GridLayout(0, 1));

        String[] buttonLabels = {"Reservations", "Changing employee salaries", "Employee recruitment", "Employee dismissal", "Price change", "New room", "salary payment","Exit"};

        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            final int index = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton sourceButton = (JButton) e.getSource();
                    switch (sourceButton.getText()){

                        case "Reservations":
                            managerPanel.removeAll();
                            managerResevation();
                            break;

                        case "Changing employee salaries":
                            managerPanel.removeAll();
                            managerChangingEmployeeSalaries();
                            break;

                        case "Employee recruitment":
                            managerPanel.removeAll();
                            managerRecruitment();
                            break;

                        case "Employee dismissal":
                            managerPanel.removeAll();
                            managerDismissal();
                            break;

                        case "Price change":
                            managerPanel.removeAll();
                            managerChangRoomPrice();
                            break;

                        case "New room":
                            managerPanel.removeAll();
                            managerNewRoom();
                            break;

                        case "salary payment":

                            for (HotelStaff hotelStaff: Hotel.getHotelStaffs()){

                                Hotel.salaryPay(Integer.parseInt(hotelStaff.getSalary()));
                                hotelStaff.setAccountBalance(hotelStaff.getSalary()+hotelStaff.getAccountBalance());
                            }

                            JOptionPane.showMessageDialog(Gui.frame,
                                    "All paid",
                                    "Confirmation",
                                    JOptionPane.INFORMATION_MESSAGE);

                            break;

                        case "Exit":
                            managerPanel.removeAll();
                            welcomePage();
                    }
                }
            });
            managerPanel.add(button);
        }

        frame.add(managerPanel);
        frame.setVisible(true);
    }

    private static void managerResevation(){

        DefaultListModel<Reservation> listModel = new DefaultListModel<>();
        for (Reservation reservation : Hotel.getReservationQueue()) {
            listModel.addElement(reservation);
        }

        JList<Reservation> list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        JPanel resevationPanel = new JPanel();
        resevationPanel.add(scrollPane);

        frame.add(resevationPanel);
        frame.setVisible(true);
    }

    private static void managerChangingEmployeeSalaries(){

        JPanel managerPanel = new JPanel();
        managerPanel.setLayout(new BorderLayout());

        JList<HotelStaff> hotelStaffList = new JList<>((ListModel) Hotel.getHotelStaffs());
        hotelStaffList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(hotelStaffList);
        managerPanel.add(scrollPane, BorderLayout.CENTER);

        JTextField textField = new JTextField(10);
        managerPanel.add(textField, BorderLayout.SOUTH);

        JButton button = new JButton("Enter Value");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = hotelStaffList.getSelectedIndex();
                if (selectedIndex != -1) {
                    HotelStaff selectedOption = hotelStaffList.getSelectedValue();
                    String textFieldValue = textField.getText();
                    JOptionPane.showMessageDialog(frame, "Selected option: " + selectedOption + ", numerical value: " + textFieldValue);
                    selectedOption.setSalary(textFieldValue);
                    managerPanel.removeAll();
                    scrollPane.removeAll();
                    Gui.managerDashboard();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select an option.");
                }
            }
        });
        managerPanel.add(button, BorderLayout.EAST);

        frame.add(managerPanel);
        frame.setVisible(true);
    }

        private static void managerRecruitment(){

            JPanel recruitmentPanel = new JPanel();
            recruitmentPanel.setLayout(new GridLayout(7, 2));

            JLabel nameLabel = new JLabel("first Name:");
            JTextField firstName = new JTextField(15);
            recruitmentPanel.add(nameLabel);
            recruitmentPanel.add(firstName);

            JLabel lastNameLabel = new JLabel("last Name:");
            JTextField lastName = new JTextField(15);
            recruitmentPanel.add(lastNameLabel);
            recruitmentPanel.add(lastName);

            JLabel personalCodeLabel = new JLabel("Personal Code:");
            JTextField personalCode = new JTextField(15);
            recruitmentPanel.add(personalCodeLabel);
            recruitmentPanel.add(personalCode);

            JLabel emailLabel = new JLabel("Email:");
            JTextField emailField = new JTextField(15);
            recruitmentPanel.add(emailLabel);
            recruitmentPanel.add(emailField);

            JLabel passwordLabel = new JLabel("Password:");
            JTextField passwordField = new JPasswordField(15);
            recruitmentPanel.add(passwordLabel);
            recruitmentPanel.add(passwordField);

            JLabel monthlySalaryLabel = new JLabel("Monthly Salary:");
            JTextField monthlySalaryField = new JTextField(15);
            recruitmentPanel.add(monthlySalaryLabel);
            recruitmentPanel.add(monthlySalaryField);

            JLabel accountBalanceLabel = new JLabel("Account Balance:");
            JTextField accountBalanceField = new JTextField(15);
            recruitmentPanel.add(accountBalanceLabel);
            recruitmentPanel.add(accountBalanceField);

            JButton registerButton = new JButton("Register");
            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String firstNamet = firstName.getText();
                    String lastNamet = lastName.getText();
                    String personnelCode = personalCode.getText();
                    String email = emailField.getText();
                    String password = passwordField.getText();
                    String monthlySalary = monthlySalaryField.getText();
                    String accountBalance = accountBalanceField.getText();

                    JOptionPane.showMessageDialog(frame, "Name: " + firstNamet + "\nLast name: " + lastNamet + "\nPersonnel Code: " + personnelCode +
                            "\nEmail: " + email + "\nPassword: " + password + "\nMonthly Salary: " + monthlySalary + "\nAccount Balance: " + accountBalance);

                    HotelStaff hotelStaff = new HotelStaff(firstNamet,lastNamet,personnelCode,email,password,monthlySalary,accountBalance);
                    Hotel.addHotelStaff(hotelStaff);

                    recruitmentPanel.removeAll();
                    managerDashboard();
                }
            });
            recruitmentPanel.add(new JLabel("recruitmen"));
            recruitmentPanel.add(registerButton);

            frame.add(recruitmentPanel);
            frame.setVisible(true);
        }

        private static void managerDismissal(){

            DefaultListModel<HotelStaff> listModel = new DefaultListModel<>();
            for (HotelStaff hotelStaff : Hotel.getHotelStaffs()) {
                listModel.addElement(hotelStaff);
            }

            JList<HotelStaff> hotelStaffList = new JList<>(listModel);
            JScrollPane scrollPane = new JScrollPane(hotelStaffList);

            JPanel dismissalPanel = new JPanel();
            dismissalPanel.add(scrollPane);

            hotelStaffList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {

                    if (!e.getValueIsAdjusting()) {

                        HotelStaff hotelStaff = hotelStaffList.getSelectedValue();
                        if (hotelStaff != null) {

                            JOptionPane.showMessageDialog(Gui.frame,
                                    "You have selected: " + hotelStaffList.toString() + "\n",
                                    "Confirmation",
                                    JOptionPane.INFORMATION_MESSAGE);
                            Hotel.hotelStaffDismissal(hotelStaff);

                            dismissalPanel.removeAll();
                            managerDashboard();
                        }
                    }

                }
            });



            frame.add(dismissalPanel);
            frame.setVisible(true);
        }

    private static void managerChangRoomPrice(){

        JPanel managerPanel = new JPanel();
        managerPanel.setLayout(new BorderLayout());

        JList<Room> roomList = new JList<>((ListModel) Hotel.getRooms());
        roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(roomList);
        managerPanel.add(scrollPane, BorderLayout.CENTER);

        JTextField textField = new JTextField(10);
        managerPanel.add(textField, BorderLayout.SOUTH);

        JButton button = new JButton("Enter Price");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = roomList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Room selectedOption = roomList.getSelectedValue();
                    String textFieldValue = textField.getText();
                    JOptionPane.showMessageDialog(frame, "Selected option: " + selectedOption + ", numerical Price: " + textFieldValue);
                    selectedOption.setReservationAmount(Integer.parseInt(textFieldValue));
                    managerPanel.removeAll();
                    scrollPane.removeAll();
                    Gui.managerDashboard();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select an room.");
                }
            }
        });
        managerPanel.add(button, BorderLayout.EAST);

        frame.add(managerPanel);
        frame.setVisible(true);
    }

    private static void managerNewRoom() {

        JPanel roomPanel = new JPanel();
        roomPanel.setLayout(new GridLayout(3, 2));

        JLabel bedsLabel = new JLabel("Number of Beds: ");
        JTextField bedsField = new JTextField(10);
        JLabel priceLabel = new JLabel("Price per Night: ");
        JTextField priceField = new JTextField(10);
        JButton submitButton = new JButton("Submit");

        roomPanel.add(bedsLabel);
        roomPanel.add(bedsField);
        roomPanel.add(priceLabel);
        roomPanel.add(priceField);
        roomPanel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int beds = Integer.parseInt(bedsField.getText());
                int price = Integer.parseInt(priceField.getText());

                Room room = new Room(Hotel.getRoonNumber()+1,beds,price,Booked.NOTBOOKED);
                Hotel.addRooms(room);
                JOptionPane.showMessageDialog(Gui.frame,
                        "Room added",
                        "Confirmation",
                        JOptionPane.INFORMATION_MESSAGE);

                frame.remove(roomPanel);
                managerDashboard();
            }
        });
        frame.add(roomPanel);
        frame.setVisible(true);
    }

}

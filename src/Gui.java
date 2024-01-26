import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gui {

    static void welcomePage() {
        JFrame frame = new JFrame("Welcom");
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

                    Passenger passenger = new Passenger(nationalCode,firstName,lastName,email,password);
                    Hotel.addPassengers(passenger);
                    FileManager fileManager = new FileManager("HotelManagement/HotelManagement/data/Passengers.csv");
                    fileManager.writeToFile(passenger.toString());

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
                // Validate and log in the user
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

}

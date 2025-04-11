import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;
import javax.swing.border.*;
//import com.intellij.ide.ui.*;
//import com.intellij.ide.ui.*;
//import com.intellij.ide.ui.*;

/*
 * Created by JFormDesigner on Sun Mar 17 16:27:30 IST 2024
 */



/**
 * @author HP
 */
public class registrationform extends JFrame {
    public registrationform() {
        initComponents();
    }

    private void btnRegister(ActionEvent e) {
        try{
            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");

// Establish connection
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "lucifer555");


            String FirstName = txtfirstname.getText();
            String LastName = txtLastName.getText();
            String Email = txtEmail.getText();
            String Phone = txtPhone.getText();
            String Password = txtCreatePassword.getText();
            String ConfirmPassword = txtConfirmPassword.getText();

            if (!isValidEmail(Email)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Invalid Email", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!isValidPhoneNumber(Phone)) {
                JOptionPane.showMessageDialog(this, "Please enter a valid 10-digit phone number.", "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!isValidPassword(Password)) {
                JOptionPane.showMessageDialog(this, "Enter valid password.", "Invalid Password", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!Password.equals(ConfirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match.", "Password Mismatch", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "insert into RegisteredUsers(firstname,lastname,email,phone,pass,customer_id) values(?,?,?,?,?,seq_customer_id.nextval)";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, FirstName);
            st.setString(2, LastName);
            st.setString(3, Email);
            st.setString(4, Phone);
            st.setString(5, Password);
            int result = st.executeUpdate();

            if(result > 0){
                System.out.println("Successfully inserted");
                lblResult.setText("Registered successfully");
                lblResult.setForeground(Color.green);
            }
            else{
                System.out.println("unsuccessful insertion");
                lblResult.setText("Not registered");
                lblResult.setForeground(Color.red);
            }
            con.close();


        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    //Phone Number validatoon (10 digit)
    private boolean isValidPhoneNumber(String phone) {
        // Check if the phone number is exactly 10 digits
        return phone.matches("\\d{10}");
    }

    // Password validation method
    private boolean isValidPassword(String password) {
        // Define criteria for a valid password
        int minLength = 8;  // Minimum length of the password
        boolean hasUppercase = false;  // At least one uppercase letter
        boolean hasLowercase = false;  // At least one lowercase letter
        boolean hasDigit = false;       // At least one digit
        boolean hasSpecialChar = false; // At least one special character

        // Check the length of the password
        if (password.length() < minLength) {
            return false;
        }

        // Check each character of the password
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else {
                // Check for special characters
                // You can define your set of special characters as per your requirement
                // Here, we are considering special characters !@#$%^&*()-_+=
                if ("!@#$%^&*()-_+=".indexOf(ch) != -1) {
                    hasSpecialChar = true;
                }
            }
        }

        // Check if all criteria are met
        return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
    }

    private void btnLoginPage(ActionEvent e) {
        LoginPage LoginFrame = new LoginPage();

        LoginFrame.setVisible(true);
        this.dispose();
    }

    private void btnCancel(ActionEvent e) {
        dispose();
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Piyush
        dialogPane = new JPanel();
        registerpanel = new JPanel();
        lblnewusericon = new JLabel();
        lblnewuseregi = new JLabel();
        lbllastname = new JLabel();
        lblEmail = new JLabel();
        lblPhone = new JLabel();
        lblCreatePassword = new JLabel();
        lblConfirmPassword = new JLabel();
        txtEmail = new JTextField();
        txtPhone = new JTextField();
        txtCreatePassword = new JPasswordField();
        txtConfirmPassword = new JTextField();
        txtLastName = new JTextField();
        lblResult = new JLabel();
        btnCancel = new JButton();
        btnRegister = new JButton();
        lblHaveAcc = new JLabel();
        btnLoginPage = new JButton();
        lblfirstname = new JLabel();
        txtfirstname = new JTextField();
        logopanel = new JPanel();
        lbllogo = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(
            new javax.swing.border.EmptyBorder(0,0,0,0), "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e"
            ,javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM
            ,new java.awt.Font("Dialo\u0067",java.awt.Font.BOLD,12)
            ,java.awt.Color.red),dialogPane. getBorder()));dialogPane. addPropertyChangeListener(
            new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
            ){if("borde\u0072".equals(e.getPropertyName()))throw new RuntimeException()
            ;}});
            dialogPane.setLayout(null);

            //======== registerpanel ========
            {
                registerpanel.setBackground(Color.black);
                registerpanel.setLayout(null);

                //---- lblnewusericon ----
                lblnewusericon.setIcon(new ImageIcon(getClass().getResource("/new-account32px.png")));
                registerpanel.add(lblnewusericon);
                lblnewusericon.setBounds(new Rectangle(new Point(10, 0), lblnewusericon.getPreferredSize()));

                //---- lblnewuseregi ----
                lblnewuseregi.setText("New User Registration");
                lblnewuseregi.setFont(new Font("Inter", Font.BOLD, 18));
                lblnewuseregi.setForeground(Color.white);
                registerpanel.add(lblnewuseregi);
                lblnewuseregi.setBounds(new Rectangle(new Point(100, 5), lblnewuseregi.getPreferredSize()));

                //---- lbllastname ----
                lbllastname.setText("Last Name");
                lbllastname.setFont(lbllastname.getFont().deriveFont(lbllastname.getFont().getStyle() | Font.BOLD));
                lbllastname.setForeground(Color.white);
                registerpanel.add(lbllastname);
                lbllastname.setBounds(new Rectangle(new Point(10, 101), lbllastname.getPreferredSize()));

                //---- lblEmail ----
                lblEmail.setText("Email");
                lblEmail.setFont(lblEmail.getFont().deriveFont(lblEmail.getFont().getStyle() | Font.BOLD));
                lblEmail.setForeground(Color.white);
                registerpanel.add(lblEmail);
                lblEmail.setBounds(new Rectangle(new Point(10, 141), lblEmail.getPreferredSize()));

                //---- lblPhone ----
                lblPhone.setText("Phone");
                lblPhone.setFont(lblPhone.getFont().deriveFont(lblPhone.getFont().getStyle() | Font.BOLD));
                lblPhone.setForeground(Color.white);
                registerpanel.add(lblPhone);
                lblPhone.setBounds(new Rectangle(new Point(10, 181), lblPhone.getPreferredSize()));

                //---- lblCreatePassword ----
                lblCreatePassword.setText("Create Password");
                lblCreatePassword.setFont(lblCreatePassword.getFont().deriveFont(lblCreatePassword.getFont().getStyle() | Font.BOLD));
                lblCreatePassword.setForeground(Color.white);
                registerpanel.add(lblCreatePassword);
                lblCreatePassword.setBounds(new Rectangle(new Point(10, 221), lblCreatePassword.getPreferredSize()));

                //---- lblConfirmPassword ----
                lblConfirmPassword.setText("Confirm Password");
                lblConfirmPassword.setFont(lblConfirmPassword.getFont().deriveFont(lblConfirmPassword.getFont().getStyle() | Font.BOLD));
                lblConfirmPassword.setForeground(Color.white);
                registerpanel.add(lblConfirmPassword);
                lblConfirmPassword.setBounds(new Rectangle(new Point(10, 261), lblConfirmPassword.getPreferredSize()));
                registerpanel.add(txtEmail);
                txtEmail.setBounds(130, 131, 275, 30);
                registerpanel.add(txtPhone);
                txtPhone.setBounds(130, 171, 275, 30);
                registerpanel.add(txtCreatePassword);
                txtCreatePassword.setBounds(130, 211, 275, 30);
                registerpanel.add(txtConfirmPassword);
                txtConfirmPassword.setBounds(130, 251, 275, 30);
                registerpanel.add(txtLastName);
                txtLastName.setBounds(130, 91, 275, 30);

                //---- lblResult ----
                lblResult.setText("Registration");
                lblResult.setForeground(Color.white);
                registerpanel.add(lblResult);
                lblResult.setBounds(20, 291, 380, lblResult.getPreferredSize().height);

                //---- btnCancel ----
                btnCancel.setText("Cancel");
                btnCancel.addActionListener(e -> btnCancel(e));
                registerpanel.add(btnCancel);
                btnCancel.setBounds(260, 321, 110, btnCancel.getPreferredSize().height);

                //---- btnRegister ----
                btnRegister.setText("Register");
                btnRegister.addActionListener(e -> btnRegister(e));
                registerpanel.add(btnRegister);
                btnRegister.setBounds(35, 321, 110, btnRegister.getPreferredSize().height);

                //---- lblHaveAcc ----
                lblHaveAcc.setText("Already have an Account?");
                lblHaveAcc.setForeground(Color.white);
                registerpanel.add(lblHaveAcc);
                lblHaveAcc.setBounds(new Rectangle(new Point(145, 366), lblHaveAcc.getPreferredSize()));

                //---- btnLoginPage ----
                btnLoginPage.setText("Login");
                btnLoginPage.addActionListener(e -> btnLoginPage(e));
                registerpanel.add(btnLoginPage);
                btnLoginPage.setBounds(320, 361, btnLoginPage.getPreferredSize().width, 30);

                //---- lblfirstname ----
                lblfirstname.setText("First Name");
                lblfirstname.setFont(lblfirstname.getFont().deriveFont(lblfirstname.getFont().getStyle() | Font.BOLD));
                lblfirstname.setForeground(Color.white);
                registerpanel.add(lblfirstname);
                lblfirstname.setBounds(10, 61, 75, 16);
                registerpanel.add(txtfirstname);
                txtfirstname.setBounds(130, 51, 275, 30);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < registerpanel.getComponentCount(); i++) {
                        Rectangle bounds = registerpanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = registerpanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    registerpanel.setMinimumSize(preferredSize);
                    registerpanel.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(registerpanel);
            registerpanel.setBounds(285, 15, 415, 405);

            //======== logopanel ========
            {
                logopanel.setBackground(new Color(0xff6633));
                logopanel.setLayout(null);

                //---- lbllogo ----
                lbllogo.setIcon(new ImageIcon(getClass().getResource("/Logo Preview (1).jpg")));
                logopanel.add(lbllogo);
                lbllogo.setBounds(new Rectangle(new Point(45, 85), lbllogo.getPreferredSize()));

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < logopanel.getComponentCount(); i++) {
                        Rectangle bounds = logopanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = logopanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    logopanel.setMinimumSize(preferredSize);
                    logopanel.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(logopanel);
            logopanel.setBounds(5, 15, 280, 405);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < dialogPane.getComponentCount(); i++) {
                    Rectangle bounds = dialogPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = dialogPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                dialogPane.setMinimumSize(preferredSize);
                dialogPane.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Piyush
    private JPanel dialogPane;
    private JPanel registerpanel;
    private JLabel lblnewusericon;
    private JLabel lblnewuseregi;
    private JLabel lbllastname;
    private JLabel lblEmail;
    private JLabel lblPhone;
    private JLabel lblCreatePassword;
    private JLabel lblConfirmPassword;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JPasswordField txtCreatePassword;
    private JTextField txtConfirmPassword;
    private JTextField txtLastName;
    private JLabel lblResult;
    private JButton btnCancel;
    private JButton btnRegister;
    private JLabel lblHaveAcc;
    private JButton btnLoginPage;
    private JLabel lblfirstname;
    private JTextField txtfirstname;
    private JPanel logopanel;
    private JLabel lbllogo;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on



}

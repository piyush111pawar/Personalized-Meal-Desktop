/*
 * Created by JFormDesigner on Mon Mar 18 15:34:20 IST 2024
 */

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.border.*;
//import com.intellij.ide.ui.*;
//import com.intellij.ide.ui.*;
//import com.intellij.ide.ui.*;
//import com.intellij.ide.ui.*;
/**
 * @author HP
 */
public class LoginPage extends JFrame {
    public LoginPage() {
        initComponents();
    }





    private void btnSignUppage(ActionEvent e) {
        registrationform f = new registrationform();

        f.setVisible(true);
        this.dispose();
    }

    private void btnLogin(ActionEvent e) {
        try{
            String Email = txtPhoneorEmail.getText();
            String Password = txtPassword.getText();

            user = getAuthenticatedUser(Email, Password);

            if (user != null) {
                System.out.println("Successful Authentication of: " + user.FirstName + " " + user.LastName);
                System.out.println("        Email: " + user.Email);
                System.out.println("        Phone: " + user.Phone);
                testdashboard testdash = new testdashboard(user);

                testdash.setVisible(true);
                this.dispose();
            } else {
                System.out.println("Authentication failed");
                JOptionPane.showMessageDialog(this, "Please enter a valid email address/password.", "Invalid Email/Password", JOptionPane.ERROR_MESSAGE);
            }
        }catch (Exception ex){

        }
    }

    public index user;
    private index getAuthenticatedUser(String Email, String Password){
        index user = null;
        try {
            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");

// Establish connection
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "lucifer555");
            String query = "select * from RegisteredUsers where email=? AND pass=?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, Email);
            st.setString(2, Password);
            ResultSet result = st.executeQuery();

            if(result.next()){
                user = new index();
                user.FirstName = result.getString("firstname");
                user.LastName = result.getString("lastname");
                user.Email = result.getString("email");
                user.Phone = String.valueOf(result.getInt("phone"));
                user.CustomerID = result.getInt("customer_id");
                user.Password = result.getString("pass");
            }
            con.close();


        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    private void btnCancel(ActionEvent e) {
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Piyush
        dialogPane = new JPanel();
        loginpanel = new JPanel();
        btnLogin = new JButton();
        btnCancel = new JButton();
        logopanel = new JPanel();
        label1 = new JLabel();
        lblLoginIcon = new JLabel();
        lblLoginTitle = new JLabel();
        lblEmail = new JLabel();
        lblPassword = new JLabel();
        txtPhoneorEmail = new JTextField();
        txtPassword = new JPasswordField();
        lblNewUser = new JLabel();
        btnSignUppage = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.
            border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder.CENTER
            ,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog",java.awt.Font
            .BOLD,12),java.awt.Color.red),dialogPane. getBorder()));dialogPane. addPropertyChangeListener(
            new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order"
            .equals(e.getPropertyName()))throw new RuntimeException();}});
            dialogPane.setLayout(new BorderLayout());

            //======== loginpanel ========
            {
                loginpanel.setBackground(Color.black);
                loginpanel.setMinimumSize(new Dimension(655, 365));
                loginpanel.setPreferredSize(new Dimension(665, 365));
                loginpanel.setLayout(null);

                //---- btnLogin ----
                btnLogin.setText("Login");
                btnLogin.addActionListener(e -> btnLogin(e));
                loginpanel.add(btnLogin);
                btnLogin.setBounds(335, 265, 110, btnLogin.getPreferredSize().height);

                //---- btnCancel ----
                btnCancel.setText("Cancel");
                btnCancel.addActionListener(e -> btnCancel(e));
                loginpanel.add(btnCancel);
                btnCancel.setBounds(515, 265, 110, btnCancel.getPreferredSize().height);

                //======== logopanel ========
                {
                    logopanel.setBackground(new Color(0xff6633));
                    logopanel.setLayout(null);

                    //---- label1 ----
                    label1.setIcon(new ImageIcon(getClass().getResource("/Logo Preview (1).jpg")));
                    logopanel.add(label1);
                    label1.setBounds(new Rectangle(new Point(40, 80), label1.getPreferredSize()));
                }
                loginpanel.add(logopanel);
                logopanel.setBounds(0, 0, 280, 365);

                //---- lblLoginIcon ----
                lblLoginIcon.setIcon(new ImageIcon(getClass().getResource("/login-.png")));
                loginpanel.add(lblLoginIcon);
                lblLoginIcon.setBounds(new Rectangle(new Point(290, 5), lblLoginIcon.getPreferredSize()));

                //---- lblLoginTitle ----
                lblLoginTitle.setText("LOGIN");
                lblLoginTitle.setFont(lblLoginTitle.getFont().deriveFont(lblLoginTitle.getFont().getStyle() | Font.BOLD, lblLoginTitle.getFont().getSize() + 18f));
                lblLoginTitle.setForeground(Color.white);
                loginpanel.add(lblLoginTitle);
                lblLoginTitle.setBounds(430, 5, 110, lblLoginTitle.getPreferredSize().height);

                //---- lblEmail ----
                lblEmail.setText("Email");
                lblEmail.setForeground(Color.white);
                loginpanel.add(lblEmail);
                lblEmail.setBounds(new Rectangle(new Point(295, 85), lblEmail.getPreferredSize()));

                //---- lblPassword ----
                lblPassword.setText("Password");
                lblPassword.setForeground(Color.white);
                loginpanel.add(lblPassword);
                lblPassword.setBounds(new Rectangle(new Point(295, 145), lblPassword.getPreferredSize()));
                loginpanel.add(txtPhoneorEmail);
                txtPhoneorEmail.setBounds(405, 75, 250, 30);
                loginpanel.add(txtPassword);
                txtPassword.setBounds(405, 135, 250, 30);

                //---- lblNewUser ----
                lblNewUser.setText("New User?");
                lblNewUser.setForeground(Color.white);
                loginpanel.add(lblNewUser);
                lblNewUser.setBounds(new Rectangle(new Point(520, 320), lblNewUser.getPreferredSize()));

                //---- btnSignUppage ----
                btnSignUppage.setText("SignUp");
                btnSignUppage.addActionListener(e -> btnSignUppage(e));
                loginpanel.add(btnSignUppage);
                btnSignUppage.setBounds(new Rectangle(new Point(580, 315), btnSignUppage.getPreferredSize()));

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < loginpanel.getComponentCount(); i++) {
                        Rectangle bounds = loginpanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = loginpanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    loginpanel.setMinimumSize(preferredSize);
                    loginpanel.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(loginpanel, BorderLayout.CENTER);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Piyush
    private JPanel dialogPane;
    private JPanel loginpanel;
    private JButton btnLogin;
    private JButton btnCancel;
    private JPanel logopanel;
    private JLabel label1;
    private JLabel lblLoginIcon;
    private JLabel lblLoginTitle;
    private JLabel lblEmail;
    private JLabel lblPassword;
    private JTextField txtPhoneorEmail;
    private JPasswordField txtPassword;
    private JLabel lblNewUser;
    private JButton btnSignUppage;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

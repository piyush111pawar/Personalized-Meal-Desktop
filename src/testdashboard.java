import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Tue Mar 19 16:47:40 IST 2024
 */



/**
 * @author HP
 */
public class testdashboard extends JFrame {
    private index LoggedInUser;

    //private searchingpanel searchPanel;
    public testdashboard(index user) {
        this.LoggedInUser = user;
        initComponents();
        txtProfileFirstName.setEditable(false);
        txtProfileLastName.setEditable(false);
        txtProfileEmail.setEditable(false);
        txtProfilePhone.setEditable(false);
        txtProfilePassword.setEditable(false);
        txtProfileGender.setEditable(false);

        updatewelcomemessage(user.FirstName);
        updateprofilepage(user);

//        searchPanel = new searchingpanel();
    }
    private void updatewelcomemessage(String firstname){
        lblWelcomMessage.setText("Welcome "+firstname);
    }
    private void logout() {
        // Close the current dashboard window
        dispose();

        // Open the login page again
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
    }
    private void updateprofilepage(index user){
        txtProfileFirstName.setText(user.FirstName);
        txtProfileLastName.setText(user.LastName);
        txtProfileEmail.setText(user.Email);
        txtProfilePhone.setText(user.Phone);
        txtProfilePassword.setText(user.Password);
    }

    private void btnSearch(ActionEvent e) {
        // TODO add your code here
        tabbedPane1.setSelectedIndex(1);
    }

    private void btnMyaccountMouseClicked(MouseEvent e) {
        // TODO add your code here
        tabbedPane1.setSelectedIndex(2);
    }

    private void btnCart(ActionEvent e) {
        // TODO add your code here
        tabbedPane1.setSelectedIndex(3);
    }

    private void lblLogoMouseClicked(MouseEvent e) {
        // TODO add your code here
        tabbedPane1.setSelectedIndex(0);
    }

    private void btnLogout(ActionEvent e) {
        // TODO add your code here
        logout();
    }






    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Piyush
        panel2 = new JPanel();
        lblLogo = new JLabel();
        lblWelcomMessage = new JLabel();
        btnSearch = new JButton();
        btnMyaccount = new JButton();
        btnCart = new JButton();
        panel3 = new JPanel();
        tabbedPane1 = new JTabbedPane();
        panelHome = new JPanel();
        label2 = new JLabel();
        panelSearch = new JPanel();
        lblSearchicon = new JLabel();
        txtSearch = new JTextField();
        panelMyAccount = new JPanel();
        tabbedPane2 = new JTabbedPane();
        panelMyOrders = new JPanel();
        panelMyAddresses = new JPanel();
        panelYourProfile = new JPanel();
        lblprofilefirstname = new JLabel();
        txtProfileFirstName = new JTextArea();
        lblprofilelastname = new JLabel();
        txtProfileLastName = new JTextArea();
        lblprofileemial = new JLabel();
        txtProfileEmail = new JTextArea();
        lblprofilephone = new JLabel();
        txtProfilePhone = new JTextArea();
        lblprofilepassword = new JLabel();
        txtProfilePassword = new JPasswordField();
        lblprofilegender = new JLabel();
        txtProfileGender = new JTextArea();
        panelLogout = new JPanel();
        btnLogout = new JButton();
        panelCart = new JPanel();

        //======== this ========
        setPreferredSize(new Dimension(785, 485));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel2 ========
        {
            panel2.setBackground(new Color(0x6666ff));
            panel2.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing.
            border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border . TitledBorder. CENTER
            ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font
            . BOLD ,12 ) ,java . awt. Color .red ) ,panel2. getBorder () ) ); panel2. addPropertyChangeListener(
            new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r"
            .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
            panel2.setLayout(new GridLayoutManager(1, 7, new Insets(0, 0, 0, 0), 0, 0));

            //---- lblLogo ----
            lblLogo.setIcon(new ImageIcon(getClass().getResource("/logo for nav Preview (1).jpg")));
            lblLogo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lblLogo.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    lblLogoMouseClicked(e);
                }
            });
            panel2.add(lblLogo, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));

            //---- lblWelcomMessage ----
            lblWelcomMessage.setText("Welcome User");
            lblWelcomMessage.setFont(new Font("Segoe UI", Font.BOLD, 16));
            panel2.add(lblWelcomMessage, new GridConstraints(0, 1, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));

            //---- btnSearch ----
            btnSearch.setText("Search");
            btnSearch.setIcon(new ImageIcon(getClass().getResource("/search 24x24.png")));
            btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnSearch.addActionListener(e -> {
			btnSearch(e);
			btnSearch(e);
		});
            panel2.add(btnSearch, new GridConstraints(0, 2, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));

            //---- btnMyaccount ----
            btnMyaccount.setText("My Account");
            btnMyaccount.setIcon(new ImageIcon(getClass().getResource("/my account 2 24x24.png")));
            btnMyaccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnMyaccount.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnMyaccountMouseClicked(e);
                }
            });
            panel2.add(btnMyaccount, new GridConstraints(0, 3, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));

            //---- btnCart ----
            btnCart.setText("Cart");
            btnCart.setIcon(new ImageIcon(getClass().getResource("/cart 24x24.png")));
            btnCart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnCart.addActionListener(e -> btnCart(e));
            panel2.add(btnCart, new GridConstraints(0, 5, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));
        }
        contentPane.add(panel2, BorderLayout.NORTH);

        //======== panel3 ========
        {
            panel3.setLayout(new BorderLayout());

            //======== tabbedPane1 ========
            {
                tabbedPane1.setBorder(null);

                //======== panelHome ========
                {
                    panelHome.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), 5, -1));

                    //---- label2 ----
                    label2.setText("TAB 0");
                    panelHome.add(label2, new GridConstraints(0, 0, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                }
                tabbedPane1.addTab("Home", new ImageIcon(getClass().getResource("/Home Icon 24x24.png")), panelHome);

                //======== panelSearch ========
                {
                    panelSearch.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), 5, -1));

                    //---- lblSearchicon ----
                    lblSearchicon.setIcon(new ImageIcon(getClass().getResource("/search 24x24.png")));
                    panelSearch.add(lblSearchicon, new GridConstraints(0, 0, 1, 1,
                        GridConstraints.ANCHOR_NORTHEAST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                    panelSearch.add(txtSearch, new GridConstraints(0, 1, 1, 1,
                        GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, new Dimension(600, 30), null));
                }
                tabbedPane1.addTab("Search", new ImageIcon(getClass().getResource("/search 24x24.png")), panelSearch);

                //======== panelMyAccount ========
                {
                    panelMyAccount.setLayout(new BorderLayout());

                    //======== tabbedPane2 ========
                    {
                        tabbedPane2.setTabPlacement(SwingConstants.LEFT);

                        //======== panelMyOrders ========
                        {
                            panelMyOrders.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), 5, -1));
                        }
                        tabbedPane2.addTab("My Orders", panelMyOrders);

                        //======== panelMyAddresses ========
                        {
                            panelMyAddresses.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), 5, -1));
                        }
                        tabbedPane2.addTab("My Addresses", panelMyAddresses);

                        //======== panelYourProfile ========
                        {
                            panelYourProfile.setLayout(new GridLayoutManager(7, 2, new Insets(0, 0, 0, 0), 10, -1));

                            //---- lblprofilefirstname ----
                            lblprofilefirstname.setText("First Name");
                            panelYourProfile.add(lblprofilefirstname, new GridConstraints(0, 0, 1, 1,
                                GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                                GridConstraints.SIZEPOLICY_FIXED,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, null, null));

                            //---- txtProfileFirstName ----
                            txtProfileFirstName.setPreferredSize(new Dimension(250, 30));
                            txtProfileFirstName.setBackground(new Color(0xcccccc));
                            panelYourProfile.add(txtProfileFirstName, new GridConstraints(0, 1, 1, 1,
                                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, null, null));

                            //---- lblprofilelastname ----
                            lblprofilelastname.setText("Last Name");
                            panelYourProfile.add(lblprofilelastname, new GridConstraints(1, 0, 1, 1,
                                GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                                GridConstraints.SIZEPOLICY_FIXED,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, null, null));

                            //---- txtProfileLastName ----
                            txtProfileLastName.setPreferredSize(new Dimension(250, 30));
                            txtProfileLastName.setBackground(new Color(0xcccccc));
                            panelYourProfile.add(txtProfileLastName, new GridConstraints(1, 1, 1, 1,
                                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, null, null));

                            //---- lblprofileemial ----
                            lblprofileemial.setText("Email");
                            panelYourProfile.add(lblprofileemial, new GridConstraints(2, 0, 1, 1,
                                GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                                GridConstraints.SIZEPOLICY_FIXED,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, null, null, 10));

                            //---- txtProfileEmail ----
                            txtProfileEmail.setPreferredSize(new Dimension(250, 30));
                            txtProfileEmail.setBackground(new Color(0xcccccc));
                            panelYourProfile.add(txtProfileEmail, new GridConstraints(2, 1, 1, 1,
                                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, null, null));

                            //---- lblprofilephone ----
                            lblprofilephone.setText("Phone Number");
                            panelYourProfile.add(lblprofilephone, new GridConstraints(3, 0, 1, 1,
                                GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                                GridConstraints.SIZEPOLICY_FIXED,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, null, null));

                            //---- txtProfilePhone ----
                            txtProfilePhone.setPreferredSize(new Dimension(250, 30));
                            txtProfilePhone.setBackground(new Color(0xcccccc));
                            panelYourProfile.add(txtProfilePhone, new GridConstraints(3, 1, 1, 1,
                                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, null, null));

                            //---- lblprofilepassword ----
                            lblprofilepassword.setText("Password");
                            panelYourProfile.add(lblprofilepassword, new GridConstraints(4, 0, 1, 1,
                                GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                                GridConstraints.SIZEPOLICY_FIXED,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, null, null));

                            //---- txtProfilePassword ----
                            txtProfilePassword.setPreferredSize(new Dimension(250, 30));
                            txtProfilePassword.setBackground(new Color(0xcccccc));
                            panelYourProfile.add(txtProfilePassword, new GridConstraints(4, 1, 1, 1,
                                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, null, null));

                            //---- lblprofilegender ----
                            lblprofilegender.setText("Gender");
                            panelYourProfile.add(lblprofilegender, new GridConstraints(5, 0, 1, 1,
                                GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                                GridConstraints.SIZEPOLICY_FIXED,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, null, null));

                            //---- txtProfileGender ----
                            txtProfileGender.setPreferredSize(new Dimension(250, 30));
                            txtProfileGender.setBackground(new Color(0xcccccc));
                            panelYourProfile.add(txtProfileGender, new GridConstraints(5, 1, 1, 1,
                                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, null, null));
                        }
                        tabbedPane2.addTab("Your Profile", panelYourProfile);

                        //======== panelLogout ========
                        {
                            panelLogout.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), 5, -1));

                            //---- btnLogout ----
                            btnLogout.setText("Log Out");
                            btnLogout.addActionListener(e -> btnLogout(e));
                            panelLogout.add(btnLogout, new GridConstraints(0, 0, 1, 1,
                                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, null, null));
                        }
                        tabbedPane2.addTab("Log Out?", panelLogout);
                    }
                    panelMyAccount.add(tabbedPane2, BorderLayout.CENTER);
                }
                tabbedPane1.addTab("My Account", new ImageIcon(getClass().getResource("/my account 2 24x24.png")), panelMyAccount);

                //======== panelCart ========
                {
                    panelCart.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), 5, -1));
                }
                tabbedPane1.addTab("Cart", new ImageIcon(getClass().getResource("/cart 24x24.png")), panelCart);
            }
            panel3.add(tabbedPane1, BorderLayout.CENTER);
        }
        contentPane.add(panel3, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Piyush
    private JPanel panel2;
    private JLabel lblLogo;
    private JLabel lblWelcomMessage;
    private JButton btnSearch;
    private JButton btnMyaccount;
    private JButton btnCart;
    private JPanel panel3;
    private JTabbedPane tabbedPane1;
    private JPanel panelHome;
    private JLabel label2;
    private JPanel panelSearch;
    private JLabel lblSearchicon;
    private JTextField txtSearch;
    private JPanel panelMyAccount;
    private JTabbedPane tabbedPane2;
    private JPanel panelMyOrders;
    private JPanel panelMyAddresses;
    private JPanel panelYourProfile;
    private JLabel lblprofilefirstname;
    private JTextArea txtProfileFirstName;
    private JLabel lblprofilelastname;
    private JTextArea txtProfileLastName;
    private JLabel lblprofileemial;
    private JTextArea txtProfileEmail;
    private JLabel lblprofilephone;
    private JTextArea txtProfilePhone;
    private JLabel lblprofilepassword;
    private JPasswordField txtProfilePassword;
    private JLabel lblprofilegender;
    private JTextArea txtProfileGender;
    private JPanel panelLogout;
    private JButton btnLogout;
    private JPanel panelCart;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

}

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import com.intellij.uiDesigner.core.*;
/*
 * Created by JFormDesigner on Fri Apr 12 14:11:24 IST 2024
 */



/**
 * @author HP
 */
public class updateaddress extends JFrame {
    int CustomerID;
    public updateaddress(int customerID) {
        initComponents();
        CustomerID = customerID;
    }

    private void lblLogoMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void btnupdateaddress(ActionEvent e) {
        // TODO add your code here
        String street, city, state, pincode, country;
        street = txtstreet.getText();
        city = txtcity.getText();
        state = txtstate.getText();
        pincode = txtpincode.getText();
        country = txtcountry.getText();
        try {
            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");
            // Establish connection
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "lucifer555");

            String query = "SELECT * FROM customer_address WHERE customer_id = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, CustomerID); // Assuming CustomerID is initialized elsewhere
            ResultSet result = st.executeQuery();

            if(result.next()) {
                // If a row exists for the customer ID, update the address
                query = "UPDATE customer_address SET street_address = ?, city = ?, state = ?, postal_code = ?, country = ? WHERE customer_id = ?";
                st = con.prepareStatement(query);
                st.setString(1, street);
                st.setString(2, city);
                st.setString(3, state);
                st.setString(4, pincode);
                st.setString(5, country);
                st.setInt(6, CustomerID);

                int rowsAffected = st.executeUpdate();
                if(rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Address updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No rows were updated.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                // If no row exists for the customer ID, insert a new address
                query = "INSERT INTO customer_address (customer_id, street_address, city, state, postal_code, country) VALUES (?, ?, ?, ?, ?, ?)";
                st = con.prepareStatement(query);
                st.setInt(1, CustomerID);
                st.setString(2, street);
                st.setString(3, city);
                st.setString(4, state);
                st.setString(5, pincode);
                st.setString(6, country);

                int rowsAffected = st.executeUpdate();
                if(rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "New address inserted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to insert new address.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            st.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace(); // This line prints the exception to the console for debugging purposes

            // Display error message in a dialog
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Piyush
        panel1 = new JPanel();
        panel3 = new JPanel();
        panelMyAddresses = new JPanel();
        btnupdateaddress = new JButton();
        lblstreet = new JLabel();
        txtstreet = new JTextField();
        lblcity = new JLabel();
        txtcity = new JTextField();
        lblstate = new JLabel();
        txtstate = new JTextField();
        lblpincode = new JLabel();
        txtpincode = new JTextField();
        lblcountry = new JLabel();
        txtcountry = new JTextField();
        panel2 = new JPanel();
        lblLogo = new JLabel();
        lblAppName = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
            swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border
            . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dialo\u0067"
            ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,panel1. getBorder
            ( )) ); panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
            .beans .PropertyChangeEvent e) {if ("borde\u0072" .equals (e .getPropertyName () )) throw new RuntimeException
            ( ); }} );
            panel1.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));

            //======== panel3 ========
            {
                panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), 5, -1));

                //======== panelMyAddresses ========
                {
                    panelMyAddresses.setLayout(new GridLayoutManager(7, 2, new Insets(0, 0, 0, 0), 5, -1));

                    //---- btnupdateaddress ----
                    btnupdateaddress.setText("Confirm Address");
                    btnupdateaddress.addActionListener(e -> btnupdateaddress(e));
                    panelMyAddresses.add(btnupdateaddress, new GridConstraints(0, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));

                    //---- lblstreet ----
                    lblstreet.setText("Street: ");
                    panelMyAddresses.add(lblstreet, new GridConstraints(1, 0, 1, 1,
                        GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                    panelMyAddresses.add(txtstreet, new GridConstraints(1, 1, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        new Dimension(400, 22), null, null));

                    //---- lblcity ----
                    lblcity.setText("City: ");
                    panelMyAddresses.add(lblcity, new GridConstraints(2, 0, 1, 1,
                        GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                    panelMyAddresses.add(txtcity, new GridConstraints(2, 1, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, new Dimension(200, 22), null));

                    //---- lblstate ----
                    lblstate.setText("State: ");
                    panelMyAddresses.add(lblstate, new GridConstraints(3, 0, 1, 1,
                        GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                    panelMyAddresses.add(txtstate, new GridConstraints(3, 1, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, new Dimension(200, 22), null));

                    //---- lblpincode ----
                    lblpincode.setText("Pin Code: ");
                    panelMyAddresses.add(lblpincode, new GridConstraints(4, 0, 1, 1,
                        GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                    panelMyAddresses.add(txtpincode, new GridConstraints(4, 1, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, new Dimension(200, 22), null));

                    //---- lblcountry ----
                    lblcountry.setText("Country: ");
                    panelMyAddresses.add(lblcountry, new GridConstraints(5, 0, 1, 1,
                        GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                    panelMyAddresses.add(txtcountry, new GridConstraints(5, 1, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, new Dimension(200, 22), null));
                }
                panel3.add(panelMyAddresses, new GridConstraints(0, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
            }
            panel1.add(panel3, new GridConstraints(1, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));

            //======== panel2 ========
            {
                panel2.setBackground(new Color(0x6666ff));
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

                //---- lblAppName ----
                lblAppName.setText("Personalized Meal Service App");
                lblAppName.setFont(new Font("Segoe UI", Font.BOLD, 22));
                panel2.add(lblAppName, new GridConstraints(0, 1, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null, 3));
            }
            panel1.add(panel2, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));
        }
        contentPane.add(panel1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Piyush
    private JPanel panel1;
    private JPanel panel3;
    private JPanel panelMyAddresses;
    private JButton btnupdateaddress;
    private JLabel lblstreet;
    private JTextField txtstreet;
    private JLabel lblcity;
    private JTextField txtcity;
    private JLabel lblstate;
    private JTextField txtstate;
    private JLabel lblpincode;
    private JTextField txtpincode;
    private JLabel lblcountry;
    private JTextField txtcountry;
    private JPanel panel2;
    private JLabel lblLogo;
    private JLabel lblAppName;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

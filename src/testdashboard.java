import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

//import path.to.EmailSender; // Replace 'path.to' with the actual package path of EmailSender

/*
 * Created by JFormDesigner on Tue Mar 19 16:47:40 IST 2024
 */



/**
 * @author HP
 */
public class testdashboard extends JFrame {
    private index LoggedInUser;

    String restaurantEmail;

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
        showCart();
        loadAddress();

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

    private void btnSearchresult(ActionEvent e) {
        // TODO add your code here
        searchRestaurants();
    }
    private DefaultTableModel modelCart2 = new DefaultTableModel();



    private void showCart(){
        try{
            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");
            // Establish connection
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "lucifer555");

            String query ="select * from cart where customer_id = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, String.valueOf(LoggedInUser.CustomerID));
            ResultSet result = st.executeQuery();

//            DefaultTableModel modelCart2 =(DefaultTableModel) tableCart2.getModel();
            // Clear existing rows from the table model
            modelCart2.setRowCount(0);// This clears the model

            // Remove all rows from the table itself
            while (tableCart2.getRowCount() > 0) {
                ((DefaultTableModel) tableCart2.getModel()).removeRow(0);
            }

            String[] colName = {"Dish ID","Restaurant ID", "Restaurant Name", "Dish Name", "Price", "Quantity", "Total Price"};
            // Set the column identifiers using your custom column names
            modelCart2.setColumnIdentifiers(colName);


            while(result.next()){
                String dishID = String.valueOf(result.getInt("dish_id"));
                String resID = String.valueOf(result.getInt("restaurant_id"));

                String resName = "something";
                // Retrieve restaurant name using resID
                String queryResname = "select res_name from registeredrestaurants where restaurant_id = ?";
                PreparedStatement st3 = con.prepareStatement(queryResname);
                st3.setString(1, resID);
                ResultSet resultResname = st3.executeQuery();
                if (resultResname.next()) {
                    resName = resultResname.getString("res_name");
                }
                String dishName = result.getString("dish_name");
                String price = String.valueOf(result.getInt("price"));
                int singleprice = result.getInt("price");
                String quantity = String.valueOf(result.getInt("quantity"));
                int quan = result.getInt("quantity");
                String totalprice = String.valueOf(singleprice*quan);
                String[] row = {dishID, resID, resName, dishName, price, quantity, totalprice}; // Include resID in the row
                modelCart2.addRow(row);
            }
            // After populating modelCart2
            tableCart2.setModel(modelCart2);


            // Initialize total amount variable
            int totalAmount = 0;

            // Iterate through the rows of the cart model
            for (int i = 0; i < modelCart2.getRowCount(); i++) {
                // Get the price and quantity from the current row
                int price = Integer.parseInt((String) modelCart2.getValueAt(i, 4)); // Assuming price is at index 3
                int quantity = Integer.parseInt((String) modelCart2.getValueAt(i, 5)); // Assuming quantity is at index 4
                // Calculate the total price for the current item
                int itemTotal = price * quantity;
                // Add the total price for the current item to the total amount
                totalAmount += itemTotal;
            }

            // Set the total amount in the txtfinalamount text field
            txtFinalAmount.setText(String.valueOf(totalAmount));

            st.close();
            con.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void searchRestaurants(){
        String searchterm = txtSearch.getText();
        try {
            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");
            // Establish connection
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "lucifer555");

            String query = "SELECT * FROM RegisteredRestaurants WHERE UPPER(res_name) LIKE UPPER('%' || ? || '%')";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, searchterm);
            ResultSet result = st.executeQuery();
            ResultSetMetaData rsmd = result.getMetaData();
            boolean found_data = false;
            DefaultTableModel model =(DefaultTableModel) tableRestaurants.getModel();
            // Clear existing rows from the table model
            model.setRowCount(0);
            // Define your custom column names
            String[] colName = {"Restaurant ID", "Restaurant Name", "Location"};

            // Set the column identifiers using your custom column names
            model.setColumnIdentifiers(colName);
            String resName, reslocation, res_id;

            while(result.next()){
                found_data = true;
                res_id = String.valueOf(result.getInt(1));
                resName = result.getString(2);
                reslocation = result.getString(3);
                String[] row = {res_id, resName, reslocation};
                model.addRow(row);
            }
            if(!found_data){
                JOptionPane.showMessageDialog(this, "Restaurant not found", "No restaurant", JOptionPane.ERROR_MESSAGE);
            }

            st.close();
            con.close();


        }catch (Exception ex){
            System.out.println(ex);
            // Display error message in a dialog
            JOptionPane.showMessageDialog(this, "An error occurred while searching: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void btnViewMenu(ActionEvent e) {
        // TODO add your code here
        String customerID = String.valueOf(LoggedInUser.CustomerID);
        String res_ID = txtResID.getText();

        RestaurantMenuPage res_menu = new RestaurantMenuPage(res_ID, customerID);
        res_menu.setVisible(true);
    }

    private void btnRefreshCart(ActionEvent e) {
        // TODO add your code here
        showCart();
    }

    private void btnChangeQuantity(ActionEvent e) {
        // TODO add your code here
        String ChangeQuanID = txtDishID.getText();
        int newquantity = (int) spinnerNewQuantity.getValue();
        try {
            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");
            // Establish connection
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "lucifer555");

            String query = "update cart set quantity = ? where customer_id = ? and dish_id = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, newquantity); // Set the new quantity
            st.setString(2, String.valueOf(LoggedInUser.CustomerID)); // Set the customer ID
            st.setString(3, ChangeQuanID); // Set the dish ID
            int rowsAffected = st.executeUpdate(); // Execute the update query
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Quantity updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No rows were updated. The dish ID is incorrect.", "No Update", JOptionPane.WARNING_MESSAGE);
            }
            
            st.close();
            con.close();


        } catch (Exception ex) {
            ex.printStackTrace(); // This line prints the exception to the console for debugging purposes

            // Display error message in a dialog
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void btnRemoveItem(ActionEvent e) {
        // TODO add your code here
        String RemoveDishID = txtremoveDishID.getText();
        try {
            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");
            // Establish connection
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "lucifer555");

            String query = "delete from cart where customer_id = ? and dish_id = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, String.valueOf(LoggedInUser.CustomerID)); // Set the customer ID
            st.setString(2, RemoveDishID); // Set the dish ID

            int rowsaffected = st.executeUpdate();
            if (rowsaffected > 0) {
                JOptionPane.showMessageDialog(this, "Item removed from cart successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No rows were deleted. The dish ID is incorrect.", "No Deletion", JOptionPane.WARNING_MESSAGE);
            }


        }catch (Exception ex){
            System.out.println(ex);
            // Display error message in a dialog
            JOptionPane.showMessageDialog(this, "An error occurred while removing item: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    private void loadAddress(){

        try {
            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");
            // Establish connection
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "lucifer555");

            String query = "select * from customer_address where customer_id = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, LoggedInUser.CustomerID);
            ResultSet result = st.executeQuery();

            while(result.next()){
                txtstreet.setText(result.getString("street_address"));
                txtcity.setText(result.getString("city"));
                txtstate.setText(result.getString("state"));
                txtpincode.setText(result.getString("postal_code"));
                txtcountry.setText(result.getString("country"));
            }


            st.close();
            con.close();


        } catch (Exception ex) {
            ex.printStackTrace(); // This line prints the exception to the console for debugging purposes

            // Display error message in a dialog
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnupdateaddress(ActionEvent e) {
        // TODO add your code here
        updateaddress updateadd = new updateaddress(LoggedInUser.CustomerID);
        updateadd.setVisible(true);
    }

    private void btnrefreshaddress(ActionEvent e) {
        // TODO add your code here
        loadAddress();
    }
    // Declare the connection variable at the class level
    private Connection con;

    private void btnPlaceOrder(ActionEvent e) {
        // Get customer ID from your application
        int customerID = LoggedInUser.CustomerID; // Assuming you have the customer ID stored in LoggedInUser.CustomerID

        try {
            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");
            // Establish connection
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "lucifer555");

            // Disable auto-commit
            con.setAutoCommit(false);
            // Get shipping address from customer_address table
            String shippingAddress = getShippingAddress(customerID);

            if (shippingAddress == null) {
                JOptionPane.showMessageDialog(this, "Shipping address not found for customer ID: " + customerID, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Calculate total amount
            int totalAmount = calculateTotalAmount();
            System.out.println("Number of rows in modelCart2: " + modelCart2.getRowCount());


            // Insert the order into the orders table with the total amount
            String insertOrderQuery = "INSERT INTO orders (customer_id, shipping_address, total_amount, restaurant_id) VALUES (?, ?, ?, ?)";
            PreparedStatement insertOrderStatement = con.prepareStatement(insertOrderQuery, new String[]{"order_id"});
            insertOrderStatement.setInt(1, customerID);
            insertOrderStatement.setString(2, shippingAddress);
            insertOrderStatement.setInt(3, totalAmount);

            // Assuming the restaurant ID is available in the cart model at index 1
            int restaurantID = Integer.parseInt((String) modelCart2.getValueAt(0, 1)); // Assuming restaurant ID is at index 1
            insertOrderStatement.setInt(4, restaurantID);
            int rowsAffected = insertOrderStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve the generated order ID
                ResultSet generatedKeys = insertOrderStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int orderID = generatedKeys.getInt(1);

                    // Insert order details into the order_details table
                    // Insert order details into the order_details table
                    // Insert order details into the order_details table
                    for (int i = 0; i < modelCart2.getRowCount(); i++) {
                        int dishID = Integer.parseInt((String) modelCart2.getValueAt(i, 0)); // Assuming dish ID is at index 0
                        int resID = Integer.parseInt((String) modelCart2.getValueAt(i, 1)); // Assuming restaurant ID is at index 1
                        String dishName = (String) modelCart2.getValueAt(i, 3); // Assuming dish name is at index 3
                        int quantity = Integer.parseInt((String) modelCart2.getValueAt(i, 5)); // Assuming quantity is at index 5
                        int price = Integer.parseInt((String) modelCart2.getValueAt(i, 4)); // Assuming price is at index 4
                        restaurantEmail = getRestaurantEmail(String.valueOf(resID));

                        int totalItemAmount = price * quantity; // Calculate the total amount

                        String insertOrderDetailQuery = "INSERT INTO order_details (order_id, dish_id, dish_name, quantity, price, total_amount) VALUES (?, ?, ?, ?, ?, ?)";
                        PreparedStatement insertOrderDetailStatement = con.prepareStatement(insertOrderDetailQuery);
                        insertOrderDetailStatement.setInt(1, orderID);
                        insertOrderDetailStatement.setInt(2, dishID);
                        insertOrderDetailStatement.setString(3, dishName);
                        insertOrderDetailStatement.setInt(4, quantity);
                        insertOrderDetailStatement.setInt(5, price);
                        insertOrderDetailStatement.setInt(6, totalItemAmount);
                        insertOrderDetailStatement.executeUpdate();
                    }




                    // Commit the transaction
                    con.commit();

                    // Display success message
                    JOptionPane.showMessageDialog(this, "Order placed successfully. Order ID: " + orderID, "Success", JOptionPane.INFORMATION_MESSAGE);
        // Now sending mail to the restaurant
//                    // Retrieve customer details

                    String customerName = LoggedInUser.FirstName + LoggedInUser.LastName;
                    String customerPhone = LoggedInUser.Phone;
                    String customerEmail = LoggedInUser.Email;
                    // Retrieve customer address details
                    String getCustomerAddressQuery = "SELECT street_address, city, state, postal_code, country FROM customer_address WHERE customer_id = ?";
                    PreparedStatement getCustomerAddressStatement = con.prepareStatement(getCustomerAddressQuery);
                    getCustomerAddressStatement.setInt(1, customerID);
                    ResultSet customerAddressResult = getCustomerAddressStatement.executeQuery();

                    String customerAddress = "";
                    if (customerAddressResult.next()) {
                        String streetAddress = customerAddressResult.getString("street_address");
                        String city = customerAddressResult.getString("city");
                        String state = customerAddressResult.getString("state");
                        String postalCode = customerAddressResult.getString("postal_code");
                        String country = customerAddressResult.getString("country");

                        // Construct customer address
                        customerAddress = streetAddress + ", " + city + ", " + state + ", " + postalCode + ", " + country;
                    }

// Construct email body with customer details
                    String body = "Customer Name: " + customerName + "\n" +
                            "Phone: " + customerPhone + "\n" +
                            "Email: " + customerEmail + "\n" +
                            "Address: " + customerAddress + "\n\n" +
                            "Order Details:\n";

// Retrieve order details
                    String getOrderDetailsQuery = "SELECT dish_name, quantity, price FROM order_details WHERE order_id = ?";
                    PreparedStatement getOrderDetailsStatement = con.prepareStatement(getOrderDetailsQuery);
                    getOrderDetailsStatement.setInt(1, orderID);
                    ResultSet orderDetailsResult = getOrderDetailsStatement.executeQuery();

                    while (orderDetailsResult.next()) {
                        String dishName = orderDetailsResult.getString("dish_name");
                        int quantity = orderDetailsResult.getInt("quantity");
                        int price = orderDetailsResult.getInt("price");
                        body += "- " + dishName + ": Quantity: " + quantity + ", Price: ₹" + price + "\n";
                    }

// Calculate total amount
                    body += "Total Amount: ₹" + totalAmount;

// Send email
                    String recipientEmail = restaurantEmail;
                    String subject = "New Order Details. Order ID:"+orderID;
                    EmailSender.sendEmail(recipientEmail, subject, body);

//
//                    // Call sendEmail method to send order details to the restaurant
//                    EmailSender.sendEmail(restaurantEmail, "New Order Details", emailBody);
                    // Clear the cart after placing the order
                    clearCart();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to retrieve order ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed to place order.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            insertOrderStatement.close();
            con.close();
        } catch (Exception ex) {
            // Rollback the transaction if an error occurs
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex2) {
                ex2.printStackTrace();
            }

            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex3) {
                ex3.printStackTrace();
            }
        }
    }

    // Method to calculate total amount
    private int calculateTotalAmount() {
        int totalAmount = 0;
        for (int i = 0; i < modelCart2.getRowCount(); i++) {
            int price = Integer.parseInt((String) modelCart2.getValueAt(i, 4)); // Assuming price is at index 3
            int quantity = Integer.parseInt((String) modelCart2.getValueAt(i, 5)); // Assuming quantity is at index 4
            totalAmount += price * quantity;
        }
        return totalAmount;
    }

    // Method to clear the cart after placing the order
    private void clearCart() {
        // Implementation of clearCart method
    }

    private String getShippingAddress(int customerID) {
        String shippingAddress = null;
        try {
            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");
            // Establish connection
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "lucifer555");

            // Prepare and execute the SQL query to retrieve the shipping address
            String query = "SELECT street_address, city, state, postal_code, country FROM customer_address WHERE customer_id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();

            // Check if a record is found
            if (rs.next()) {
                // Construct the shipping address from the retrieved fields
                String streetAddress = rs.getString("street_address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String postalCode = rs.getString("postal_code");
                String country = rs.getString("country");

                // Concatenate the address fields into a single string
                shippingAddress = streetAddress + ", " + city + ", " + state + ", " + postalCode + ", " + country;
            }

            // Close resources
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return shippingAddress;
    }
    private String getRestaurantEmail(String restaurantId) {

        try {
            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");

            // Establish connection
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "lucifer555");

            // Prepare SQL query
            String query = "SELECT email FROM RegisteredRestaurants WHERE restaurant_id = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, Integer.parseInt(restaurantId));

            // Execute the query
            ResultSet result = st.executeQuery();

            // Check if a row was found
            if (result.next()) {
                // Retrieve the email from the result set
                restaurantEmail = result.getString("email");
            }

            // Close resources
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurantEmail;
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
        panelhometab = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        panel4 = new JPanel();
        label9 = new JLabel();
        scrollPane2 = new JScrollPane();
        txtArea = new JTextArea();
        panelSearch = new JPanel();
        lblSearchicon = new JLabel();
        txtSearch = new JTextField();
        btnSearchresult = new JButton();
        lblRestaurantID = new JLabel();
        txtResID = new JTextField();
        btnViewMenu = new JButton();
        JpanelSearchResult = new JPanel();
        scrollpanelSearchResult = new JScrollPane();
        tableRestaurants = new JTable();
        panelMyAccount = new JPanel();
        tabbedPane2 = new JTabbedPane();
        panelMyOrders = new JPanel();
        panelMyAddresses = new JPanel();
        btnrefreshaddress = new JButton();
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
        panel1 = new JPanel();
        lblChangeQuanMessage = new JLabel();
        lblEnterDishID = new JLabel();
        txtDishID = new JTextField();
        lblEnterQuantity = new JLabel();
        spinnerNewQuantity = new JSpinner();
        btnChangeQuantity = new JButton();
        lblRemoveTitle = new JLabel();
        lblEnterIDtoremove = new JLabel();
        txtremoveDishID = new JTextField();
        btnRemoveItem = new JButton();
        lblFinalAmount = new JLabel();
        txtFinalAmount = new JTextField();
        panel5 = new JPanel();
        panel6 = new JPanel();
        btnPlaceOrder = new JButton();
        btnRefreshCart = new JButton();
        scrollPane1 = new JScrollPane();
        tableCart2 = new JTable();

        //======== this ========
        setPreferredSize(new Dimension(785, 485));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel2 ========
        {
            panel2.setBackground(new Color(0x6666ff));
            panel2.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
            . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e" , javax. swing .border . TitledBorder
            . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dialo\u0067", java .
            awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,panel2. getBorder () ) )
            ; panel2. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "borde\u0072" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
            ;
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

                    //======== panelhometab ========
                    {
                        panelhometab.setBackground(new Color(0x66ffff));
                        panelhometab.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), 5, -1));

                        //---- label1 ----
                        label1.setText("Personalized Meal Service App");
                        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 14f));
                        panelhometab.add(label1, new GridConstraints(0, 1, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_FIXED,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null));

                        //---- label2 ----
                        label2.setText("Created By");
                        label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD, label2.getFont().getSize() + 7f));
                        panelhometab.add(label2, new GridConstraints(1, 1, 1, 1,
                            GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null));

                        //---- label3 ----
                        label3.setText("Piyush S Pawar");
                        label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD, label3.getFont().getSize() + 7f));
                        panelhometab.add(label3, new GridConstraints(2, 0, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null, null, null, 5));

                        //---- label4 ----
                        label4.setText("Siddhesh A Patil");
                        label4.setFont(label4.getFont().deriveFont(label4.getFont().getStyle() | Font.BOLD, label4.getFont().getSize() + 7f));
                        panelhometab.add(label4, new GridConstraints(2, 1, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null, null, null));

                        //---- label5 ----
                        label5.setText("Siddhesh S Patil");
                        label5.setFont(label5.getFont().deriveFont(label5.getFont().getStyle() | Font.BOLD, label5.getFont().getSize() + 7f));
                        panelhometab.add(label5, new GridConstraints(2, 2, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null, null, null));

                        //---- label6 ----
                        label6.setText("122B1B221");
                        label6.setFont(label6.getFont().deriveFont(label6.getFont().getStyle() | Font.BOLD, label6.getFont().getSize() + 7f));
                        panelhometab.add(label6, new GridConstraints(3, 0, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null, null, null, 5));

                        //---- label7 ----
                        label7.setText("122B1B217");
                        label7.setFont(label7.getFont().deriveFont(label7.getFont().getStyle() | Font.BOLD, label7.getFont().getSize() + 7f));
                        panelhometab.add(label7, new GridConstraints(3, 1, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null));

                        //---- label8 ----
                        label8.setText("122B1B216");
                        label8.setFont(label8.getFont().deriveFont(label8.getFont().getStyle() | Font.BOLD, label8.getFont().getSize() + 7f));
                        panelhometab.add(label8, new GridConstraints(3, 2, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_FIXED,
                            null, null, null));

                        //======== panel4 ========
                        {
                            panel4.setBackground(Color.lightGray);
                            panel4.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));

                            //---- label9 ----
                            label9.setText("Copyright \u00a9 Personalized Meal Service App 2024");
                            panel4.add(label9, new GridConstraints(0, 0, 1, 1,
                                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, null, null));

                            //======== scrollPane2 ========
                            {

                                //---- txtArea ----
                                txtArea.setText("This project is the property of Personalized Meal Service App Team. It is intended for academic purposes and may not be reproduced or distributed without proper citation or permission. Any unauthorized use or distribution is strictly prohibited. ");
                                txtArea.setLineWrap(true);
                                txtArea.setWrapStyleWord(true);
                                scrollPane2.setViewportView(txtArea);
                            }
                            panel4.add(scrollPane2, new GridConstraints(1, 0, 1, 1,
                                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, null, null));
                        }
                        panelhometab.add(panel4, new GridConstraints(4, 0, 1, 3,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null));
                    }
                    panelHome.add(panelhometab, new GridConstraints(0, 0, 1, 1,
                        GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, new Dimension(500, 300), null));
                }
                tabbedPane1.addTab("Home", new ImageIcon(getClass().getResource("/Home Icon 24x24.png")), panelHome);

                //======== panelSearch ========
                {
                    panelSearch.setLayout(new GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), 5, -1));

                    //---- lblSearchicon ----
                    lblSearchicon.setIcon(new ImageIcon(getClass().getResource("/search 24x24.png")));
                    panelSearch.add(lblSearchicon, new GridConstraints(0, 0, 1, 1,
                        GridConstraints.ANCHOR_NORTHEAST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_FIXED,
                        null, null, null));
                    panelSearch.add(txtSearch, new GridConstraints(0, 1, 1, 1,
                        GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_FIXED,
                        null, null, null));

                    //---- btnSearchresult ----
                    btnSearchresult.setText("Search");
                    btnSearchresult.addActionListener(e -> btnSearchresult(e));
                    panelSearch.add(btnSearchresult, new GridConstraints(0, 2, 1, 1,
                        GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_FIXED,
                        null, null, null));

                    //---- lblRestaurantID ----
                    lblRestaurantID.setText("Enter Restaurant ID to view menu:");
                    panelSearch.add(lblRestaurantID, new GridConstraints(1, 1, 1, 1,
                        GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));

                    //---- txtResID ----
                    txtResID.setPreferredSize(new Dimension(140, 22));
                    panelSearch.add(txtResID, new GridConstraints(1, 2, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));

                    //---- btnViewMenu ----
                    btnViewMenu.setText("View Menu");
                    btnViewMenu.addActionListener(e -> btnViewMenu(e));
                    panelSearch.add(btnViewMenu, new GridConstraints(1, 3, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));

                    //======== JpanelSearchResult ========
                    {
                        JpanelSearchResult.setBackground(new Color(0x999999));
                        JpanelSearchResult.setLayout(new GridLayout());

                        //======== scrollpanelSearchResult ========
                        {
                            scrollpanelSearchResult.setBackground(new Color(0xcccccc));

                            //---- tableRestaurants ----
                            tableRestaurants.setModel(new DefaultTableModel());
                            scrollpanelSearchResult.setViewportView(tableRestaurants);
                        }
                        JpanelSearchResult.add(scrollpanelSearchResult);
                    }
                    panelSearch.add(JpanelSearchResult, new GridConstraints(2, 1, 1, 2,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
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
                            panelMyAddresses.setLayout(new GridLayoutManager(7, 2, new Insets(0, 0, 0, 0), 5, -1));

                            //---- btnrefreshaddress ----
                            btnrefreshaddress.setText("Refresh Address");
                            btnrefreshaddress.addActionListener(e -> btnrefreshaddress(e));
                            panelMyAddresses.add(btnrefreshaddress, new GridConstraints(0, 0, 1, 1,
                                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, null, null));

                            //---- btnupdateaddress ----
                            btnupdateaddress.setText("Update Address");
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

                            //---- txtstreet ----
                            txtstreet.setEditable(false);
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

                            //---- txtcity ----
                            txtcity.setEditable(false);
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

                            //---- txtstate ----
                            txtstate.setEditable(false);
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

                            //---- txtpincode ----
                            txtpincode.setEditable(false);
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

                            //---- txtcountry ----
                            txtcountry.setEditable(false);
                            panelMyAddresses.add(txtcountry, new GridConstraints(5, 1, 1, 1,
                                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, new Dimension(200, 22), null));
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
                    panelCart.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), 5, -1));

                    //======== panel1 ========
                    {
                        panel1.setBackground(Color.lightGray);
                        panel1.setLayout(new GridLayoutManager(5, 5, new Insets(0, 0, 0, 0), 0, 0));

                        //---- lblChangeQuanMessage ----
                        lblChangeQuanMessage.setText("Change Quantity");
                        panel1.add(lblChangeQuanMessage, new GridConstraints(0, 1, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null));

                        //---- lblEnterDishID ----
                        lblEnterDishID.setText("Enter Dish ID to change quantity:");
                        panel1.add(lblEnterDishID, new GridConstraints(1, 0, 1, 1,
                            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null));

                        //---- txtDishID ----
                        txtDishID.setPreferredSize(new Dimension(120, 22));
                        panel1.add(txtDishID, new GridConstraints(1, 1, 1, 1,
                            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null));

                        //---- lblEnterQuantity ----
                        lblEnterQuantity.setText("Enter new quantity:");
                        panel1.add(lblEnterQuantity, new GridConstraints(1, 2, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_FIXED,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null));
                        panel1.add(spinnerNewQuantity, new GridConstraints(1, 3, 1, 1,
                            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null));

                        //---- btnChangeQuantity ----
                        btnChangeQuantity.setText("Change Quantity");
                        btnChangeQuantity.addActionListener(e -> btnChangeQuantity(e));
                        panel1.add(btnChangeQuantity, new GridConstraints(1, 4, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null));

                        //---- lblRemoveTitle ----
                        lblRemoveTitle.setText("Remove Item");
                        panel1.add(lblRemoveTitle, new GridConstraints(2, 1, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null));

                        //---- lblEnterIDtoremove ----
                        lblEnterIDtoremove.setText("Enter ID to remove item from cart: ");
                        panel1.add(lblEnterIDtoremove, new GridConstraints(3, 0, 1, 1,
                            GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null));

                        //---- txtremoveDishID ----
                        txtremoveDishID.setPreferredSize(new Dimension(120, 22));
                        panel1.add(txtremoveDishID, new GridConstraints(3, 1, 1, 1,
                            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null));

                        //---- btnRemoveItem ----
                        btnRemoveItem.setText("Remove Item");
                        btnRemoveItem.addActionListener(e -> btnRemoveItem(e));
                        panel1.add(btnRemoveItem, new GridConstraints(3, 2, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null));

                        //---- lblFinalAmount ----
                        lblFinalAmount.setText("Final Amount:");
                        panel1.add(lblFinalAmount, new GridConstraints(4, 1, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null));

                        //---- txtFinalAmount ----
                        txtFinalAmount.setEditable(false);
                        panel1.add(txtFinalAmount, new GridConstraints(4, 2, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, new Dimension(120, 22), null));
                    }
                    panelCart.add(panel1, new GridConstraints(0, 0, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));

                    //======== panel5 ========
                    {
                        panel5.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), 0, 0));

                        //======== panel6 ========
                        {
                            panel6.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), 5, -1));

                            //---- btnPlaceOrder ----
                            btnPlaceOrder.setText("Place Order");
                            btnPlaceOrder.setBackground(Color.cyan);
                            btnPlaceOrder.addActionListener(e -> btnPlaceOrder(e));
                            panel6.add(btnPlaceOrder, new GridConstraints(0, 0, 1, 1,
                                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, null, null));

                            //---- btnRefreshCart ----
                            btnRefreshCart.setText("Refresh Cart");
                            btnRefreshCart.addActionListener(e -> btnRefreshCart(e));
                            panel6.add(btnRefreshCart, new GridConstraints(0, 1, 1, 1,
                                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                                null, null, null));
                        }
                        panel5.add(panel6, new GridConstraints(0, 0, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null));

                        //======== scrollPane1 ========
                        {
                            scrollPane1.setViewportView(tableCart2);
                        }
                        panel5.add(scrollPane1, new GridConstraints(1, 0, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null, 4));
                    }
                    panelCart.add(panel5, new GridConstraints(1, 0, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                }
                tabbedPane1.addTab("Cart", new ImageIcon(getClass().getResource("/cart 24x24.png")), panelCart);
            }
            panel3.add(tabbedPane1, BorderLayout.NORTH);
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
    private JPanel panelhometab;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JPanel panel4;
    private JLabel label9;
    private JScrollPane scrollPane2;
    private JTextArea txtArea;
    private JPanel panelSearch;
    private JLabel lblSearchicon;
    private JTextField txtSearch;
    private JButton btnSearchresult;
    private JLabel lblRestaurantID;
    private JTextField txtResID;
    private JButton btnViewMenu;
    private JPanel JpanelSearchResult;
    private JScrollPane scrollpanelSearchResult;
    private JTable tableRestaurants;
    private JPanel panelMyAccount;
    private JTabbedPane tabbedPane2;
    private JPanel panelMyOrders;
    private JPanel panelMyAddresses;
    private JButton btnrefreshaddress;
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
    private JPanel panel1;
    private JLabel lblChangeQuanMessage;
    private JLabel lblEnterDishID;
    private JTextField txtDishID;
    private JLabel lblEnterQuantity;
    private JSpinner spinnerNewQuantity;
    private JButton btnChangeQuantity;
    private JLabel lblRemoveTitle;
    private JLabel lblEnterIDtoremove;
    private JTextField txtremoveDishID;
    private JButton btnRemoveItem;
    private JLabel lblFinalAmount;
    private JTextField txtFinalAmount;
    private JPanel panel5;
    private JPanel panel6;
    private JButton btnPlaceOrder;
    private JButton btnRefreshCart;
    private JScrollPane scrollPane1;
    private JTable tableCart2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

}

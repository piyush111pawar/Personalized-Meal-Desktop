import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.intellij.uiDesigner.core.*;
import org.jdesktop.swingx.*;
/*
 * Created by JFormDesigner on Wed Apr 10 18:10:24 IST 2024
 */



/**
 * @author HP
 */
public class RestaurantMenuPage extends JFrame {
    String resID;
    String customer_ID;
    String Res_name;
    String Res_location;
    String Res_Description;
    String Res_Phone;
    public RestaurantMenuPage(String res_ID, String customerID) {
        initComponents();
        customer_ID = customerID;
        resID = res_ID;

        loadRestaurant(resID);
        loadMenu(resID);
    }
    public RestaurantMenuPage Restaurant;
    private void loadRestaurant(String resID){
        try{
            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");
            // Establish connection
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "lucifer555");
            
            String query ="select * from registeredrestaurants where Restaurant_id = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, resID);
            ResultSet result = st.executeQuery();
            if(result.next()){
                Res_name = result.getString("res_name");
                Res_location = result.getString("location");
                Res_Description = result.getString("description");
                Res_Phone = result.getString("phone");
            }
            lblContactNo.setText("Contact No: "+Res_Phone);
            lblRestaurantName.setText(Res_name);
            lblResDescription.setText(Res_Description);
            lblResLocation.setText("Location: "+Res_location);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private void loadMenu(String resID){
        try{
            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");
            // Establish connection
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "lucifer555");

            String query ="select * from menu_of_restaurant where restaurant_id = ? order by category";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, resID);
            ResultSet result = st.executeQuery();
            ResultSetMetaData rsmd = result.getMetaData();
            DefaultTableModel modelMenu =(DefaultTableModel) tableResMenu.getModel();
            
            String[] colName = {"Menu ID", "Category", "Dish Name", "Price", "Description"};
            // Set the column identifiers using your custom column names
            modelMenu.setColumnIdentifiers(colName);
            String dishID, dishCategory, dishName, price, dish_Descrition;

            
            while(result.next()){
                dishID = String.valueOf(result.getInt("dish_id"));
                dishCategory = result.getString("category");
                dishName = result.getString("dish_name");
                price = String.valueOf(result.getInt("price"));
                dish_Descrition = result.getString("description");
                String[] row = {dishID, dishCategory, dishName, price, dish_Descrition};
                modelMenu.addRow(row);
            }
            // Refresh the table model to display all rows
            tableResMenu.setModel(modelMenu);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    

    private void lblLogoMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void btnSearch(ActionEvent e) {
        // TODO add your code here
    }

    private void btnMyaccountMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void btnCart(ActionEvent e) {
        // TODO add your code here
    }

    private void btnAddtoCart(ActionEvent e) {

        // TODO add your code here
        String menuID = txtMenuID.getText();
        double dishPrice = 0.0;
        String dishName = "";
        int quantity =(int) spinnerQuantity.getValue();
        System.out.println("assing to cart");
        try{
            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");
            // Establish connection
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "lucifer555");


            // Query to get dish name and price
            String queryDishNamePrice = "SELECT dish_name, price FROM menu_of_restaurant WHERE dish_id = ?";
            PreparedStatement st2 = con.prepareStatement(queryDishNamePrice);
            st2.setString(1, menuID);
            ResultSet resultst2 = st2.executeQuery();
            // Check if the result set has any rows
            if (resultst2.next()) {
                dishName = resultst2.getString("dish_name");
                dishPrice = resultst2.getDouble("price");
            } else {
                JOptionPane.showMessageDialog(this, "Menu item not found.");
                return; // Exit method if menu item not found
            }

            // Construct the SQL INSERT statement
            String query = "INSERT INTO cart (customer_id, dish_id, restaurant_id, dish_name, price, quantity) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(query);


            if (true) {
                st.setString(1, customer_ID);
                st.setString(2, menuID);
                st.setString(3, resID);
                st.setString(4, dishName);
                st.setDouble(5, dishPrice);
                st.setInt(6, quantity);

                int rowsInserted = st.executeUpdate();

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Item added to cart successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add item to cart");
                }
            }

            st.close();
            con.close();

        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Piyush
        panel2 = new JPanel();
        lblLogo = new JLabel();
        lblAppName = new JLabel();
        panel1 = new JPanel();
        panel4 = new JPanel();
        lblRestaurantName = new JLabel();
        lblResLocation = new JLabel();
        lblContactNo = new JLabel();
        scrollPane1 = new JScrollPane();
        lblResDescription = new JTextArea();
        lblGetMenuID = new JLabel();
        txtMenuID = new JTextField();
        label1 = new JLabel();
        spinnerQuantity = new JSpinner();
        btnAddtoCart = new JButton();
        panel5 = new JPanel();
        panel6 = new JPanel();
        scrollPane4 = new JScrollPane();
        tableResMenu = new JTable();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel2 ========
        {
            panel2.setBackground(new Color(0x6666ff));
            panel2.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
            border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER
            , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font
            .BOLD ,12 ), java. awt. Color. red) ,panel2. getBorder( )) ); panel2. addPropertyChangeListener (
            new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r"
            .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
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
        contentPane.add(panel2, BorderLayout.NORTH);

        //======== panel1 ========
        {
            panel1.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));

            //======== panel4 ========
            {
                panel4.setBackground(Color.lightGray);
                panel4.setLayout(new GridLayoutManager(5, 5, new Insets(0, 0, 0, 0), 0, 0));

                //---- lblRestaurantName ----
                lblRestaurantName.setText("Name Of Restaurant");
                lblRestaurantName.setFont(lblRestaurantName.getFont().deriveFont(lblRestaurantName.getFont().getStyle() | Font.BOLD, lblRestaurantName.getFont().getSize() + 16f));
                panel4.add(lblRestaurantName, new GridConstraints(0, 0, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null, 2));

                //---- lblResLocation ----
                lblResLocation.setText("Location");
                panel4.add(lblResLocation, new GridConstraints(1, 0, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null, 3));

                //---- lblContactNo ----
                lblContactNo.setText("Restaurant Contact Number: ");
                panel4.add(lblContactNo, new GridConstraints(2, 0, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null, 3));

                //======== scrollPane1 ========
                {

                    //---- lblResDescription ----
                    lblResDescription.setText("Experience the freshest sushi and sashimi in town, along with traditional Japanese dishes.");
                    lblResDescription.setForeground(Color.gray);
                    lblResDescription.setLineWrap(true);
                    lblResDescription.setPreferredSize(null);
                    lblResDescription.setEditable(false);
                    lblResDescription.setWrapStyleWord(true);
                    scrollPane1.setViewportView(lblResDescription);
                }
                panel4.add(scrollPane1, new GridConstraints(3, 0, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_FIXED,
                    null, null, null, 3));

                //---- lblGetMenuID ----
                lblGetMenuID.setText("Enter MenuID to add to cart");
                panel4.add(lblGetMenuID, new GridConstraints(4, 0, 1, 1,
                    GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- txtMenuID ----
                txtMenuID.setPreferredSize(new Dimension(100, 22));
                txtMenuID.setToolTipText("Enter Menu ID");
                panel4.add(txtMenuID, new GridConstraints(4, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null, 1));

                //---- label1 ----
                label1.setText("Quantity: ");
                panel4.add(label1, new GridConstraints(4, 2, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
                panel4.add(spinnerQuantity, new GridConstraints(4, 3, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //---- btnAddtoCart ----
                btnAddtoCart.setText("Add to Cart");
                btnAddtoCart.addActionListener(e -> btnAddtoCart(e));
                panel4.add(btnAddtoCart, new GridConstraints(4, 4, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null, 1));
            }
            panel1.add(panel4, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));

            //======== panel5 ========
            {
                panel5.setLayout(new GridLayout());

                //======== panel6 ========
                {
                    panel6.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), 0, 0));

                    //======== scrollPane4 ========
                    {

                        //---- tableResMenu ----
                        tableResMenu.setPreferredSize(null);
                        scrollPane4.setViewportView(tableResMenu);
                    }
                    panel6.add(scrollPane4, new GridConstraints(0, 0, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null, 2));
                }
                panel5.add(panel6);
            }
            panel1.add(panel5, new GridConstraints(1, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));
        }
        contentPane.add(panel1, BorderLayout.CENTER);
        setSize(770, 480);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Piyush
    private JPanel panel2;
    private JLabel lblLogo;
    private JLabel lblAppName;
    private JPanel panel1;
    private JPanel panel4;
    private JLabel lblRestaurantName;
    private JLabel lblResLocation;
    private JLabel lblContactNo;
    private JScrollPane scrollPane1;
    private JTextArea lblResDescription;
    private JLabel lblGetMenuID;
    private JTextField txtMenuID;
    private JLabel label1;
    private JSpinner spinnerQuantity;
    private JButton btnAddtoCart;
    private JPanel panel5;
    private JPanel panel6;
    private JScrollPane scrollPane4;
    private JTable tableResMenu;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

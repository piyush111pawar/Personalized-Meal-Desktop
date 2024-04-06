import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Mon Mar 18 21:18:57 IST 2024
 */



/**
 * @author HP
 */
public class dashboardform extends JFrame {
    public dashboardform() {
        initComponents();
    }

    private void lblMyAccountMouseMoved(MouseEvent e) {
        // TODO add your code here
    }





    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Piyush
        navpanel = new JPanel();
        lblLogo = new JLabel();
        txtSearch = new JTextField();
        lblSearchIcon = new JLabel();
        btnMyAccount = new JButton();
        btnCart = new JButton();
        label1 = new JLabel();
        bgpanel = new JPanel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== navpanel ========
        {
            navpanel.setBackground(new Color(0x6666ff));
            navpanel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border
            . EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax
            . swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,
            12 ), java. awt. Color. red) ,navpanel. getBorder( )) ); navpanel. addPropertyChangeListener (new java. beans
            . PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .
            getPropertyName () )) throw new RuntimeException( ); }} );
            navpanel.setLayout(null);

            //---- lblLogo ----
            lblLogo.setIcon(new ImageIcon(getClass().getResource("/logo for nav Preview (1).jpg")));
            navpanel.add(lblLogo);
            lblLogo.setBounds(5, 5, 64, 64);

            //---- txtSearch ----
            txtSearch.setText("Search");
            navpanel.add(txtSearch);
            txtSearch.setBounds(240, 20, 380, 30);

            //---- lblSearchIcon ----
            lblSearchIcon.setIcon(new ImageIcon(getClass().getResource("/search 24x24.png")));
            navpanel.add(lblSearchIcon);
            lblSearchIcon.setBounds(new Rectangle(new Point(210, 25), lblSearchIcon.getPreferredSize()));

            //---- btnMyAccount ----
            btnMyAccount.setText("My Account");
            btnMyAccount.setIcon(new ImageIcon(getClass().getResource("/my account 2 24x24.png")));
            btnMyAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            navpanel.add(btnMyAccount);
            btnMyAccount.setBounds(625, 20, 125, 30);

            //---- btnCart ----
            btnCart.setText("Cart");
            btnCart.setIcon(new ImageIcon(getClass().getResource("/cart 24x24.png")));
            btnCart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            navpanel.add(btnCart);
            btnCart.setBounds(new Rectangle(new Point(755, 20), btnCart.getPreferredSize()));

            //---- label1 ----
            label1.setText("Welcome User");
            label1.setForeground(Color.white);
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 3f));
            navpanel.add(label1);
            label1.setBounds(new Rectangle(new Point(70, 25), label1.getPreferredSize()));

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < navpanel.getComponentCount(); i++) {
                    Rectangle bounds = navpanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = navpanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                navpanel.setMinimumSize(preferredSize);
                navpanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(navpanel);
        navpanel.setBounds(0, 0, 855, 75);

        //======== bgpanel ========
        {
            bgpanel.setLayout(null);
        }
        contentPane.add(bgpanel);
        bgpanel.setBounds(0, 0, 855, 470);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Piyush
    private JPanel navpanel;
    private JLabel lblLogo;
    private JTextField txtSearch;
    private JLabel lblSearchIcon;
    private JButton btnMyAccount;
    private JButton btnCart;
    private JLabel label1;
    private JPanel bgpanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

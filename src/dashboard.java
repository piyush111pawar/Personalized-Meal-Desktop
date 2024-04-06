import java.awt.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Tue Mar 19 14:28:37 IST 2024
 */



/**
 * @author HP
 */
public class dashboard extends JFrame {
    public dashboard() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Piyush
        navpanel = new JPanel();
        lblLogo = new JLabel();
        label1 = new JLabel();
        btnSearch = new JButton();
        btnMyAccount = new JButton();
        btnCart = new JButton();
        bgpanel = new JPanel();
        contentpanel = new JPanel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== navpanel ========
        {
            navpanel.setBackground(new Color(0x6666ff));
            navpanel.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
            . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder
            . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .
            awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,navpanel. getBorder () ) )
            ; navpanel. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "bord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
            ;
            navpanel.setLayout(new GridLayout());

            //---- lblLogo ----
            lblLogo.setIcon(new ImageIcon(getClass().getResource("/logo for nav Preview (1).jpg")));
            navpanel.add(lblLogo);

            //---- label1 ----
            label1.setText("Welcome User");
            label1.setForeground(Color.white);
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 3f));
            navpanel.add(label1);

            //---- btnSearch ----
            btnSearch.setIcon(new ImageIcon(getClass().getResource("/search 24x24.png")));
            btnSearch.setText("Search");
            btnSearch.setHorizontalAlignment(SwingConstants.RIGHT);
            navpanel.add(btnSearch);

            //---- btnMyAccount ----
            btnMyAccount.setText("My Account");
            btnMyAccount.setIcon(new ImageIcon(getClass().getResource("/my account 2 24x24.png")));
            btnMyAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnMyAccount.setHorizontalAlignment(SwingConstants.RIGHT);
            navpanel.add(btnMyAccount);

            //---- btnCart ----
            btnCart.setText("Cart");
            btnCart.setIcon(new ImageIcon(getClass().getResource("/cart 24x24.png")));
            btnCart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnCart.setHorizontalAlignment(SwingConstants.RIGHT);
            navpanel.add(btnCart);
        }
        contentPane.add(navpanel);
        navpanel.setBounds(0, 0, 855, 75);

        //======== bgpanel ========
        {
            bgpanel.setLayout(null);

            //======== contentpanel ========
            {
                contentpanel.setLayout(null);
            }
            bgpanel.add(contentpanel);
            contentpanel.setBounds(0, 75, 855, 395);
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
        // Set bounds of the frame
        setBounds(100, 100, 855, 470); // Example bounds (x, y, width, height)

        // Maximize the frame
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Piyush
    private JPanel navpanel;
    private JLabel lblLogo;
    private JLabel label1;
    private JButton btnSearch;
    private JButton btnMyAccount;
    private JButton btnCart;
    private JPanel bgpanel;
    private JPanel contentpanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

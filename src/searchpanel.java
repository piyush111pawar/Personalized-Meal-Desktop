import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import java.awt.*;
import javax.swing.*;
import com.intellij.uiDesigner.core.*;
/*
 * Created by JFormDesigner on Tue Mar 19 15:06:28 IST 2024
 */



/**
 * @author HP
 */
public class searchpanel extends JPanel {
    public searchpanel() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Piyush
        lblSearchIcon = new JLabel();
        txtSearch = new JTextField();
        lblSearchpanelmsg = new JLabel();

        //======== this ========
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder (
        new javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion"
        , javax. swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM
        , new java. awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,12 )
        ,java . awt. Color .red ) , getBorder () ) );  addPropertyChangeListener(
        new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
        ) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException( )
        ;} } );
        setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), 0, 0));

        //---- lblSearchIcon ----
        lblSearchIcon.setIcon(new ImageIcon(getClass().getResource("/search 24x24.png")));
        add(lblSearchIcon, new GridConstraints(0, 0, 1, 1,
            GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        add(txtSearch, new GridConstraints(0, 1, 1, 1,
            GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, new Dimension(400, 30), null));

        //---- lblSearchpanelmsg ----
        lblSearchpanelmsg.setText("SEARCHED RESTAURANTS HERE");
        add(lblSearchpanelmsg, new GridConstraints(1, 1, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Piyush
    private JLabel lblSearchIcon;
    private JTextField txtSearch;
    private JLabel lblSearchpanelmsg;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

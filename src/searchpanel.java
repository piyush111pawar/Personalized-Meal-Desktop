import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import java.awt.*;
import javax.swing.*;
import com.intellij.uiDesigner.core.*;
import org.jdesktop.swingx.*;
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
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        txtSearch = new JTextField();
        lblSearchpanelmsg = new JLabel();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
        . swing. border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing
        . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
        Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
        ) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
        public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName (
        ) )) throw new RuntimeException( ); }} );
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //---- lblSearchIcon ----
        lblSearchIcon.setIcon(new ImageIcon(getClass().getResource("/search 24x24.png")));
        add(lblSearchIcon);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1);
        add(txtSearch);

        //---- lblSearchpanelmsg ----
        lblSearchpanelmsg.setText("SEARCHED RESTAURANTS HERE");
        add(lblSearchpanelmsg);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Piyush
    private JLabel lblSearchIcon;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JTextField txtSearch;
    private JLabel lblSearchpanelmsg;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

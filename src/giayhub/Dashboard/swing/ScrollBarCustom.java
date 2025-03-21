package giayhub.Dashboard.swing;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarCustom extends JScrollBar {

    public ScrollBarCustom() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(5, 5));
//        setForeground(new Color(94, 139, 231));
        setForeground(new Color(0, 0, 0));
        setUnitIncrement(20);
        setOpaque(false);
    }
}

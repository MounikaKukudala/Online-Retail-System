package ecommerce;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class mytextField extends JTextField {
    mytextField(String s) {
        super(s);
        //setFont(Font.SANS_SERIF);
        setBackground(new Color(255, 255, 255));
		setForeground(new Color(68, 50, 102));
		//setBorder(new LineBorder(new Color(132, 89, 107), 4));
		setMinimumSize(new Dimension(200,50));
		setPreferredSize(new Dimension(200, 50));
   }
}
package org.esca.app.auth;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Logotipo extends JPanel {
    public Logotipo() {
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setPreferredSize(new Dimension(383, getHeight()));
        setBackground(new java.awt.Color(14, 190, 208));
        JLabel title = new JLabel();
        title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bico_tech.png")));

        add(title);
    }
}

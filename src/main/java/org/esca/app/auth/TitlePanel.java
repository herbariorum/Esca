package org.esca.app.auth;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TitlePanel extends JPanel {
    public JButton btnClose;


    public TitlePanel(){
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setBackground(Color.BLACK);

        btnClose = new JButton("X");
        btnClose.setForeground(Color.GRAY);
        btnClose.setBorderPainted(false);
        btnClose.setFocusPainted(false);
        btnClose.setContentAreaFilled(false);

        btnClose.putClientProperty("JButton.buttonType", "roundRect");
        btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnClose.setContentAreaFilled(true);
                btnClose.setBackground(Color.RED);
                btnClose.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e){
                btnClose.setContentAreaFilled(false);
                btnClose.setForeground(Color.GRAY);
            }
        });


        JLabel title = new JLabel("LOGIN FORM");
        title.setForeground(Color.WHITE);
        title.setFont(new java.awt.Font("Roboto Black", Font.BOLD, 14));

        addItem(this, title, 0, 0, 2, 1, GridBagConstraints.CENTER);
        addItem(this, btnClose, 1, 0, 1, 1, GridBagConstraints.EAST);
    }


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Color color1 = new Color(87, 52, 71);
        Color color2 = Color.black;
        Graphics2D graphics2D = (Graphics2D) g;
        GradientPaint p = new GradientPaint(0, 0, color1, getWidth(), 0, color2);
        graphics2D.setPaint(p);
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
    }
    // X COL Y ROW
    private void addItem(JPanel p, JComponent c, int x, int y, int width, int height, int align) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = width;
        gc.gridheight = height;
        gc.weightx = 100.0;
        gc.weighty = 100.0;
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        p.add(c, gc);
    }
}

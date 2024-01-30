package org.esca.app.auth;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Formulario extends JPanel {

    public JTextField txtEmail;
    public JPasswordField txtPassword;

    public JButton btnLogin;

    public JLabel lblMailMessage, lblPasswordMessage;

    public Formulario() {
        setLayout(null);
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setPreferredSize(new Dimension(383, getHeight()));
        setFont(new java.awt.Font("Roboto Black", Font.BOLD, 12));
//        setBackground(Color.RED);
        JLabel titleIcon = new JLabel();
        titleIcon.setForeground(Color.WHITE);
        titleIcon.setIcon(new ImageIcon(getClass().getResource("/images/55827_lock_padlock_private_icon.png")));
        titleIcon.setBounds(127, 30, 128, 128);

        txtEmail = new JTextField();
        txtEmail.putClientProperty("JTextField.leadingIcon", new ImageIcon(getClass().getResource("/images/mail.png")));
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email");
        txtEmail.setBounds(50, 230, 270, 27);

        lblMailMessage = new JLabel();
        lblMailMessage.setForeground(Color.RED);
        lblMailMessage.setFont(new Font("Roboto", Font.ITALIC, 10));
        lblMailMessage.setBounds(50, 255, 270, 27);

        txtPassword = new JPasswordField();
        txtPassword.putClientProperty("JTextField.leadingIcon", new ImageIcon(getClass().getResource("/images/key.png")));
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");
        txtPassword.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true;"+"showCapsLock:true;");
        txtPassword.setBounds(50, 300, 270, 27);

        lblPasswordMessage = new JLabel();
        lblPasswordMessage.setForeground(Color.RED);
        lblPasswordMessage.setFont(new Font("Roboto", Font.ITALIC, 10));
        lblPasswordMessage.setBounds(50, 325, 270, 27);

        btnLogin = new JButton("Entrar");
        btnLogin.putClientProperty("JButton.buttonType", "roundRect");
        btnLogin.setBounds(120, 390, 120, 30);

        add(titleIcon);
        add(txtEmail);
        add(lblMailMessage);
        add(txtPassword);
        add(lblPasswordMessage);
        add(btnLogin);
    }

}

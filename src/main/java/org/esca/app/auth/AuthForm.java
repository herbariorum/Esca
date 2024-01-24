package org.esca.app.auth;


import com.formdev.flatlaf.FlatClientProperties;
import org.apache.commons.validator.routines.EmailValidator;
import org.esca.app.FormMenu;
import org.esca.app.auth.dao.impl.UsuarioDAOImpl;
import org.esca.app.auth.dominio.Usuarios;
import org.esca.app.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AuthForm extends JFrame {

    private static AuthForm login;
    private Formulario formulario;

    public AuthForm() {
        initComponents();
    }
    private void initComponents(){
        setTitle("Login Form");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        Container content = getContentPane();

        TitlePanel titleBar = new TitlePanel();
        titleBar.btnClose.addActionListener((ActionEvent actionEvent) -> {
            dispose();
        });

        formulario = new Formulario();
        formulario.btnLogin.addActionListener(this::btnLoginActionPerformed);

        formulario.txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                txtPasswordKeyPressed(e);
            }
        });
        content.add(titleBar, BorderLayout.NORTH);
        content.add(formulario, BorderLayout.WEST);
        content.add(new Logotipo(), BorderLayout.EAST);

        setSize(new java.awt.Dimension(766, 580));
        setLocationRelativeTo(null);
    }

    private void txtPasswordKeyPressed(KeyEvent evt){
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.logar();
        }
    }
    private void btnLoginActionPerformed(ActionEvent evt){
        this.logar();
    }

    private void logar(){
        String email = formulario.txtEmail.getText().strip();
        String pwd = String.valueOf(formulario.txtPassword.getPassword()).strip();
        JLabel msgMail = formulario.lblMailMessage;
        JLabel msgPwd = formulario.lblPasswordMessage;
        if (email.isEmpty()){
            msgMail.setText("Email requerido.");
            formulario.txtEmail.putClientProperty("JComponent.outline", "warning");
            formulario.txtEmail.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            formulario.txtEmail.requestFocus();
            return;
        }else{
            msgMail.setText(null);
            formulario.txtEmail.putClientProperty("JComponent.outline", Color.BLUE);
        }
        if (!EmailValidator.getInstance().isValid(email)){
            msgMail.setText("Email inválido.");
            formulario.txtEmail.putClientProperty("JComponent.outline", "warning");
            formulario.txtEmail.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            formulario.txtEmail.requestFocus();
            return;
        }else{
            msgMail.setText(null);
            formulario.txtEmail.putClientProperty("JComponent.outline", Color.BLUE);
        }
        if (pwd.isEmpty()){
            msgPwd.setText("Password requerido.");
            formulario.txtPassword.putClientProperty("JComponent.outline", "warning");
            formulario.txtPassword.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            formulario.txtPassword.requestFocus();
            return;
        }else{
            msgPwd.setText(null);
            formulario.txtPassword.putClientProperty("JComponent.outline", Color.BLUE);
        }

        if (!new Util().isValidPwd(pwd)){
            msgPwd.setText("Password inválido.");
            formulario.txtPassword.putClientProperty("JComponent.outline", "warning");
            formulario.txtPassword.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            formulario.txtPassword.requestFocus();
            return;
        }else{
            msgPwd.setText(null);
            formulario.txtPassword.putClientProperty("JComponent.outline", Color.BLUE);
        }
        Usuarios user = new Usuarios();
        user.setEmail(email);
        user.setPassword(pwd);

        this.conferirUser(user);
    }

    //    20mP'd!`46zz
    private void conferirUser(Usuarios user){
        UsuarioDAOImpl dao = new UsuarioDAOImpl();
        // Verifica se o usuário existe
        Usuarios u = dao.selectEmailUser(user);
        if (u == null){
            JOptionPane.showMessageDialog(this, "Usuário não existe\nou não está cadastrado", "Warning", JOptionPane.WARNING_MESSAGE);
        }else{
            // Verifica se a senha é válida
            boolean check = new Util().checkPassword(String.valueOf(formulario.txtPassword.getPassword()), u);
            if (!check){
                JOptionPane.showMessageDialog(this, "Senha não confere.\nTente novamente", "Warning", JOptionPane.WARNING_MESSAGE);
            }else{
                formulario.txtEmail.setText(null);
                formulario.txtPassword.setText(null);
                // Inicia o formulário principal
                new FormMenu(u).start();
                this.dispose();
            }
        }
    }

    public static AuthForm getInstance(){
        if (login == null){
            login = new AuthForm();
        }
        return login;
    }
}

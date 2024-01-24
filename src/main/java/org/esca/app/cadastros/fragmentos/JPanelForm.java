package org.esca.app.cadastros.fragmentos;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;

public class JPanelForm extends javax.swing.JPanel {
    private javax.swing.JLabel lblMsgCargo;
    private javax.swing.JLabel lblMsgEmail;
    private javax.swing.JLabel lblMsgNome;
    private javax.swing.JLabel lblMsgPhone;
    private javax.swing.JLabel lblMsgPwd;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JComboBox<Object> cbcRoles;

    public JPanelForm() {
        initComponents();
    }

    private void initComponents() {
        // Inicializa os componentes
        txtNome = new javax.swing.JTextField();
        txtNome.setPreferredSize(new Dimension(450, 27));
        txtEmail = new javax.swing.JTextField();
        txtEmail.setPreferredSize(new Dimension(450, 27));
        txtCargo = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        cbcRoles = new javax.swing.JComboBox<>();
        lblMsgNome = new javax.swing.JLabel();
        lblMsgNome.setText(null);
        lblMsgEmail = new javax.swing.JLabel();
        lblMsgEmail.setText(null);
        lblMsgCargo = new javax.swing.JLabel();
        lblMsgCargo.setText(null);
        lblMsgPhone = new javax.swing.JLabel();
        lblMsgPhone.setText(null);
        lblMsgPwd = new javax.swing.JLabel();
        lblMsgPwd.setText(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        this.setBackground(Color.BLUE);


        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING,false)
                                        .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                )
                                .addContainerGap(23, Short.MAX_VALUE)
                        )
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(181, Short.MAX_VALUE)
                        )
        );

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(txtNome)
                .addComponent(txtEmail)
        );

    }
}

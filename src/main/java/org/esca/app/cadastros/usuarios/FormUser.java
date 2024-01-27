package org.esca.app.cadastros.usuarios;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.TableRowSorter;
import org.esca.app.auth.dao.impl.RoleDAOImpl;
import org.esca.app.auth.dao.impl.UsuarioDAOImpl;
import org.esca.app.auth.dominio.Roles;
import org.esca.app.auth.dominio.Usuarios;
import org.esca.app.cadastros.estudantes.config.DateValidator;
import org.esca.app.cadastros.estudantes.config.DateValidatorUsingDateFormat;
import org.esca.app.cadastros.estudantes.config.EmailValidatorFormat;
import org.esca.app.cadastros.usuarios.config.UserCellRenderer;
import org.esca.app.cadastros.usuarios.config.UserTableModel;
import org.esca.app.util.ComboBoxList;
import org.esca.app.util.GeradorSenha;
import org.esca.app.util.Util;

public class FormUser extends javax.swing.JDialog {

    private List<Usuarios> users;
    private Usuarios user = new Usuarios();
    private final UsuarioDAOImpl daoUser = new UsuarioDAOImpl();
    private final RoleDAOImpl daoRole = new RoleDAOImpl();
    private UserTableModel modelo;
    Font fontError = new Font("Roboto Black", Font.ITALIC, 10);
    private EmailValidatorFormat validator;
    public FormUser(javax.swing.JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        panelTitle.btnClose.addActionListener((ActionEvent actionEvent) -> {
            dispose();
        });
        panelTitle.btnClose.putClientProperty("JButton.buttonType", "roundRect");
        panelTitle.lblTitle.setText("Listagem de Usuários");

        jPanelButton1.btnNew.addActionListener((ActionEvent actionEvent) -> {
            adicionarActionPerformed(actionEvent);
        });
        jPanelButton1.btnCancelar.addActionListener((ActionEvent actionEvent) -> {
            cancelarActionPerformed(actionEvent);
        });

        jPanelButton1.btnSalvar.addActionListener((ActionEvent actionEvent) -> {
            salvarActionPerformed(actionEvent);
        });

        jPanelButton1.btnRemover.addActionListener((ActionEvent actionEvent) -> {
            removerActionPerformed(actionEvent);
        });

        txtNome.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nome do Usuário");
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email do Usuário");
        txtCargo.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Cargo");
        txtPhone.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Telefone Ex.: 63999329304");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");
        txtPassword.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true;" + "showCapsLock:true;");

        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabelaMouseClicked(e);
            }
        });

        txtLocalizar.putClientProperty("JTextField.leadingIcon", new ImageIcon("/images/24 search.png"));
        txtLocalizar.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Localizar");
        txtLocalizar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                txtLocalizarKeyPressed(e);
            }
        });

        lblMsgNome.setText(null);
        lblMsgNome.setFont(fontError);
        lblMsgEmail.setText(null);
        lblMsgEmail.setFont(fontError);
        lblMsgCargo.setText(null);
        lblMsgCargo.setFont(fontError);
        lblMsgPhone.setText(null);
        lblMsgPhone.setFont(fontError);
        lblMsgPwd.setText(null);
        lblMsgPwd.setFont(fontError);

        this.habilitaBotoes(true);
        this.habilitaCampos(false);
        this.setComboBoxRole();

        setSize(new Dimension(800, 525));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTitle = new org.esca.app.cadastros.fragmentos.JPanelTitle();
        jPanelButton1 = new org.esca.app.cadastros.fragmentos.JPanelButton();
        txtLocalizar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        panelForm = new javax.swing.JPanel();
        txtNome = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtCargo = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        cbcRoles = new javax.swing.JComboBox<>();
        lblMsgNome = new javax.swing.JLabel();
        lblMsgEmail = new javax.swing.JLabel();
        lblMsgCargo = new javax.swing.JLabel();
        lblMsgPhone = new javax.swing.JLabel();
        lblMsgPwd = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        panelForm.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informações do Usuário", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 0, 13))); // NOI18N

        txtNome.setName("nomeUsuario"); // NOI18N
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeKeyReleased(evt);
            }
        });

        txtEmail.setName("emailUsuario"); // NOI18N
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        txtCargo.setName("cargoUsuario"); // NOI18N
        txtCargo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCargoKeyReleased(evt);
            }
        });

        txtPhone.setName("telefoneUsuario"); // NOI18N
        txtPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPhoneKeyTyped(evt);
            }
        });

        txtPassword.setName("passwordUsuario"); // NOI18N
        txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPasswordFocusGained(evt);
            }
        });
        txtPassword.addActionListener(this::txtPasswordActionPerformed);

        lblMsgNome.setFont(new java.awt.Font("Roboto", 2, 10)); // NOI18N
        lblMsgNome.setForeground(new java.awt.Color(255, 51, 102));
        lblMsgNome.setText("jLabel1");

        lblMsgEmail.setFont(new java.awt.Font("Roboto", 2, 10)); // NOI18N
        lblMsgEmail.setForeground(new java.awt.Color(255, 51, 102));
        lblMsgEmail.setText("jLabel2");

        lblMsgCargo.setFont(new java.awt.Font("Roboto", 2, 10)); // NOI18N
        lblMsgCargo.setForeground(new java.awt.Color(255, 51, 102));
        lblMsgCargo.setText("jLabel3");

        lblMsgPhone.setFont(new java.awt.Font("Roboto", 2, 10)); // NOI18N
        lblMsgPhone.setForeground(new java.awt.Color(255, 51, 102));
        lblMsgPhone.setText("jLabel4");

        lblMsgPwd.setFont(new java.awt.Font("Roboto", 2, 10)); // NOI18N
        lblMsgPwd.setForeground(new java.awt.Color(255, 51, 102));
        lblMsgPwd.setText("jLabel5");

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblMsgNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelFormLayout.createSequentialGroup()
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbcRoles, 0, 92, Short.MAX_VALUE))
                            .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail)
                            .addComponent(lblMsgEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblMsgCargo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCargo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPhone)
                            .addComponent(lblMsgPhone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addComponent(lblMsgPwd, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMsgNome)
                    .addComponent(lblMsgEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMsgCargo)
                    .addComponent(lblMsgPhone))
                .addGap(25, 25, 25)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbcRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMsgPwd)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                    .addComponent(txtLocalizar)
                    .addComponent(jPanelButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelForm, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(621, 523));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        preencherTabela("");
    }//GEN-LAST:event_formWindowActivated

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        int row = tabela.getSelectedRow();
        if (row != -1) {
            this.preencheForm(row);
        }
    }//GEN-LAST:event_tabelaMouseClicked

    private void txtPhoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneKeyTyped
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;
        if (!numero) {
            evt.consume();
        }
        if (txtPhone.getText().strip().length() == 11) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPhoneKeyTyped

    private void txtNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyReleased

        this.toUpper(txtNome);
    }//GEN-LAST:event_txtNomeKeyReleased

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtCargoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCargoKeyReleased
        this.toUpper(txtCargo);
    }//GEN-LAST:event_txtCargoKeyReleased

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void txtPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordFocusGained
        lblMsgPwd.setText(new GeradorSenha().gerarSenha());
    }//GEN-LAST:event_txtPasswordFocusGained

    private void txtLocalizarKeyPressed(KeyEvent evt) {
        preencherTabela(txtLocalizar.getText());
    }

    private void adicionarActionPerformed(ActionEvent evt) {
        this.user.setId(null);
        this.habilitaBotoes(false);
        this.habilitaCampos(true);
        this.limpaCampos();
    }

    private void cancelarActionPerformed(ActionEvent evt) {
        this.habilitaBotoes(true);
        this.habilitaCampos(false);
        this.limpaCampos();
    }

    private void salvarActionPerformed(ActionEvent evt) {
        // Pega os valores dos campos
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String cargo = txtCargo.getText();
        String phone = txtPhone.getText();
        String pwd = Arrays.toString(txtPassword.getPassword());
        if (nome.isEmpty()) {
            lblMsgNome.setText("Nome é obrigatório");
            txtNome.putClientProperty("JComponent.outline", "warning");
            txtNome.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            txtNome.requestFocus();
            return;
        } else if (nome.length() <= 5) {
            lblMsgNome.setText("Nome deve ter no mínimo 5 characteres.");
            txtNome.putClientProperty("JComponent.outline", "warning");
            txtNome.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            txtNome.requestFocus();
            return;
        } else {
            lblMsgNome.setText(null);
            txtNome.putClientProperty("JComponent.outline", java.awt.Color.GREEN);
        }
        validator = new EmailValidatorFormat();
        if (!validator.isValid(email)) {
            lblMsgEmail.setText("Email inválido");
            txtEmail.putClientProperty("JComponent.outline", "warning");
            txtEmail.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            txtEmail.requestFocus();
            return;
        } else {
            lblMsgEmail.setText(null);
            txtEmail.putClientProperty("JComponent.outline", java.awt.Color.GREEN);
        }
        if (cargo.isEmpty()) {
            lblMsgCargo.setText("Cargo é obrigatório");
            txtCargo.putClientProperty("JComponent.outline", "warning");
            txtCargo.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            txtCargo.requestFocus();
            return;
        } else {
            lblMsgCargo.setText(null);
            txtCargo.putClientProperty("JComponent.outline", java.awt.Color.GREEN);
        }
        if (!phone.matches("\\d{11}")) {
            lblMsgPhone.setText("Telefone é obrigatório");
            txtPhone.putClientProperty("JComponent.outline", "warning");
            txtPhone.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            txtPhone.requestFocus();
            return;
        } else {
            lblMsgPhone.setText(null);
            txtPhone.putClientProperty("JComponent.outline", java.awt.Color.GREEN);
        }
        if (pwd.isEmpty()) {
            lblMsgPwd.setText("Password é obrigatório");
            txtPassword.putClientProperty("JComponent.outline", "warning");
            txtPassword.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            txtPassword.requestFocus();
            return;
        } else if (!new Util().isValidPwd(pwd)) {
            lblMsgPwd.setText("Password inválido");
            txtPassword.putClientProperty("JComponent.outline", "warning");
            txtPassword.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            txtPassword.requestFocus();
            return;
        } else {
            lblMsgPwd.setText(null);
            txtPassword.putClientProperty("JComponent.outline", java.awt.Color.GREEN);
        }
        LocalDate created_at = LocalDate.now();

        // Envia os dados para a classe de serviço
        this.user.setNome(nome);
        this.user.setEmail(email);
        this.user.setCargo(cargo);
        this.user.setPhone(phone);
        ComboBoxList roleId = (ComboBoxList) this.cbcRoles.getSelectedItem();

        Roles role = new Roles();
        role.setId(roleId.getId());
        role.setRole(roleId.getName());

        this.user.setRole(role);

        this.user.setPassword(new Util().gerarPassword(pwd));
        if (this.user.getCreated_at() == null) {
            this.user.setCreated_at(created_at);
        } else {
            this.user.setCreated_at(this.user.getCreated_at());
        }

        if (user.getId() == null) {
            try {
                daoUser.addUser(user);
                JOptionPane.showMessageDialog(this, "Salvo com sucesso. ", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                this.habilitaBotoes(true);
                this.habilitaCampos(false);
                this.limpaCampos();
                this.alterarCorDoBorderTexto();
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(this, "Error " + e.getMessage());
            }
        } else {
            try {
                daoUser.updateUser(user);
                JOptionPane.showMessageDialog(this, "Atualizado com sucesso. ", "Sucesso.", JOptionPane.INFORMATION_MESSAGE);
                this.habilitaBotoes(true);
                this.habilitaCampos(false);
                this.limpaCampos();
                this.alterarCorDoBorderTexto();
                this.user.setId(null);
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(this, "Error " + e.getMessage());
            }
        }
    }

    private void removerActionPerformed(ActionEvent evt) {
        if (this.user.getId() == null) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione um registro na tabela primeiro.");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Confirma a exclusão?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                daoUser.deleteUser(this.user);
                this.habilitaCampos(true);
                this.limpaCampos();
                this.user.setId(null);
            } else {
                this.habilitaCampos(true);
                this.limpaCampos();
                this.user.setId(null);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Object> cbcRoles;
    private org.esca.app.cadastros.fragmentos.JPanelButton jPanelButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMsgCargo;
    private javax.swing.JLabel lblMsgEmail;
    private javax.swing.JLabel lblMsgNome;
    private javax.swing.JLabel lblMsgPhone;
    private javax.swing.JLabel lblMsgPwd;
    private javax.swing.JPanel panelForm;
    private org.esca.app.cadastros.fragmentos.JPanelTitle panelTitle;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLocalizar;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables

    public void preencherTabela(String value) {
        this.users = daoUser.selectByValue(value);
        modelo = new UserTableModel(users);
        tabela.setModel(modelo);
        tabela.setDefaultRenderer(Object.class, new UserCellRenderer());
        tabela.setRowSorter(new TableRowSorter<>(tabela.getModel()));
    }

    private void habilitaBotoes(boolean valor) {
        jPanelButton1.btnNew.setEnabled(valor);
        jPanelButton1.btnSalvar.setEnabled(!valor);
        jPanelButton1.btnCancelar.setEnabled(!valor);
    }

    private void habilitaCampos(boolean valor) {
        txtNome.setEnabled(valor);
        txtEmail.setEnabled(valor);
        txtCargo.setEnabled(valor);
        txtPhone.setEnabled(valor);
        txtPassword.setEnabled(valor);
        cbcRoles.setEnabled(valor);
    }

    private void limpaCampos() {
        txtNome.setText("");
        txtEmail.setText("");
        txtCargo.setText("");
        txtPhone.setText("");
        txtPassword.setText("");
    }

    private void preencheForm(int row) {
        Long userId = Long.valueOf(tabela.getValueAt(row, 0).toString());
        Long roleId = Long.valueOf(tabela.getValueAt(row, 1).toString());
        String nome = tabela.getValueAt(row, 2).toString();
        String email = tabela.getValueAt(row, 3).toString();
        String cargo = tabela.getValueAt(row, 4).toString();
        String telefone = tabela.getValueAt(row, 5).toString();
        String created_at = tabela.getValueAt(row, 6).toString();
        String role = tabela.getValueAt(row, 7).toString();
        txtNome.setText(nome);
        txtEmail.setText(email);
        txtCargo.setText(cargo);
        txtPhone.setText(telefone);
        txtPassword.setText(null);
        for (ComboBoxList a : this.daoRole.getList()) {
            a.setSelectedId(daoRole.getList(), String.valueOf(roleId), cbcRoles);
        }

        Roles r = new Roles();
        r.setId(roleId);
        r.setRole(role);
        Usuarios u = new Usuarios();
        u.setId(userId);
        u.setNome(nome);
        u.setEmail(email);
        u.setCargo(cargo);
        u.setPhone(telefone);
        u.setCreated_at(new Util().dateToLocalDate(created_at));
        u.setRole(r);
        this.user = u;

        this.habilitaCampos(true);
        this.habilitaBotoes(false);
    }

    private void alterarCorDoBorderTexto() {
        for (java.awt.Component c : panelForm.getComponents()) {
            if (c instanceof javax.swing.JTextField) {
                ((javax.swing.JTextField) c).putClientProperty("JComponent.outline", null);
            } else if (c instanceof javax.swing.JFormattedTextField) {
                ((javax.swing.JFormattedTextField) c).putClientProperty("JComponent.outline", null);
            } else if (c instanceof javax.swing.JPasswordField) {
                ((javax.swing.JPasswordField) c).putClientProperty("JComponent.outline", null);
            }
        }
    }

    private void setComboBoxRole() {
        daoRole.comboBoxRole();
        cbcRoles.removeAllItems();
        for (ComboBoxList a : daoRole.getList()) {
            cbcRoles.addItem(a);
        }
    }

    private void toUpper(javax.swing.JComponent componente) {
        if (componente instanceof JTextField) {
            String s = ((JTextField) componente).getText().toUpperCase();
            ((JTextField) componente).setText(s);
        }
    }

    public JDialog getInstance() {
        return this;
    }

    public void start() {
        setVisible(true);
    }
}

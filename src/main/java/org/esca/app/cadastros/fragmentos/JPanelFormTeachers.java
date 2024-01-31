package org.esca.app.cadastros.fragmentos;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class JPanelFormTeachers extends JPanel {
    public javax.swing.JTextField txtNome, txtCpf, txtDtaNascimento, txtCargo, txtAgencia, txtConta, txtSalario;
    public javax.swing.JTextField txtLogradouro, txtNumero, txtTelefone, txtComplemento, txtBairro, txtCep, txtBanco;
    public javax.swing.JComboBox cbxEsdado, cbxCidade;
    public javax.swing.JButton btnAddConta;
    private Font fontError = new Font("Roboto Black", Font.ITALIC, 10);
    public javax.swing.JLabel lblMsgNome, lblMsg;


    public JPanelFormTeachers() {
        criarComponents();
    }

    private void criarComponents(){
        JPanel panel = new JPanel();
        FormLayout layout = new FormLayout(
                "150px, 4dlu, 90px, 4dlu, 70px, 4dlu, 70px, 4dlu, 80px", // 09 columns
                        "4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref," +
                        "4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref," + //  22 rows
                        "4dlu, pref"
        );
        panel.setLayout(layout);

        PanelBuilder builder = new PanelBuilder(layout, panel);
        CellConstraints cc = new CellConstraints();

        builder.border(Borders.DLU4);
        builder.opaque(true);
        builder.addSeparator("Informações do Professor", cc.xyw(1, 2, 9));

        txtNome = new JTextField();
        txtNome.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nome do Professor");
        builder.add(txtNome, cc.xyw(1,4, 9));
        txtNome.setPreferredSize(new Dimension(470, 23));
        lblMsgNome = new JLabel();
        lblMsgNome.setFont(fontError);
        lblMsgNome.setForeground(Color.RED);
        builder.add(lblMsgNome, cc.xyw(1,6, 7));

        txtCpf = new JTextField();
        txtCpf.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "CPF do Professor");
        builder.add(txtCpf, cc.xy(1,8));

        txtDtaNascimento = new JTextField();
        txtDtaNascimento.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Dta Nascimento");
        builder.add(txtDtaNascimento, cc.xy(3, 8));

        txtCargo = new JTextField();
        txtCargo.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Cargo do Professor");
        builder.add(txtCargo, cc.xyw(5, 8, 5));

        lblMsg = new JLabel();
        lblMsg.setFont(fontError);
        lblMsg.setForeground(Color.RED);
        builder.add(lblMsg, cc.xyw(1,10, 7));

        builder.addSeparator("Informações Bancária", cc.xyw(1, 12, 9));

        txtBanco = new JTextField();
        txtBanco.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Banco");
        txtBanco.setEditable(false);
        builder.add(txtBanco, cc.xy(1, 14));

        btnAddConta = new JButton("Add");
        builder.add(btnAddConta, cc.xy(3, 14));
        btnAddConta.putClientProperty("JButton.buttonType", "roundRect");
        btnAddConta.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/172525_plus_icon.png"))));


        txtAgencia = new JTextField();
        txtAgencia.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Agência");
        txtAgencia.setEditable(false);
        builder.add(txtAgencia, cc.xy(5, 14));

        txtConta = new JTextField();
        txtConta.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Conta");
        txtConta.setEditable(false);
        builder.add(txtConta, cc.xy(7, 14));

        txtSalario = new JTextField();
        txtSalario.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Salário");
        txtSalario.setEditable(false);
        builder.add(txtSalario, cc.xy(9, 14));

        builder.addSeparator("Endereço do Professor", cc.xyw(1, 16, 9));

        txtLogradouro = new JTextField();
        txtLogradouro.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Logradouro");
        builder.add(txtLogradouro, cc.xyw(1, 18, 9));

        txtComplemento = new JTextField();
        txtComplemento.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Complemento");
        builder.add(txtComplemento, cc.xyw(1, 20, 9));

        txtBairro = new JTextField();
        txtBairro.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Bairro");
        builder.add(txtBairro, cc.xy(1, 22));

        cbxEsdado = new JComboBox();
        builder.add(cbxEsdado, cc.xy(3, 22));

        cbxCidade = new JComboBox();
        builder.add(cbxCidade, cc.xyw(5, 22, 3));

        txtCep = new JTextField();
        txtCep.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "77960000");
        builder.add(txtCep, cc.xy(9, 22));

        add(panel);
    }
}

package org.esca.app.cadastros.fragmentos;

import com.formdev.flatlaf.FlatClientProperties;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.awt.*;

public class JPanelFormStudent extends JPanel {
    public javax.swing.JTextField txtNome, txtCpf, txtDtaNascimento, txtNomeMae, txtNomePai, txtRespAcademico, txtRespFinanceiro, txtNacionalidade;
    public javax.swing.JTextField txtLogradouro, txtNumero, txtTelefone, txtComplemento, txtBairro, txtCep;
    public javax.swing.JComboBox cbxSexo, cbxNaturalidade, cbxEstadoNaturalidade, cbxEsdado, cbxCidade;
    public javax.swing.JCheckBox ckbStatus;
    private Font fontError = new Font("Roboto Black", Font.ITALIC, 10);
    public javax.swing.JLabel lblMsgNome, lblMsgNomeMae, lblMsg, lblMsgLogradouro;
    public JPanelFormStudent() {
        criaComponents();
    }

    private void criaComponents(){
        System.out.println("Formulario std");
        JPanel panel = new JPanel();

        FormLayout layout = new FormLayout(
                "150px, 4dlu, 100px, 4dlu, pref, 4dlu, 70px, 4dlu, 70px, 4dlu, pref", //columns
                        "4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, " +
                        "4dlu,  pref, 4dlu, pref, 4dlu, pref, 4dlu, pref, " +
                        "4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref,"    +
                        "4dlu, pref, 4dlu, pref, 4dlu, pref, 4dlu, pref"
                //rows
        );

        panel.setLayout(layout);

        PanelBuilder builder = new PanelBuilder(layout, panel);
        CellConstraints cc = new CellConstraints();

        builder.border(Borders.DLU4);
        builder.opaque(true);
        builder.addSeparator("Informações do Estudante", cc.xyw(1, 2, 11));

        txtNome = new JTextField();
        txtNome.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nome do Estudante");
        builder.add(txtNome, cc.xyw(1,4, 11));
        txtNome.setPreferredSize(new Dimension(470, 23));
        lblMsgNome = new JLabel();
        lblMsgNome.setFont(fontError);
        lblMsgNome.setForeground(Color.RED);
        builder.add(lblMsgNome, cc.xyw(1,6, 11));
        txtCpf = new JTextField();
        txtCpf.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "CPF do Estudante");
        builder.add(txtCpf, cc.xyw(1, 8, 3));
        txtCpf.setPreferredSize(new Dimension(150, 23));
        txtDtaNascimento = new JTextField();
        txtDtaNascimento.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "11/06/1969");
        txtDtaNascimento.setPreferredSize(new Dimension(150, 23));
        builder.add(txtDtaNascimento, cc.xy(5, 8));
        cbxSexo = new JComboBox<String>();
        cbxSexo.setPreferredSize(new Dimension(100, 23));
        cbxSexo.removeAllItems();
        cbxSexo.addItem("MASCULINO");
        cbxSexo.addItem("FEMININO");
        builder.add(cbxSexo, cc.xyw(7, 8, 3));
        ckbStatus = new JCheckBox("Ativo");
        ckbStatus.setPreferredSize(new Dimension(100, 23));
        builder.add(ckbStatus, cc.xy(11, 8));
        lblMsg = new JLabel();
        lblMsg.setFont(fontError);
        lblMsg.setForeground(Color.RED);
        builder.add(lblMsg, cc.xyw(1, 10, 11));

        txtNomeMae = new JTextField();
        txtNomeMae.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nome da Mãe");
        txtNomeMae.setPreferredSize(new Dimension(235, 23));
        builder.add(txtNomeMae, cc.xyw(1, 12,11));

        lblMsgNomeMae = new JLabel();
        lblMsgNomeMae.setFont(fontError);
        lblMsgNomeMae.setForeground(Color.RED);
        builder.add(lblMsgNomeMae, cc.xyw(1, 14, 11));

        txtNomePai = new JTextField();
        txtNomePai.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nome do Pai");
        txtNomePai.setPreferredSize(new Dimension(235, 23));
        builder.add(txtNomePai, cc.xyw(1, 16, 11));

        txtNacionalidade = new JTextField();
        txtNacionalidade.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nacionalidade");
        txtNacionalidade.setPreferredSize(new Dimension((int) txtNacionalidade.getPreferredSize().getWidth(), 23));
        builder.add(txtNacionalidade, cc.xyw(1, 18, 3));

        cbxEstadoNaturalidade = new JComboBox();
        cbxEstadoNaturalidade.setPreferredSize(new Dimension((int) cbxEstadoNaturalidade.getPreferredSize().getWidth(), 23));
        builder.add(cbxEstadoNaturalidade, cc.xy(5, 18));
        cbxNaturalidade = new JComboBox();
        cbxNaturalidade.setPreferredSize(new Dimension((int) cbxNaturalidade.getPreferredSize().getWidth(), 23));
        builder.add(cbxNaturalidade, cc.xyw( 7, 18, 5));

        txtRespAcademico = new JTextField();
        txtRespAcademico.setPreferredSize(new Dimension((int) txtRespAcademico.getPreferredSize().getWidth(), 23));
        txtRespAcademico.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Responsável Acadêmico");
        builder.add(txtRespAcademico, cc.xyw(1, 20, 11));
        txtRespFinanceiro = new JTextField();
        txtRespFinanceiro.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Responsável Financeiro");
        txtRespFinanceiro.setPreferredSize(new Dimension((int)txtRespFinanceiro.getPreferredSize().getWidth(), 23));
        builder.add(txtRespFinanceiro, cc.xyw(1, 22, 11));

        builder.addSeparator("Endereço do Estudante", cc.xyw(1, 24, 11));

        txtLogradouro = new JTextField();
        txtLogradouro.setPreferredSize(new Dimension((int) txtLogradouro.getPreferredSize().getWidth(), 23));
        txtLogradouro.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Logradouro. Ex. Rua, Avenida etc");
        builder.add(txtLogradouro, cc.xyw(1, 26, 7));
        txtNumero = new JTextField();
        txtNumero.setPreferredSize(new Dimension((int) txtNumero.getPreferredSize().getWidth(), 23));
        txtNumero.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Número");
        builder.add(txtNumero, cc.xy(9, 26));

        txtTelefone = new JTextField();
        txtTelefone.setPreferredSize(new Dimension((int) txtTelefone.getPreferredSize().getWidth(), 23));
        txtTelefone.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Telefone");
        builder.add(txtTelefone, cc.xy(11, 26));

        lblMsgLogradouro = new JLabel();
        lblMsgLogradouro.setFont(fontError);
        lblMsgLogradouro.setForeground(Color.RED);
        builder.add(lblMsgLogradouro, cc.xyw(1, 28, 11));


        txtComplemento = new JTextField();
        txtComplemento.setPreferredSize(new Dimension((int) txtComplemento.getPreferredSize().getWidth(), 23));
        txtComplemento.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Complemento");
        builder.add(txtComplemento, cc.xyw(1, 30, 11));

        txtBairro = new JTextField();
        txtBairro.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Bairro");
        txtBairro.setPreferredSize(new Dimension((int) txtBairro.getPreferredSize().getWidth(), 23));
        builder.add(txtBairro, cc.xyw(1, 32, 3));

        cbxEsdado = new JComboBox();
        cbxEsdado.setPreferredSize(new Dimension((int) cbxEsdado.getPreferredSize().getWidth(), 23));
        builder.add(cbxEsdado, cc.xy(5, 32));

        cbxCidade = new JComboBox();
        cbxCidade.setPreferredSize(new Dimension((int) cbxCidade.getPreferredSize().getWidth(), 23));
        builder.add(cbxCidade, cc.xyw(7, 32, 3));

        txtCep = new JTextField();
        txtCep.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "CEP");
        txtCep.setPreferredSize(new Dimension((int) txtCep.getPreferredSize().getWidth(), 23));
        builder.add(txtCep, cc.xy(11, 32));

        add(panel);
    }
}

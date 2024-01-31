package org.esca.app.cadastros.modelos;

import com.formdev.flatlaf.FlatClientProperties;
import lombok.Getter;
import lombok.Setter;
import org.esca.app.cadastros.fragmentos.JPanelButton;
import org.esca.app.cadastros.fragmentos.JPanelFormStudent;
import org.esca.app.cadastros.fragmentos.JPanelTable;
import org.esca.app.cadastros.fragmentos.JPanelTitle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

abstract public class FormPadraoTwo extends javax.swing.JDialog{

    abstract public void limparCampos();
    abstract public void habilitarCampos(boolean valor);
    abstract public void habilitarBotoes(boolean valor);
    abstract public void preencherFormulario();
    abstract public void preencherTabela(String valor);
    abstract public void salvarNoBD();
    abstract public void deleteDoBD();
    private Font fontError = new Font("Roboto Black", Font.ITALIC, 10);
    private JPanelTitle panelTitle;
    public JPanelTable panelTable;
    public JPanelButton panelButton;
    public JTabbedPane abas ;

    public JPanel formulario;
    private String titulo;

    public FormPadraoTwo(javax.swing.JFrame owner, boolean modal, String titulo){
        super(owner, modal);
        Container content = getContentPane();
        this.titulo = titulo;
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        this.panelTable = new JPanelTable();

        content.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        panelTitle = new JPanelTitle();
        panelTitle.lblTitle.setText(this.titulo);

        panelTitle.btnClose.putClientProperty("JButton.buttonType", "roundRect");
        panelTitle.btnClose.addActionListener((ActionEvent evt) -> {dispose();});
        gbc.weightx = 1;
        gbc.weighty = 0.01;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        content.add(panelTitle, gbc);

        panelButton = new JPanelButton();
        gbc.weighty = 0.01;
        gbc.gridy = 1;
        content.add(panelButton, gbc);

        gbc.weighty = 0.98;
        gbc.gridy = 2;
        content.add(Abas(), gbc);


        this.panelTable.txtLocalizar.putClientProperty("JTextField.leadingIcon", new ImageIcon(getClass().getResource("/images/211818_search_icon.png")));
        this.panelTable.txtLocalizar.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Localizar");
        this.panelTable.txtLocalizar.setPreferredSize(new Dimension(panelTable.txtLocalizar.getWidth(), 27));

        setSize(new Dimension(800, 550));
        setLocationRelativeTo(null);
    }


    private JTabbedPane Abas(){
        this.abas = new JTabbedPane(JTabbedPane.TOP);
        this.abas.setBorder(BorderFactory.createEmptyBorder(5, 10, 15 , 10));

        this.formulario = new JPanel();
        this.abas.addTab("Formulário", this.formulario);
        this.abas.addTab("Listagem" ,this.panelTable);
        this.abas.setSelectedIndex(1);

        return abas;
    }

    private javax.swing.JDialog getInstance(){ return this; };
    private void start(){ setVisible(true);};
}

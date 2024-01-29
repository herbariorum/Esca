package org.esca.app.cadastros.modelos;

import com.formdev.flatlaf.FlatClientProperties;
import com.jgoodies.forms.builder.ListViewBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import org.esca.app.cadastros.fragmentos.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

abstract public class FormPadrão extends javax.swing.JDialog{

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
    public JPanelFormStudent panelForm;
    private String titulo;
    public FormPadrão(javax.swing.JFrame owner, boolean modal, String titulo) {
        super(owner, modal);
        Container content = getContentPane();
        this.titulo = titulo;
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);


//         DEFINIR O GRID PRINCIPAL
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
        panelForm = new JPanelFormStudent();
//        panelForm.setBackground(Color.BLUE);
        gbc.weighty = 0.38;
        gbc.gridy = 1;
        content.add(panelForm, gbc);

        panelButton = new JPanelButton();
        gbc.weighty = 0.01;
        gbc.gridy = 2;
        content.add(panelButton, gbc);

        panelTable = new JPanelTable();
        gbc.weighty = 0.60;
        gbc.gridy = 3;
        content.add(panelTable, gbc);

        panelTable.txtLocalizar.putClientProperty("JTextField.leadingIcon", new ImageIcon(getClass().getResource("/images/211818_search_icon.png")));
        panelTable.txtLocalizar.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Localizar");
        panelTable.txtLocalizar.setPreferredSize(new Dimension(panelTable.txtLocalizar.getWidth(), 27));

        setSize(new Dimension(800, 750));
        setLocationRelativeTo(null);
    }


    private javax.swing.JDialog getInstance(){ return this; };
    private void start(){ setVisible(true);};
}

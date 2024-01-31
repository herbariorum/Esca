package org.esca.app.cadastros.professores.aux;

import org.esca.app.cadastros.dao.impl.BancoDAOImpl;
import org.esca.app.cadastros.dao.impl.TearcherDAOImpl;
import org.esca.app.cadastros.dominio.Teachers;
import org.esca.app.cadastros.fragmentos.JPanelTitle;
import org.esca.app.cadastros.professores.ProfessorDlg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Locale;

public class ProfessorDlgAux extends javax.swing.JDialog{

    private JPanelTitle panelTitle;
    private JPanel topPanel, formPanel;
    private JPanel btnPanel;
    private JScrollPane tableContainer;
    private String titulo;
    private JTextField txtBanco, txtAgencia, txtConta;
    private JFormattedTextField txtSalario;
    private TearcherDAOImpl tearcherDAO;
    private BancoDAOImpl bancoDAO;
    private Long idTeacher;
    private JLabel lblMsg;
    public ProfessorDlgAux(javax.swing.JDialog parent, boolean modal, String titulo, Long idTeacher){
        super(parent, titulo, modal);
        this.titulo = titulo;
        this.idTeacher = idTeacher;

        this.tearcherDAO = new TearcherDAOImpl();
        this.bancoDAO = new BancoDAOImpl();

        initComponents();
    }

    private void initComponents(){
        Container container = getContentPane();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        Locale.setDefault(new Locale("pt", "BR"));
        panelTitle = new JPanelTitle();
        panelTitle.lblTitle.setText(this.titulo);
        panelTitle.btnClose.putClientProperty("JButton.buttonType", "roundRect");
        panelTitle.btnClose.addActionListener((ActionEvent evt) -> {dispose();});

        topPanel = new JPanel();
        // FORMULÁRIO
        formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JLabel label1 = new JLabel("Banco");
        txtBanco = new JTextField(15);
        txtBanco.setPreferredSize(new Dimension(100, 23));
        JLabel label2 = new JLabel("Agência Bancária");
        txtAgencia = new JTextField(15);
        txtAgencia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                int key = evt.getKeyChar();
                boolean numero = key >= 48 && key <= 57;
                if (!numero) {
                    evt.consume();
                }
            }
        });
        JLabel label3 = new JLabel("Conta Corrente");
        txtConta = new JTextField(15);
        txtConta.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                int key = evt.getKeyChar();
                boolean numero = key >= 48 && key <= 57;
                if (!numero) {
                    evt.consume();
                }
            }
        });
        JLabel label4 = new JLabel("Salário Mensal");
        txtSalario = new JFormattedTextField();
        txtSalario.setColumns(10);
        txtSalario.setFormatterFactory( new javax.swing.text.DefaultFormatterFactory(	new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3 );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(label1, gbc);
        gbc.gridy = 1;
        formPanel.add(txtBanco, gbc);

        gbc.gridy= 2;
        formPanel.add(label2, gbc);
        gbc.gridy = 3;
        formPanel.add(txtAgencia, gbc);

        gbc.gridy = 4;
        formPanel.add(label3, gbc);
        gbc.gridy = 5;
        formPanel.add(txtConta, gbc);

        gbc.gridy = 6;
        formPanel.add(label4, gbc);
        gbc.gridy = 7;
        formPanel.add(txtSalario, gbc);

        lblMsg = new JLabel();
        lblMsg.setForeground(Color.RED);
        gbc.gridy = 8;
        formPanel.add(lblMsg, gbc);

        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        topPanel.setLayout(new BorderLayout());

        topPanel.add(formPanel, BorderLayout.CENTER);

        btnPanel = new JPanel();
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        JButton addBtn = new JButton("ADD");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        JButton delBtn = new JButton("DELETE");
        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparFormulario();
            }
        });
        btnPanel.add(addBtn);
        btnPanel.add(delBtn);

        container.add(panelTitle, BorderLayout.NORTH);
        container.add(topPanel, BorderLayout.CENTER);
        container.add(btnPanel, BorderLayout.SOUTH);

        preencheFormulario();

        setSize(new Dimension(450, 400));
        setLocationRelativeTo(null);
    }

    private void preencheFormulario(){
        if(idTeacher != null){
            Teachers t = tearcherDAO.getById(idTeacher);
            txtBanco.setText(t.getConta_bancaria().getBanco());
            txtAgencia.setText(t.getConta_bancaria().getAgencia());
            txtConta.setText(t.getConta_bancaria().getConta());
            txtSalario.setValue(t.getConta_bancaria().getSalario());
        }

    }
    private void save(){
        String banco = txtBanco.getText();
        String agencia = txtAgencia.getText();
        String conta = txtConta.getText();
        String salario = txtSalario.getValue().toString();
        if (banco.isEmpty()){
            this.lblMsg.setText("O nome do banco é requerido.");
            return;
        }
        if (agencia.isEmpty()){
            this.lblMsg.setText("O número da agência é requerido.");
            return;
        }
        if (conta.isEmpty()){
            this.lblMsg.setText("O nome da conta é requerido.");
            return;
        }
        if (salario.isEmpty()){
            this.lblMsg.setText("O valor do salário recebido é requerido");
            return;
        }
        ProfessorDlg.panelFormTeachers.txtBanco.setText(banco);
        ProfessorDlg.panelFormTeachers.txtAgencia.setText(agencia);
        ProfessorDlg.panelFormTeachers.txtConta.setText(conta);
        ProfessorDlg.panelFormTeachers.txtSalario.setText(salario);

        this.dispose();
    }

    private void limparFormulario(){
        txtBanco.setText(null);
        txtConta.setText(null);
        txtAgencia.setText(null);
        txtSalario.setText(null);
     }
    public void start() {
        setVisible(true);
    }
}

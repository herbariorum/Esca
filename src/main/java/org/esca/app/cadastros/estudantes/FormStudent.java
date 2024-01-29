package org.esca.app.cadastros.estudantes;

import com.formdev.flatlaf.FlatClientProperties;
import org.esca.app.cadastros.dao.impl.EstadoDAOImpl;
import org.esca.app.cadastros.dao.impl.MunicipioDAOImpl;
import org.esca.app.cadastros.dao.impl.StudentDAOImpl;
import org.esca.app.cadastros.dominio.Estado;
import org.esca.app.cadastros.dominio.Municipio;
import org.esca.app.cadastros.dominio.Students;
import org.esca.app.cadastros.estudantes.config.IDateValidator;
import org.esca.app.cadastros.estudantes.config.DateValidatorUsingIDateFormat;
import org.esca.app.cadastros.estudantes.config.StudentCellRenderer;
import org.esca.app.cadastros.estudantes.config.StudentTableModel;
import org.esca.app.util.CPF;
import org.esca.app.util.ComboBoxList;
import org.esca.app.util.SComboBoxList;
import org.esca.app.util.Util;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FormStudent extends javax.swing.JDialog {

    private MaskFormatter cpfFormat;
    private MaskFormatter telefoneFormat, cepFormat;
    private JPanel panelGeral;
    private JTextField txtNome;
    private JTextField txtCpf;
    private JButton novoButton;
    private JButton salvaButton;
    private JButton cancelaButton;
    private JButton removeButton;
    private JPanel panelButton;
    private JTextField txtLocalizar;
    private JPanel panelTable;
    private JTable tabelaStudent;
    private JTabbedPane tabbedInfoEstudante;
    private JPanel panelForm;
    private JTextField txtDtaNascimento;
    private JTextField txtNomeMae;
    private JTextField txtNomePai;
    private JComboBox cbxSexo;
    private JTextField txtNacionalidade;
    private JTextField txtResponsavelAcademico;
    private JCheckBox chbStatus;
    private JComboBox cbxNaturalidadeUf;
    private JTextField txtNaturalidade;
    private JTextField txtResponsavelFinanceiro;
    private JTextField txtLogradouro;
    private JTextField txtNumero;
    private JTextField txtComplemento;
    private JTextField txtTelefone;
    private JTextField txtBairro;
    private JTextField txtCep;
    private JComboBox cbxCidade;
    private JComboBox cbxEstado;
    private JLabel lblMsgNome;
    private JLabel lbmMsgCpf;
    private JLabel lblMsgNascimento;
    private JLabel lblMsgNomeMae;
    private JLabel lblMsgLogradouro;
    private Students student = new Students();
    private List<Students> students;
    private StudentDAOImpl daoStudent = new StudentDAOImpl();
    private List<Estado> estados;
    private EstadoDAOImpl daoEstado = new EstadoDAOImpl();
    private List<Municipio> municipios;
    private MunicipioDAOImpl daoMunicipio = new MunicipioDAOImpl();
    private IDateValidator validator;


    public FormStudent(javax.swing.JFrame parent, boolean modal) {
        super(parent, "Manutenção de Estudantes", modal);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(panelGeral);

        // INFORMAÇÕES DO ALUNO
        txtNome.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nome do Estudante");
        txtCpf.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "CPF do Estudante");
        txtNacionalidade.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Pais de origem");
        txtNaturalidade.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Natural da Cidade");
//        txtEstado.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Natural do Estado");
        txtDtaNascimento.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Data de Nascimento");
        txtNomeMae.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nome da Mãe");
        txtNomePai.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nome do Pai");
        txtResponsavelAcademico.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Responsável Acadêmico");
        txtResponsavelFinanceiro.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Responsável Financeiro");

        // ENDEREÇO DO ALUNO
        txtLogradouro.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Logradouro (Rua, Avenida, etc.");
        txtNumero.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Número da residência");
        txtComplemento.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Informações complementares");
        txtTelefone.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Telefone");
        txtBairro.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Bairro");
        txtCep.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "CEP");

        Font fontError = new Font("Roboto Black", Font.ITALIC, 10);


        lblMsgNome.setFont(fontError);
        lblMsgNome.setForeground(Color.RED);
        lbmMsgCpf.setFont(fontError);
        lbmMsgCpf.setForeground(Color.RED);
        lblMsgNascimento.setFont(fontError);
        lblMsgNascimento.setForeground(Color.RED);
        lblMsgNomeMae.setFont(fontError);
        lblMsgNomeMae.setForeground(Color.RED);
        lblMsgLogradouro.setFont(fontError);
        lblMsgLogradouro.setForeground(Color.RED);

        txtLocalizar.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Entre com o nome do estudante");

        setSize(new Dimension(800, 625));
        setLocationRelativeTo(null);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        carregaEstados();
        preencherComboCidade();

        cbxEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                preencherComboCidade();
            }
        });

        tabelaStudent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                habilitaCampos(true);
                habilitaBotoes(false);
                preencherFormulario();
            }
        });

        txtCpf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                int key = evt.getKeyChar();
                boolean numero = key >= 48 && key <= 57;
                if (!numero) {
                    evt.consume();
                }
                if (txtCpf.getText().strip().length() == 11) {
                    evt.consume();
                }
            }
        });
        txtTelefone.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                int key = evt.getKeyChar();
                boolean numero = key >= 48 && key <= 57;
                if (!numero) {
                    evt.consume();
                }
                if (txtTelefone.getText().strip().length() == 11) {
                    evt.consume();
                }
            }
        });
        txtCep.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                int key = evt.getKeyChar();
                boolean numero = key >= 48 && key <= 57;
                if (!numero) {
                    evt.consume();
                }
                if (txtCep.getText().strip().length() == 7) {
                    evt.consume();
                }
            }
        });
        novoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpaCampos();
                habilitaCampos(true);
                habilitaBotoes(false);
            }
        });
        cancelaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpaCampos();
                habilitaCampos(false);
                habilitaBotoes(true);
            }
        });

        salvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarNoBd();
            }
        });
    }

    private void formWindowActivated(java.awt.event.WindowEvent evt) {
        habilitaCampos(false);
        habilitaBotoes(true);
        preencherTabela("");
    }

    private void limpaCampos(){
        txtNome.setText(null);
        txtCpf.setText(null);
        txtDtaNascimento.setText(null);
        txtComplemento.setText(null);
        txtNomePai.setText(null);
        txtNomeMae.setText(null);
        txtNacionalidade.setText(null);
        txtNaturalidade.setText(null);
        txtResponsavelFinanceiro.setText(null);
        txtResponsavelAcademico.setText(null);

        txtLogradouro.setText(null);
        txtNumero.setText(null);
        txtBairro.setText(null);
        txtCep.setText(null);
        txtComplemento.setText(null);
        txtTelefone.setText(null);
        txtCep.setText(null);

    }
    private void habilitaCampos(boolean valor){
        txtNome.setEnabled(valor);
        txtCpf.setEnabled(valor);
        txtDtaNascimento.setEnabled(valor);
        txtComplemento.setEnabled(valor);
        txtNomePai.setEnabled(valor);
        txtNomeMae.setEnabled(valor);
        txtNacionalidade.setEnabled(valor);
        txtNaturalidade.setEnabled(valor);
        txtResponsavelFinanceiro.setEnabled(valor);
        txtResponsavelAcademico.setEnabled(valor);
        cbxNaturalidadeUf.setEnabled(valor);
        cbxSexo.setEnabled(valor);
        chbStatus.setEnabled(valor);

        txtLogradouro.setEnabled(valor);
        txtNumero.setEnabled(valor);
        txtBairro.setEnabled(valor);
        cbxCidade.setEnabled(valor);
        cbxEstado.setEnabled(valor);
        txtComplemento.setEnabled(valor);
        txtTelefone.setEnabled(valor);
        txtCep.setEnabled(valor);
    }
    private void habilitaBotoes(boolean valor){
        novoButton.setEnabled(valor);
        cancelaButton.setEnabled(!valor);
        salvaButton.setEnabled(!valor);
    }
    private void preencherFormulario() {
        int row = tabelaStudent.getSelectedRow();
        if (row != -1) {
            Long idStudent = Long.valueOf(tabelaStudent.getValueAt(row, 0).toString());
            this.student = daoStudent.getById(idStudent);

            txtNome.setText(this.student.getNome());
            txtCpf.setText(this.student.getCpf());
            txtDtaNascimento.setText(new Util().formatDate(this.student.getDtaNascimento()));
            cbxSexo.getModel().setSelectedItem(this.student.getSexo());
            chbStatus.setSelected(this.student.isStatus());
            txtTelefone.setText(this.student.getTelefone());
            txtNomeMae.setText(this.student.getNomeMae());
            txtNomePai.setText(this.student.getNomePai());
            txtNacionalidade.setText(this.student.getNacionalidade());
            txtNaturalidade.setText(this.student.getNaturalidade());
            cbxNaturalidadeUf.setSelectedItem(this.student.getNaturalidade_uf());

            txtResponsavelAcademico.setText(this.student.getResponsavelAcademico());
            txtResponsavelFinanceiro.setText(this.student.getResponsavelFinanceiro());

            txtLogradouro.setText(this.student.getLogradouro());
            txtNumero.setText(this.student.getNumero());
            txtTelefone.setText(this.student.getTelefone());
            txtComplemento.setText(this.student.getComplemento());
            txtBairro.setText(this.student.getBairro());
            cbxEstado.setSelectedItem(this.student.getEstado());
            cbxCidade.setSelectedItem(this.student.getCidade());
            txtCep.setText(this.student.getCep());
        }
    }

    private void preencherTabela(String value) {
        List<Students> estudantes = daoStudent.selectByName(value);
        tabelaStudent.setModel(new StudentTableModel(estudantes));
        tabelaStudent.setDefaultRenderer(Object.class, new StudentCellRenderer());
        tabelaStudent.setRowSorter(new TableRowSorter<>(tabelaStudent.getModel()));
    }

    private void carregaEstados() {
        daoEstado.comboBoxEstado();
        cbxEstado.removeAllItems();
        cbxNaturalidadeUf.removeAllItems();
        for (ComboBoxList e : daoEstado.getList()) {
            cbxEstado.addItem(e);
            cbxNaturalidadeUf.addItem(e);
        }
    }

    private void preencherComboCidade() {
        SComboBoxList estadoId = (SComboBoxList) this.cbxEstado.getSelectedItem();
        daoMunicipio.comboBoxMunicipio(estadoId.getUf());
        cbxCidade.removeAllItems();
        for (ComboBoxList m : daoMunicipio.getList()) {
            cbxCidade.addItem(m);
        }
    }

    private void toUpper(javax.swing.JComponent componente) {
        if (componente instanceof JTextField) {
            String s = ((JTextField) componente).getText().toUpperCase();
            ((JTextField) componente).setText(s);
        }
    }

    private void salvarNoBd(){
        String nome = txtNome.getText();
        String cpf = txtCpf.getText();
        String nascimento = txtDtaNascimento.getText().toString();
        String nomeMae = txtNomeMae.getText();
        String logradouro = txtLogradouro.getText();

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
            txtNome.putClientProperty("JComponent.outline", null);
        }
        if (cpf.isEmpty()){
            lbmMsgCpf.setText("CPF é obrigatório");
            txtCpf.putClientProperty("JComponent.outline", "warning");
            txtCpf.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            txtCpf.requestFocus();
            return;
        }else if (!new CPF(cpf).isCPF()){
            lbmMsgCpf.setText("CPF inválido");
            txtCpf.putClientProperty("JComponent.outline", "warning");
            txtCpf.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            txtCpf.requestFocus();
            return;
        }else{
            lbmMsgCpf.setText(null);
            txtCpf.putClientProperty("JComponent.outline", null);
        }

        validator = new DateValidatorUsingIDateFormat("MM/dd/yyyy");
        if (nascimento.isEmpty()){
            lblMsgNascimento.setText("Data é obrigatório");
            txtDtaNascimento.putClientProperty("JComponent.outline", "warning");
            txtDtaNascimento.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            txtDtaNascimento.requestFocus();
            return;
        } else if (!validator.isValid(nascimento)){
            lblMsgNascimento.setText("Data inválida");
            txtDtaNascimento.putClientProperty("JComponent.outline", "warning");
            txtDtaNascimento.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            txtDtaNascimento.requestFocus();
            return;
        }else{
            lblMsgNascimento.setText(null);
            txtDtaNascimento.putClientProperty("JComponent.outline", null);
        }
        if (nomeMae.isEmpty()){
            lblMsgNomeMae.setText("Nome da Mãe é obrigatório");
            txtNomeMae.putClientProperty("JComponent.outline", "warning");
            txtNomeMae.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            txtNomeMae.requestFocus();
            return;
        }else{
            lblMsgNomeMae.setText(null);
            txtNomeMae.putClientProperty("JComponent.outline", null);
        }
        if (logradouro.isEmpty()){
            tabbedInfoEstudante.setSelectedIndex(1);
            lblMsgLogradouro.setText("Logradouro é obrigatório");
            txtLogradouro.putClientProperty("JComponent.outline", "warning");
            txtLogradouro.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            txtLogradouro.requestFocus();
            return;
        }else{
            lblMsgLogradouro.setText(null);
            txtLogradouro.putClientProperty("JComponent.outline", null);
        }

        this.student.setNome(nome);
        this.student.setCpf(cpf);

        this.student.setDtaNascimento(new Util().formatDateToUs(nascimento));

        this.student.setNomeMae(nomeMae);
        this.student.setNomePai(txtNomePai.getText());

        this.student.setSexo(String.valueOf(cbxSexo.getSelectedItem()));
        this.student.setNacionalidade(txtNacionalidade.getText());
        this.student.setResponsavelAcademico(txtResponsavelAcademico.getText());
        this.student.setResponsavelFinanceiro(txtResponsavelFinanceiro.getText());
        ComboBoxList naturalidade = ((ComboBoxList) cbxNaturalidadeUf.getSelectedItem());
        this.student.setNaturalidade_uf(naturalidade.getNome());
        this.student.setNaturalidade(txtNaturalidade.getText());
        if (chbStatus.isSelected()){
            this.student.setStatus(true);
        }

        this.student.setLogradouro(txtLogradouro.getText());
        this.student.setNumero(txtNumero.getText());
        this.student.setComplemento(txtComplemento.getText());
        this.student.setBairro(txtBairro.getText());
        ComboBoxList estado = ((ComboBoxList) cbxEstado.getSelectedItem());
        this.student.setEstado(estado.getNome());
        this.student.setCidade(String.valueOf(cbxCidade.getSelectedItem()));
        this.student.setTelefone(txtTelefone.getText());
        this.student.setCep(txtCep.getText());


        System.out.println("Usuário a ser cadastrado ----> "+this.student);
    }

    public JDialog getInstance() {
        return this;
    }

    public void start() {
        setVisible(true);
    }
}

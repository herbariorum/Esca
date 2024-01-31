package org.esca.app.cadastros.estudantes;

import com.formdev.flatlaf.FlatClientProperties;
import org.esca.app.cadastros.dao.impl.EstadoDAOImpl;
import org.esca.app.cadastros.dao.impl.MunicipioDAOImpl;
import org.esca.app.cadastros.dao.impl.StudentDAOImpl;
import org.esca.app.cadastros.dominio.Estado;
import org.esca.app.cadastros.dominio.Municipio;
import org.esca.app.cadastros.dominio.Students;
import org.esca.app.cadastros.estudantes.config.DateValidatorUsingIDateFormat;
import org.esca.app.cadastros.estudantes.config.IDateValidator;
import org.esca.app.cadastros.estudantes.config.StudentCellRenderer;
import org.esca.app.cadastros.estudantes.config.StudentTableModel;
import org.esca.app.cadastros.fragmentos.JPanelFormStudent;
import org.esca.app.cadastros.modelos.FormPadraoTwo;
import org.esca.app.util.CPF;
import org.esca.app.util.Util;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.util.List;

public class EstudanteDlg extends FormPadraoTwo {
    public JPanelFormStudent panelFormStudent;
    private StudentDAOImpl studentDAO = new StudentDAOImpl();
    private EstadoDAOImpl daoEstado = new EstadoDAOImpl();
    private Long idRow;
    private List<Municipio> municipios;
    private MunicipioDAOImpl daoMunicipio = new MunicipioDAOImpl();
    private IDateValidator validator;
    public EstudanteDlg(JFrame parent, boolean modal, String titulo) {
        super(parent ,modal, titulo);
        setUndecorated(true);
        // Inicializo o fragmento formulário estudantes
        panelFormStudent = new JPanelFormStudent();
        // adiciono o mesmo a aba de formulários do JTabbedPane
        this.formulario.add(panelFormStudent);

        // configura a inicialização dos campos
        habilitarCampos(false);
        habilitarBotoes(true);
        preencherTabela("");
        preencherComboEstadoNatural();
        preencherComboCidadeNatural();
        preencherComboEstadoLogradouro();
        preencherComboCidadeLogradouro();

        panelTable.txtLocalizar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                preencherTabela(panelTable.txtLocalizar.getText());
            }
        });

        panelTable.tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                preencherFormulario();
                habilitarCampos(true);
                habilitarBotoes(false);
                abas.setSelectedIndex(0); // abre aba do formulário
            }
        });
        panelButton.btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAction();
                if (abas.getSelectedIndex() != 0){
                    abas.setSelectedIndex(0);
                }
            }
        });
        panelButton.btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarAction();
            }
        });
        panelButton.btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarNoBD();
            }
        });
        panelButton.btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteDoBD();
            }
        });
        panelFormStudent.txtCpf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                int key = evt.getKeyChar();
                boolean numero = key >= 48 && key <= 57;
                if (!numero) {
                    evt.consume();
                }
                if (panelFormStudent.txtCpf.getText().strip().length() == 11) {
                    evt.consume();
                }
            }
        });
        panelFormStudent.txtTelefone.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                int key = evt.getKeyChar();
                boolean numero = key >= 48 && key <= 57;
                if (!numero) {
                    evt.consume();
                }
                if (panelFormStudent.txtTelefone.getText().strip().length() == 11) {
                    evt.consume();
                }
            }
        });
        panelFormStudent.txtCep.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                int key = evt.getKeyChar();
                boolean numero = key >= 48 && key <= 57;
                if (!numero) {
                    evt.consume();
                }
                if (panelFormStudent.txtCep.getText().strip().length() == 7) {
                    evt.consume();
                }
            }
        });
        panelFormStudent.cbxEstadoNaturalidade.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                preencherComboCidadeNatural();
            }
        });
        panelFormStudent.cbxEsdado.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                preencherComboCidadeLogradouro();
            }
        });
        abas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                if (index == 1){
                    cancelarAction();
                }else if (index == 0){
                    adicionarAction();
                }
            }
        });
    }

    private void adicionarAction(){
        limparCampos();
        habilitarCampos(true);
        habilitarBotoes(false);
        setarCombos();
        preencherComboCidadeNatural();
        preencherComboCidadeLogradouro();
        if (idRow != null){
            idRow = null;
        }

    }
    private void cancelarAction(){
        limparCampos();
        habilitarCampos(false);
        habilitarBotoes(true);
        setarCombos();
        preencherComboCidadeNatural();
        preencherComboCidadeLogradouro();
        panelTable.tabela.clearSelection();
        if (idRow != null){
            idRow = null;
        }
        abas.setSelectedIndex(1);
    }
    @Override
    public void limparCampos() {
        panelFormStudent.txtNome.setText(null);
        panelFormStudent.txtCpf.setText(null);
        panelFormStudent.txtDtaNascimento.setText(null);
        if (panelFormStudent.ckbStatus.isSelected()){
            panelFormStudent.ckbStatus.setSelected(false);
        }
        panelFormStudent.txtNomeMae.setText(null);
        panelFormStudent.txtNomePai.setText(null);
        panelFormStudent.txtNacionalidade.setText(null);
        panelFormStudent.txtRespFinanceiro.setText(null);
        panelFormStudent.txtRespAcademico.setText(null);

        panelFormStudent.txtLogradouro.setText(null);
        panelFormStudent.txtNumero.setText(null);
        panelFormStudent.txtTelefone.setText(null);
        panelFormStudent.txtComplemento.setText(null);
        panelFormStudent.txtBairro.setText(null);
        panelFormStudent.txtCep.setText(null);
    }
    public void setarCombos(){
        panelFormStudent.cbxEstadoNaturalidade.getModel().setSelectedItem("TO");
        panelFormStudent.cbxEsdado.getModel().setSelectedItem("TO");
    }
    @Override
    public void habilitarCampos(boolean valor) {
        panelFormStudent.txtNome.setEnabled(valor);
        panelFormStudent.txtCpf.setEnabled(valor);
        panelFormStudent.txtDtaNascimento.setEnabled(valor);
        panelFormStudent.cbxSexo.setEnabled(valor);
        panelFormStudent.ckbStatus.setEnabled(valor);

        panelFormStudent.txtNomeMae.setEnabled(valor);
        panelFormStudent.txtNomePai.setEnabled(valor);
        panelFormStudent.txtNacionalidade.setEnabled(valor);
        panelFormStudent.cbxNaturalidade.setEnabled(valor);
        panelFormStudent.cbxEstadoNaturalidade.setEnabled(valor);
        panelFormStudent.txtRespFinanceiro.setEnabled(valor);
        panelFormStudent.txtRespAcademico.setEnabled(valor);

        panelFormStudent.txtLogradouro.setEnabled(valor);
        panelFormStudent.txtNumero.setEnabled(valor);
        panelFormStudent.txtTelefone.setEnabled(valor);
        panelFormStudent.txtComplemento.setEnabled(valor);
        panelFormStudent.txtBairro.setEnabled(valor);
        panelFormStudent.txtCep.setEnabled(valor);
        panelFormStudent.cbxEsdado.setEnabled(valor);
        panelFormStudent.cbxCidade.setEnabled(valor);
    }
    @Override
    public void habilitarBotoes(boolean valor) {
        panelButton.btnNew.setEnabled(valor);
        panelButton.btnSalvar.setEnabled(!valor);
        panelButton.btnCancelar.setEnabled(!valor);
    }
    @Override
    public void preencherFormulario() {
        int row = panelTable.tabela.getSelectedRow();
        if (row != -1) {
            Long idStudent = Long.valueOf(panelTable.tabela.getValueAt(row, 0).toString());
            Students student = new Students();
            student = studentDAO.getById(idStudent);

            this.idRow = student.getId();
            panelFormStudent.txtNome.setText(student.getNome());
            panelFormStudent.txtCpf.setText(student.getCpf());
            panelFormStudent.txtDtaNascimento.setText(new Util().formatDate(student.getDtaNascimento()));
            panelFormStudent.cbxSexo.getModel().setSelectedItem(student.getSexo());
            panelFormStudent.ckbStatus.setSelected(student.isStatus());
            panelFormStudent.txtNomeMae.setText(student.getNomeMae());
            panelFormStudent.txtNomePai.setText(student.getNomePai());
            panelFormStudent.txtNacionalidade.setText(student.getNacionalidade());
            panelFormStudent.cbxEstadoNaturalidade.getModel().setSelectedItem(student.getNaturalidade_uf());
            panelFormStudent.cbxNaturalidade.getModel().setSelectedItem(student.getNaturalidade());

            panelFormStudent.txtRespAcademico.setText(student.getResponsavelAcademico());
            panelFormStudent.txtRespFinanceiro.setText(student.getResponsavelFinanceiro());

            panelFormStudent.txtLogradouro.setText(student.getLogradouro());
            panelFormStudent.txtNumero.setText(student.getNumero());
            panelFormStudent.txtTelefone.setText(student.getTelefone());
            panelFormStudent.txtComplemento.setText(student.getComplemento());
            panelFormStudent.txtBairro.setText(student.getBairro());
            panelFormStudent.cbxEsdado.getModel().setSelectedItem(student.getEstado());
            panelFormStudent.cbxCidade.getModel().setSelectedItem(student.getCidade());
            panelFormStudent.txtCep.setText(student.getCep());
        }
    }
    @Override
    public void preencherTabela(String valor) {
        List<Students> students;
        students = studentDAO.selectByName(valor);
        StudentTableModel modelo = new StudentTableModel(students);
        panelTable.tabela.setModel(modelo);
        panelTable.tabela.setDefaultRenderer(Object.class, new StudentCellRenderer());
        panelTable.tabela.setRowSorter(new TableRowSorter<>(panelTable.tabela.getModel()));
    }
    private void preencherComboEstadoNatural() {
        List<Estado> lista = daoEstado.selectEstados();
        panelFormStudent.cbxEstadoNaturalidade.removeAllItems();
        for (Estado e : lista){
            panelFormStudent.cbxEstadoNaturalidade.addItem(e);
        }
    }
    private void preencherComboCidadeNatural(){
        String uf = String.valueOf(panelFormStudent.cbxEstadoNaturalidade.getSelectedItem());
        List<Municipio> lista = daoMunicipio.listaMunicipioPorUf(uf);
        panelFormStudent.cbxNaturalidade.removeAllItems();
        for (Municipio m: lista){
            panelFormStudent.cbxNaturalidade.addItem(m);
        }
    }
    private void preencherComboEstadoLogradouro(){
        List<Estado> lista = daoEstado.selectEstados();
        panelFormStudent.cbxEsdado.removeAllItems();
        for (Estado e: lista){
            panelFormStudent.cbxEsdado.addItem(e);
        }
    }
    private void preencherComboCidadeLogradouro(){
        String uf = String.valueOf(panelFormStudent.cbxEsdado.getSelectedItem());
        List<Municipio> lista = daoMunicipio.listaMunicipioPorUf(uf);
        panelFormStudent.cbxCidade.removeAllItems();
        for (Municipio m: lista){
            panelFormStudent.cbxCidade.addItem(m);
        }
    }

    @Override
    public void salvarNoBD() {
        String nome = panelFormStudent.txtNome.getText();
        String cpf = panelFormStudent.txtCpf.getText();
        String nascimento = panelFormStudent.txtDtaNascimento.getText().toString();
        String nomeMae = panelFormStudent.txtNomeMae.getText();
        String logradouro = panelFormStudent.txtLogradouro.getText();

        if (nome.isEmpty()) {
            panelFormStudent.lblMsgNome.setText("Nome é obrigatório");
            panelFormStudent.txtNome.putClientProperty("JComponent.outline", "warning");
            panelFormStudent.txtNome.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelFormStudent.txtNome.requestFocus();
            return;
        } else if (nome.length() <= 5) {
            panelFormStudent.lblMsgNome.setText("Nome deve ter no mínimo 5 characteres.");
            panelFormStudent.txtNome.putClientProperty("JComponent.outline", "warning");
            panelFormStudent.txtNome.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelFormStudent.txtNome.requestFocus();
            return;
        } else {
            panelFormStudent.lblMsgNome.setText(null);
            panelFormStudent.txtNome.putClientProperty("JComponent.outline", null);
        }
        if (cpf.isEmpty()){
            panelFormStudent.lblMsg.setText("CPF é obrigatório");
            panelFormStudent.txtCpf.putClientProperty("JComponent.outline", "warning");
            panelFormStudent.txtCpf.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelFormStudent.txtCpf.requestFocus();
            return;
        }else if (!new CPF(cpf).isCPF()){
            panelFormStudent.lblMsg.setText("CPF inválido");
            panelFormStudent.txtCpf.putClientProperty("JComponent.outline", "warning");
            panelFormStudent.txtCpf.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelFormStudent.txtCpf.requestFocus();
            return;
        }else{
            panelFormStudent.lblMsg.setText(null);
            panelFormStudent.txtCpf.putClientProperty("JComponent.outline", null);
        }
        validator = new DateValidatorUsingIDateFormat("dd/MM/yyyy");
        if (nascimento.isEmpty()){
            panelFormStudent.lblMsg.setText("Data é obrigatória");
            panelFormStudent.txtDtaNascimento.putClientProperty("JComponent.outline", "warning");
            panelFormStudent.txtDtaNascimento.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelFormStudent.txtDtaNascimento.requestFocus();
            return;
        } else if (!validator.isValid(nascimento)){
            panelFormStudent.lblMsg.setText("Data inválida");
            panelFormStudent.txtDtaNascimento.putClientProperty("JComponent.outline", "warning");
            panelFormStudent.txtDtaNascimento.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelFormStudent.txtDtaNascimento.requestFocus();
            return;
        }else{
            panelFormStudent.lblMsg.setText(null);
            panelFormStudent.txtDtaNascimento.putClientProperty("JComponent.outline", null);
        }
        if (nomeMae.isEmpty()){
            panelFormStudent.lblMsgNomeMae.setText("Nome da Mãe é obrigatório");
            panelFormStudent.txtNomeMae.putClientProperty("JComponent.outline", "warning");
            panelFormStudent.txtNomeMae.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelFormStudent.txtNomeMae.requestFocus();
            return;
        }else{
            panelFormStudent.lblMsgNomeMae.setText(null);
            panelFormStudent.txtNomeMae.putClientProperty("JComponent.outline", null);
        }
        if (logradouro.isEmpty()){
            panelFormStudent.lblMsgLogradouro.setText("Logradouro é obrigatório");
            panelFormStudent.txtLogradouro.putClientProperty("JComponent.outline", "warning");
            panelFormStudent.txtLogradouro.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelFormStudent.txtLogradouro.requestFocus();
            return;
        }else{
            panelFormStudent.lblMsgLogradouro.setText(null);
            panelFormStudent.txtLogradouro.putClientProperty("JComponent.outline", null);
        }
        Students student = new Students();
        student.setNome(nome);
        student.setCpf(cpf);
        student.setDtaNascimento(new Util().formatDateToUs(nascimento));
        student.setSexo(String.valueOf(panelFormStudent.cbxSexo.getModel().getSelectedItem()));
        student.setNomeMae(nomeMae);
        student.setNomePai(panelFormStudent.txtNomePai.getText());
        student.setNacionalidade(panelFormStudent.txtNacionalidade.getText());
        student.setNaturalidade_uf(String.valueOf(panelFormStudent.cbxEstadoNaturalidade.getModel().getSelectedItem()));
        student.setNaturalidade(String.valueOf(panelFormStudent.cbxNaturalidade.getModel().getSelectedItem()));
        student.setResponsavelAcademico(panelFormStudent.txtRespAcademico.getText());
        student.setResponsavelFinanceiro(panelFormStudent.txtRespFinanceiro.getText());
        if (panelFormStudent.ckbStatus.isSelected()){
            student.setStatus(true);
        }

        student.setLogradouro(logradouro);
        student.setNumero(panelFormStudent.txtNumero.getText());
        student.setTelefone(panelFormStudent.txtTelefone.getText());
        student.setComplemento(panelFormStudent.txtComplemento.getText());
        student.setBairro(panelFormStudent.txtBairro.getText());
        student.setEstado(String.valueOf(panelFormStudent.cbxEsdado.getModel().getSelectedItem()));
        student.setCidade(String.valueOf(panelFormStudent.cbxCidade.getModel().getSelectedItem()));
        student.setCep(panelFormStudent.txtCep.getText());

        if (this.idRow != null){
            try {
                student.setId(this.idRow);
                studentDAO.updateStudent(student);
                JOptionPane.showMessageDialog(this, "Atualizado com sucesso. ", "Sucesso.", JOptionPane.INFORMATION_MESSAGE);
                this.habilitarBotoes(true);
                this.habilitarCampos(false);
                this.limparCampos();
                preencherTabela("");
                panelTable.tabela.clearSelection();
                if (idRow != null){
                    idRow = null;
                }
                abas.setSelectedIndex(1);
            }catch (Exception e){
                JOptionPane.showMessageDialog(this, "Error " + e.getMessage());
            }
        }else{
            try {
                studentDAO.addStudent(student);
                JOptionPane.showMessageDialog(this, "Salvo com sucesso. ", "Sucesso.", JOptionPane.INFORMATION_MESSAGE);
                this.habilitarBotoes(true);
                this.habilitarCampos(false);
                this.limparCampos();
                preencherTabela("");
                panelTable.tabela.clearSelection();
                if (idRow != null){
                    idRow = null;
                }
                abas.setSelectedIndex(1);
            }catch (Exception e){
                JOptionPane.showMessageDialog(this, "Error " + e.getMessage());
            }
        }
    }

    @Override
    public void deleteDoBD() {
        if (this.idRow == null) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione um registro na tabela primeiro.");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Confirma a exclusão?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                Students student = new Students();
                student.setId(this.idRow);
                studentDAO.deleteStudent(student);
                limparCampos();
                habilitarBotoes(true);
                habilitarCampos(false);
                this.preencherTabela("");
                panelTable.tabela.clearSelection();
                if (idRow != null){
                    idRow = null;
                }
            } else {
                limparCampos();
                habilitarBotoes(true);
                habilitarCampos(false);
                if (idRow != null){
                    idRow = null;
                }
            }
            abas.setSelectedIndex(1);
        }
    }


    public void start(){
        setVisible(true);
    }
}

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
import org.esca.app.cadastros.modelos.FormPadraoTwo;
import org.esca.app.util.CPF;
import org.esca.app.util.Util;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.util.List;

public class EstudanteDlg extends FormPadraoTwo {

     private StudentDAOImpl studentDAO = new StudentDAOImpl();
    private EstadoDAOImpl daoEstado = new EstadoDAOImpl();
    private Long idRow;
    private List<Municipio> municipios;
    private MunicipioDAOImpl daoMunicipio = new MunicipioDAOImpl();
    private IDateValidator validator;
    public EstudanteDlg(JFrame parent, boolean modal, String titulo) {
        super(parent ,modal,titulo);
        setUndecorated(true);

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
        panelForm.txtCpf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                int key = evt.getKeyChar();
                boolean numero = key >= 48 && key <= 57;
                if (!numero) {
                    evt.consume();
                }
                if (panelForm.txtCpf.getText().strip().length() == 11) {
                    evt.consume();
                }
            }
        });
        panelForm.txtTelefone.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                int key = evt.getKeyChar();
                boolean numero = key >= 48 && key <= 57;
                if (!numero) {
                    evt.consume();
                }
                if (panelForm.txtTelefone.getText().strip().length() == 11) {
                    evt.consume();
                }
            }
        });
        panelForm.txtCep.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                int key = evt.getKeyChar();
                boolean numero = key >= 48 && key <= 57;
                if (!numero) {
                    evt.consume();
                }
                if (panelForm.txtCep.getText().strip().length() == 7) {
                    evt.consume();
                }
            }
        });
        panelForm.cbxEstadoNaturalidade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                preencherComboCidadeNatural();
            }
        });
        panelForm.cbxEsdado.addItemListener(new ItemListener() {
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
        panelForm.txtNome.setText(null);
        panelForm.txtCpf.setText(null);
        panelForm.txtDtaNascimento.setText(null);
        if (panelForm.ckbStatus.isSelected()){
            panelForm.ckbStatus.setSelected(false);
        }
        panelForm.txtNomeMae.setText(null);
        panelForm.txtNomePai.setText(null);
        panelForm.txtNacionalidade.setText(null);
        panelForm.txtRespFinanceiro.setText(null);
        panelForm.txtRespAcademico.setText(null);

        panelForm.txtLogradouro.setText(null);
        panelForm.txtNumero.setText(null);
        panelForm.txtTelefone.setText(null);
        panelForm.txtComplemento.setText(null);
        panelForm.txtBairro.setText(null);
        panelForm.txtCep.setText(null);
    }
    public void setarCombos(){
        panelForm.cbxEstadoNaturalidade.getModel().setSelectedItem("TO");
        panelForm.cbxEsdado.getModel().setSelectedItem("TO");
    }
    @Override
    public void habilitarCampos(boolean valor) {
        panelForm.txtNome.setEnabled(valor);
        panelForm.txtCpf.setEnabled(valor);
        panelForm.txtDtaNascimento.setEnabled(valor);
        panelForm.cbxSexo.setEnabled(valor);
        panelForm.ckbStatus.setEnabled(valor);

        panelForm.txtNomeMae.setEnabled(valor);
        panelForm.txtNomePai.setEnabled(valor);
        panelForm.txtNacionalidade.setEnabled(valor);
        panelForm.cbxNaturalidade.setEnabled(valor);
        panelForm.cbxEstadoNaturalidade.setEnabled(valor);
        panelForm.txtRespFinanceiro.setEnabled(valor);
        panelForm.txtRespAcademico.setEnabled(valor);

        panelForm.txtLogradouro.setEnabled(valor);
        panelForm.txtNumero.setEnabled(valor);
        panelForm.txtTelefone.setEnabled(valor);
        panelForm.txtComplemento.setEnabled(valor);
        panelForm.txtBairro.setEnabled(valor);
        panelForm.txtCep.setEnabled(valor);
        panelForm.cbxEsdado.setEnabled(valor);
        panelForm.cbxCidade.setEnabled(valor);
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
            panelForm.txtNome.setText(student.getNome());
            panelForm.txtCpf.setText(student.getCpf());
            panelForm.txtDtaNascimento.setText(new Util().formatDate(student.getDtaNascimento()));
            panelForm.cbxSexo.getModel().setSelectedItem(student.getSexo());
            panelForm.ckbStatus.setSelected(student.isStatus());
            panelForm.txtNomeMae.setText(student.getNomeMae());
            panelForm.txtNomePai.setText(student.getNomePai());
            panelForm.txtNacionalidade.setText(student.getNacionalidade());
            panelForm.cbxEstadoNaturalidade.getModel().setSelectedItem(student.getNaturalidade_uf());
            panelForm.cbxNaturalidade.getModel().setSelectedItem(student.getNaturalidade());

            panelForm.txtRespAcademico.setText(student.getResponsavelAcademico());
            panelForm.txtRespFinanceiro.setText(student.getResponsavelFinanceiro());

            panelForm.txtLogradouro.setText(student.getLogradouro());
            panelForm.txtNumero.setText(student.getNumero());
            panelForm.txtTelefone.setText(student.getTelefone());
            panelForm.txtComplemento.setText(student.getComplemento());
            panelForm.txtBairro.setText(student.getBairro());
            panelForm.cbxEsdado.getModel().setSelectedItem(student.getEstado());
            panelForm.cbxCidade.getModel().setSelectedItem(student.getCidade());
            panelForm.txtCep.setText(student.getCep());
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
        panelForm.cbxEstadoNaturalidade.removeAllItems();
        for (Estado e : lista){
            panelForm.cbxEstadoNaturalidade.addItem(e);
        }
    }
    private void preencherComboCidadeNatural(){
        String uf = String.valueOf(panelForm.cbxEstadoNaturalidade.getSelectedItem());
        List<Municipio> lista = daoMunicipio.listaMunicipioPorUf(uf);
        panelForm.cbxNaturalidade.removeAllItems();
        for (Municipio m: lista){
            panelForm.cbxNaturalidade.addItem(m);
        }
    }
    private void preencherComboEstadoLogradouro(){
        List<Estado> lista = daoEstado.selectEstados();
        panelForm.cbxEsdado.removeAllItems();
        for (Estado e: lista){
            panelForm.cbxEsdado.addItem(e);
        }
    }
    private void preencherComboCidadeLogradouro(){
        String uf = String.valueOf(panelForm.cbxEsdado.getSelectedItem());
        List<Municipio> lista = daoMunicipio.listaMunicipioPorUf(uf);
        panelForm.cbxCidade.removeAllItems();
        for (Municipio m: lista){
            panelForm.cbxCidade.addItem(m);
        }
    }

    @Override
    public void salvarNoBD() {
        String nome = panelForm.txtNome.getText();
        String cpf = panelForm.txtCpf.getText();
        String nascimento = panelForm.txtDtaNascimento.getText().toString();
        String nomeMae = panelForm.txtNomeMae.getText();
        String logradouro = panelForm.txtLogradouro.getText();

        if (nome.isEmpty()) {
            panelForm.lblMsgNome.setText("Nome é obrigatório");
            panelForm.txtNome.putClientProperty("JComponent.outline", "warning");
            panelForm.txtNome.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelForm.txtNome.requestFocus();
            return;
        } else if (nome.length() <= 5) {
            panelForm.lblMsgNome.setText("Nome deve ter no mínimo 5 characteres.");
            panelForm.txtNome.putClientProperty("JComponent.outline", "warning");
            panelForm.txtNome.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelForm.txtNome.requestFocus();
            return;
        } else {
            panelForm.lblMsgNome.setText(null);
            panelForm.txtNome.putClientProperty("JComponent.outline", null);
        }
        if (cpf.isEmpty()){
            panelForm.lblMsg.setText("CPF é obrigatório");
            panelForm.txtCpf.putClientProperty("JComponent.outline", "warning");
            panelForm.txtCpf.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelForm.txtCpf.requestFocus();
            return;
        }else if (!new CPF(cpf).isCPF()){
            panelForm.lblMsg.setText("CPF inválido");
            panelForm.txtCpf.putClientProperty("JComponent.outline", "warning");
            panelForm.txtCpf.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelForm.txtCpf.requestFocus();
            return;
        }else{
            panelForm.lblMsg.setText(null);
            panelForm.txtCpf.putClientProperty("JComponent.outline", null);
        }
        validator = new DateValidatorUsingIDateFormat("dd/MM/yyyy");
        if (nascimento.isEmpty()){
            panelForm.lblMsg.setText("Data é obrigatória");
            panelForm.txtDtaNascimento.putClientProperty("JComponent.outline", "warning");
            panelForm.txtDtaNascimento.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelForm.txtDtaNascimento.requestFocus();
            return;
        } else if (!validator.isValid(nascimento)){
            panelForm.lblMsg.setText("Data inválida");
            panelForm.txtDtaNascimento.putClientProperty("JComponent.outline", "warning");
            panelForm.txtDtaNascimento.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelForm.txtDtaNascimento.requestFocus();
            return;
        }else{
            panelForm.lblMsg.setText(null);
            panelForm.txtDtaNascimento.putClientProperty("JComponent.outline", null);
        }
        if (nomeMae.isEmpty()){
            panelForm.lblMsgNomeMae.setText("Nome da Mãe é obrigatório");
            panelForm.txtNomeMae.putClientProperty("JComponent.outline", "warning");
            panelForm.txtNomeMae.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelForm.txtNomeMae.requestFocus();
            return;
        }else{
            panelForm.lblMsgNomeMae.setText(null);
            panelForm.txtNomeMae.putClientProperty("JComponent.outline", null);
        }
        if (logradouro.isEmpty()){
            panelForm.lblMsgLogradouro.setText("Logradouro é obrigatório");
            panelForm.txtLogradouro.putClientProperty("JComponent.outline", "warning");
            panelForm.txtLogradouro.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelForm.txtLogradouro.requestFocus();
            return;
        }else{
            panelForm.lblMsgLogradouro.setText(null);
            panelForm.txtLogradouro.putClientProperty("JComponent.outline", null);
        }
        Students student = new Students();
        student.setNome(nome);
        student.setCpf(cpf);
        student.setDtaNascimento(new Util().formatDateToUs(nascimento));
        student.setSexo(String.valueOf(panelForm.cbxSexo.getModel().getSelectedItem()));
        student.setNomeMae(nomeMae);
        student.setNomePai(panelForm.txtNomePai.getText());
        student.setNacionalidade(panelForm.txtNacionalidade.getText());
        student.setNaturalidade_uf(String.valueOf(panelForm.cbxEstadoNaturalidade.getModel().getSelectedItem()));
        student.setNaturalidade(String.valueOf(panelForm.cbxNaturalidade.getModel().getSelectedItem()));
        student.setResponsavelAcademico(panelForm.txtRespAcademico.getText());
        student.setResponsavelFinanceiro(panelForm.txtRespFinanceiro.getText());
        if (panelForm.ckbStatus.isSelected()){
            student.setStatus(true);
        }

        student.setLogradouro(logradouro);
        student.setNumero(panelForm.txtNumero.getText());
        student.setTelefone(panelForm.txtTelefone.getText());
        student.setComplemento(panelForm.txtComplemento.getText());
        student.setBairro(panelForm.txtBairro.getText());
        student.setEstado(String.valueOf(panelForm.cbxEsdado.getModel().getSelectedItem()));
        student.setCidade(String.valueOf(panelForm.cbxCidade.getModel().getSelectedItem()));
        student.setCep(panelForm.txtCep.getText());

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

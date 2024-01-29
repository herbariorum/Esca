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
import org.esca.app.cadastros.modelos.FormPadrão;
import org.esca.app.util.CPF;
import org.esca.app.util.Util;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.util.List;

public class EstudanteDlg extends FormPadrão {

    private List<Students> students;
    private Students student = new Students();
    private StudentDAOImpl studentDAO = new StudentDAOImpl();
    private EstadoDAOImpl daoEstado = new EstadoDAOImpl();
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
            }
        });
        panelButton.btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
                habilitarCampos(true);
                habilitarBotoes(false);
                setarCombos();
                preencherComboCidadeNatural();
                preencherComboCidadeLogradouro();
            }
        });
        panelButton.btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
                habilitarCampos(false);
                habilitarBotoes(true);
                setarCombos();
                preencherComboCidadeNatural();
                preencherComboCidadeLogradouro();

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
            this.student = studentDAO.getById(idStudent);

            panelForm.txtNome.setText(this.student.getNome());
            panelForm.txtCpf.setText(this.student.getCpf());
            panelForm.txtDtaNascimento.setText(new Util().formatDate(this.student.getDtaNascimento()));
            panelForm.cbxSexo.getModel().setSelectedItem(this.student.getSexo());
            panelForm.ckbStatus.setSelected(this.student.isStatus());
            panelForm.txtNomeMae.setText(this.student.getNomeMae());
            panelForm.txtNomePai.setText(this.student.getNomePai());
            panelForm.txtNacionalidade.setText(this.student.getNacionalidade());
            panelForm.cbxEstadoNaturalidade.getModel().setSelectedItem(this.student.getNaturalidade_uf());
            panelForm.cbxNaturalidade.getModel().setSelectedItem(this.student.getNaturalidade());

            panelForm.txtRespAcademico.setText(this.student.getResponsavelAcademico());
            panelForm.txtRespFinanceiro.setText(this.student.getResponsavelFinanceiro());

            panelForm.txtLogradouro.setText(this.student.getLogradouro());
            panelForm.txtNumero.setText(this.student.getNumero());
            panelForm.txtTelefone.setText(this.student.getTelefone());
            panelForm.txtComplemento.setText(this.student.getComplemento());
            panelForm.txtBairro.setText(this.student.getBairro());
            panelForm.cbxEsdado.getModel().setSelectedItem(this.student.getEstado());
            panelForm.cbxCidade.getModel().setSelectedItem(this.student.getCidade());
            panelForm.txtCep.setText(this.student.getCep());
        }
    }
    @Override
    public void preencherTabela(String valor) {
        this.students = studentDAO.selectByName(valor);
        StudentTableModel modelo = new StudentTableModel(this.students);
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

        this.student.setNome(nome);
        this.student.setCpf(cpf);
        this.student.setDtaNascimento(new Util().formatDateToUs(nascimento));
        this.student.setSexo(String.valueOf(panelForm.cbxSexo.getModel().getSelectedItem()));
        this.student.setNomeMae(nomeMae);
        this.student.setNomePai(panelForm.txtNomePai.getText());
        this.student.setNacionalidade(panelForm.txtNacionalidade.getText());
        this.student.setNaturalidade_uf(String.valueOf(panelForm.cbxEstadoNaturalidade.getModel().getSelectedItem()));
        this.student.setNaturalidade(String.valueOf(panelForm.cbxNaturalidade.getModel().getSelectedItem()));
        this.student.setResponsavelAcademico(panelForm.txtRespAcademico.getText());
        this.student.setResponsavelFinanceiro(panelForm.txtRespFinanceiro.getText());
        if (panelForm.ckbStatus.isSelected()){
            this.student.setStatus(true);
        }

        this.student.setLogradouro(logradouro);
        this.student.setNumero(panelForm.txtNumero.getText());
        this.student.setTelefone(panelForm.txtTelefone.getText());
        this.student.setComplemento(panelForm.txtComplemento.getText());
        this.student.setBairro(panelForm.txtBairro.getText());
        this.student.setEstado(String.valueOf(panelForm.cbxEsdado.getModel().getSelectedItem()));
        this.student.setCidade(String.valueOf(panelForm.cbxCidade.getModel().getSelectedItem()));
        this.student.setCep(panelForm.txtCep.getText());

        if (this.student.getId() != null){
            try {
                studentDAO.updateStudent(this.student);
                JOptionPane.showMessageDialog(this, "Atualizado com sucesso. ", "Sucesso.", JOptionPane.INFORMATION_MESSAGE);
                this.habilitarBotoes(true);
                this.habilitarCampos(false);
                this.limparCampos();
                preencherTabela("");

            }catch (Exception e){
                JOptionPane.showMessageDialog(this, "Error " + e.getMessage());
            }
        }else{
            try {
                studentDAO.addStudent(this.student);
                JOptionPane.showMessageDialog(this, "Salvo com sucesso. ", "Sucesso.", JOptionPane.INFORMATION_MESSAGE);
                this.habilitarBotoes(true);
                this.habilitarCampos(false);
                this.limparCampos();
                preencherTabela("");
            }catch (Exception e){
                JOptionPane.showMessageDialog(this, "Error " + e.getMessage());
            }
        }
    }

    @Override
    public void deleteDoBD() {
        if (this.student.getId() == null) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione um registro na tabela primeiro.");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Confirma a exclusão?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                studentDAO.deleteStudent(this.student);
                limparCampos();
                habilitarBotoes(false);
                habilitarCampos(true);
                this.student.setId(null);
            } else {
                limparCampos();
                habilitarBotoes(true);
                habilitarCampos(false);
            }
        }
    }

    public void start(){
        setVisible(true);
    }
}

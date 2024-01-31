package org.esca.app.cadastros.professores;

import com.formdev.flatlaf.FlatClientProperties;
import org.esca.app.cadastros.dao.impl.EstadoDAOImpl;
import org.esca.app.cadastros.dao.impl.MunicipioDAOImpl;
import org.esca.app.cadastros.dao.impl.TearcherDAOImpl;
import org.esca.app.cadastros.dominio.Estado;
import org.esca.app.cadastros.dominio.Municipio;
import org.esca.app.cadastros.dominio.Students;
import org.esca.app.cadastros.dominio.Teachers;
import org.esca.app.cadastros.estudantes.config.DateValidatorUsingIDateFormat;
import org.esca.app.cadastros.estudantes.config.IDateValidator;
import org.esca.app.cadastros.fragmentos.JPanelFormTeachers;
import org.esca.app.cadastros.modelos.FormPadraoTwo;
import org.esca.app.cadastros.professores.aux.ProfessorDlgAux;
import org.esca.app.cadastros.professores.config.TeacherCellRenderer;
import org.esca.app.cadastros.professores.config.TearcherTableModel;
import org.esca.app.util.CPF;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class ProfessorDlg extends FormPadraoTwo {
    public static JPanelFormTeachers panelFormTeachers;
    private IDateValidator validator;
    private Long idRow;
    private List<Municipio> municipios;
    private MunicipioDAOImpl daoMunicipio = new MunicipioDAOImpl();
    private TearcherDAOImpl tearcherDAO = new TearcherDAOImpl();
    private EstadoDAOImpl daoEstado = new EstadoDAOImpl();
    public ProfessorDlg(JFrame parent, boolean modal, String titulo) {
        super(parent, modal, titulo);
        setUndecorated(true);
        // Inicializo o fragmento formulário estudantes
        panelFormTeachers = new JPanelFormTeachers();
        // adiciono o mesmo a aba de formulários do JTabbedPane
        this.formulario.add(panelFormTeachers);
        // configura a inicialização dos campos
        habilitarCampos(false);
        habilitarBotoes(true);
        preencherTabela("");
        preencherComboEstado();
        preencherComboCidade();

        panelButton.btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAction();
                if (abas.getSelectedIndex() != 0){
                    abas.setSelectedIndex(0);
                }
            }
        });
        panelFormTeachers.btnAddConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                buscaContaCorrente();
            }
        });
    }

    private void buscaContaCorrente(){
        new ProfessorDlgAux(getInstance(), true, "Informações Bancárias", idRow).start();
    }
    private void adicionarAction(){
        limparCampos();
        habilitarCampos(true);
        habilitarBotoes(false);
        setarCombos();
        preencherComboCidade();
        if (idRow != null){
            idRow = null;
        }
    }

    private void setarCombos(){
        panelFormTeachers.cbxEsdado.getModel().setSelectedItem("TO");
    }
    private void preencherComboEstado(){
        List<Estado> lista = daoEstado.selectEstados();
        System.out.println("Estados "+lista);
        panelFormTeachers.cbxEsdado.removeAllItems();
        for (Estado e: lista){
            panelFormTeachers.cbxEsdado.addItem(e);
        }
    }
    private void preencherComboCidade(){
        String uf = String.valueOf(panelFormTeachers.cbxEsdado.getSelectedItem());
        List<Municipio> lista = daoMunicipio.listaMunicipioPorUf(uf);
        panelFormTeachers.cbxCidade.removeAllItems();
        for (Municipio m : lista){
            panelFormTeachers.cbxCidade.addItem(m);
        }
    }


    @Override
    public void limparCampos() {

    }

    @Override
    public void habilitarCampos(boolean valor) {

    }

    @Override
    public void habilitarBotoes(boolean valor) {

    }

    @Override
    public void preencherFormulario() {

    }

    @Override
    public void preencherTabela(String valor) {
        List<Teachers> teachers;
        teachers = tearcherDAO.selectByName(valor);
        TearcherTableModel modelo = new TearcherTableModel(teachers);
        panelTable.tabela.setModel(modelo);
        panelTable.tabela.setDefaultRenderer(Object.class, new TeacherCellRenderer());
        panelTable.tabela.setRowSorter(new TableRowSorter<>(panelTable.tabela.getModel()));
    }

    @Override
    public void salvarNoBD() {
        String nome = panelFormTeachers.txtNome.getText();
        String cpf = panelFormTeachers.txtCpf.getText();
        String cargo = panelFormTeachers.txtCargo.getText();
        String nascimento = panelFormTeachers.txtDtaNascimento.getText().toString();
        String telefone = panelFormTeachers.txtTelefone.getText();
        String logradouro = panelFormTeachers.txtLogradouro.getText();
        String numero = panelFormTeachers.txtNumero.getText();
        String complemento = panelFormTeachers.txtComplemento.getText();
        String bairro = panelFormTeachers.txtBairro.getText();
        String estado = String.valueOf(panelFormTeachers.cbxEsdado.getModel().getSelectedItem());
        String cidade = String.valueOf(panelFormTeachers.cbxCidade.getModel().getSelectedItem());
        String cep = panelFormTeachers.txtCep.getText();

        if (nome.isEmpty()) {
            panelFormTeachers.lblMsgNome.setText("Nome é obrigatório");
            panelFormTeachers.txtNome.putClientProperty("JComponent.outline", "warning");
            panelFormTeachers.txtNome.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelFormTeachers.txtNome.requestFocus();
            return;
        } else if (nome.length() <= 5) {
            panelFormTeachers.lblMsgNome.setText("Nome deve ter no mínimo 5 characteres.");
            panelFormTeachers.txtNome.putClientProperty("JComponent.outline", "warning");
            panelFormTeachers.txtNome.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelFormTeachers.txtNome.requestFocus();
            return;
        } else {
            panelFormTeachers.lblMsgNome.setText(null);
            panelFormTeachers.txtNome.putClientProperty("JComponent.outline", null);
        }
        if (cpf.isEmpty()){
            panelFormTeachers.lblMsg.setText("CPF é obrigatório");
            panelFormTeachers.txtCpf.putClientProperty("JComponent.outline", "warning");
            panelFormTeachers.txtCpf.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelFormTeachers.txtCpf.requestFocus();
            return;
        }else if (!new CPF(cpf).isCPF()){
            panelFormTeachers.lblMsg.setText("CPF inválido");
            panelFormTeachers.txtCpf.putClientProperty("JComponent.outline", "warning");
            panelFormTeachers.txtCpf.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelFormTeachers.txtCpf.requestFocus();
            return;
        }else{
            panelFormTeachers.lblMsg.setText(null);
            panelFormTeachers.txtCpf.putClientProperty("JComponent.outline", null);
        }
        validator = new DateValidatorUsingIDateFormat("dd/MM/yyyy");
        if (nascimento.isEmpty()){
            panelFormTeachers.lblMsg.setText("Data é obrigatória");
            panelFormTeachers.txtDtaNascimento.putClientProperty("JComponent.outline", "warning");
            panelFormTeachers.txtDtaNascimento.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelFormTeachers.txtDtaNascimento.requestFocus();
            return;
        } else if (!validator.isValid(nascimento)){
            panelFormTeachers.lblMsg.setText("Data inválida");
            panelFormTeachers.txtDtaNascimento.putClientProperty("JComponent.outline", "warning");
            panelFormTeachers.txtDtaNascimento.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
            panelFormTeachers.txtDtaNascimento.requestFocus();
            return;
        }else{
            panelFormTeachers.lblMsg.setText(null);
            panelFormTeachers.txtDtaNascimento.putClientProperty("JComponent.outline", null);
        }

        Students student = new Students();

        // CONTA
    }

    @Override
    public void deleteDoBD() {

    }

    public  javax.swing.JDialog getInstance(){
        return this;
    }
    public void start(){
        setVisible(true);
    }
}

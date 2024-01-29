package org.esca.app;

import org.esca.app.auth.AuthForm;
import org.esca.app.auth.dominio.Usuarios;


import org.esca.app.cadastros.estudantes.EstudanteDlg;
import org.esca.app.cadastros.estudantes.FormStudent;
import org.esca.app.util.StatusBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import org.esca.app.cadastros.estudantes.StudentDialog;
import org.esca.app.cadastros.usuarios.FormUser;

public class FormMenu extends JFrame {

    private final Usuarios user;
    private StatusBar statusBar;
    private JMenuItem mnuUserManagerAction, mnuGerenciar;

    public FormMenu(Usuarios user) {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        this.user = user;

        initComponents();

        this.configurarMenu();
    }

    private void initComponents(){

        addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent e) {
                formWindowClosing(e);
            }
        });

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        /*
        * MENU APLICAÇÃO
         */
        JMenu fileMenu = new JMenu("Aplicação");
        JMenuItem exitAction = new JMenuItem("Sair");
        exitAction.addActionListener((ActionEvent evt) -> {
            mnuExitActionPerformed(evt);
        });
        fileMenu.add(exitAction);

        /*
        * MENU CADASTROS
         */
        JMenu cadastroMenu = new JMenu("Cadastros");
        JMenuItem cadastroEstudantesAction = new JMenuItem("Manuteção de Estudantes");
        cadastroEstudantesAction.addActionListener((ActionEvent evt)->{
            mnuCadastrarEstudantesActionPerformed(evt);
        });
        cadastroMenu.add(cadastroEstudantesAction);

        /*
        * MENU CONFIGURAÇÕES
         */
        JMenu configMenu = new JMenu("Configurações");

        mnuGerenciar = new JMenuItem("Gerenciar Usuários");
        mnuGerenciar.addActionListener((ActionEvent evt)->{
            mnuManagerUser(evt);
        });
        configMenu.add(mnuGerenciar);
        
        mnuUserManagerAction = new JMenuItem("Trocar de Usuário");
        mnuUserManagerAction.addActionListener((ActionEvent evt) -> {
            mnuTrocarUser(evt);
        });
        configMenu.add(mnuUserManagerAction);

        // Adiciona a barra de menus
        menuBar.add(fileMenu);
        menuBar.add(configMenu);
        menuBar.add(cadastroMenu);
        /**
         *  Cria a barra de Status
         *
         * @param statusBar
         */
        this.statusBar = new StatusBar(getWidth());
        this.statusBar.setMessagem("Logado como "+this.user.getNome());

        add(this.statusBar, BorderLayout.SOUTH);
    }

    private void formWindowClosing(WindowEvent evt){
        this.sairDoApp();
    }

    private void mnuExitActionPerformed(ActionEvent evt){
        this.sairDoApp();
    }
    private void mnuManagerUser(ActionEvent evt){
        new FormUser(getInstance(), true).start();

    }

    private void mnuTrocarUser(ActionEvent evt){
        AuthForm login = AuthForm.getInstance();
        this.dispose();
        login.setVisible(true);
    }

    private void configurarMenu(){
        if (this.user.getRole().getRole().equals("USERS")){
            mnuGerenciar.setVisible(false);
        }
    }

    private void mnuCadastrarEstudantesActionPerformed(ActionEvent evt){
//        new FormStudent(getInstance(), true).start();
        new EstudanteDlg(getInstance(), true, "Manutenção de Estudantes").start();
    }



    private void sairDoApp(){
        int resposta = JOptionPane.showConfirmDialog(null, "Você deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resposta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    public JFrame getInstance(){
        return this;
    }

    public void start(){
        setVisible(true);
    }
}

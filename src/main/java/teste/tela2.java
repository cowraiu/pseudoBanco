package teste;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.MessageDigest;
import java.sql.Connection;
import java.awt.*;

public class tela2 extends JFrame implements ActionListener{
JButton B = new JButton("cadastar");

JLabel label = new JLabel();

static TextField caixaNome = new TextField();
static TextField caixaCpf = new TextField();
static TextField caixaSobrenome = new TextField();
static TextField caixaEmail = new TextField();
static TextField caixaSenha = new TextField();

JLabel tx1 = new JLabel("Digite o seu: ");
JLabel tx2 = new JLabel("nome:");
JLabel tx3 = new JLabel("Sobrenome:");
JLabel tx4 = new JLabel("CPF:");
JLabel tx5 = new JLabel("EMAIL:");
JLabel tx6 = new JLabel("SENHA:");

JOptionPane janelaDEAviso = new JOptionPane();

conexaoDB conexaoDB = new conexaoDB();
Connection conn = conexaoDB.coneDB(security.getDb_name(), security.getUser(), security.getPass());



public tela2(){
    setTitle("titulo");
    setSize(500, 600);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setResizable(false);
    setLocationRelativeTo(null);
    setVisible(true);
    setLayout(null);

    label.add(tx1);
    tx1.setFont(new Font("Arial-BoldIt",Font.PLAIN, 26));
    tx1.setBounds(180, 20, 1000, 60);

    label.add(tx2);
    tx2.setFont(new Font("Arial-BoldIt",Font.PLAIN, 16));
    tx2.setBounds(120, 80, 50, 30);

    label.add(tx3);
    tx3.setFont(new Font("Arial-BoldIt",Font.PLAIN, 16));
    tx3.setBounds(70, 120, 100, 30);

    label.add(tx4);
    tx4.setFont(new Font("Arial-BoldIt",Font.PLAIN, 16));
    tx4.setBounds(120, 160, 60, 30);

    label.add(tx5);
    tx5.setFont(new Font("Arial-BoldIt",Font.PLAIN, 16));
    tx5.setBounds(120, 210, 60, 30);

    label.add(tx6);
    tx6.setFont(new Font("Arial-BoldIt",Font.PLAIN, 16));
    tx6.setBounds(120, 258, 60, 30);



    add(label);
    label.setBounds(0, 0, 500, 600);
    label.setBackground(Color.GRAY);
    label.setOpaque(true);
    label.setHorizontalTextPosition(JLabel.CENTER);
    label.setVerticalTextPosition(JLabel.TOP);
    label.setVerticalAlignment(JLabel.CENTER);
    label.setHorizontalAlignment(JLabel.CENTER);


    label.add(B);
    B.addActionListener(this);
    B.setBounds(205, 380, 90, 40 );

    label.add(caixaNome);
    caixaNome.setBounds(180, 87, 120, 20 );

    label.add(caixaCpf);
    caixaCpf.setBounds(180, 165, 120, 20 );

    label.add(caixaEmail);
    caixaEmail.setBounds(180, 213, 120, 20);

    label.add(caixaSenha);
    caixaSenha.setBounds(180, 261, 120, 20);

    label.add(caixaSobrenome);
    caixaSobrenome.setBounds(180, 125, 120, 20 );

    caixaCpf.addKeyListener(new KeyAdapter(){
        @Override
        public void keyTyped(KeyEvent e) {
            super.keyTyped(e);
            if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || (e.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {

                caixaCpf.setEditable(true);

            } else {

                caixaCpf.setEditable(false);

            }

        }
    });

    }
    @Override
    public void actionPerformed(ActionEvent e){

        if (e.getSource() == B) {
            if (    caixaNome.getText().trim().isEmpty() ||
                    caixaCpf.getText().trim().isEmpty() ||
                    caixaSobrenome.getText().trim().isEmpty() ||
                    caixaEmail.getText().trim().isEmpty() ||
                    caixaSenha.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(janelaDEAviso, "um dos campos esta vazio ");
            } else {
                try {

                    conexaoDB.inserir_linha(conn, caixaNome.getText(),
                            caixaEmail.getText(),
                            senhaHex(caixaSenha.getText()),
                            caixaSobrenome.getText(),
                            Integer.parseInt(caixaCpf.getText()),
                            security.getTableName());

                    new telaLogin();
                } catch (Exception u) {

                    System.out.println(u);

                }
            }
        }
    }
    public String senhaHex(String nome){
    String senha = nome;
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = md.digest(senha.getBytes("UTF-8"));

            StringBuilder sb = new StringBuilder();

            for(byte b :  messageDigest){
                sb .append(String.format("%02X", 0xff & b));
            }
            String senhaHex = sb.toString();

            return senhaHex;

        }catch (Exception e){
            System.out.println(e);

        }
        return null;

    }

}


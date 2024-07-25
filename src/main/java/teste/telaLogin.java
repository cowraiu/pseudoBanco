package teste;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.Connection;

public class telaLogin extends JFrame implements ActionListener {

    JLabel label = new JLabel();

    JLabel texto = new JLabel("Acesse sua conta:");
    JLabel SenhaErrada = new JLabel("verificar email/senha");
    JLabel texto2 = new JLabel("email");
    JLabel texto3 = new JLabel("senha");

    JTextField digiteSeuEmail = new JTextField();
    JTextField digiteSenha = new JTextField ();

    JButton butao = new JButton("login");



    private static int empid;
    private static double saldo;
    private  static String email;
    private  static String nome;


    public telaLogin(){

        setTitle("titulo");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);

        add(label);
        label.setBounds(0, 0, 500, 400);
        label.setBackground(Color.GRAY);
        label.setOpaque(true);

        label.add(texto);
        texto.setBounds(170, 100, 200, 20);
        texto.setFont(new Font("Modern No. 20",  Font.PLAIN, 20));

        label.add(texto2);
        texto2.setBounds(120, 180, 200, 20);

        label.add(texto3);
        texto3.setBounds(120, 250, 200, 20);



        label.add(digiteSeuEmail);
        digiteSeuEmail.setBounds(180, 180, 180, 20);



        label.add(butao);
        butao.setBounds(200, 290, 100, 20);
        butao.addActionListener(this);

        label.add(digiteSenha);
        digiteSenha.setBounds(180, 250, 180, 20);

        label.add(SenhaErrada);
        SenhaErrada.setBounds(210, 220, 180, 20);
        SenhaErrada.setVisible(false);
        SenhaErrada.setForeground(Color.red);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        conexaoDB cone = new conexaoDB();
        Connection conn = cone.coneDB(security.getDb_name(), security.getUser(), security.getPass());

        if(e.getSource() == butao){
            try {
                if(senhaHex(digiteSenha.getText()).equals(cone.busca(conn, security.getTableName(), digiteSeuEmail.getText()))){
                    empid = (cone.empid(conn, security.getTableName(), digiteSeuEmail.getText()));
                    saldo = (cone.saldo(conn, security.getTableName(), digiteSeuEmail.getText()));
                    email = digiteSeuEmail.getText();
                    nome = (cone.name(conn, security.getTableName(), digiteSeuEmail.getText()));
                    new tela();
                    dispose();
                }
                else{
                    SenhaErrada.setVisible(true);

                }


            } catch (Exception ex) {
                throw new RuntimeException(ex);
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

    public static int getEmpid(){
        return empid;
    }
    public static double getSaldo(){
        return saldo;
    }
    public static String getEmail(){
        return email;
    }
    public static String getNome(){
        return nome;
    }
}


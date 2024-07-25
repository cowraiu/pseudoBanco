package teste;

import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.NumberFormat;
import java.util.Locale;

public class tela extends JFrame implements ActionListener {

    JTextField caixaR = new JTextField(10);
    JTextField caixa2 = new JTextField(10 );

    JLabel texto2 = new JLabel("Digite um valor:");
    JLabel texto3 = new JLabel("sobrenome");
    JLabel texto4 = new JLabel("cpf");


    JButton butao = new JButton("Sacar");
    JButton butao3 = new JButton("Depositar");

    JLabel label = new JLabel();

    JOptionPane janelaDEAviso = new JOptionPane();


    JLabel texto5 = new JLabel("Bem vindo " + telaLogin.getNome());


    public tela(){

        conexaoDB cone = new conexaoDB();
        Connection conn = cone.coneDB(security.getDb_name(), security.getUser(), security.getPass());


        setTitle("titulo");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);


        add(label);
        label.setBounds(0, 0, 500, 400);



        label.add(caixaR);
        caixaR.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || (e.getKeyChar() == KeyEvent.VK_BACK_SPACE ) || (e.getKeyChar() == '.' )) {
                    caixaR.setEditable(true);

                } else {
                    caixaR.setEditable(false);

                }
            }
        });
        label.add(caixa2);


        caixaR.setBounds(200, 150, 100, 20);


        caixa2.addActionListener(this);
        caixa2.setEditable(false);
        caixa2.setBounds(200, 220, 100, 20);
        caixa2.setText("Saldo = " + cone.saldo(conn, security.getTableName(), telaLogin.getEmail()) + "$");



        label.add(texto2);
        label.add(texto5);



        setVisible(true);

        texto2.setBounds(40, 150, 200, 20);
        texto3.setBounds(110, 250, 100, 20);
        texto4.setBounds(150, 50, 100, 20);
        texto5.setBounds(200, 20, 600, 50);



        label.add(butao);
        butao.setBounds(280, 300, 80, 30);
        butao.addActionListener(this);
        label.add(butao3);
        butao3.setBounds(120, 300, 100, 30);
        butao3.addActionListener(this);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        conexaoDB cone = new conexaoDB();
        Connection conn = cone.coneDB(security.getDb_name(), security.getUser(), security.getPass());
        double t;
        double saldo = (cone.saldo(conn, security.getTableName(), telaLogin.getEmail()));
        double resultado;
        String finali;
        t = Double.parseDouble(caixaR.getText());



        if(e.getSource()==butao){

            try {
                resultado = saldo - t;

                if(resultado >= 0){
                    finali = String.valueOf(saldo - t);
                    cone.setSaldo(conn, security.getTableName(), telaLogin.getEmpid(), finali);
                    caixa2.setText("Saldo = " + cone.saldo(conn, security.getTableName(), telaLogin.getEmail()) + "$");
                    System.out.println("empid botao" + telaLogin.getEmpid());
                    System.out.println("saldo " + telaLogin.getSaldo());
                    System.out.println("email " + telaLogin.getEmail());
                }else{
                    JOptionPane.showMessageDialog(janelaDEAviso, "saque não disponivel");
                }


            }catch(RuntimeException f){

                JOptionPane.showMessageDialog(janelaDEAviso, f.getMessage());

            }

        }else if(e.getSource() == butao3){
            finali = String.valueOf(saldo + t);
//            NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "br"));
//            format.format(resultado);
            System.out.println(finali);

            cone.setSaldo(conn, security.getTableName(), telaLogin.getEmpid(), finali);
            caixa2.setText("Saldo = " + cone.saldo(conn, security.getTableName(), telaLogin.getEmail()) + "$");



        }
    }
}

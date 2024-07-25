package teste;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class teDEOpcao extends JFrame implements ActionListener {
    JButton butao2 = new JButton("cadastrar");
    JButton butao1 = new JButton("ja possuo cadastro ");

    JLabel label = new JLabel();

    JTextField textu = new JTextField("Seja bem vindo ao vestidoBank. ");


    public teDEOpcao(){
        setTitle("titulo");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);

        add(label);
        label.setBounds(0, 0, 500, 400);
        label.setBackground(Color.GRAY);
        label.setOpaque(true);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        label.add(butao1);
        butao1.addActionListener(this);
        butao1.setBounds(180, 280, 150, 40 );

        label.add(butao2);
        butao2.addActionListener(this);
        butao2.setBounds(180, 180, 150, 40 );

        label.add(textu);
        textu.setFont(new Font("Modern No. 20",  Font.PLAIN, 16));
        textu.setEditable(false);
        textu.setBounds(140, 80, 250, 40);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==butao2){
            new tela2();
            dispose();
        }
        if(e.getSource() == butao1){
            new telaLogin();
            dispose();
        }
    }
}

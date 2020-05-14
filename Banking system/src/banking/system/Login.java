/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.system;

/**
 *
 * @author om
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Login extends JFrame{
    public JLabel heading = new JLabel("Banking System");
    public JLabel userName = new JLabel("User Name");
    public JLabel password = new JLabel("Password");
    
    public JTextField userText = new JTextField(15);
    public JPasswordField userPassword = new JPasswordField(15);  
    public JButton signIn = new JButton("Sign in");
    public JButton clear = new JButton("Clear");
    public JButton signUp = new JButton("Sign up");
    public String a,b;
    Login() {
        setLayout(null);
        setVisible(true);
        setSize(750,750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Banking System");
        heading.setFont(new Font("Airal",Font.BOLD,40));
        heading.setBounds(175,50,450,200);
        add(heading);
        userName.setBounds(125,150,375,200);
        add(userName);
        password.setBounds(125,225,375,200);
        add(password);
        userText.setBounds(300,235,230,30);
        add(userText);
        userPassword.setBounds(300,310,230,30);
        add(userPassword);
        signIn.setBounds(300,400,100,30);
        add(signIn);
        clear.setBounds(430,400,100,30);
        add(clear);
        signUp.setBounds(300,450,230,30);
        add(signUp);
        signIn.addActionListener(ae -> {
            try {
                Connect connect = new Connect();
                a = userText.getText();
                b = userPassword.getText();
                String q = "select * from banking where userName='"+a+"' and password = '"+b+"'";
                ResultSet rs = connect.s.executeQuery(q);
                if (rs.next()) {
                    new Transcations().setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null,"Incorrect User Name or Password");
                }
                
            } catch (Exception e) {
                System.out.println(e);
            }
        });
        clear.addActionListener(ae -> {
            userText.setText("");
            userPassword.setText("");
        });
        signUp.addActionListener(ae -> {
            new SignUp().setVisible(true);
            setVisible(false);
        });
    }
}

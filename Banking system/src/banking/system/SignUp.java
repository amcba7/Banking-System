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
public class SignUp extends JFrame {
    public JLabel register = new JLabel("Sign Up");
    public JLabel name = new JLabel("Enter User Name");
    public JLabel password = new JLabel("Enter password");
    public JTextField nameText = new JTextField(15);
    public JTextField passwordText = new JTextField(15);
    public JButton submit = new JButton("Submit");
    public static String a,b;
    SignUp() {
       // setVisible(true);
        setTitle("Sign Up");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750,750);
        setLayout(null);
        register.setFont(new Font("Airal",Font.BOLD,40));
        register.setBounds(175,50,450,200);
        add(register);
        name.setBounds(125,150,375,200);
        add(name);
        password.setBounds(125,225,375,200);
        add(password);
        nameText.setBounds(300,235,230,30);
        add(nameText);
        passwordText.setBounds(300,310,230,30);
        add(passwordText);
        submit.setBounds(300,450,230,30);
        add(submit);
        submit.addActionListener(ae -> {
           a = nameText.getText();
           b = passwordText.getText();
           try {
               if (nameText.getText().equals("")) {
                   JOptionPane.showMessageDialog(null,"fill all the requried field");
               } else if (passwordText.getText().equals("")) {
                   JOptionPane.showMessageDialog(null,"fill all the requried field");
               } else {
                   Connect connect = new Connect();
                   String q1 = "insert into banking(userName,password)values('"+a+"','"+b+"')";
                   //String q2 = "insert into bank values('"+a+"','"+b+"','','','')";
                   //connect.s.executeUpdate(q2);
                   connect.s.executeUpdate(q1);
                   new Transcations().setVisible(true);
                   setVisible(false);
                   
               }
           } catch (Exception e) {
               System.out.println(e);
           }
        });    
    }
}
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
public class Deposit extends JFrame {
    public JLabel heading = new JLabel("Deposit");
    public JTextField value = new JTextField(15);
    public JButton dep = new JButton("Deposit");
    public Login l = new Login();
    public double bal,d;
    public Deposit() {
        setLayout(null);
        setVisible(true);
        setSize(750,750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        heading.setFont(new Font("Airal",Font.BOLD,40));
        heading.setBounds(300,50,450,200);
        add(heading);
        value.setBounds(300,250,230,30);
        add(value);
        dep.setBounds(300,350,230,30);
        add(dep);
        
        dep.addActionListener(ae->{
          try {
              String val = value.getText();
              if (val.equals("")) {
                  JOptionPane.showMessageDialog(null,"Enter the amount");
              } else {
                  Connect connect = new Connect();
                  ResultSet rs = connect.s.executeQuery("select * from bank where userName='"+l.a+"',password='"+l.b+"' ");
                  double bal = 0;
                  if (rs.next()) {
                      String pass = rs.getString("password");
                      bal = rs.getDouble("balance");
                      double d = Double.parseDouble(val);
                      bal += d;
                      String q1 = "insert into bank values('"+l.a+"','"+pass+"','"+d+"',null,'"+bal+"') ";
                      connect.s.executeUpdate(q1);
                      
                      
                  }
                  
                  JOptionPane.showMessageDialog(null, "Rs. "+val+" Deposited Successfully");
                    
                  new Transcations().setVisible(true);
                  setVisible(false);
              }
          } catch (Exception e) { 
              System.out.println(e);
          }
        });
    }
}

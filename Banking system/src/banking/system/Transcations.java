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
public class Transcations extends JFrame {
    public JLabel heading = new JLabel("Transcations");
    public JButton deposit = new JButton("Deposit");
    public JButton withdraw = new JButton("Withdraw");
    public JButton balance = new JButton("Check balance");
    public Login l = new Login();
    public Transcations() {
        setLayout(null);
        setVisible(true);
        setSize(750,750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        heading.setFont(new Font("Airal",Font.BOLD,40));
        heading.setBounds(300,50,450,200);
        add(heading);
        deposit.setBounds(300,250,230,30);
        add(deposit);
        withdraw.setBounds(300,350,230,30);
        add(withdraw);
        balance.setBounds(300,450,230,30);
        add(balance);
        
        deposit.addActionListener(ae->{
           new Deposit().setVisible(true);
           setVisible(false);
        });
        withdraw.addActionListener(ae->{
            new WithDrawl().setVisible(true);
            setVisible(false);
        });
        balance.addActionListener(ae->{
            try {
                Connect c = new Connect();
                ResultSet rs = c.s.executeQuery("Select balance FROM bank ORDER BY userName='"+l.a+"',password='"+l.b+"' DESE LIMIT 1 ");
                if (rs.next()) {
                    String value = rs.getString("balance");
                    if (value==null) {
                        JOptionPane.showMessageDialog(null,"Your Account Balance is zero");
                    } else if (value.equals("0")) {
                        JOptionPane.showMessageDialog(null,"Your Account Balance is zero");
                    } else {
                        JOptionPane.showMessageDialog(null,"Your Account Balance is "+value);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        });
        
    }
}

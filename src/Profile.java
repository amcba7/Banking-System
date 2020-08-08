import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class Profile extends JFrame {
    LogIn l = new LogIn(1);
    Connect connect = new Connect();
    JButton checkBalence = new JButton("Check Balence");
    JButton deposite = new JButton("Deposite");
    JButton withdraw = new JButton("Withdraw");
    JButton transfer = new JButton("Transfer Money");
    JButton history = new JButton("Transation History");
    private String nameData,genderData,passwordData;
    private int balenceData,depositeData,transferData,historyData;
    private int accNo;
    Profile() {
	profileFrame();
    }
    private void profileFrame() {
        
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Banking System");
        setSize(500,500);
        add(profilePanel());
    }
    private JPanel heading() {
        
	JPanel headingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,50));
	JLabel heading = new JLabel("Welcome");
	heading.setFont(new Font("Arial",Font.PLAIN,40));
	headingPanel.add(heading);
	return headingPanel;
    }
    private JPanel profileData() {
        retriveData();
        JPanel profileData = new JPanel();
        profileData.setLayout(new BoxLayout(profileData,BoxLayout.Y_AXIS));
        JLabel num = new JLabel("Account Number : "+accNo);
        num.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel userName = new JLabel("User Name : "+nameData);
        userName.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel password = new JLabel("Password : "+passwordData);
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel gender = new JLabel("Gender : "+genderData);
        gender.setAlignmentX(Component.CENTER_ALIGNMENT);
        profileData.add(num);
        profileData.add(userName);
        profileData.add(password);
        profileData.add(gender);
        return profileData;
    }
    private void retriveData() {
        try {
            String q = "select * from UserInfo where AccountNumber='"+l.a+"' and Password = '"+l.b+"'";
            ResultSet rs = connect.s.executeQuery(q);
            accNo = rs.getInt("AccountNumber");
            passwordData = rs.getString("password");
            nameData = rs.getString("UserName");
            genderData = rs.getString("Gender");
            balenceData = rs.getInt("Balence");
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private JPanel profileButton() {
        JPanel profileButton = new JPanel(new FlowLayout());
        //profileButton.setLayout(new BoxLayout(profileButton,BoxLayout.Y_AXIS));
        profileButton.add(checkBalence);
        profileButton.add(deposite);
        profileButton.add(withdraw);
        profileButton.add(transfer);
        profileButton.add(history);
        return profileButton;
    }
    private JPanel profilePanel() {
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel,BoxLayout.Y_AXIS));
        profilePanel.add(heading());
        profilePanel.add(profileData());
        profilePanel.add(profileButton());
        actionListener();
        return profilePanel;
    }
    private void checkBalence() {
        checkBalence.addActionListener(ae->{
           JOptionPane.showMessageDialog(this,"you Balence is "+balenceData);
        });
    }
    private void depositeAction() {
        deposite.addActionListener(ae->{
            try {
                int depositeAmmount;
                String dep = JOptionPane.showInputDialog("Enter the ammount for deposite");
                depositeAmmount = Integer.parseInt(dep);
                String q ="update UserInfo set Deposite = '"+depositeAmmount+"' where AccountNumber='"+l.a+"' ";
                connect.s.executeUpdate(q);
                balenceData = balenceData + depositeAmmount;
                String q1 ="update UserInfo set Balence = '"+balenceData+"' where AccountNumber='"+l.a+"' ";
                connect.s.executeUpdate(q1);
                JOptionPane.showMessageDialog(this,depositeAmmount+" deposit successfully");
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(this,"invalid input");
            }
        });
    }
    private void withdrawAction() {
        withdraw.addActionListener(ae->{
            int withdrawAmmount;
            String with = JOptionPane.showInputDialog("Enter the ammount for withdraw");
            withdrawAmmount = Integer.parseInt(with);
            if (balenceData<withdrawAmmount) {
                JOptionPane.showMessageDialog(this,"insufficient balance");
            } else {
                try {
                    String q ="update UserInfo set Withdraw = '"+withdrawAmmount+"' where AccountNumber='"+l.a+"' ";
                    connect.s.executeUpdate(q);
                    balenceData = balenceData - withdrawAmmount;
                    String q1 ="update UserInfo set Balence = '"+balenceData+"' where AccountNumber='"+l.a+"' ";
                    connect.s.executeUpdate(q1);
                    JOptionPane.showMessageDialog(this,withdrawAmmount+" withdraw successfully");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "invalid input");
                } 
            }
        });
    }
    private void actionListener() {
        checkBalence();
        depositeAction();
        withdrawAction();
    }
}
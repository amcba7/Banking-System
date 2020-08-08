import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.sql.*;
class Register extends JFrame {
    Connect connect = new Connect();
	private JLabel userName = new JLabel("Enter the Name ");
	private JTextField userNameText = new JTextField(20);
	private JLabel password = new JLabel("Enter Password");
	private JPasswordField passwordText = new JPasswordField(20);
	private JLabel heading = new JLabel("Registration Form");
	private JRadioButton male = new JRadioButton("Male");
	private JRadioButton female = new JRadioButton("Female");
	private JRadioButton other = new JRadioButton("Other");
	private ButtonGroup bg = new ButtonGroup();
	private JLabel genderLabel = new JLabel("Select Gender");
	private JButton register = new JButton("Register");
	private JButton home = new JButton("Home");
	public String genderType,pass,user;
	private boolean flag;
	private Random rand = new Random();
	private int acc_no;
        Register(int n) {};
	Register() {
		registerFrame();
	}
	private void registerFrame() {
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(500,500);
		add(registerPanel());
		actionListener();
		setTitle("Banking System");
		setExtendedState(MAXIMIZED_BOTH);
	}
	private JPanel heading() {
		JPanel headingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,50));
		heading.setFont(new Font("Arial",Font.PLAIN,40));
		headingPanel.add(heading);
		return headingPanel;
	}
	private JPanel registerPanelTop() {
		JPanel registerPanelTop = new JPanel(new GridBagLayout());
		JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		GridBagConstraints gc = new GridBagConstraints();
		gc.insets = new Insets(10,10,10,10);
		gc.gridx=0;
		gc.gridy=0;
		registerPanelTop.add(userName,gc);
		gc.gridx=1;
		gc.gridy=0;
		registerPanelTop.add(userNameText,gc);
		gc.gridx=0;
		gc.gridy=1;
		registerPanelTop.add(password,gc);
		gc.gridx=1;
		gc.gridy=1;
		registerPanelTop.add(passwordText,gc);
		bg.add(male);
		bg.add(female);
		bg.add(other);
		gc.gridx=0;
		gc.gridy=2;
		registerPanelTop.add(genderLabel,gc);
		gc.gridx=1;
		gc.gridy=2;
		genderPanel.add(male);
		genderPanel.add(female);
		genderPanel.add(other);
		registerPanelTop.add(genderPanel,gc);
		gc.gridx=1;
		gc.gridy=3;
		buttonPanel.add(register);
		buttonPanel.add(home);
		registerPanelTop.add(buttonPanel,gc);
		return registerPanelTop;
	}
	private JPanel registerPanel() {
		JPanel registerPanel = new JPanel();
		registerPanel.setLayout(new BoxLayout(registerPanel,BoxLayout.Y_AXIS));
		registerPanel.add(heading());
		registerPanel.add(registerPanelTop());
		return registerPanel;
	}
	private void homeAction() {
		home.addActionListener(ae-> {
			new LogIn();
			this.dispose();
		});
	}
	private void registerAction() {
		register.addActionListener(ae-> {
			if (userNameText.getText().equals("")) {
				JOptionPane.showMessageDialog(this,"Enter the User Name");
			} else if (passwordText.getText().equals("")) {
				JOptionPane.showMessageDialog(this,"Enter the password");
			}else if (male.isSelected()) {
				genderType = "male";
				flag = true;
			} else if (female.isSelected()) {
				genderType = "female";
				flag = true;
			} else if (other.isSelected()) {
                                genderType = "other";
				flag = true;
			} else {
				JOptionPane.showMessageDialog(this,"Select the Gender Type");
				flag = false;
			}
			if (flag == true) {
                            acc_no = checkRandom();
                            int msg = JOptionPane.showConfirmDialog(this,"Your Account Number :"+acc_no+"\n\nUser Name : "+userNameText.getText()+"\n Password : "+passwordText.getText()+"\nGender :"+genderType,"Account Information",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                            if (msg == JOptionPane.YES_OPTION) {
                                registerConnection();
                                new LogIn();
                                this.dispose();
                            }
			} 
                    }
                );
	}
        private int checkRandom() {
            int number = rand.nextInt(99);
            try {
                String q = "select * from UserInfo where AccountNumber='"+number+"'";
                ResultSet rs = connect.s.executeQuery(q);
                if (rs.next()) {
                    checkRandom();
                } else {
                    return number;
                }
                connect.c.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            return number;
        }
        private void registerConnection() {
            try {
                pass = passwordText.getText();
                user = userNameText.getText();
                String q1 = "insert into UserInfo(AccountNumber,Password,Gender,UserName)values('"+acc_no+"','"+pass+"','"+genderType+"','"+user+"')";
                connect.s.executeUpdate(q1);
                connect.c.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
	private void actionListener() {
		homeAction();
		registerAction();
	}
}
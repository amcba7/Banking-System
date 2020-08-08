import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class LogIn extends JFrame {
    Connect connect = new Connect();
	JLabel acc_no_label = new JLabel("Enter the Account No. ");
	JLabel password_label = new JLabel("Enter the password ");
	JTextField acc_no_text = new JTextField(20);
	JPasswordField passwordText = new JPasswordField(20);
	JButton login = new JButton("Log In");
	JButton register = new JButton("Register");
	JButton forgetPassword = new JButton("Forget Password");
        public static String b;
        public static int a;
        LogIn(int a) {};
	LogIn() {
		loginFrame();
	}
	private void loginFrame() {
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		add(loginPanel());
		actionListener();
		setTitle("Banking System");
		setExtendedState(MAXIMIZED_BOTH);
	}
	private JPanel loginPanelTop() {
		JPanel loginPanelTop = new JPanel(new GridLayout(2,2,0,20));//(rows,cols,hgap,vgap)
		loginPanelTop.add(acc_no_label);
		loginPanelTop.add(acc_no_text);
		loginPanelTop.add(password_label);
		loginPanelTop.add(passwordText);
		return loginPanelTop;
	}
	private JPanel loginPanelBottom() {
		JPanel loginPanelBottom = new JPanel(new FlowLayout(FlowLayout.CENTER,30,30)); // (alignment, hgap,vgap)
		loginPanelBottom.add(login);
		loginPanelBottom.add(register);
		loginPanelBottom.add(forgetPassword);
		return loginPanelBottom;
	}
	private JPanel heading() {
		JPanel headingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,50));
		JLabel heading = new JLabel("Banking System");
		heading.setFont(new Font("Arial",Font.PLAIN,40));
		headingPanel.add(heading);
		return headingPanel;
	}
	private JPanel loginPanel() {
		JPanel loginPanel = new JPanel(); 
		loginPanel.setLayout(new BoxLayout(loginPanel,BoxLayout.Y_AXIS));
		loginPanel.add(heading());
		loginPanel.add(loginPanelTop());
		loginPanel.add(loginPanelBottom());
		return loginPanel;
	}
	private void registerAction() {
		register.addActionListener(ae -> {
			new Register();
			this.dispose();
		});
	}
        private void logInAction() {
            login.addActionListener(ae->{
                try {
                a = Integer.parseInt(acc_no_text.getText());
                b = passwordText.getText();
                String q = "select * from UserInfo where AccountNumber='"+a+"' and Password = '"+b+"'";
                ResultSet rs = connect.s.executeQuery(q);
                if (rs.next()) {
                     new Profile();
                     this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null,"Incorrect Account Number or Password");
                }
                connect.c.close();
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Incorrect Account Number or Password");
            }
            });
        }
	private void actionListener() {
		registerAction();
                logInAction();
	}
}
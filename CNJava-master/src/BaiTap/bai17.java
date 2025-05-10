package BaiTap;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class bai17 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				bai17 frame = new bai17();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public bai17() {
		setTitle("Login Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 200);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
		setContentPane(contentPane);

		JLabel lblUsername = new JLabel("Username:");
		contentPane.add(lblUsername);

		txtUsername = new JTextField(15);
		contentPane.add(txtUsername);

		JLabel lblPassword = new JLabel("Password:");
		contentPane.add(lblPassword);

		txtPassword = new JPasswordField(15);
		contentPane.add(txtPassword);

		JButton btnLogin = new JButton("Login");
		contentPane.add(btnLogin);
	}
}

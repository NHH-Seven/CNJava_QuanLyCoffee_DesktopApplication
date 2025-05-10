package BaiTap;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Bai2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				// Hiển thị hộp thoại thông báo
				int result = JOptionPane.showConfirmDialog(
						null,
						"Welcome to Java Swing",
						"Message",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE
				);

				// Nếu người dùng nhấn OK thì thoát ứng dụng
				if (result == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});
	}

	// Constructor không cần dùng trong trường hợp này,
	// vì ứng dụng thoát sau khi nhấn OK
	public Bai2() {
		setTitle("Welcome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	}
}

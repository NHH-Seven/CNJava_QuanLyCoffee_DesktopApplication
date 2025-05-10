package BaiTap;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class Bai11 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai11 frame = new Bai11();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Bai11() {
		// Cài đặt kích thước JFrame
		setTitle("System Info");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 200);  // Kích thước 300x200
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Lấy tên hệ điều hành và hiển thị
		JLabel osLabel = new JLabel("OS: " + System.getProperty("os.name"));
		osLabel.setBounds(50, 30, 200, 20);
		contentPane.add(osLabel);

		// Lấy phiên bản Java và hiển thị
		JLabel javaVersionLabel = new JLabel("Java Version: " + System.getProperty("java.version"));
		javaVersionLabel.setBounds(50, 70, 200, 20);
		contentPane.add(javaVersionLabel);
	}
}

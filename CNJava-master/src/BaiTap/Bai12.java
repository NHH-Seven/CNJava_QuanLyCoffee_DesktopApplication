package BaiTap;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Bai12 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// Cài đặt giao diện Nimbus hoặc Metal
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // Nimbus/Metal tùy thuộc vào hệ thống
		} catch (Exception e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai12 frame = new Bai12();
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
	public Bai12() {
		// Thiết lập JFrame
		setTitle("Custom Look and Feel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);  // Kích thước 400x300
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Thêm JLabel "Custom Look and Feel"
		JLabel label = new JLabel("Custom Look and Feel");
		label.setBounds(120, 120, 200, 30);  // Đặt vị trí của JLabel ở giữa
		contentPane.add(label);
	}
}

package BaiTap;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class bai8 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bai8 frame = new bai8();
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
	public bai8() {
		setTitle("Colored Background");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setBackground(Color.GREEN); // Màu nền xanh lá
		contentPane.setLayout(null); // Không sử dụng layout để căn chỉnh thủ công
		setContentPane(contentPane);

		// Tạo JLabel ở giữa
		JLabel lblText = new JLabel("Colored Background");
		lblText.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblText.setHorizontalAlignment(SwingConstants.CENTER);
		lblText.setBounds(50, 160, 300, 40); // Căn giữa tương đối
		contentPane.add(lblText);
	}
}

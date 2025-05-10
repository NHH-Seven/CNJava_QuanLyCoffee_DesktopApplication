package BaiTap;

import java.awt.EventQueue;
import java.awt.Dimension;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Bai19 extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Bai19 frame = new Bai19();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Bai19() {
		setTitle("BoxLayout Example");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);

		// JPanel chính với BoxLayout theo chiều dọc (Y_AXIS)
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		// Tạo JPanel con 1 (BoxLayout theo chiều ngang)
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.add(new JLabel("Label 1: "));
		panel1.add(new JLabel("Info 1   "));
		panel1.add(new JButton("Button 1"));

		// Tạo JPanel con 2 (BoxLayout theo chiều ngang)
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.add(new JLabel("Label 2: "));
		panel2.add(new JLabel("Info 2   "));
		panel2.add(new JButton("Button 2"));

		// Thêm 2 panel con vào panel chính
		mainPanel.add(panel1);
		mainPanel.add(panel2);

		// Thêm mainPanel vào frame
		add(mainPanel);
	}
}

package BaiTap;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Bai38 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Bai38() {
		setTitle("Double Click to Change Color");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// JLabel hiển thị mã màu RGB
		JLabel colorLabel = new JLabel("RGB Color: (255, 255, 255)");
		colorLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// MouseListener để thay đổi màu nền khi nhấp đúp
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {  // Kiểm tra nhấp đúp chuột
					// Tạo màu ngẫu nhiên
					int r = (int) (Math.random() * 256);
					int g = (int) (Math.random() * 256);
					int b = (int) (Math.random() * 256);

					// Đổi màu nền JFrame và cập nhật mã màu trên JLabel
					getContentPane().setBackground(new Color(r, g, b));
					colorLabel.setText("RGB Color: (" + r + ", " + g + ", " + b + ")");
				}
			}
		});

		// Thêm thành phần vào JFrame
		add(colorLabel, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Bai38 frame = new Bai38();
			frame.setVisible(true);
		});
	}
}

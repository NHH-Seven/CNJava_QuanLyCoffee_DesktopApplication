package BaiTap;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Bai37 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Bai37() {
		setTitle("Mouse Coordinates");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());

		// JLabel hiển thị tọa độ chuột
		JLabel label = new JLabel("Mouse at: (0, 0)");

		// MouseListener để cập nhật tọa độ chuột
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label.setText("Mouse at: (" + e.getX() + ", " + e.getY() + ")");
			}
		});

		// Thêm thành phần vào JFrame
		add(label);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Bai37 frame = new Bai37();
			frame.setVisible(true);
		});
	}
}

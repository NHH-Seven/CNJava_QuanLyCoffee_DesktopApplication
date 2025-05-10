package BaiTap;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Bai32 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Bai32() {
		setTitle("JTextArea trong JScrollPane");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));

		// Tạo JTextArea
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true); // Tự động xuống dòng
		textArea.setWrapStyleWord(true);

		// Đặt vào JScrollPane
		JScrollPane scrollPane = new JScrollPane(textArea);

		// Tạo nút Clear
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(e -> {
			
				textArea.setText(" "); // Xóa nội dung
			
		});

		// Thêm vào giao diện
		add(scrollPane, BorderLayout.CENTER);
		add(btnClear, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Bai32 frame = new Bai32();
			frame.setVisible(true);
		});
	}
}

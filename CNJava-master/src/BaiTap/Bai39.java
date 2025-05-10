package BaiTap;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Bai39 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Bai39() {
		setTitle("Key Listener Example");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());

		// JTextField và JLabel
		JTextField textField = new JTextField(20);
		JLabel label = new JLabel("You typed: ");

		// KeyListener để hiển thị ký tự
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				label.setText("You typed: " + e.getKeyChar());
			}
		});

		// Thêm thành phần vào JFrame
		add(label);
		add(textField);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Bai39 frame = new Bai39();
			frame.setVisible(true);
		});
	}
}

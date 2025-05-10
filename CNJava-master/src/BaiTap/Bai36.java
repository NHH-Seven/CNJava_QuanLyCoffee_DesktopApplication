package BaiTap;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bai36 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Bai36() {
		setTitle("Change Text");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());

		// JLabel và JButton
		JLabel label = new JLabel("Original Text");
		JButton changeButton = new JButton("Change");

		// ActionListener cho JButton
		changeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				label.setText("Text Changed");
			}
		});

		// Thêm thành phần vào JFrame
		add(label);
		add(changeButton);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Bai36 frame = new Bai36();
			frame.setVisible(true);
		});
	}
}

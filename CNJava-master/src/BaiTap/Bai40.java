package BaiTap;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bai40 extends JFrame {

	private static final long serialVersionUID = 1L;
	private int count = 0;
	
	public Bai40() {
		setTitle("Button Click Counter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());

		// JLabel và JButton
		JLabel label = new JLabel("Count: 0");
		JButton clickButton = new JButton("Click Me");

	
		clickButton.addActionListener(e -> label.setText("Count: " + (++count)));


		add(label);
		add(clickButton);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Bai40 frame = new Bai40();
			frame.setVisible(true);
		});
	}
}

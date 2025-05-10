package BaiTap;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bai35 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Bai35() {
		setTitle("BorderLayout with GridLayout");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 400);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// Tạo JPanel với GridLayout 3x3 cho 9 JButton
		JPanel centerPanel = new JPanel(new GridLayout(3, 3, 10, 10)); // 3x3 GridLayout với khoảng cách giữa các nút
		for (int i = 1; i <= 9; i++) {
			JButton button = new JButton("Button " + i);
			centerPanel.add(button);
		}

		// Tạo JButton "Reset"
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Reset action: chỉ là một ví dụ đơn giản, có thể làm gì đó khi nhấn Reset
				JOptionPane.showMessageDialog(null, "Reset button clicked!");
			}
		});

		// Thêm các thành phần vào JFrame
		add(centerPanel, BorderLayout.CENTER);
		add(resetButton, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Bai35 frame = new Bai35();
			frame.setVisible(true);
		});
	}
}

package BaiTap;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Bai18 extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Bai18 frame = new Bai18();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Bai18() {
		setTitle("Chess Board");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setLocationRelativeTo(null); 
		setLayout(new GridLayout(8, 8));

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				JPanel cell = new JPanel();
				if ((row + col) % 2 == 0) {
					cell.setBackground(Color.WHITE);
				} else {
					cell.setBackground(Color.BLACK);
				}
				add(cell);
			}
		}
	}
}

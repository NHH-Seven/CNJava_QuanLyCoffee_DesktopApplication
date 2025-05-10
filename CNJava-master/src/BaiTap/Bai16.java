package BaiTap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class Bai16 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Bai16 frame = new Bai16();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Bai16() {
		setTitle("BorderLayout Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5, 5)); // Thêm khoảng cách giữa các vùng
		setContentPane(contentPane);

		// North
		JLabel lblNorth = new JLabel("Đây là vùng North", SwingConstants.CENTER);
		lblNorth.setOpaque(true);
		lblNorth.setBackground(Color.CYAN);
		contentPane.add(lblNorth, BorderLayout.NORTH);

		// South
		JLabel lblSouth = new JLabel("Đây là vùng South", SwingConstants.CENTER);
		lblSouth.setOpaque(true);
		lblSouth.setBackground(Color.PINK);
		contentPane.add(lblSouth, BorderLayout.SOUTH);

		// East
		JLabel lblEast = new JLabel("Đây là vùng East", SwingConstants.CENTER);
		lblEast.setOpaque(true);
		lblEast.setBackground(Color.ORANGE);
		contentPane.add(lblEast, BorderLayout.EAST);

		// West
		JLabel lblWest = new JLabel("Đây là vùng West", SwingConstants.CENTER);
		lblWest.setOpaque(true);
		lblWest.setBackground(Color.GREEN);
		contentPane.add(lblWest, BorderLayout.WEST);

		// Center
		JLabel lblCenter = new JLabel("Đây là vùng Center", SwingConstants.CENTER);
		lblCenter.setOpaque(true);
		lblCenter.setBackground(Color.LIGHT_GRAY);
		contentPane.add(lblCenter, BorderLayout.CENTER);
	}
}

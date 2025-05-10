package BaiTap;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bai22 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Bai22 frame = new Bai22();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Bai22() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 200); // Kích thước 300x200
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		setContentPane(contentPane);

		// Tạo JComboBox với 5 loại trái cây
		String[] fruits = {"Táo", "Chuối", "Cam", "Xoài", "Dưa hấu"};
		JComboBox<String> comboBox = new JComboBox<>(fruits);
		contentPane.add(comboBox);

		// Tạo JLabel để hiển thị lựa chọn
		JLabel lblSelection = new JLabel("Bạn chọn: " + fruits[0]);
		contentPane.add(lblSelection);

		// Thêm sự kiện khi chọn trái cây
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedFruit = (String) comboBox.getSelectedItem();
				lblSelection.setText("Bạn chọn: " + selectedFruit);
			}
		});
	}
}

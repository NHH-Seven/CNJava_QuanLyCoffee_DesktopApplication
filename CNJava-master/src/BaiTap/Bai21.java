package BaiTap;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Bai21 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Bai21 frame = new Bai21();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Bai21() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 400); // kích thước 300x400
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		setContentPane(contentPane);

		// Danh sách 10 thành phố
		String[] cities = {"Hà Nội", "Hồ Chí Minh", "Đà Nẵng", "Hải Phòng", "Cần Thơ",
						   "Huế", "Nha Trang", "Vũng Tàu", "Buôn Ma Thuột", "Đà Lạt"};

		JList<String> cityList = new JList<>(cities);
		JScrollPane scrollPane = new JScrollPane(cityList);
		contentPane.add(scrollPane);

		// Label hiển thị số lượng mục
		JLabel lblCount = new JLabel("Số lượng mục: " + cities.length);
		contentPane.add(lblCount);
	}
}

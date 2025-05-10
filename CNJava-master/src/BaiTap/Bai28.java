package BaiTap;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Bai28 extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai28 frame = new Bai28();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Bai28() {
		setTitle("Danh sách sinh viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);

		// Cột và dữ liệu cố định
		String[] columnNames = {"Tên", "Tuổi", "Điểm"};
		Object[][] data = {
			{"Nguyễn Văn A", 20, 8.5},
			{"Trần Thị B", 21, 7.0},
			{"Lê Văn C", 19, 9.0},
			{"Phạm Thị D", 22, 6.8},
			{"Hoàng Văn E", 20, 7.5}
		};
	}
}

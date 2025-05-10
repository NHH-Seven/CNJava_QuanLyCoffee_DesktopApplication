package Baitap2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Bai62 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private int idCounter = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Bai62 frame = new Bai62();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Bai62() {
		setTitle("Bảng dữ liệu thời gian");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(10, 10));
		setContentPane(contentPane);

		// Tạo bảng với 3 cột: ID, Name, Time
		String[] columns = { "ID", "Name", "Time" };
		tableModel = new DefaultTableModel(columns, 0);
		table = new JTable(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		// Tạo thread thêm bản ghi mỗi 2 giây
		Thread thread = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(2000);
					SwingUtilities.invokeLater(() -> {
						SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
						String currentTime = sdf.format(new Date());
						Object[] row = { idCounter, "Tên " + idCounter, currentTime };
						tableModel.addRow(row);
						idCounter++;
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
}

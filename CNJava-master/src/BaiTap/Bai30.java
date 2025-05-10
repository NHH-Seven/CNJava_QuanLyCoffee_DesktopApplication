package BaiTap;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Bai30 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Bai30() {
		setTitle("JToolBar Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		// Tạo thanh công cụ (JToolBar)
		JToolBar toolBar = new JToolBar();

		// Tạo các nút
		JButton btnNew = new JButton("New");
		JButton btnSave = new JButton("Save");
		JButton btnOpen = new JButton("Open");

		// Thêm các nút vào thanh công cụ
		toolBar.add(btnNew);
		toolBar.add(btnSave);
		toolBar.add(btnOpen);

		// Thêm thanh công cụ vào JFrame
		add(toolBar, BorderLayout.NORTH);

		// Xử lý sự kiện khi nhấn nút
		btnNew.addActionListener(e ->
			JOptionPane.showMessageDialog(this, "Bạn đã nhấn nút New")
		);

		btnSave.addActionListener(e ->
			JOptionPane.showMessageDialog(this, "Bạn đã nhấn nút Save")
		);

		btnOpen.addActionListener(e ->
			JOptionPane.showMessageDialog(this, "Bạn đã nhấn nút Open")
		);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Bai30 frame = new Bai30();
			frame.setVisible(true);
		});
	}
}

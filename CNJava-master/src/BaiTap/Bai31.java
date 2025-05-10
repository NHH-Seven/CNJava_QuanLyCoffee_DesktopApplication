package BaiTap;

import java.awt.*;
import javax.swing.*;

public class Bai31 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Bai31() {
		setTitle("JSplitPane Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);

		// Dữ liệu mẫu cho JList
		String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
		JList<String> list = new JList<>(items);
		JScrollPane listScrollPane = new JScrollPane(list);

		// Khu vực văn bản
		JTextArea textArea = new JTextArea();
		JScrollPane textScrollPane = new JScrollPane(textArea);

		// Tạo JSplitPane chia theo chiều ngang (VERTICAL_SPLIT là trên/dưới, HORIZONTAL_SPLIT là trái/phải)
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, textScrollPane);
		splitPane.setDividerLocation(200); // Vị trí chia mặc định
		splitPane.setOneTouchExpandable(true); // Cho phép người dùng mở/thu gọn

		// Thêm vào frame
		add(splitPane);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Bai31 frame = new Bai31();
			frame.setVisible(true);
		});
	}
}

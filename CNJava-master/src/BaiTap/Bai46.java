package BaiTap;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

public class Bai46 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<String> list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai46 frame = new Bai46();
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
	public Bai46() {
		setTitle("Danh sách JList");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] items = {
			"Mục 1", "Mục 2", "Mục 3", "Mục 4", "Mục 5",
			"Mục 6", "Mục 7", "Mục 8", "Mục 9", "Mục 10"
		};

		list = new JList<>(items);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(20, 20, 240, 320);
		contentPane.add(scrollPane);

		// Thêm MouseListener để xử lý nhấp đúp chuột
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // Nhấp đúp chuột
					int index = list.locationToIndex(e.getPoint());
					if (index >= 0) {
						String selectedItem = list.getModel().getElementAt(index);
						JOptionPane.showMessageDialog(Bai46.this, "Bạn đã chọn: " + selectedItem);
					}
				}
			}
		});
	}
}

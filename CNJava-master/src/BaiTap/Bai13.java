package BaiTap;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bai13 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai13 frame = new Bai13();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the main frame.
	 */
	public Bai13() {
		setTitle("Main Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300); // Kích thước 400x300
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// Tạo nút mở cửa sổ con
		JButton btnOpenChild = new JButton("Open Child Window");
		btnOpenChild.setBounds(120, 110, 160, 30);
		contentPane.add(btnOpenChild);

		// Xử lý sự kiện khi nhấn nút
		btnOpenChild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tạo cửa sổ con
				JFrame childFrame = new JFrame("Child Window");
				childFrame.setBounds(150, 150, 200, 200); // Kích thước 200x200
				childFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				JPanel childPanel = new JPanel();
				childPanel.add(new JLabel("Child Window"));
				childFrame.setContentPane(childPanel);
				childFrame.setVisible(true);
			}
		});
	}
}

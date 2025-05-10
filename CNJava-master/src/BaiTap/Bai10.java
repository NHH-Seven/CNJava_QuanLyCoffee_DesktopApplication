package BaiTap;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Bai10 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai10 frame = new Bai10();
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
	public Bai10() {
		setTitle("Menu Demo");  // Đặt tiêu đề cho JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// Tạo JMenuBar
		JMenuBar menuBar = new JMenuBar();

		// Tạo JMenu "File"
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		// Tạo JMenuItem "Exit" và thêm sự kiện cho nó
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);  // Thoát ứng dụng khi chọn "Exit"
			}
		});
		fileMenu.add(exitMenuItem);

		// Đặt JMenuBar cho JFrame
		setJMenuBar(menuBar);
	}
}

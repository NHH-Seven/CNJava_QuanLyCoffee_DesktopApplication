package BaiTap;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ImageIcon;

public class Bai45 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JCheckBox chkShowImage;
	private JLabel lblImage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai45 frame = new Bai45();
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
	public Bai45() {
		setTitle("Hiển thị ảnh với JCheckBox");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		chkShowImage = new JCheckBox("Show Image");
		chkShowImage.setBounds(20, 20, 120, 25);
		contentPane.add(chkShowImage);

		// Đường dẫn đến ảnh - bạn thay đường dẫn phù hợp
		ImageIcon icon = new ImageIcon("‪C:\\Users\\Admin\\Downloads\\GTwb_Y3XwAEJlWO.jpg"); // ảnh phải đặt trong thư mục dự án hoặc chỉnh đường dẫn cho đúng
		Image img = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(img);

		lblImage = new JLabel();
		lblImage.setBounds(50, 60, 300, 300);
		lblImage.setIcon(new ImageIcon("C:\\Users\\Admin\\Downloads\\GTwb_Y3XwAEJlWO.jpg"));
		lblImage.setVisible(false); // ban đầu ẩn ảnh
		contentPane.add(lblImage);

		// Xử lý sự kiện khi check/uncheck
		chkShowImage.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				lblImage.setVisible(chkShowImage.isSelected());
			}
		});
	}
}

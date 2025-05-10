package BaiTap;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Bai47 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblFollowMe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai47 frame = new Bai47();
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
	public Bai47() {
		setTitle("Di chuyển JLabel theo chuột");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblFollowMe = new JLabel("Follow Me");
		lblFollowMe.setBounds(50, 50, 100, 20);
		contentPane.add(lblFollowMe);

		// MouseMotionListener để di chuyển JLabel theo chuột
		contentPane.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				lblFollowMe.setLocation(e.getX() - lblFollowMe.getWidth() / 2, e.getY() - lblFollowMe.getHeight() / 2);
			}
		});
	}
}

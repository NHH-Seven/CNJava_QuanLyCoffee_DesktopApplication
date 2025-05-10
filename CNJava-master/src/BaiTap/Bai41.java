package BaiTap;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Bai41 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai41 frame = new Bai41();
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
	public Bai41() {
		setTitle("Bài 41 - Mở JDialog");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnOpenDialog = new JButton("Open Dialog");
		btnOpenDialog.setBounds(140, 100, 120, 30);
		contentPane.add(btnOpenDialog);

		// Sự kiện mở JDialog
		btnOpenDialog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tạo JDialog
				JDialog dialog = new JDialog(Bai41.this, "Dialog", true);
				dialog.setSize(200, 150);
				dialog.setLocationRelativeTo(Bai41.this); // canh giữa so với JFrame
				dialog.setLayout(null);

				JLabel label = new JLabel("Dialog Opened");
				label.setBounds(50, 50, 100, 20);
				dialog.add(label);

				dialog.setVisible(true);
			}
		});
	}
}

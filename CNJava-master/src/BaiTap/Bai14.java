package BaiTap;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bai14 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai14 frame = new Bai14();
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
	public Bai14() {
		setTitle("Message Dialog Example");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 200); // Kích thước 300x200
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JButton btnShowMessage = new JButton("Show Message");
		btnShowMessage.setBounds(90, 40, 120, 30);
		contentPane.add(btnShowMessage);

		lblResult = new JLabel("Your choice will appear here.");
		lblResult.setBounds(40, 100, 220, 20);
		contentPane.add(lblResult);

		btnShowMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(
						Bai14.this,
						"Do you want to continue?",
						"Confirm",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				switch (result) {
					case JOptionPane.YES_OPTION:
						lblResult.setText("You chose: Yes");
						break;
					case JOptionPane.NO_OPTION:
						lblResult.setText("You chose: No");
						break;
					case JOptionPane.CANCEL_OPTION:
						lblResult.setText("You chose: Cancel");
						break;
					default:
						lblResult.setText("No option selected.");
				}
			}
		});
	}
}

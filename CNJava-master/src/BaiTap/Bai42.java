package BaiTap;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

public class Bai42 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai42 frame = new Bai42();
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
	public Bai42() {
		setTitle("Bài 42 - JComboBox chọn màu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] colors = {"Red", "Blue", "Green", "Yellow", "Orange"};
		JComboBox<String> comboBox = new JComboBox<>(colors);
		comboBox.setBounds(80, 60, 120, 25);
		contentPane.add(comboBox);

		// Sự kiện chọn màu
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedColor = (String) comboBox.getSelectedItem();
				JOptionPane.showMessageDialog(Bai42.this, "Bạn đã chọn: " + selectedColor);
			}
		});
	}
}

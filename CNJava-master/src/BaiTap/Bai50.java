package BaiTap;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Bai50 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblClickCount;
	private JLabel lblCoordinates;
	private int clickCount = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai50 frame = new Bai50();
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
	public Bai50() {
		setTitle("Kết hợp nhiều sự kiện");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblClickCount = new JLabel("Click count: 0");
		lblClickCount.setBounds(20, 20, 200, 30);
		contentPane.add(lblClickCount);

		lblCoordinates = new JLabel("Coordinates: ");
		lblCoordinates.setBounds(20, 50, 200, 30);
		contentPane.add(lblCoordinates);

		JButton btnMultiEvent = new JButton("Multi-Event");
		btnMultiEvent.setBounds(20, 100, 150, 30);
		contentPane.add(btnMultiEvent);

		// ActionListener để đếm số lần nhấn
		btnMultiEvent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickCount++;
				lblClickCount.setText("Click count: " + clickCount);
			}
		});

		// MouseListener để hiển thị tọa độ nhấp chuột
		btnMultiEvent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblCoordinates.setText("Coordinates: " + e.getX() + ", " + e.getY());
			}
		});
	}
}

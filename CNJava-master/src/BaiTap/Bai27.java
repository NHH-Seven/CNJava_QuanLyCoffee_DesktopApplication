package BaiTap;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bai27 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JProgressBar progressBar;
	private JButton btnStart;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai27 frame = new Bai27();
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
	public Bai27() {
		setTitle("Tiến trình");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		progressBar = new JProgressBar();
		progressBar.setBounds(30, 30, 220, 30);
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setStringPainted(true);
		contentPane.add(progressBar);

		btnStart = new JButton("Start");
		btnStart.setBounds(100, 90, 80, 30);
		contentPane.add(btnStart);

		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStart.setEnabled(false); // tránh nhấn nhiều lần
				new Thread(new Runnable() {
					public void run() {
						for (int i = 0; i <= 100; i++) {
							progressBar.setValue(i);
							try {
								Thread.sleep(50); // 5 giây tổng cộng (100 * 50ms)
							} catch (InterruptedException ex) {
								ex.printStackTrace();
							}
						}
						btnStart.setEnabled(true); // kích hoạt lại sau khi chạy xong
					}
				}).start();
			}
		});
	}
}

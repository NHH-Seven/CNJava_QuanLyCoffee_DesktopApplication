package Baitap2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Bai61 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel imageLabel;
	private JButton btnLoadImage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Bai61 frame = new Bai61();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Bai61() {
		setTitle("Tải hình ảnh từ URL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(10, 10));
		setContentPane(contentPane);

		imageLabel = new JLabel("Ảnh sẽ hiển thị ở đây", SwingConstants.CENTER);
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(imageLabel, BorderLayout.CENTER);

		btnLoadImage = new JButton("Load Image");
		contentPane.add(btnLoadImage, BorderLayout.SOUTH);

		btnLoadImage.addActionListener(e -> {
			btnLoadImage.setEnabled(false);
			imageLabel.setText("Đang tải hình ảnh...");
			loadImageFromURL("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Apple_logo_black.svg/768px-Apple_logo_black.svg.png");
		});
	}

	/**
	 * Load image using SwingWorker to avoid UI freezing.
	 */
	private void loadImageFromURL(String urlString) {
		SwingWorker<ImageIcon, Void> worker = new SwingWorker<ImageIcon, Void>() {
			@Override
			protected ImageIcon doInBackground() throws Exception {
				URL url = new URL(urlString);
				Image img = ImageIO.read(url);
				if (img != null) {
					img = img.getScaledInstance(400, 300, Image.SCALE_SMOOTH);
					return new ImageIcon(img);
				} else {
					throw new Exception("Không thể tải ảnh.");
				}
			}

			@Override
			protected void done() {
				try {
					ImageIcon icon = get();
					imageLabel.setIcon(icon);
					imageLabel.setText(null); // clear text
				} catch (Exception e) {
					imageLabel.setText("Không thể tải ảnh.");
					e.printStackTrace();
				} finally {
					btnLoadImage.setEnabled(true);
				}
			}
		};

		worker.execute();
	}
}

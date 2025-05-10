package BaiTap;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Bai44 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblSampleText;
	private JSlider slider;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai44 frame = new Bai44();
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
	public Bai44() {
		setTitle("Thay đổi kích thước font");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblSampleText = new JLabel("Sample Text");
		lblSampleText.setFont(new Font("Arial", Font.PLAIN, 20));
		lblSampleText.setBounds(130, 20, 200, 30);
		contentPane.add(lblSampleText);

		slider = new JSlider(10, 50, 20); // min:10, max:50, initial:20
		slider.setBounds(50, 80, 300, 50);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		contentPane.add(slider);

		// ChangeListener để thay đổi kích thước font khi kéo
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int size = slider.getValue();
				lblSampleText.setFont(new Font("Arial", Font.PLAIN, size));
			}
		});
	}
}

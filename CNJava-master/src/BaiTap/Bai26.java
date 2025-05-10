package BaiTap;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Bai26 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JSlider slider;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Bai26 frame = new Bai26();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Bai26() {
		setTitle("Thay đổi độ sáng nền");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 200);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		setContentPane(contentPane);

		// Slider từ 0 đến 255
		slider = new JSlider(0, 255, 255);
		slider.setMajorTickSpacing(50);
		slider.setMinorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);

		// Lắng nghe thay đổi
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = slider.getValue();
				Color color = new Color(value, value, value); // tạo màu xám theo giá trị
				contentPane.setBackground(color);
			}
		});

		contentPane.add(new JLabel("Chỉnh độ sáng:"));
		contentPane.add(slider);
		contentPane.setBackground(Color.WHITE);
	}
}

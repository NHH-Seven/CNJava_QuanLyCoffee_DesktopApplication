package BaiTap;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Bai34 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Bai34() {
		setTitle("JSpinner Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));

		// Tạo JSpinner với giá trị từ 1 đến 100
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));

		// Tạo JLabel để hiển thị giá trị hiện tại
		JLabel lblValue = new JLabel("Current Value: 1");

		// Lắng nghe thay đổi giá trị
		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int value = (int) spinner.getValue();
				lblValue.setText("Current Value: " + value);
			}
		});

		// Thêm thành phần vào JFrame
		add(spinner);
		add(lblValue);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Bai34 frame = new Bai34();
			frame.setVisible(true);
		});
	}
}

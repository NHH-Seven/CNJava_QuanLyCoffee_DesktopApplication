package BaiTap;

import java.awt.*;
import javax.swing.*;

public class Bai33 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Bai33() {
		setTitle("GridBagLayout Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);

		// Thiết lập layout
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các thành phần

		// JLabel "Name"
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Name:"), gbc);

		// JTextField
		JTextField txtName = new JTextField(15);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(txtName, gbc);

		// JLabel "Age"
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Age:"), gbc);

		// JSpinner (tuổi)
		JSpinner spinnerAge = new JSpinner(new SpinnerNumberModel(18, 0, 100, 1));
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(spinnerAge, gbc);

		// JButton "Submit"
		JButton btnSubmit = new JButton("Submit");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		add(btnSubmit, gbc);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Bai33 frame = new Bai33();
			frame.setVisible(true);
		});
	}
}

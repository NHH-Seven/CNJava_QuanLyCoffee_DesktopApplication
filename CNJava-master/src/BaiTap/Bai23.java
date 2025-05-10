package BaiTap;

import java.awt.*;
import javax.swing.*;

public class Bai23 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Bai23 frame = new Bai23();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Bai23() {
		setTitle("Máy tính đơn giản");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 400);
		setLocationRelativeTo(null);

		contentPane = new JPanel(new GridBagLayout());
		setContentPane(contentPane);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5); // padding giữa các nút

		// Vùng hiển thị kết quả
		textField = new JTextField();
		textField.setEditable(false);
		textField.setHorizontalAlignment(JTextField.RIGHT);
		textField.setFont(new Font("Arial", Font.BOLD, 18));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.weightx = 1;
		gbc.weighty = 0.2;
		contentPane.add(textField, gbc);

		// Mảng các nút
		String[] buttons = {
			"7", "8", "9", "/",
			"4", "5", "6", "*",
			"1", "2", "3", "-",
			"0", "=", "+", ""
		};

		int index = 0;
		for (int row = 1; row <= 4; row++) {
			for (int col = 0; col < 4; col++) {
				String label = buttons[index++];
				if (label.isEmpty()) continue; // bỏ qua ô trống
				JButton button = new JButton(label);
				button.setFont(new Font("Arial", Font.BOLD, 16));

				gbc.gridx = col;
				gbc.gridy = row;
				gbc.gridwidth = 1;
				gbc.weightx = 1;
				gbc.weighty = 0.2;
				contentPane.add(button, gbc);

				// Thêm sự kiện cho mỗi nút (demo đơn giản)
				button.addActionListener(e -> {
					String currentText = textField.getText();
					String buttonText = ((JButton) e.getSource()).getText();

					if (buttonText.equals("=")) {
						// Tính toán đơn giản bằng ScriptEngine
						try {
							javax.script.ScriptEngine engine = new javax.script.ScriptEngineManager().getEngineByName("JavaScript");
							Object result = engine.eval(currentText);
							textField.setText(result.toString());
						} catch (Exception ex) {
							textField.setText("Lỗi");
						}
					} else {
						textField.setText(currentText + buttonText);
					}
				});
			}
		}
	}
}

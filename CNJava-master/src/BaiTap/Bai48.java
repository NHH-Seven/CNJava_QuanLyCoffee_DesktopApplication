package BaiTap;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class Bai48 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai48 frame = new Bai48();
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
	public Bai48() {
		setTitle("Giới hạn nhập số");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 200);
		contentPane = new JPanel();
		contentPane.setLayout(new FlowLayout());
		setContentPane(contentPane);

		lblNumber = new JLabel("Number: ");
		contentPane.add(lblNumber);

		textField = new JTextField();
		textField.setColumns(10);
		contentPane.add(textField);

		// DocumentListener để giới hạn nhập số
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				validateInput();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				validateInput();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				validateInput();
			}

			private void validateInput() {
				String text = textField.getText();
				if (!text.matches("\\d*")) {
					textField.setText(text.replaceAll("[^\\d]", ""));
				}
				lblNumber.setText("Number: " + textField.getText());
			}
		});
	}
}

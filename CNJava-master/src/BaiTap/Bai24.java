package BaiTap;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Bai24 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JCheckBox chckbxEnableBackground;
	private Color defaultColor;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Bai24 frame = new Bai24();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Bai24() {
		setTitle("CheckBox Background");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 200);

		contentPane = new JPanel();
		defaultColor = contentPane.getBackground();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		setContentPane(contentPane);

		chckbxEnableBackground = new JCheckBox("Enable Background");

		// Xử lý sự kiện khi chọn hoặc bỏ chọn checkbox
		chckbxEnableBackground.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (chckbxEnableBackground.isSelected()) {
					contentPane.setBackground(Color.GREEN);
				} else {
					contentPane.setBackground(defaultColor);
				}
			}
		});

		contentPane.add(chckbxEnableBackground);
	}
}

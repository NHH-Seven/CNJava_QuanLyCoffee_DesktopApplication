package BaiTap;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class Bai20 extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Bai20 frame = new Bai20();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Bai20() {
		setTitle("JTabbedPane Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		// Tạo JTabbedPane
		JTabbedPane tabbedPane = new JTabbedPane();

		// Tạo tab "Home"
		JPanel homePanel = new JPanel();
		JLabel label = new JLabel("Welcome to Home Tab");
		homePanel.add(label);
		tabbedPane.addTab("Home", homePanel);

		// Tạo tab "Profile"
		JPanel profilePanel = new JPanel();
		JLabel label_2 = new JLabel("This is your Profile");
		profilePanel.add(label_2);
		tabbedPane.addTab("Profile", profilePanel);

		// Tạo tab "Settings"
		JPanel settingsPanel = new JPanel();
		JLabel label_1 = new JLabel("Adjust your Settings here");
		settingsPanel.add(label_1);
		tabbedPane.addTab("Settings", settingsPanel);

		// Thêm tabbedPane vào JFrame
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{homePanel, label, label_1, profilePanel, label_2, settingsPanel}));
	}
}

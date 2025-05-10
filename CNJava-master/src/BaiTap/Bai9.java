package BaiTap;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Bai9 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Bai9 frame = new Bai9();
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
    public Bai9() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Tạo JButton "Open Dialog"
        JButton btnOpenDialog = new JButton("Open Dialog");
        btnOpenDialog.setBounds(200, 150, 120, 30);  // Vị trí và kích thước
        contentPane.add(btnOpenDialog);
        
        // Thêm sự kiện ActionListener cho JButton
        btnOpenDialog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Khi nhấn nút "Open Dialog", mở JDialog
                openDialog();
            }
        });
    }
    
    // Phương thức mở JDialog
    private void openDialog() {
        JDialog dialog = new JDialog(this, "Dialog Example", true);  // true để modal
        dialog.setBounds(100, 100, 200, 150);  // Kích thước 200x150
        JLabel label = new JLabel("This is a dialog", JLabel.CENTER);  // JLabel hiển thị nội dung
        dialog.add(label);
        dialog.setVisible(true);  // Hiển thị JDialog
    }
}

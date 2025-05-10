package Baitap2;

import java.awt.*;
import javax.swing.*;

public class Bai64 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel lblStatus;
    private JButton btnRun;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Bai64 frame = new Bai64();
            frame.setVisible(true);
        });
    }

    public Bai64() {
        setTitle("Hiển thị lỗi từ Thread");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        lblStatus = new JLabel("Status: Ready");
        lblStatus.setFont(new Font("Arial", Font.PLAIN, 16));
        btnRun = new JButton("Run Task");

        add(lblStatus);
        add(btnRun);

        btnRun.addActionListener(e -> {
            lblStatus.setText("Status: Running...");

            // Tạo thread giả lập lỗi
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(2000); // giả lập xử lý
                    throw new RuntimeException("Đã xảy ra lỗi trong tác vụ!");
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> {
                        lblStatus.setText("Status: Error");
                        JOptionPane.showMessageDialog(Bai64.this,
                                ex.getMessage(),
                                "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    });
                }
            });
            thread.start();
        });
    }
}

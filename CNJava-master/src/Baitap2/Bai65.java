package Baitap2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

public class Bai65 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel lblTime;
    private JButton btnStart, btnStop, btnReset;
    private Timer timer;
    private int seconds = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Bai65 frame = new Bai65();
            frame.setVisible(true);
        });
    }

    public Bai65() {
        setTitle("Đồng hồ bấm giờ");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        lblTime = new JLabel("Time: 00:00");
        lblTime.setFont(new Font("Arial", Font.BOLD, 24));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");
        btnReset = new JButton("Reset");

        add(lblTime);
        add(btnStart);
        add(btnStop);
        add(btnReset);

        // Timer chạy mỗi 1000ms (1 giây)
        timer = new Timer(1000, e -> {
            seconds++;
            int minutes = seconds / 60;
            int sec = seconds % 60;
            lblTime.setText(String.format("Time: %02d:%02d", minutes, sec));
        });

        btnStart.addActionListener(e -> {
            if (!timer.isRunning()) {
                timer.start();
            }
        });

        btnStop.addActionListener(e -> {
            timer.stop();
        });

        btnReset.addActionListener(e -> {
            timer.stop();
            seconds = 0;
            lblTime.setText("Time: 00:00");
        });
    }
}

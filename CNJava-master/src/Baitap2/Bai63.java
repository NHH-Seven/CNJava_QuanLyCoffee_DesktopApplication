package Baitap2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Bai63 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel lblCount;
    private JButton btnStart, btnPause, btnResume;

    private int count = 0;
    private Thread thread;
    private boolean isRunning = false;
    private boolean isPaused = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Bai63 frame = new Bai63();
            frame.setVisible(true);
        });
    }

    public Bai63() {
        setTitle("Đếm số có thể Tạm dừng / Tiếp tục");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        lblCount = new JLabel("Count: 0");
        lblCount.setFont(new Font("Arial", Font.BOLD, 20));

        btnStart = new JButton("Start");
        btnPause = new JButton("Pause");
        btnResume = new JButton("Resume");

        add(lblCount);
        add(btnStart);
        add(btnPause);
        add(btnResume);

        // Start button
        btnStart.addActionListener(e -> {
            if (thread == null || !thread.isAlive()) {
                isRunning = true;
                isPaused = false;
                thread = new Thread(() -> {
                    while (isRunning) {
                        try {
                            Thread.sleep(1000);
                            synchronized (this) {
                                while (isPaused) {
                                    wait();
                                }
                            }
                            count++;
                            SwingUtilities.invokeLater(() -> lblCount.setText("Count: " + count));
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        });

        // Pause button
        btnPause.addActionListener(e -> {
            isPaused = true;
        });

        // Resume button
        btnResume.addActionListener(e -> {
            synchronized (this) {
                isPaused = false;
                notify(); // đánh thức thread đang chờ
            }
        });
    }
}

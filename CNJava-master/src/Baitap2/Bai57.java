package Baitap2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Bai57 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel statusLabel;
    private JButton startButton, stopButton;
    private Thread taskThread;

    public Bai57() {
        setTitle("Run and Stop Heavy Task");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        statusLabel = new JLabel("Status: Idle", SwingConstants.CENTER);
        add(statusLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        add(buttonPanel, BorderLayout.SOUTH);

        startButton.addActionListener(this::startTask);
        stopButton.addActionListener(this::stopTask);

        stopButton.setEnabled(false);
    }

    private void startTask(ActionEvent e) {
        statusLabel.setText("Status: Running");
        startButton.setEnabled(false);
        stopButton.setEnabled(true);

        taskThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                // Simulate heavy task
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    break;
                }
            }
        });
        taskThread.start();
    }

    private void stopTask(ActionEvent e) {
        statusLabel.setText("Status: Idle");
        startButton.setEnabled(true);
        stopButton.setEnabled(false);

        if (taskThread != null) {
            taskThread.interrupt();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Bai57 frame = new Bai57();
            frame.setVisible(true);
        });
    }
}

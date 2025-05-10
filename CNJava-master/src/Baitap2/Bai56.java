package Baitap2;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.SwingWorker;

public class Bai56 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel timeLabel;
    private JButton startButton;

    public Bai56() {
        setTitle("Countdown Timer");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timeLabel = new JLabel("Time: 10", SwingConstants.CENTER);
        add(timeLabel, BorderLayout.CENTER);

        startButton = new JButton("Start");
        startButton.addActionListener(e -> startCountdown());
        add(startButton, BorderLayout.SOUTH);
    }

    private void startCountdown() {
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 10; i >= 0; i--) {
                    Thread.sleep(1000);  // Giả lập đếm ngược
                    publish(i);
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                timeLabel.setText("Time: " + chunks.get(chunks.size() - 1));
            }

            @Override
            protected void done() {
                timeLabel.setText("Finished");
            }
        };
        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Bai56 frame = new Bai56();
            frame.setVisible(true);
        });
    }
}

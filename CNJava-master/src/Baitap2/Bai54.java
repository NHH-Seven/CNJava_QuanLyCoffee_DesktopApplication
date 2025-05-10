package Baitap2;

import javax.swing.*;
import java.awt.*;
import javax.swing.SwingWorker;

public class Bai54 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JProgressBar progressBar;
    private JButton loadButton;
    private JLabel statusLabel;

    public Bai54() {
        setTitle("Data Loading Simulation");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        add(progressBar, BorderLayout.NORTH);

        loadButton = new JButton("Load");
        loadButton.addActionListener(e -> startLoading());
        add(loadButton, BorderLayout.CENTER);

        statusLabel = new JLabel("Status: Waiting", JLabel.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    private void startLoading() {
        // Sử dụng SwingWorker để giả lập quá trình tải dữ liệu
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(100);  // Giả lập công việc
                    publish(i);  // Gửi cập nhật
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                progressBar.setValue(chunks.get(chunks.size() - 1));
            }

            @Override
            protected void done() {
                statusLabel.setText("Done!");
            }
        };
        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Bai54 frame = new Bai54();
            frame.setVisible(true);
        });
    }
}

package Baitap2;

import javax.swing.*;
import java.awt.*;
import javax.swing.SwingWorker;

public class Bai60 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JProgressBar progressBar;
    private JButton downloadButton;
    private JLabel statusLabel;

    public Bai60() {
        setTitle("File Download Simulation");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        progressBar = new JProgressBar(0, 100);
        downloadButton = new JButton("Download");
        statusLabel = new JLabel("Status: ");

        add(progressBar);
        add(downloadButton);
        add(statusLabel);

        downloadButton.addActionListener(e -> startDownload());
    }

    private void startDownload() {
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(100);
                    publish(i);
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                int progress = chunks.get(chunks.size() - 1);
                progressBar.setValue(progress);
            }

            @Override
            protected void done() {
                statusLabel.setText("Download Complete");
            }
        };
        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Bai60 frame = new Bai60();
            frame.setVisible(true);
        });
    }
}

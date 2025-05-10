package Baitap2;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

public class Bai51 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JProgressBar progressBar;
    private JButton startButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Bai51 frame = new Bai51();
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
    public Bai51() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        // Tạo JProgressBar
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);  // Hiển thị giá trị dưới dạng phần trăm
        contentPane.add(progressBar, BorderLayout.CENTER);

        // Tạo JButton "Start"
        startButton = new JButton("Start");
        startButton.addActionListener(e -> startProgress());
        contentPane.add(startButton, BorderLayout.SOUTH);
    }

    private void startProgress() {
        // Dùng SwingWorker để chạy tác vụ trong nền
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(100);  // Giả lập công việc đang thực hiện
                    publish(i);  // Gửi cập nhật vào process() 
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                // Cập nhật giá trị progressBar
                progressBar.setValue(chunks.get(chunks.size() - 1));
            }

            @Override
            protected void done() {
                // Khi hoàn tất, có thể hiển thị một thông báo
                startButton.setText("Completed!");
            }
        };
        worker.execute();  // Khởi động SwingWorker
    }
}

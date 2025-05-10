package Baitap2;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;

public class Bai58 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel label1, label2, label3;

    public Bai58() {
        setTitle("Multiple Tasks with ExecutorService");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        label1 = new JLabel("Task 1: ");
        label2 = new JLabel("Task 2: ");
        label3 = new JLabel("Task 3: ");
        add(label1);
        add(label2);
        add(label3);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(() -> label1.setText("Task 1 Result: " + sum(1, 100)));
        executor.submit(() -> label2.setText("Task 2 Result: " + sum(1, 200)));
        executor.submit(() -> label3.setText("Task 3 Result: " + sum(1, 300)));

        executor.shutdown();
    }

    private int sum(int start, int end) {
        int total = 0;
        for (int i = start; i <= end; i++) {
            total += i;
        }
        return total;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Bai58 frame = new Bai58();
            frame.setVisible(true);
        });
    }
}

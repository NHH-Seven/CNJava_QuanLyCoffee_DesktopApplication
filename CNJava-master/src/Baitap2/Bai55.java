package Baitap2;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Bai55 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Random Background Color");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);

        Timer timer = new Timer(1000, e -> {
            Random rand = new Random();
            Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            panel.setBackground(randomColor);
        });
        timer.start();

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> timer.stop());
        panel.add(stopButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}

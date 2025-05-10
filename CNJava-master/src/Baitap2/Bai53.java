package Baitap2;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bai53 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Real-time Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        
        JLabel timeLabel = new JLabel("", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Serif", Font.BOLD, 30));
        frame.add(timeLabel);
        
        frame.setVisible(true);

        // Cập nhật thời gian mỗi giây
        new Timer(1000, e -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            timeLabel.setText(sdf.format(new Date()));
        }).start();
    }
}

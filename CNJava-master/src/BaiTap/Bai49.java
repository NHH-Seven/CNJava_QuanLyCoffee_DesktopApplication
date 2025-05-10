package BaiTap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Bai49 extends JFrame {
    JLabel label;

    public Bai49() {
        setTitle("Bai 49");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        // Tạo các JRadioButton
        JRadioButton red = new JRadioButton("Red");
        JRadioButton blue = new JRadioButton("Blue");
        JRadioButton green = new JRadioButton("Green");

        // Tạo ButtonGroup để nhóm các JRadioButton lại với nhau
        ButtonGroup group = new ButtonGroup();
        group.add(red);
        group.add(blue);
        group.add(green);

        // Tạo JLabel để thay đổi màu văn bản
        label = new JLabel("Sample");
        label.setFont(new Font("Arial", Font.BOLD, 20));

        // Sử dụng ActionListener để thay đổi màu văn bản khi chọn một JRadioButton
        red.addActionListener(e -> label.setForeground(Color.RED));
        blue.addActionListener(e -> label.setForeground(Color.BLUE));
        green.addActionListener(e -> label.setForeground(Color.GREEN));

        // Thêm các component vào JFrame
        add(red); 
        add(blue); 
        add(green); 
        add(label);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Bai49();
    }
}

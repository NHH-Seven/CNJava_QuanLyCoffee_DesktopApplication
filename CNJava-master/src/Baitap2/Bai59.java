package Baitap2;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Bai59 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private JButton generateButton;

    public Bai59() {
        setTitle("Random Numbers in JList");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        add(scrollPane, BorderLayout.CENTER);

        generateButton = new JButton("Generate");
        add(generateButton, BorderLayout.SOUTH);

        generateButton.addActionListener(e -> generateRandomNumbers());
    }

    private void generateRandomNumbers() {
        new Thread(() -> {
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                listModel.addElement(String.valueOf(random.nextInt(100)));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Bai59 frame = new Bai59();
            frame.setVisible(true);
        });
    }
}

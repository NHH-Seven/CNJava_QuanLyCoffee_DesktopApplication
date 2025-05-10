package Baitap2;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Bai52 {

    public static void main(String[] args) {
        // Đảm bảo JFrame được khởi tạo trên EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Safe GUI");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 300);
                frame.add(new JLabel("Safe GUI"));
                frame.setVisible(true);
            }
        });
    }
}

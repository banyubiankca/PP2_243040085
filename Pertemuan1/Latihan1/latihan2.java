package Latihan1;

import javax.swing.*;

public class latihan2 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new latihan2());
    }

    public latihan2() {
        JFrame frame = new JFrame("latihan 1");
        JLabel label = new JLabel("Hello World", JLabel.CENTER);
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

package Latihan1;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class latihan {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new latihan());
    }

    public latihan() {
        JFrame frame = new JFrame("Form Data Barang");

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        panel.add(new JLabel("Nama:"));
        JTextField txtNama = new JTextField(15);
        panel.add(txtNama);

        panel.add(new JLabel("Stok:"));
        JTextField txtStok = new JTextField(15);
        panel.add(txtStok);

        panel.add(new JLabel("Harga:"));
        JTextField txtHarga = new JTextField(15);
        panel.add(txtHarga);

        panel.add(new JLabel("Status:"));
        panel.add(new JLabel("Aktif"));

        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(400, 250);
        frame.setMinimumSize(new java.awt.Dimension(300, 200));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

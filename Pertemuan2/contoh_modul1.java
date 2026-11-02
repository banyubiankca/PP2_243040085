package Pertemuan2;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class contoh_modul1 {

    private int jumlahklik = 0;

    private void tampilam() {
        JFrame frame = new JFrame("Contoh Modul 1");
        JLabel pesan = new JLabel("tekan tombol ini");
        JButton tombol = new JButton("Klik Saya");

        tombol.addActionListener(event -> {
            jumlahklik++;
            pesan.setText("saya sudah mengklik tombol ini sebanyak " + jumlahklik);
        });

        JPanel panel = new JPanel(new BorderLayout(50, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(pesan, BorderLayout.NORTH);
        panel.add(tombol, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        contoh_modul1 modul = new contoh_modul1();
        modul.tampilam();
    }
}

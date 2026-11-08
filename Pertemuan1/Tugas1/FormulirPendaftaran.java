package Tugas1;

import java.awt.*;
import javax.swing.*;

public class FormulirPendaftaran {

    static void tampilGUI() {
        JFrame frame = new JFrame("Formulir Pendaftaran Kegiatan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel Identitas Peserta
        JPanel panelIdentitas = new JPanel(new GridLayout(3, 2, 5, 5));
        panelIdentitas.setBorder(BorderFactory.createTitledBorder("Identitas Peserta"));

        panelIdentitas.add(new JLabel("Nama Lengkap:"));
        panelIdentitas.add(new JTextField(20));

        panelIdentitas.add(new JLabel("NPM:"));
        panelIdentitas.add(new JTextField(20));

        panelIdentitas.add(new JLabel("Program Studi:"));
        String[] prodi = {"Teknik Informatika", "Teknik Elektro", "Teknik Mesin"};
        panelIdentitas.add(new JComboBox<>(prodi));

        // Panel Pilihan Kegiatan
        JPanel panelKegiatan = new JPanel(new GridLayout(2, 1, 5, 5));
        panelKegiatan.setBorder(BorderFactory.createTitledBorder("Pilihan Kegiatan"));

        JPanel panelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton rbLaki = new JRadioButton("Laki-laki");
        JRadioButton rbPerempuan = new JRadioButton("Perempuan");

        ButtonGroup bgGender = new ButtonGroup();
        bgGender.add(rbLaki);
        bgGender.add(rbPerempuan);

        panelRadio.add(new JLabel("Gender: "));
        panelRadio.add(rbLaki);
        panelRadio.add(rbPerempuan);
        panelKegiatan.add(panelRadio);

        JPanel panelCek = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JCheckBox cbSertifikat = new JCheckBox("Cetak Sertifikat Fisik");
        JCheckBox cbKonsumsi = new JCheckBox("Pesan Konsumsi");

        panelCek.add(cbSertifikat);
        panelCek.add(cbKonsumsi);
        panelKegiatan.add(panelCek);

        // Panel Catatan Tambahan
        JPanel panelCatatan = new JPanel(new BorderLayout(5, 5));
        panelCatatan.setBorder(BorderFactory.createTitledBorder("Catatan Tambahan"));

        JTextArea areaCatatan = new JTextArea(4, 20);
        JScrollPane scrollCatatan = new JScrollPane(areaCatatan);
        panelCatatan.add(scrollCatatan, BorderLayout.CENTER);

        // Panel Tengah
        JPanel panelTengah = new JPanel();
        panelTengah.setLayout(new BoxLayout(panelTengah, BoxLayout.Y_AXIS));
        panelTengah.add(panelIdentitas);
        panelTengah.add(panelKegiatan);
        panelTengah.add(panelCatatan);

        // Panel Tombol
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnDaftar = new JButton("Daftar Kegiatan");
        panelTombol.add(btnDaftar);

        // Menambahkan Panel ke Frame
        frame.add(panelTengah, BorderLayout.CENTER);
        frame.add(panelTombol, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FormulirPendaftaran::tampilGUI);
    }
}

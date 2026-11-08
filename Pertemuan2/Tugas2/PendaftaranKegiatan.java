package Tugas2;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PendaftaranKegiatan extends JFrame {

    private final CardLayout cardLayout;
    private final JPanel mainPanel;

    private JTextField txtNama, txtEmail, txtUmur;
    private JComboBox<String> cmbKegiatan;
    private JTextArea txtCatatan;

    private JLabel lblRingkasan;
    private boolean perubahanBelumDisimpan = false;

    private final String FORM = "FORM";
    private final String RINGKASAN = "RINGKASAN";

    public PendaftaranKegiatan() {
        setTitle("Aplikasi Pendaftaran Kegiatan");
        setSize(600, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(buatFormulir(), FORM);
        mainPanel.add(buatRingkasan(), RINGKASAN);

        add(mainPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                keluarAplikasi();
            }
        });
    }

    private JPanel buatFormulir() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel judul = new JLabel("FORMULIR PENDAFTARAN KEGIATAN");
        judul.setFont(new Font("Arial", Font.BOLD, 20));
        judul.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(judul, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 15));

        txtNama = new JTextField();
        txtEmail = new JTextField();
        txtUmur = new JTextField();

        cmbKegiatan = new JComboBox<>(new String[]{
            "-- Pilih Kegiatan --",
            "Seminar",
            "Workshop",
            "Pelatihan",
            "Lomba",
            "Donor Darah"
        });

        txtCatatan = new JTextArea(4, 20);
        txtCatatan.setLineWrap(true);
        txtCatatan.setWrapStyleWord(true);

        form.add(new JLabel("Nama Lengkap"));
        form.add(txtNama);

        form.add(new JLabel("Email"));
        form.add(txtEmail);

        form.add(new JLabel("Umur"));
        form.add(txtUmur);

        form.add(new JLabel("Pilihan Kegiatan"));
        form.add(cmbKegiatan);

        form.add(new JLabel("Catatan"));
        form.add(new JScrollPane(txtCatatan));

        // Pengaturan tombol
        JPanel tombol = new JPanel(new GridLayout(2, 1, 10, 10));

        JButton btnSimpan = new JButton("Simpan / Tampilkan");
        JButton btnReset = new JButton("Reset");
        JButton btnKeluar = new JButton("Keluar");

        // Baris pertama: Simpan dan Reset
        JPanel barisAtas = new JPanel(new GridLayout(1, 2, 10, 10));
        barisAtas.add(btnSimpan);
        barisAtas.add(btnReset);

        // Baris kedua: Keluar di tengah
        JPanel panelKeluar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelKeluar.add(btnKeluar);

        tombol.add(barisAtas);
        tombol.add(panelKeluar);

        JPanel tengah = new JPanel(new BorderLayout(10, 20));
        tengah.add(form, BorderLayout.CENTER);
        tengah.add(tombol, BorderLayout.SOUTH);

        panel.add(tengah, BorderLayout.CENTER);

        btnSimpan.addActionListener(e -> simpanData());
        btnReset.addActionListener(e -> resetForm());

        // btnBantuan.addActionListener(e -> {
        //     cardLayout.show(mainPanel, BANTUAN);
        // });
        btnKeluar.addActionListener(e -> keluarAplikasi());

        pasangListenerPerubahan();

        return panel;
    }

    private JPanel buatRingkasan() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel judul = new JLabel("RINGKASAN PENDAFTARAN");
        judul.setFont(new Font("Arial", Font.BOLD, 22));
        judul.setHorizontalAlignment(SwingConstants.CENTER);

        lblRingkasan = new JLabel();
        lblRingkasan.setVerticalAlignment(SwingConstants.TOP);
        lblRingkasan.setFont(new Font("Arial", Font.PLAIN, 15));

        JPanel tombol = new JPanel(new FlowLayout());

        JButton btnKembali = new JButton("Kembali");
        JButton btnKeluar = new JButton("Keluar");

        tombol.add(btnKembali);
        tombol.add(btnKeluar);

        panel.add(judul, BorderLayout.NORTH);
        panel.add(lblRingkasan, BorderLayout.CENTER);
        panel.add(tombol, BorderLayout.SOUTH);

        btnKembali.addActionListener(e -> {
            cardLayout.show(mainPanel, FORM);
        });

        btnKeluar.addActionListener(e -> keluarAplikasi());

        return panel;
    }

    private void pasangListenerPerubahan() {

        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                perubahanBelumDisimpan = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                perubahanBelumDisimpan = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                perubahanBelumDisimpan = true;
            }
        };

        txtNama.getDocument().addDocumentListener(listener);
        txtEmail.getDocument().addDocumentListener(listener);
        txtUmur.getDocument().addDocumentListener(listener);
        txtCatatan.getDocument().addDocumentListener(listener);

        cmbKegiatan.addActionListener(e -> {
            perubahanBelumDisimpan = true;
        });
    }

    private void simpanData() {

        String nama = txtNama.getText().trim();
        String email = txtEmail.getText().trim();
        String umurText = txtUmur.getText().trim();
        String kegiatan = (String) cmbKegiatan.getSelectedItem();
        String catatan = txtCatatan.getText().trim();

        if (nama.isEmpty()) {
            tampilkanError("Nama lengkap wajib diisi.", txtNama);
            return;
        }

        if (email.isEmpty()) {
            tampilkanError("Email wajib diisi.", txtEmail);
            return;
        }

        String polaEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (!Pattern.matches(polaEmail, email)) {
            tampilkanError(
                    "Format email tidak valid. Contoh: nama@gmail.com",
                    txtEmail
            );
            return;
        }

        if (umurText.isEmpty()) {
            tampilkanError("Umur wajib diisi.", txtUmur);
            return;
        }

        int umur;

        try {
            if (!umurText.matches("\\d+")) {
                throw new NumberFormatException();
            }

            umur = Integer.parseInt(umurText);

        } catch (NumberFormatException ex) {
            tampilkanError(
                    "Umur harus berupa bilangan bulat.",
                    txtUmur
            );
            return;
        }

        if (umur < 15 || umur > 100) {
            tampilkanError(
                    "Umur harus berada dalam rentang 15 sampai 100 tahun.",
                    txtUmur
            );
            return;
        }

        if (cmbKegiatan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Silakan pilih salah satu kegiatan.",
                    "Validasi Kegiatan",
                    JOptionPane.WARNING_MESSAGE
            );

            cmbKegiatan.requestFocusInWindow();
            return;
        }

        lblRingkasan.setText(
                "<html>"
                + "<b>Data Pendaftaran Berhasil Disimpan!</b><br><br>"
                + "<b>Nama Lengkap:</b> " + escapeHTML(nama) + "<br><br>"
                + "<b>Email:</b> " + escapeHTML(email) + "<br><br>"
                + "<b>Umur:</b> " + umur + " tahun<br><br>"
                + "<b>Kegiatan:</b> " + escapeHTML(kegiatan) + "<br><br>"
                + "<b>Catatan:</b> "
                + (catatan.isEmpty() ? "-" : escapeHTML(catatan))
                + "<br><br>"
                + "Terima kasih telah melakukan pendaftaran."
                + "</html>"
        );

        perubahanBelumDisimpan = false;

        JOptionPane.showMessageDialog(
                this,
                "Data pendaftaran berhasil disimpan.",
                "Berhasil",
                JOptionPane.INFORMATION_MESSAGE
        );

        cardLayout.show(mainPanel, RINGKASAN);
    }

    private String escapeHTML(String teks) {
        return teks.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    private void tampilkanError(String pesan, JComponent komponen) {

        JOptionPane.showMessageDialog(
                this,
                pesan,
                "Validasi Input",
                JOptionPane.WARNING_MESSAGE
        );

        komponen.requestFocusInWindow();

        if (komponen instanceof JTextField field) {
            field.selectAll();
        }
    }

    private void resetForm() {

        int konfirmasi = JOptionPane.showConfirmDialog(
                this,
                "Apakah Anda yakin ingin mengosongkan formulir?",
                "Konfirmasi Reset",
                JOptionPane.YES_NO_OPTION
        );

        if (konfirmasi == JOptionPane.YES_OPTION) {

            txtNama.setText("");
            txtEmail.setText("");
            txtUmur.setText("");
            txtCatatan.setText("");
            cmbKegiatan.setSelectedIndex(0);

            perubahanBelumDisimpan = true;

            txtNama.requestFocusInWindow();
        }
    }

    private void keluarAplikasi() {

        if (perubahanBelumDisimpan) {

            int pilihan = JOptionPane.showConfirmDialog(
                    this,
                    """
                    Terdapat perubahan yang belum disimpan.
                    Apakah Anda yakin ingin keluar?""",
                    "Konfirmasi Keluar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (pilihan == JOptionPane.YES_OPTION) {
                dispose();
            }

        } else {

            int pilihan = JOptionPane.showConfirmDialog(
                    this,
                    "Apakah Anda yakin ingin keluar dari aplikasi?",
                    "Konfirmasi Keluar",
                    JOptionPane.YES_NO_OPTION
            );

            if (pilihan == JOptionPane.YES_OPTION) {
                dispose();
            }
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            PendaftaranKegiatan frame = new PendaftaranKegiatan();
            frame.setVisible(true);
        });
    }
}

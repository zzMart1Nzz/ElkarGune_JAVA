package eragiketak;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;

import objetuak.Bazkidea;
import panelak.BazkideakPanela;
import kontrolak.KontrolAdmin;

public class sartuBazkideak extends JFrame {
    private JPanel contentPane;
    private static final long serialVersionUID = 1L;
    private JTextField txt_kontuKorrontea, txt_nan, txt_izena, txt_abizena, txt_telefonoa, txt_email, txt_erabiltzailea, txt_pasahitza;
    private JCheckBox chk_admin;
    private JLabel okIkonoa, lblFondoa, lblAtzera;

    // 🆕 Constructor sin parámetros
    public sartuBazkideak() {
        this(new Bazkidea()); // Llama al constructor existente con un Bazkidea vacío
    }

    public sartuBazkideak(Bazkidea bazkidea) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(-5, 0, 1550, 770);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Campos de entrada
        txt_kontuKorrontea = new JTextField();
        txt_kontuKorrontea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_kontuKorrontea.setBounds(693, 451, 168, 35);
        contentPane.add(txt_kontuKorrontea);

        JLabel lblKontuKorrontea = new JLabel("Kontu Korrontea:");
        lblKontuKorrontea.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblKontuKorrontea.setBounds(693, 421, 168, 20);
        contentPane.add(lblKontuKorrontea);

        txt_nan = new JTextField();
        txt_nan.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_nan.setBounds(249, 173, 168, 35);
        contentPane.add(txt_nan);

        JLabel lblNan = new JLabel("NAN:");
        lblNan.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNan.setBounds(249, 150, 79, 20);
        contentPane.add(lblNan);

        txt_izena = new JTextField();
        txt_izena.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_izena.setBounds(467, 173, 168, 35);
        contentPane.add(txt_izena);

        JLabel lblIzena = new JLabel("Izena:");
        lblIzena.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblIzena.setBounds(467, 150, 71, 20);
        contentPane.add(lblIzena);

        txt_abizena = new JTextField();
        txt_abizena.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_abizena.setBounds(693, 173, 168, 35);
        contentPane.add(txt_abizena);

        JLabel lblAbizena = new JLabel("Abizena:");
        lblAbizena.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblAbizena.setBounds(693, 150, 168, 20);
        contentPane.add(lblAbizena);

        txt_telefonoa = new JTextField();
        txt_telefonoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_telefonoa.setBounds(249, 451, 168, 35);
        contentPane.add(txt_telefonoa);

        JLabel lblTelefonoa = new JLabel("Telefonoa:");
        lblTelefonoa.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTelefonoa.setBounds(249, 421, 139, 20);
        contentPane.add(lblTelefonoa);

        txt_email = new JTextField();
        txt_email.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_email.setBounds(467, 451, 168, 35);
        contentPane.add(txt_email);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblEmail.setBounds(467, 421, 106, 20);
        contentPane.add(lblEmail);

        txt_erabiltzailea = new JTextField();
        txt_erabiltzailea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_erabiltzailea.setBounds(912, 173, 168, 35);
        contentPane.add(txt_erabiltzailea);

        JLabel lblErabiltzailea = new JLabel("Erabiltzailea:");
        lblErabiltzailea.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblErabiltzailea.setBounds(912, 150, 168, 20);
        contentPane.add(lblErabiltzailea);

        txt_pasahitza = new JTextField();
        txt_pasahitza.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_pasahitza.setBounds(1128, 173, 168, 35);
        contentPane.add(txt_pasahitza);

        JLabel lblPasahitza = new JLabel("Pasahitza:");
        lblPasahitza.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblPasahitza.setBounds(1128, 145, 148, 30);
        contentPane.add(lblPasahitza);

        chk_admin = new JCheckBox();
        chk_admin.setBounds(912, 451, 168, 35);
        chk_admin.setSelected(bazkidea.isAdmin());
        contentPane.add(chk_admin);

        // Botón de confirmación
        okIkonoa = new JLabel("");
        okIkonoa.setBounds(1374, 535, 108, 109);
        okIkonoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        contentPane.add(okIkonoa);

        // Acción al hacer clic en "OK"
        okIkonoa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object[] aukerak = {"Bai", "Ez"};
                int konfirmazioa = JOptionPane.showOptionDialog(null, "Ziur zaude datuak egokiak direla?", "BAIEZTATU",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, aukerak, aukerak[0]);

                if (konfirmazioa == JOptionPane.YES_OPTION) {
                	String nan = txt_nan.getText();
                	String izena = txt_izena.getText();
                	String abizena = txt_abizena.getText();
                	String erabiltzailea = txt_erabiltzailea.getText();
                	String pasahitza = txt_pasahitza.getText();
                	String telefonoa = txt_telefonoa.getText();
                	String postaElektronikoa = txt_email.getText();
                	String kontuKorrontea = txt_kontuKorrontea.getText();
                	Boolean admin = chk_admin.isSelected();
                	
                    Bazkidea bazkidea = new Bazkidea(
                    		0, 
                    		nan,
                    		izena,
                    		abizena,
                    		erabiltzailea,
                    		pasahitza,
                    		telefonoa,
                    		postaElektronikoa,
                    		kontuKorrontea,
                    		admin,
                            null
                            );

                    KontrolAdmin kontrol = new KontrolAdmin();
                    kontrol.sortuBazkidea(bazkidea);

                    JOptionPane.showMessageDialog(null, "Bazkidea ondo sartu da.");
                    dispose();
                    new BazkideakPanela().setVisible(true);
                }
            }
        });
        
		JLabel lblItxi = new JLabel("");
		lblItxi.setBounds(1464, 17, 53, 59);
		lblItxi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblItxi);
		lblItxi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object[] options = { "Bai", "Ez" }; // Bai edo ez izateko aukerak ematen ditu
				int erantzuna = JOptionPane.showOptionDialog(null, "Programatik atera nahi duzu?",
						"Konfirmatu atera nahi duzun", JOptionPane.YES_NO_OPTION, +JOptionPane.QUESTION_MESSAGE, null,
						options, options[0]);
				if (erantzuna == JOptionPane.YES_OPTION) {
					dispose();
					JOptionPane.showMessageDialog(null, "Eskerrik asko aplikazioa erabiltzeagatik.", "ATERA ZARA",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
        
        lblAtzera = new JLabel("");
        lblAtzera.setBounds(24, 22, 148, 57);
        lblAtzera.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        contentPane.add(lblAtzera);
        lblAtzera.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new BazkideakPanela().setVisible(true);
            }
        });
        
        JLabel lblAdmin1 = new JLabel("Admin:");
        lblAdmin1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblAdmin1.setBounds(912, 421, 106, 20);
        contentPane.add(lblAdmin1);
        
                lblFondoa = new JLabel("");
                lblFondoa.setIcon(new ImageIcon(sartuBazkideak.class.getResource("/media/FONDOAK_ONARTU.png")));
                lblFondoa.setBounds(0, 0, 1538, 735);
                contentPane.add(lblFondoa);
    }
}

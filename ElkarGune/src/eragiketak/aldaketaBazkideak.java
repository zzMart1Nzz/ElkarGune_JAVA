package eragiketak;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;


import objetuak.Bazkidea;
import panelak.BazkideakPanela;
import kudeaketak.kudeaketaBazkidea;


import java.awt.Color;
import java.awt.Cursor;

public class aldaketaBazkideak extends JFrame {
    private JPanel contentPane;
    private static final long serialVersionUID = 1L;
    private JTextField txt_kontuKorrontea;
    private JTextField txt_nan;
    private JTextField txt_izena;
    private JTextField txt_abizena;
    private JTextField txt_telefonoa;
    private JTextField txt_email;
    private JTextField txt_erabiltzailea;
    private JTextField txt_pasahitza;
    private JCheckBox chk_admin;
    private JLabel okIkonoa;
    private JLabel lblFondoa;
    private JLabel lblID;
    private JLabel lblid;
    private JLabel lblAtzera;
    private JLabel lblAdmin;

    public aldaketaBazkideak(Bazkidea bazkidea) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(-5, 0, 1550, 770);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

     // Sarrera-kampoak
        txt_kontuKorrontea = new JTextField();
        txt_kontuKorrontea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_kontuKorrontea.setColumns(10);
        txt_kontuKorrontea.setBounds(693, 451, 168, 35);
        contentPane.add(txt_kontuKorrontea);
        txt_kontuKorrontea.setText(bazkidea.getKontuKorronteZkia());

        JLabel lblKontuKorrontea = new JLabel("Kontu Korrontea:");
        lblKontuKorrontea.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblKontuKorrontea.setBounds(693, 421, 168, 20);
        contentPane.add(lblKontuKorrontea);

        txt_nan = new JTextField();
        txt_nan.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_nan.setColumns(10);
        txt_nan.setBounds(249, 173, 168, 35);
        contentPane.add(txt_nan);
        txt_nan.setText(bazkidea.getNan());

        JLabel lblNan = new JLabel("NAN:");
        lblNan.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNan.setBounds(249, 150, 79, 20);
        contentPane.add(lblNan);

        txt_izena = new JTextField();
        txt_izena.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_izena.setColumns(10);
        txt_izena.setBounds(467, 173, 168, 35);
        contentPane.add(txt_izena);
        txt_izena.setText(bazkidea.getIzena());

        JLabel lblIzena = new JLabel("Izena:");
        lblIzena.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblIzena.setBounds(467, 150, 71, 20);
        contentPane.add(lblIzena);

        txt_abizena = new JTextField();
        txt_abizena.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_abizena.setColumns(10);
        txt_abizena.setBounds(693, 173, 168, 35);
        contentPane.add(txt_abizena);
        txt_abizena.setText(bazkidea.getAbizena());

        JLabel lblAbizena = new JLabel("Abizena:");
        lblAbizena.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblAbizena.setBounds(693, 150, 168, 20);
        contentPane.add(lblAbizena);

        txt_telefonoa = new JTextField();
        txt_telefonoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_telefonoa.setColumns(10);
        txt_telefonoa.setBounds(249, 451, 168, 35);
        contentPane.add(txt_telefonoa);
        txt_telefonoa.setText(bazkidea.getTelefonoZkia());

        JLabel lblTelefonoa = new JLabel("Telefonoa:");
        lblTelefonoa.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTelefonoa.setBounds(249, 421, 139, 20);
        contentPane.add(lblTelefonoa);

        txt_email = new JTextField();
        txt_email.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_email.setColumns(10);
        txt_email.setBounds(467, 451, 168, 35);
        contentPane.add(txt_email);
        txt_email.setText(bazkidea.getPostaElektronikoa());

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblEmail.setBounds(467, 421, 106, 20);
        contentPane.add(lblEmail);

        txt_erabiltzailea = new JTextField();
        txt_erabiltzailea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_erabiltzailea.setColumns(10);
        txt_erabiltzailea.setBounds(912, 173, 168, 35);
        contentPane.add(txt_erabiltzailea);
        txt_erabiltzailea.setText(bazkidea.getErabiltzailea());

        JLabel lblErabiltzailea = new JLabel("Erabiltzailea:");
        lblErabiltzailea.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblErabiltzailea.setBounds(912, 150, 168, 20);
        contentPane.add(lblErabiltzailea);

        txt_pasahitza = new JTextField();
        txt_pasahitza.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_pasahitza.setColumns(10);
        txt_pasahitza.setBounds(1128, 173, 168, 35);
        contentPane.add(txt_pasahitza);
        txt_pasahitza.setText(bazkidea.getPasahitza());

        JLabel lblPasahitza = new JLabel("Pasahitza:");
        lblPasahitza.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblPasahitza.setBounds(1128, 145, 148, 30);
        contentPane.add(lblPasahitza);

        chk_admin = new JCheckBox();
        chk_admin.setFont(new Font("Tahoma", Font.PLAIN, 18));
        chk_admin.setBounds(912, 451, 168, 35);
        chk_admin.setSelected(bazkidea.isAdmin()); // Cargar el valor booleano del objeto
        contentPane.add(chk_admin);


        // ID Label (bistaratu bakarrik)
        lblID = new JLabel("ID:");
        lblID.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblID.setBounds(249, 100, 35, 35);
        contentPane.add(lblID);

        lblid = new JLabel("");
        lblid.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblid.setBounds(294, 100, 35, 35);
        contentPane.add(lblid);
        lblid.setText(String.valueOf(bazkidea.getIdBazkidea()));

        // Aldaketak baieztatzeko ikonoa
        okIkonoa = new JLabel("");
        okIkonoa.setIcon(null);
        okIkonoa.setBounds(1374, 535, 108, 109);
        okIkonoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        contentPane.add(okIkonoa);

        // "OK" ikonoa klik egitean egindako aldaketak gordetzeko
        okIkonoa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object[] aukerak = {"Bai", "Ez"};
                int konfirmazioa = JOptionPane.showOptionDialog(null, "Ziur zaude aldaketak gorde nahi dituzula?", "BAIEZTATU",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, aukerak, aukerak[0]);

                if (konfirmazioa == JOptionPane.YES_OPTION) {
                    // Testu-kampoetatik balioak lortu
                    String nan = txt_nan.getText();
                    String izena = txt_izena.getText();
                    String abizena = txt_abizena.getText();
                    String erabiltzailea = txt_erabiltzailea.getText();
                    String pasahitza = txt_pasahitza.getText();
                    String telefonoZkia = txt_telefonoa.getText();
                    String postaElektronikoa = txt_email.getText();
                    String kontuKorronteZkia = txt_kontuKorrontea.getText();
                    boolean admin = chk_admin.isSelected();



                    // Mezua eguneratzea
                    bazkidea.setNan(nan);
                    bazkidea.setIzena(izena);
                    bazkidea.setAbizena(abizena);
                    bazkidea.setErabiltzailea(erabiltzailea);
                    bazkidea.setPasahitza(pasahitza);
                    bazkidea.setTelefonoZkia(telefonoZkia);
                    bazkidea.setPostaElektronikoa(postaElektronikoa);
                    bazkidea.setKontuKorronteZkia(kontuKorronteZkia);
                    bazkidea.setAdmin(admin);



                    // Hornitzailea datu-basean eguneratzea
                    kudeaketaBazkidea kudeaketa = new kudeaketaBazkidea();
                    kudeaketa.eguneratuBazkidea(bazkidea);

                    // Mezu bat erakustea
                    JOptionPane.showMessageDialog(null, "Aldaketak ondo gorde dira.");
                    dispose();
                    BazkideakPanela bp = new BazkideakPanela();
                    bp.setVisible(true);
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
        		 // Botoi hau leihoa itxi edo beste ekintza bat egin behar du.
                dispose();
                BazkideakPanela bp = new BazkideakPanela();
                bp.setVisible(true);
        	}
        });
                
                lblAdmin = new JLabel("Admin");
                lblAdmin.setFont(new Font("Tahoma", Font.BOLD, 20));
                lblAdmin.setBounds(912, 423, 139, 17);
                contentPane.add(lblAdmin);
                
                        // Interfazean fondo baten gehitzea
                        lblFondoa = new JLabel("");
                        lblFondoa.setIcon(new ImageIcon(aldaketaBazkideak.class.getResource("/media/FONDOAK_ONARTU.png")));
                        lblFondoa.setBounds(0, 0, 1538, 735);
                        contentPane.add(lblFondoa);
    }
}

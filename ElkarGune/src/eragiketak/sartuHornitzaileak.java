package eragiketak;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;


import objetuak.Hornitzailea;
import panelak.HornitzaileakPanela;
import kudeaketak.kudeaketaHornitzailea;


import java.awt.Color;
import java.awt.Cursor;

public class sartuHornitzaileak extends JFrame {
    private JPanel contentPane;
    private static final long serialVersionUID = 1L;
    private JTextField txt_postaElektronikoa;
    private JTextField txt_nif;
    private JTextField txt_izena;
    private JTextField txt_helbidea;
    private JTextField txt_telefonoa;
    private JLabel okIkonoa;
    private JLabel lblFondoa;
    private JLabel lblAtzera;

    public sartuHornitzaileak() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(-5, 0, 1550, 770);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

     // Sarrera-kampoak
        txt_postaElektronikoa = new JTextField();
        txt_postaElektronikoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_postaElektronikoa.setColumns(10);
        txt_postaElektronikoa.setBounds(1128, 173, 168, 35);
        contentPane.add(txt_postaElektronikoa);
 

        JLabel lblPostaElektronikoa = new JLabel("Email:");
        lblPostaElektronikoa.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblPostaElektronikoa.setBounds(1128, 150, 168, 20);
        contentPane.add(lblPostaElektronikoa);

        txt_nif = new JTextField();
        txt_nif.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_nif.setColumns(10);
        txt_nif.setBounds(467, 173, 168, 35);
        contentPane.add(txt_nif);


        JLabel lblNif = new JLabel("NIF:");
        lblNif.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNif.setBounds(467, 150, 79, 20);
        contentPane.add(lblNif);

        txt_izena = new JTextField();
        txt_izena.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_izena.setColumns(10);
        txt_izena.setBounds(249, 173, 168, 35);
        contentPane.add(txt_izena);


        JLabel lblIzena = new JLabel("Izena:");
        lblIzena.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblIzena.setBounds(249, 150, 71, 20);
        contentPane.add(lblIzena);

        txt_helbidea = new JTextField();
        txt_helbidea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_helbidea.setColumns(10);
        txt_helbidea.setBounds(693, 173, 168, 35);
        contentPane.add(txt_helbidea);


        JLabel lblHelbidea = new JLabel("Helbidea:");
        lblHelbidea.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblHelbidea.setBounds(693, 150, 168, 20);
        contentPane.add(lblHelbidea);

        txt_telefonoa = new JTextField();
        txt_telefonoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_telefonoa.setColumns(10);
        txt_telefonoa.setBounds(912, 173, 168, 35);
        contentPane.add(txt_telefonoa);

        
        JLabel lblTelefonoa = new JLabel("Telefonoa:");
        lblTelefonoa.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTelefonoa.setBounds(912, 150, 139, 20);
        contentPane.add(lblTelefonoa);


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
                int konfirmazioa = JOptionPane.showOptionDialog(null, "Ziur zaude datuak ondo sartu direla?", "BAIEZTATU",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, aukerak, aukerak[0]);

                if (konfirmazioa == JOptionPane.YES_OPTION) {
                    // Testu-kampoetatik balioak lortu


                    // Mezua eguneratzea
                    Hornitzailea hornitzailea = new Hornitzailea(
                    		0,
                    		txt_nif.getText(),
                    		txt_izena.getText(),
                    		txt_helbidea.getText(),
                    		txt_telefonoa.getText(),
                    		txt_postaElektronikoa.getText()
                    		);



                    // Hornitzailea datu-basean eguneratzea
                    kudeaketaHornitzailea kudeaketa = new kudeaketaHornitzailea();
                    kudeaketa.sortuHornitzailea(hornitzailea);

                    // Mezu bat erakustea
                    JOptionPane.showMessageDialog(null, "Hornitzailea ondo sartu da.");
                    dispose();
                    HornitzaileakPanela hp = new HornitzaileakPanela();
                    hp.setVisible(true);
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
                HornitzaileakPanela hp = new HornitzaileakPanela();
                hp.setVisible(true);
        	}
        });
                
                        // Interfazean fondo baten gehitzea
                        lblFondoa = new JLabel("");
                        lblFondoa.setIcon(new ImageIcon(sartuHornitzaileak.class.getResource("/media/FONDOAK_ONARTU.png")));
                        lblFondoa.setBounds(0, 0, 1538, 735);
                        contentPane.add(lblFondoa);
    }
}

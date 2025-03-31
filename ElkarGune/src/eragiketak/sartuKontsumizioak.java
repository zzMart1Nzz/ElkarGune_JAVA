package eragiketak;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;


import objetuak.Kontsumizioak;
import panelak.KontsumizioakPanela;
import kudeaketak.kudeaketaKontsumizioak;


import java.awt.Color;
import java.awt.Cursor;

public class sartuKontsumizioak extends JFrame {
    private JPanel contentPane;
    private static final long serialVersionUID = 1L;
    private JTextField txt_erreserbaZkia;
    private JTextField txt_idFaktura;
    private JTextField txt_idProduktua;
    private JTextField txt_kopurua;
    private JTextField txt_prezioa;
    private JLabel okIkonoa;
    private JLabel lblFondoa;
    private JLabel lblAtzera;

    public sartuKontsumizioak() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(-5, 0, 1550, 770);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txt_erreserbaZkia = new JTextField();
        txt_erreserbaZkia.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_erreserbaZkia.setColumns(10);
        txt_erreserbaZkia.setBounds(249, 173, 168, 35);
        contentPane.add(txt_erreserbaZkia);


        JLabel lblErreserbaZkia = new JLabel("ErreserbaZkia.:");
        lblErreserbaZkia.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblErreserbaZkia.setBounds(249, 150, 168, 20);
        contentPane.add(lblErreserbaZkia);



        txt_idFaktura = new JTextField();
        txt_idFaktura.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_idFaktura.setColumns(10);
        txt_idFaktura.setBounds(467, 173, 168, 35);
        contentPane.add(txt_idFaktura);


        JLabel lblIdFaktura = new JLabel("ID Faktura:");
        lblIdFaktura.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblIdFaktura.setBounds(467, 150, 168, 20);
        contentPane.add(lblIdFaktura);

        txt_idProduktua = new JTextField();
        txt_idProduktua.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_idProduktua.setColumns(10);
        txt_idProduktua.setBounds(693, 173, 168, 35);
        contentPane.add(txt_idProduktua);


        JLabel lblIdProduktua = new JLabel("ID Produktua:");
        lblIdProduktua.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblIdProduktua.setBounds(693, 150, 168, 20);
        contentPane.add(lblIdProduktua);

        txt_kopurua = new JTextField();
        txt_kopurua.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_kopurua.setColumns(10);
        txt_kopurua.setBounds(912, 173, 168, 35);
        contentPane.add(txt_kopurua);


        JLabel lblKopurua = new JLabel("Kopurua:");
        lblKopurua.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblKopurua.setBounds(912, 150, 168, 20);
        contentPane.add(lblKopurua);

        txt_prezioa = new JTextField();
        txt_prezioa.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_prezioa.setColumns(10);
        txt_prezioa.setBounds(1128, 173, 168, 35);
        contentPane.add(txt_prezioa);


        JLabel lblPrezioa = new JLabel("Prezioa:");
        lblPrezioa.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblPrezioa.setBounds(1128, 145, 148, 30);
        contentPane.add(lblPrezioa);


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
                int konfirmazioa = JOptionPane.showOptionDialog(null, "Ziur zaude datuak ondo sartuta daudela?", "BAIEZTATU",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, aukerak, aukerak[0]);

                if (konfirmazioa == JOptionPane.YES_OPTION) {




                    // Mezua eguneratzea
                	Kontsumizioak kontsumizioak = new Kontsumizioak(
                		    0,
                		    Integer.parseInt(txt_erreserbaZkia.getText()),  
                		    Integer.parseInt(txt_idFaktura.getText()),  
                		    Integer.parseInt(txt_idProduktua.getText()),  
                		    Integer.parseInt(txt_kopurua.getText()),  
                		    Double.parseDouble(txt_prezioa.getText()),
                		    0
                		);




                    // Hornitzailea datu-basean eguneratzea
                    kudeaketaKontsumizioak kudeaketa = new kudeaketaKontsumizioak();
                    kudeaketa.sortuKontsumizioa(kontsumizioak);

                    // Mezu bat erakustea
                    JOptionPane.showMessageDialog(null, "Kontsumizioa ondo sartu da.");
                    dispose();
                    KontsumizioakPanela kp = new KontsumizioakPanela();
                    kp.setVisible(true);
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
                KontsumizioakPanela kp = new KontsumizioakPanela();
                kp.setVisible(true);
        	}
        });
                
                        // Interfazean fondo baten gehitzea
                        lblFondoa = new JLabel("");
                        lblFondoa.setIcon(new ImageIcon(sartuKontsumizioak.class.getResource("/media/FONDOAK_ONARTU.png")));
                        lblFondoa.setBounds(0, 0, 1538, 735);
                        contentPane.add(lblFondoa);
    }
}

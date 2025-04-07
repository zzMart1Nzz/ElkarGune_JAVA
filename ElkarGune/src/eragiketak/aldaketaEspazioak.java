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



import objetuak.Espazioa;
import panelak.EspazioakPanela;
import kudeaketak.kudeaketaEspazioa;


import java.awt.Color;
import java.awt.Cursor;

public class aldaketaEspazioak extends JFrame {
    private JPanel contentPane;
    private static final long serialVersionUID = 1L;
    private JTextField txt_izena;
    private JTextField txt_Gaitasuna;
    private JCheckBox chk_egoera;
    private JLabel okIkonoa;
    private JLabel lblFondoa;
    private JLabel lblID;
    private JLabel lblid;
    private JLabel lblAtzera;

    public aldaketaEspazioak(Espazioa espazioa) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(-5, 0, 1550, 770);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txt_izena = new JTextField();
        txt_izena.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_izena.setColumns(10);
        txt_izena.setBounds(249, 173, 168, 35);
        contentPane.add(txt_izena);
        txt_izena.setText(espazioa.getIzena());

        JLabel lblIzena = new JLabel("Izena:");
        lblIzena.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblIzena.setBounds(249, 150, 168, 20);
        contentPane.add(lblIzena);

        txt_Gaitasuna = new JTextField();
        txt_Gaitasuna.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_Gaitasuna.setColumns(10);
        txt_Gaitasuna.setBounds(467, 173, 168, 35);
        contentPane.add(txt_Gaitasuna);
        txt_Gaitasuna.setText(String.valueOf(espazioa.getGaitasuna()));

        JLabel lblGaitasuna = new JLabel("Gaitasuna:");
        lblGaitasuna.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblGaitasuna.setBounds(467, 150, 168, 20);
        contentPane.add(lblGaitasuna);

        chk_egoera = new JCheckBox();
        chk_egoera.setFont(new Font("Tahoma", Font.PLAIN, 18));
        chk_egoera.setBounds(693, 173, 168, 35);
        chk_egoera.setSelected(espazioa.isEgoera()); // Cargar el valor booleano del objeto
        contentPane.add(chk_egoera);

        JLabel lblEgoera = new JLabel("Egoera:");
        lblEgoera.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblEgoera.setBounds(693, 150, 168, 20);
        contentPane.add(lblEgoera);


        // ID Label (bistaratu bakarrik)
        lblID = new JLabel("ID:");
        lblID.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblID.setBounds(249, 100, 35, 35);
        contentPane.add(lblID);

        lblid = new JLabel("");
        lblid.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblid.setBounds(294, 100, 35, 35);
        contentPane.add(lblid);
        lblid.setText(String.valueOf(espazioa.getIdEspazioa()));

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
                int konfirmazioa = JOptionPane.showOptionDialog(null, "Ziur zaude aldaketak gordetu nahi dituzula?", "BAIEZTATU",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, aukerak, aukerak[0]);

                if (konfirmazioa == JOptionPane.YES_OPTION) {
                    // Testu-kampoetatik balioak lortu
                    String izena =txt_izena.getText();
                    int gaitasuna = Integer.parseInt(txt_Gaitasuna.getText());
                    boolean egoera = chk_egoera.isSelected();
                  



                    // Mezua eguneratzea
                    espazioa.setIzena(izena);
                    espazioa.setGaitasuna(gaitasuna);
                    espazioa.setEgoera(egoera);



                    // Hornitzailea datu-basean eguneratzea
                    kudeaketaEspazioa kudeaketa = new kudeaketaEspazioa();
                    kudeaketa.eguneratuEspazioa(espazioa);

                    // Mezu bat erakustea
                    JOptionPane.showMessageDialog(null, "Aldaketak ondo gorde dira.");
                    dispose();
                    EspazioakPanela ep = new EspazioakPanela();
                    ep.setVisible(true);
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
                EspazioakPanela ep = new EspazioakPanela();
                ep.setVisible(true);
        	}
        });
                
                        // Interfazean fondo baten gehitzea
                        lblFondoa = new JLabel("");
                        lblFondoa.setIcon(new ImageIcon(aldaketaEspazioak.class.getResource("/media/FONDOAK_ONARTU.png")));
                        lblFondoa.setBounds(0, 0, 1538, 735);
                        contentPane.add(lblFondoa);
    }
}


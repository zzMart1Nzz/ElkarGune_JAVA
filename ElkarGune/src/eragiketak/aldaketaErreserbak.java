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


import objetuak.Erreserba;
import panelak.ErreserbakPanela;
import kudeaketak.kudeaketaErreserba;


import java.awt.Color;
import java.awt.Cursor;

public class aldaketaErreserbak extends JFrame {
    private JPanel contentPane;
    private static final long serialVersionUID = 1L;
    private JTextField txt_erreserbaZkia;
    private JTextField txt_idBazkidea;
    private JCheckBox chk_mota;
    private JLabel okIkonoa;
    private JLabel lblFondoa;
    private JLabel lblID;
    private JLabel lblid;
    private JLabel lblAtzera;

    public aldaketaErreserbak(Erreserba erreserba) {
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
        txt_erreserbaZkia.setText(String.valueOf(erreserba.getErreserbaZkia()));

        JLabel lblErreserbaZkia = new JLabel("ErreserbaZkia:");
        lblErreserbaZkia.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblErreserbaZkia.setBounds(249, 150, 168, 20);
        contentPane.add(lblErreserbaZkia);

        txt_idBazkidea = new JTextField();
        txt_idBazkidea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_idBazkidea.setColumns(10);
        txt_idBazkidea.setBounds(467, 173, 168, 35);
        contentPane.add(txt_idBazkidea);
        txt_idBazkidea.setText(String.valueOf(erreserba.getIdBazkidea()));

        JLabel lblIdBazkidea = new JLabel("ID Bazkidea:");
        lblIdBazkidea.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblIdBazkidea.setBounds(467, 150, 148, 20);
        contentPane.add(lblIdBazkidea);

        chk_mota = new JCheckBox();
        chk_mota.setFont(new Font("Tahoma", Font.PLAIN, 18));
        chk_mota.setBounds(693, 173, 168, 35);
        chk_mota.setSelected(erreserba.isMota()); // Cargar el valor booleano del objeto
        contentPane.add(chk_mota);

        JLabel lblMota = new JLabel("Mota:");
        lblMota.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblMota.setBounds(693, 150, 168, 20);
        contentPane.add(lblMota);


        // ID Label (bistaratu bakarrik)
        lblID = new JLabel("ID:");
        lblID.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblID.setBounds(249, 100, 35, 35);
        contentPane.add(lblID);

        lblid = new JLabel("");
        lblid.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblid.setBounds(294, 100, 35, 35);
        contentPane.add(lblid);
        lblid.setText(String.valueOf(erreserba.getIdErreserba()));

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
                    int erreserbaZkia = Integer.parseInt(txt_erreserbaZkia.getText());
                    int idBazkidea = Integer.parseInt(txt_idBazkidea.getText());
                    boolean mota = chk_mota.isSelected();
                  



                    // Mezua eguneratzea
                    erreserba.setErreserbaZkia(erreserbaZkia);
                    erreserba.setIdBazkidea(idBazkidea);
                    erreserba.setMota(mota);



                    // Hornitzailea datu-basean eguneratzea
                    kudeaketaErreserba kudeaketa = new kudeaketaErreserba();
                    kudeaketa.eguneratuErreserba(erreserba);

                    // Mezu bat erakustea
                    JOptionPane.showMessageDialog(null, "Aldaketak ondo gorde dira.");
                    dispose();
                    ErreserbakPanela ep = new ErreserbakPanela();
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
                ErreserbakPanela ep = new ErreserbakPanela();
                ep.setVisible(true);
        	}
        });
                
                        // Interfazean fondo baten gehitzea
                        lblFondoa = new JLabel("");
                        lblFondoa.setIcon(new ImageIcon(aldaketaErreserbak.class.getResource("/media/FONDOAK_ONARTU.png")));
                        lblFondoa.setBounds(0, 0, 1538, 735);
                        contentPane.add(lblFondoa);
    }
}


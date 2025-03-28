package eragiketak;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import objetuak.Abisuak;
import panelak.AbisuakPanela;
import kudeaketak.kudeaketaAbisuak;
import java.awt.Color;
import java.awt.Cursor;

public class aldaketaAbisuak extends JFrame {
    private JPanel contentPane;
    private JTextField txt_mezua;
    private JLabel lblFondoa;
    private JLabel okIkonoa;
    private JLabel lblID;
    private JLabel lblid;
    private JLabel lblAtzera;

    public aldaketaAbisuak(Abisuak abisuak) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(-5, 0, 1550, 770);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Testu-kampoak hornitzailearen datuekin betetzea
        JLabel lblMezua = new JLabel("Mezua:");
        lblMezua.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblMezua.setBounds(270, 161, 80, 25);
        contentPane.add(lblMezua);

        txt_mezua = new JTextField();
        txt_mezua.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txt_mezua.setBounds(270, 197, 995, 320);
        contentPane.add(txt_mezua);
        txt_mezua.setText(abisuak.getMezua());

        // ID Label (bistaratu bakarrik)
        lblID = new JLabel("ID:");
        lblID.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblID.setBounds(249, 115, 35, 35);
        contentPane.add(lblID);

        lblid = new JLabel("");
        lblid.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblid.setBounds(294, 115, 35, 35);
        contentPane.add(lblid);
        lblid.setText(String.valueOf(abisuak.getIdAbisuak()));

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
                    String mezua = txt_mezua.getText();


                    // Hornitzailea eguneratzea
                    abisuak.setMezua(mezua);


                    // Hornitzailea datu-basean eguneratzea
                    kudeaketaAbisuak kudeaketa = new kudeaketaAbisuak();
                    kudeaketa.eguneratuAbisua(abisuak);

                    // Mezu bat erakustea
                    JOptionPane.showMessageDialog(null, "Aldaketak ondo gorde dira.");
                    dispose();
                    AbisuakPanela ap = new AbisuakPanela();
                    ap.setVisible(true);
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
                AbisuakPanela ab = new AbisuakPanela();
                ab.setVisible(true);
        	}
        });
        
                // Interfazean fondo baten gehitzea
                lblFondoa = new JLabel("");
                lblFondoa.setIcon(new ImageIcon(aldaketaAbisuak.class.getResource("/media/FONDOAK_ONARTU.png")));
                lblFondoa.setBounds(0, 0, 1538, 735);
                contentPane.add(lblFondoa);
    }
}
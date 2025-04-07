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


import objetuak.Mezuak;
import panelak.MezuakPanela;
import kontrolak.KontrolMezuak;


import java.awt.Color;
import java.awt.Cursor;

public class sartuMezuak extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_mezua;
	private JLabel lblFondoa;
	private JLabel okIkonoa;
	private JLabel lblAtzera;
	private JTextField txt_idBazkidea;

	public sartuMezuak() {
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
				Object[] options = { "Bai", "Ez" };
				int erantzuna = JOptionPane.showOptionDialog(null, "Datuak ondo sartuta daude?", "KONFIRMATU",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (erantzuna == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Mezua ondo sartu da.", "SARTU DA",
							JOptionPane.INFORMATION_MESSAGE);

					// 1. Formularioan dauden datuak jaso
					int idBazkidea = Integer.parseInt(txt_idBazkidea.getText());
					String mezua = txt_mezua.getText();
					

					// Mezua objektua sortu
					Mezuak mezuak = new Mezuak(
							0,
							idBazkidea,
							mezua, 
							null

					);

					// kudeaketaLangileak klasea erabili langilea datu-basean sartzeko
					KontrolMezuak kontrola = new KontrolMezuak();
					kontrola.sortuMezuak(mezuak);

					// Formularioa garbitzea (hautazkoa)
					garbitu();
					dispose();
					MezuakPanela mp = new MezuakPanela();
					mp.setVisible(true);
				} else {
				}
			}

			private void garbitu() {
				txt_mezua.setText("");

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
				MezuakPanela mp = new MezuakPanela();
				mp.setVisible(true);
			}
		});
		
		txt_idBazkidea = new JTextField();
		txt_idBazkidea.setFont(new Font("Tahoma", Font.BOLD, 20));
		txt_idBazkidea.setBounds(270, 130, 106, 31);
		contentPane.add(txt_idBazkidea);
		txt_idBazkidea.setColumns(10);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblId.setBounds(270, 104, 43, 20);
		contentPane.add(lblId);
		
				// Interfazean fondo baten gehitzea
				lblFondoa = new JLabel("");
				lblFondoa.setIcon(new ImageIcon(sartuMezuak.class.getResource("/media/FONDOAK_ONARTU.png")));
				lblFondoa.setBounds(0, 0, 1538, 735);
				contentPane.add(lblFondoa);
	}
}

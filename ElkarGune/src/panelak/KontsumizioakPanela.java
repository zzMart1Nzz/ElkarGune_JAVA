package panelak;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import eragiketak.aldaketaKontsumizioak;
import eragiketak.sartuKontsumizioak;
import kudeaketak.kudeaketaKontsumizioak;
import objetuak.Kontsumizioak;
import taulak.KontsumizioakTaula;



public class KontsumizioakPanela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private kudeaketaKontsumizioak dao;
	private JTextField txt_id;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				KontsumizioakPanela frame = new KontsumizioakPanela();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public KontsumizioakPanela() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(-5, 0, 1550, 770);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		dao = new kudeaketaKontsumizioak();
		List<Kontsumizioak> lista = dao.lortuKontsumizioak();
		KontsumizioakTaula model = new KontsumizioakTaula(lista);

		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(175, 101, 1185, 527);
		contentPane.add(scrollPane);



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

		JLabel birkargatuIko = new JLabel();
		// No se especifica el icono
		birkargatuIko.setBounds(1097, 22, 57, 50);
		birkargatuIko.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		birkargatuIko.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				actualizarTabla();
			}
		});
		contentPane.add(birkargatuIko);

		txt_id = new JTextField();
		txt_id.setBounds(450, 674, 637, 20);
		contentPane.add(txt_id);
		txt_id.setColumns(10);

		JLabel ezabatuIkonoa = new JLabel();
		// No se especifica el icono
		ezabatuIkonoa.setBounds(1097, 655, 55, 57);
		ezabatuIkonoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ezabatuIkonoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int idKontsumizioa = Integer.parseInt(txt_id.getText().trim());
					Optional<Kontsumizioak> kontsumizioa = lortuKontsumizioakIdarekin(idKontsumizioa);
					if (kontsumizioa.isPresent()) {
						Object[] options = { "Bai", "Ez" }; // Bai edo ez izateko aukerak ematen ditu
						int erantzuna = JOptionPane.showOptionDialog(null, "Kontsumizioa ezabatu nahi duzu?",
								"EZABATU", JOptionPane.YES_NO_OPTION, +JOptionPane.QUESTION_MESSAGE, null,
								options, options[0]);
						if (erantzuna == JOptionPane.YES_OPTION) {
							ezabatuKontsumizioa(idKontsumizioa);
							actualizarTabla();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Kontsumizioa ez da aurkitu ID honekin: " + idKontsumizioa,
								"Errorea", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "ID-a zenbaki baliodun bat izan behar du.", "ERROREA",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		contentPane.add(ezabatuIkonoa);

		JLabel lblAldatu = new JLabel("");
		lblAldatu.setBounds(387, 662, 53, 50);
		lblAldatu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblAldatu);
		lblAldatu.addMouseListener(new MouseAdapter () {
			@Override
            public void mouseClicked(MouseEvent e) {
                // ID-a hartu
                try {
                    int idKontsumizioa = Integer.parseInt(txt_id.getText());
                    // Mezua bilatu ID-a erabiliz
                    Optional<Kontsumizioak> kontsumizioakOpt = lortuKontsumizioakIdarekin(idKontsumizioa);
                    
                    if (kontsumizioakOpt.isPresent()) {
                        Kontsumizioak kontsumizioa = kontsumizioakOpt.get();
                        // Hornitzailea aurkitu bada, datuak pasatzea eta AldaketaHornitzaileak erakustea
                        dispose();
                        aldaketaKontsumizioak ak = new aldaketaKontsumizioak(kontsumizioa);
                        ak.setVisible(true);  // Erakutsi
                    } else {
                        // Hornitzailea ez bada aurkitu, errore-mezua erakutsi
                        JOptionPane.showMessageDialog(null, "Kontsumizioa ez da aurkitu ID honekin: " + idKontsumizioa, "Errorea", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID-a zenbaki baliodun bat izan behar du.", "Errorea", JOptionPane.ERROR_MESSAGE);
                }
            }
	});
		
		JLabel lblSartu = new JLabel("");
		lblSartu.setIcon(null);
		lblSartu.setBounds(387, 17, 53, 59);
		lblSartu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblSartu);
		lblSartu.addMouseListener(new MouseAdapter () {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				sartuKontsumizioak sb = new sartuKontsumizioak();
				sb.setVisible(true);
			}
		});
				
				JLabel lblAtzera = new JLabel("");
				lblAtzera.setBounds(26, 27, 147, 49);
				lblAtzera.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				contentPane.add(lblAtzera);
				lblAtzera.addMouseListener(new MouseAdapter () {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
						Printzipala p = new Printzipala();
						p.setVisible(true);
					}
				});
				
						JLabel lblFondoa = new JLabel("");
						lblFondoa.setIcon(new ImageIcon(KontsumizioakPanela.class.getResource("/media/FONDOAK_OSOA.png")));
						lblFondoa.setBounds(0, 0, 1538, 735);
						contentPane.add(lblFondoa);

	}

	private void actualizarTabla() {
		List<Kontsumizioak> lista = dao.lortuKontsumizioak();
		KontsumizioakTaula model = new KontsumizioakTaula(lista);
		if (table == null) {
			table = new JTable(model);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(50, 73, 900, 300);
			contentPane.add(scrollPane);
		} else {
			table.setModel(model);
		}
	}

	private Optional<Kontsumizioak> lortuKontsumizioakIdarekin(int idKontsumizioa) {
		return dao.lortuKontsumizioak().stream().filter(kontsumizioa -> kontsumizioa.getIdKontsumizioa() == idKontsumizioa).findFirst();
	}

	private void ezabatuKontsumizioa(int idKontsumizioa) {
		boolean isDeleted = dao.ezabatuKontsumizioa(idKontsumizioa);
		if (!isDeleted) {
			txt_id.setText("");
			JOptionPane.showMessageDialog(null, "Errore bat gertatu da. Kontsumizioa ezin izan da ezabatu.", "Errorea",
					JOptionPane.ERROR_MESSAGE);
		} else {
			txt_id.setText("");
			JOptionPane.showMessageDialog(null, "Kontsumizioa ezabatua izan da.");
		}
	}
}



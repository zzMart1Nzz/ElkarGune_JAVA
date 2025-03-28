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

import eragiketak.sartuBazkideak;
import eragiketak.aldaketaBazkideak;
import kudeaketak.kudeaketaBazkidea;
import objetuak.Bazkidea;
import taulak.BazkideakTaula;



public class BazkideakPanela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private kudeaketaBazkidea dao;
	private JTextField txt_id;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				BazkideakPanela frame = new BazkideakPanela();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public BazkideakPanela() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(-5, 0, 1550, 770);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		dao = new kudeaketaBazkidea();
		List<Bazkidea> lista = dao.lortuBazkidea();
		BazkideakTaula model = new BazkideakTaula(lista);

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
					int idBazkidea = Integer.parseInt(txt_id.getText().trim());
					Optional<Bazkidea> bazkidea = lortuBazkideaIdarekin(idBazkidea);
					if (bazkidea.isPresent()) {
						Object[] options = { "Bai", "Ez" }; // Bai edo ez izateko aukerak ematen ditu
						int erantzuna = JOptionPane.showOptionDialog(null, "Bazkidea ezabatu nahi duzu?",
								"EZABATU", JOptionPane.YES_NO_OPTION, +JOptionPane.QUESTION_MESSAGE, null,
								options, options[0]);
						if (erantzuna == JOptionPane.YES_OPTION) {
							ezabatuBazkidea(idBazkidea);
							actualizarTabla();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Bazkidea ez da aurkitu ID honekin: " + idBazkidea,
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
                    int idBazkidea = Integer.parseInt(txt_id.getText());
                    // Mezua bilatu ID-a erabiliz
                    Optional<Bazkidea> bazkideakOpt = lortuBazkideaIdarekin(idBazkidea);
                    
                    if (bazkideakOpt.isPresent()) {
                        Bazkidea bazkidea = bazkideakOpt.get();
                        // Hornitzailea aurkitu bada, datuak pasatzea eta AldaketaHornitzaileak erakustea
                        dispose();
                        aldaketaBazkideak ab = new aldaketaBazkideak(bazkidea);
                        ab.setVisible(true);  // Erakutsi
                    } else {
                        // Hornitzailea ez bada aurkitu, errore-mezua erakutsi
                        JOptionPane.showMessageDialog(null, "Bazkidea ez da aurkitu ID honekin: " + idBazkidea, "Errorea", JOptionPane.ERROR_MESSAGE);
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
				sartuBazkideak sb = new sartuBazkideak();
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
						lblFondoa.setIcon(new ImageIcon(BazkideakPanela.class.getResource("/media/FONDOAK_OSOA.png")));
						lblFondoa.setBounds(0, 0, 1538, 735);
						contentPane.add(lblFondoa);

	}

	private void actualizarTabla() {
		List<Bazkidea> lista = dao.lortuBazkidea();
		BazkideakTaula model = new BazkideakTaula(lista);
		if (table == null) {
			table = new JTable(model);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(50, 73, 900, 300);
			contentPane.add(scrollPane);
		} else {
			table.setModel(model);
		}
	}

	private Optional<Bazkidea> lortuBazkideaIdarekin(int idBazkidea) {
		return dao.lortuBazkidea().stream().filter(bazkidea -> bazkidea.getIdBazkidea() == idBazkidea).findFirst();
	}

	private void ezabatuBazkidea(int idBazkidea) {
		boolean isDeleted = dao.ezabatuBazkidea(idBazkidea);
		if (!isDeleted) {
			txt_id.setText("");
			JOptionPane.showMessageDialog(null, "Errore bat gertatu da. Bazkidea ezin izan da ezabatu.", "Errorea",
					JOptionPane.ERROR_MESSAGE);
		} else {
			txt_id.setText("");
			JOptionPane.showMessageDialog(null, "Bazkidea ezabatua izan da.");
		}
	}
}



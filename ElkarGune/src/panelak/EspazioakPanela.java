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


import eragiketak.aldaketaEspazioak;
import kudeaketak.kudeaketaEspazioa;
import objetuak.Espazioa;
import taulak.EspazioakTaula;



public class EspazioakPanela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private kudeaketaEspazioa dao;
	private JTextField txt_id;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				EspazioakPanela frame = new EspazioakPanela();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public EspazioakPanela() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(-5, 0, 1550, 770);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		dao = new kudeaketaEspazioa();
		List<Espazioa> lista = dao.lortuEspazioa();
		EspazioakTaula model = new EspazioakTaula(lista);

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

		JLabel lblAldatu = new JLabel("");
		lblAldatu.setBounds(387, 662, 53, 50);
		lblAldatu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblAldatu);
		lblAldatu.addMouseListener(new MouseAdapter () {
			@Override
            public void mouseClicked(MouseEvent e) {
                // ID-a hartu
                try {
                    int idEspazioa = Integer.parseInt(txt_id.getText());
                    // Mezua bilatu ID-a erabiliz
                    Optional<Espazioa> espazioakOpt = lortuEspazioaIdarekin(idEspazioa);
                    
                    if (espazioakOpt.isPresent()) {
                        Espazioa espazioa = espazioakOpt.get();
                        // Hornitzailea aurkitu bada, datuak pasatzea eta AldaketaHornitzaileak erakustea
                        dispose();
                        aldaketaEspazioak ae = new aldaketaEspazioak(espazioa);
                        ae.setVisible(true);  // Erakutsi
                    } else {
                        // Hornitzailea ez bada aurkitu, errore-mezua erakutsi
                        JOptionPane.showMessageDialog(null, "Espazioa ez da aurkitu ID honekin: " + idEspazioa, "Errorea", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID-a zenbaki baliodun bat izan behar du.", "Errorea", JOptionPane.ERROR_MESSAGE);
                }
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
						lblFondoa.setIcon(new ImageIcon(EspazioakPanela.class.getResource("/media/FONDOAK_soloE.png")));
						lblFondoa.setBounds(0, 0, 1538, 735);
						contentPane.add(lblFondoa);

	}

	private void actualizarTabla() {
		List<Espazioa> lista = dao.lortuEspazioa();
		EspazioakTaula model = new EspazioakTaula(lista);
		if (table == null) {
			table = new JTable(model);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(50, 73, 900, 300);
			contentPane.add(scrollPane);
		} else {
			table.setModel(model);
		}
	}

	private Optional<Espazioa> lortuEspazioaIdarekin(int idEspazioa) {
		return dao.lortuEspazioa().stream().filter(espazioa -> espazioa.getIdEspazioa() == idEspazioa).findFirst();
	}

	/*private void ezabatuEspazioa(int idEspazioa) {
		boolean isDeleted = dao.ezabatuEspazioa(idEspazioa);
		if (!isDeleted) {
			txt_id.setText("");
			JOptionPane.showMessageDialog(null, "Errore bat gertatu da. Espazioa ezin izan da ezabatu.", "Errorea",
					JOptionPane.ERROR_MESSAGE);
		} else {
			txt_id.setText("");
			JOptionPane.showMessageDialog(null, "Espazioa ezabatua izan da.");
		}
	}*/
}


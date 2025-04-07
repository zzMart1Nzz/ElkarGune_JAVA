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

import eragiketak.sartuProduktuak;
import eragiketak.aldaketaProduktuak;
import kudeaketak.kudeaketaProduktuak;
import objetuak.Produktua;
import taulak.ProduktuakTaula;



public class BodegaPanela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private kudeaketaProduktuak dao;
	private JTextField txt_id;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				BodegaPanela frame = new BodegaPanela();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public BodegaPanela() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(-5, 0, 1550, 770);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		dao = new kudeaketaProduktuak();
		List<Produktua> lista = dao.lortuProduktua();
		ProduktuakTaula model = new ProduktuakTaula(lista);

		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(175, 101, 1185, 527);
		contentPane.add(scrollPane);

		table.getSelectionModel().addListSelectionListener(e -> {
			int filaVista = table.getSelectedRow();

			if (filaVista != -1) {
				// Si hay sorter o filtros aplicados, convertimos a índice del modelo
				int filaModelo = table.convertRowIndexToModel(filaVista);

				// Supongamos que queremos el valor de la columna 1 (segunda columna)
				Object valor = table.getModel().getValueAt(filaModelo, 0);
				txt_id.setText(valor != null ? valor.toString() : "");

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
					int idProduktua = Integer.parseInt(txt_id.getText().trim());
					Optional<Produktua> produktua = lortuProduktuaIdarekin(idProduktua);
					if (produktua.isPresent()) {
						Object[] options = { "Bai", "Ez" }; // Bai edo ez izateko aukerak ematen ditu
						int erantzuna = JOptionPane.showOptionDialog(null, "Produktua ezabatu nahi duzu?",
								"EZABATU", JOptionPane.YES_NO_OPTION, +JOptionPane.QUESTION_MESSAGE, null,
								options, options[0]);
						if (erantzuna == JOptionPane.YES_OPTION) {
							ezabatuProduktua(idProduktua);
							actualizarTabla();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Produktua ez da aurkitu ID honekin: " + idProduktua,
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
                    int idProduktua = Integer.parseInt(txt_id.getText());
                    // Mezua bilatu ID-a erabiliz
                    Optional<Produktua> produktuakOpt = lortuProduktuaIdarekin(idProduktua);
                    
                    if (produktuakOpt.isPresent()) {
                        Produktua produktua = produktuakOpt.get();
                        // Produktua aurkitu bada, datuak pasatzea eta aldaketaProduktuak erakustea
                        dispose();
                        aldaketaProduktuak ap = new aldaketaProduktuak(produktua);
                        ap.setVisible(true);  // Erakutsi
                    } else {
                        // Hornitzailea ez bada aurkitu, errore-mezua erakutsi
                        JOptionPane.showMessageDialog(null, "Produktua ez da aurkitu ID honekin: " + idProduktua, "Errorea", JOptionPane.ERROR_MESSAGE);
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
				sartuProduktuak sb = new sartuProduktuak();
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
						lblFondoa.setIcon(new ImageIcon(BodegaPanela.class.getResource("/media/FONDOAK_OSOA.png")));
						lblFondoa.setBounds(0, 0, 1538, 735);
						contentPane.add(lblFondoa);

	}

	private void actualizarTabla() {
		List<Produktua> lista = dao.lortuProduktua();
		ProduktuakTaula model = new ProduktuakTaula(lista);
		if (table == null) {
			table = new JTable(model);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(50, 73, 900, 300);
			contentPane.add(scrollPane);
		} else {
			table.setModel(model);
		}
	}

	private Optional<Produktua> lortuProduktuaIdarekin(int idProduktua) {
		return dao.lortuProduktua().stream().filter(abisua -> abisua.getIdProduktua() == idProduktua).findFirst();
	}

	private void ezabatuProduktua(int idProduktua) {
		boolean isDeleted = dao.ezabatuProduktua(idProduktua);
		if (!isDeleted) {
			txt_id.setText("");
			JOptionPane.showMessageDialog(null, "Errore bat gertatu da. Produktua ezin izan da ezabatu.", "Errorea",
					JOptionPane.ERROR_MESSAGE);
		} else {
			txt_id.setText("");
			JOptionPane.showMessageDialog(null, "Produktua ezabatua izan da.");
		}
	}
}


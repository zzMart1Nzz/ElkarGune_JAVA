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
import kudeaketak.kudeaketaFakturak;
import objetuak.Espazioa;
import objetuak.Fakturak;
import taulak.FakturakTaula;
import eragiketak.fakturaEskaera;




public class FakturakPanela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private kudeaketaFakturak dao;
	private JTextField txt_id;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FakturakPanela frame = new FakturakPanela();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public FakturakPanela() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(-5, 0, 1550, 770);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		dao = new kudeaketaFakturak();
		List<Fakturak> lista = dao.lortuFakturak();
		FakturakTaula model = new FakturakTaula(lista);

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
						
						JLabel lblAldatu = new JLabel("");
						lblAldatu.setBounds(387, 662, 53, 50);
						lblAldatu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						contentPane.add(lblAldatu);
						lblAldatu.addMouseListener(new MouseAdapter () {
							@Override
				            public void mouseClicked(MouseEvent e) {
				                // ID-a hartu
				                try {
				                    int idFaktura = Integer.parseInt(txt_id.getText());
				                    // Mezua bilatu ID-a erabiliz
				                    Optional<Fakturak> fakturakOpt = lortuFakturaIdarekin(idFaktura);
				                    
				                    if (fakturakOpt.isPresent()) {
				                    	kudeaketaFakturak kf = new kudeaketaFakturak();
				                    	kf.eguneratuFakturak(idFaktura);
				                    	fakturaEskaera fe = new fakturaEskaera();
				                    	fe.sortuFaktura(idFaktura);
				                    	
				                        //Fakturak faktura = fakturakOpt.get();
				                        // Hornitzailea aurkitu bada, datuak pasatzea eta AldaketaHornitzaileak erakustea
				                        actualizarTabla();
				                        txt_id.setText("");
				                    } else {
				                        // Hornitzailea ez bada aurkitu, errore-mezua erakutsi
				                        JOptionPane.showMessageDialog(null, "Faktura ez da aurkitu ID honekin: " + idFaktura, "Errorea", JOptionPane.ERROR_MESSAGE);
				                    }
				                } catch (NumberFormatException ex) {
				                    JOptionPane.showMessageDialog(null, "ID-a zenbaki baliodun bat izan behar du.", "Errorea", JOptionPane.ERROR_MESSAGE);
				                }
				            }
							
					});
						}

	private void actualizarTabla() {
		List<Fakturak> lista = dao.lortuFakturak();
		FakturakTaula model = new FakturakTaula(lista);
		if (table == null) {
			table = new JTable(model);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(50, 73, 900, 300);
			contentPane.add(scrollPane);
		} else {
			table.setModel(model);
		}
	}
	private Optional<Fakturak> lortuFakturaIdarekin(int idFaktura) {
		return dao.lortuFakturak().stream().filter(faktura -> faktura.getIdFaktura() == idFaktura).findFirst();
	}
}



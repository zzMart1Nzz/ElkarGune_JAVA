package panelak;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


import kudeaketak.kudeaketaFakturak;
import objetuak.Fakturak;
import taulak.FakturakTaula;



public class FakturakPanela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private kudeaketaFakturak dao;

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
						lblFondoa.setIcon(new ImageIcon(FakturakPanela.class.getResource("/media/FONDOAK_errekarga.png")));
						lblFondoa.setBounds(0, 0, 1538, 735);
						contentPane.add(lblFondoa);

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

	/*private Optional<Fakturak> lortuBazkideaIdarekin(int idFaktura) {
		return dao.lortuFakturak().stream().filter(faktura -> faktura.getIdFaktura() == idFaktura).findFirst();
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
	}*/
}



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


import objetuak.Produktua;
import panelak.BodegaPanela;
import kudeaketak.kudeaketaProduktuak;


import java.awt.Color;
import java.awt.Cursor;

public class aldaketaProduktuak extends JFrame {
    private JPanel contentPane;
    private static final long serialVersionUID = 1L;
    private JTextField txt_idProduktuMota;
    private JTextField txt_izena;
    private JTextField txt_erosketaPrezioa;
    private JTextField txt_idHornitzailea;
    private JTextField txt_salmentaPrezioa;
    private JTextField txt_stock;
    private JLabel okIkonoa;
    private JLabel lblFondoa;
    private JLabel lblID;
    private JLabel lblid;
    private JLabel lblAtzera;

    public aldaketaProduktuak(Produktua produktua) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(-5, 0, 1550, 770);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txt_idProduktuMota = new JTextField();
        txt_idProduktuMota.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_idProduktuMota.setColumns(10);
        txt_idProduktuMota.setBounds(249, 173, 168, 35);
        contentPane.add(txt_idProduktuMota);
        txt_idProduktuMota.setText(String.valueOf(produktua.getIdProduktuMota()));

        JLabel lblidProduktuMota = new JLabel("Mota:");
        lblidProduktuMota.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblidProduktuMota.setBounds(249, 150, 79, 20);
        contentPane.add(lblidProduktuMota);

        txt_izena = new JTextField();
        txt_izena.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_izena.setColumns(10);
        txt_izena.setBounds(467, 173, 168, 35);
        contentPane.add(txt_izena);
        txt_izena.setText(produktua.getIzena());

        JLabel lblIzena = new JLabel("Izena:");
        lblIzena.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblIzena.setBounds(467, 150, 71, 20);
        contentPane.add(lblIzena);

        txt_erosketaPrezioa = new JTextField();
        txt_erosketaPrezioa.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_erosketaPrezioa.setColumns(10);
        txt_erosketaPrezioa.setBounds(693, 173, 168, 35);
        contentPane.add(txt_erosketaPrezioa);
        txt_erosketaPrezioa.setText(String.valueOf(produktua.getErosketaPrezioa()));

        JLabel lblErosketaPrezioa = new JLabel("ErosketaPrezioa:");
        lblErosketaPrezioa.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblErosketaPrezioa.setBounds(693, 150, 168, 20);
        contentPane.add(lblErosketaPrezioa);

        txt_idHornitzailea = new JTextField();
        txt_idHornitzailea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_idHornitzailea.setColumns(10);
        txt_idHornitzailea.setBounds(249, 451, 168, 35);
        contentPane.add(txt_idHornitzailea);
        txt_idHornitzailea.setText(String.valueOf(produktua.getIdHornitzailea()));

        JLabel lblIdHornitzailea = new JLabel("idHornitzailea:");
        lblIdHornitzailea.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblIdHornitzailea.setBounds(249, 421, 139, 20);
        contentPane.add(lblIdHornitzailea);

        txt_salmentaPrezioa = new JTextField();
        txt_salmentaPrezioa.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_salmentaPrezioa.setColumns(10);
        txt_salmentaPrezioa.setBounds(912, 173, 168, 35);
        contentPane.add(txt_salmentaPrezioa);
        txt_salmentaPrezioa.setText(String.valueOf(produktua.getSalmentaPrezioa()));

        JLabel lblSalmentaPrezioa = new JLabel("SalmentaPrezioa:");
        lblSalmentaPrezioa.setFont(new Font("Tahoma", Font.BOLD, 19));
        lblSalmentaPrezioa.setBounds(912, 150, 181, 20);
        contentPane.add(lblSalmentaPrezioa);

        txt_stock = new JTextField();
        txt_stock.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txt_stock.setColumns(10);
        txt_stock.setBounds(1128, 173, 168, 35);
        contentPane.add(txt_stock);
        txt_stock.setText(String.valueOf(produktua.getStock()));

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblStock.setBounds(1128, 145, 148, 30);
        contentPane.add(lblStock);


        // ID Label (bistaratu bakarrik)
        lblID = new JLabel("ID:");
        lblID.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblID.setBounds(249, 100, 35, 35);
        contentPane.add(lblID);

        lblid = new JLabel("");
        lblid.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblid.setBounds(294, 100, 35, 35);
        contentPane.add(lblid);
        lblid.setText(String.valueOf(produktua.getIdProduktua()));

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
                int konfirmazioa = JOptionPane.showOptionDialog(null, "Ziur zaude aldaketak gorde nahi dituzula?", "BAIEZTATU",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, aukerak, aukerak[0]);

                if (konfirmazioa == JOptionPane.YES_OPTION) {
                    // Testu-kampoetatik balioak lortu
                    int idProduktuMota = Integer.parseInt(txt_idProduktuMota.getText());
                    String izena = txt_izena.getText();
                    double erosketaPrezioa = Double.parseDouble(txt_erosketaPrezioa.getText());
                    double salmentaPrezioa = Double.parseDouble(txt_salmentaPrezioa.getText());
                    int stock = Integer.parseInt(txt_stock.getText());
                    int idHornitzailea = Integer.parseInt(txt_idHornitzailea.getText());




                    // Mezua eguneratzea
                    produktua.setIdProduktuMota(idProduktuMota);
                    produktua.setIzena(izena);
                    produktua.setErosketaPrezioa(erosketaPrezioa);
                    produktua.setSalmentaPrezioa(salmentaPrezioa);
                    produktua.setStock(stock);
                    produktua.setIdHornitzailea(idHornitzailea);




                    // Hornitzailea datu-basean eguneratzea
                    kudeaketaProduktuak kudeaketa = new kudeaketaProduktuak();
                    kudeaketa.eguneratuProduktua(produktua);

                    // Mezu bat erakustea
                    JOptionPane.showMessageDialog(null, "Aldaketak ondo gorde dira.");
                    dispose();
                    BodegaPanela bp = new BodegaPanela();
                    bp.setVisible(true);
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
                BodegaPanela bp = new BodegaPanela();
                bp.setVisible(true);
        	}
        });
                
                        // Interfazean fondo baten gehitzea
                        lblFondoa = new JLabel("");
                        lblFondoa.setIcon(new ImageIcon(aldaketaProduktuak.class.getResource("/media/FONDOAK_ONARTU.png")));
                        lblFondoa.setBounds(0, 0, 1538, 735);
                        contentPane.add(lblFondoa);
    }
}

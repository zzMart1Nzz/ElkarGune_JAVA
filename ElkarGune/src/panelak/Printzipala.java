package panelak;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import eragiketak.cSharpIreki;

public class Printzipala extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Printzipala frame = new Printzipala();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


	/**
	 * Create the frame.
	 */
	public Printzipala() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(-5, 0, 1550, 770);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEspazioakIco = new JLabel("");
		lblEspazioakIco.setBounds(1105, 422, 190, 188);
		lblEspazioakIco.setIcon(null);
		lblEspazioakIco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblEspazioakIco);
		lblEspazioakIco.addMouseListener(new MouseAdapter () {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				EspazioakPanela esp = new EspazioakPanela();
				esp.setVisible(true);


			}
		});

		JLabel lblErreserbaHistIco = new JLabel("");
		lblErreserbaHistIco.setBounds(890, 422, 190, 188);
		lblErreserbaHistIco.setIcon(null);
		lblErreserbaHistIco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblErreserbaHistIco);
		lblErreserbaHistIco.addMouseListener(new MouseAdapter () {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();

				ErresHistPanela ehp = new ErresHistPanela();
				ehp.setVisible(true);
			}
		});

		JLabel lblBazkideakIco = new JLabel("");
		lblBazkideakIco.setBounds(1105, 141, 190, 193);
		lblBazkideakIco.setIcon(null);
		lblBazkideakIco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblBazkideakIco);
		lblBazkideakIco.addMouseListener(new MouseAdapter () {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();

				BazkideakPanela bp = new BazkideakPanela();
				bp.setVisible(true);
			}
		});

		JLabel lblMezuakIco = new JLabel("");
		lblMezuakIco.setBounds(890, 141, 190, 193);
		lblMezuakIco.setIcon(null);
		lblMezuakIco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblMezuakIco);
		lblMezuakIco.addMouseListener(new MouseAdapter () {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();

				MezuakPanela mp = new MezuakPanela();
				mp.setVisible(true);
			}
		});

		JLabel lblHornitzaileakIco = new JLabel("");
		lblHornitzaileakIco.setBounds(674, 422, 188, 188);
		lblHornitzaileakIco.setIcon(null);
		lblHornitzaileakIco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblHornitzaileakIco);
		lblHornitzaileakIco.addMouseListener(new MouseAdapter () {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();

				HornitzaileakPanela hp = new HornitzaileakPanela();
				hp.setVisible(true);
			}
		});

		JLabel lblAbisuakIco = new JLabel("");
		lblAbisuakIco.setBounds(674, 141, 190, 193);
		lblAbisuakIco.setIcon(null);
		lblAbisuakIco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblAbisuakIco);
		lblAbisuakIco.addMouseListener(new MouseAdapter () {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();

				AbisuakPanela ap = new AbisuakPanela();
				ap.setVisible(true);
			}
		});

		JLabel lblKontsumizioakIco = new JLabel("");
		lblKontsumizioakIco.setBounds(243, 422, 190, 188);
		lblKontsumizioakIco.setIcon(null);
		lblKontsumizioakIco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblKontsumizioakIco);
		lblKontsumizioakIco.addMouseListener(new MouseAdapter () {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();

				KontsumizioakPanela kp = new KontsumizioakPanela();
				kp.setVisible(true);
			}
		});

		JLabel lblErreserbakIco = new JLabel("");
		lblErreserbakIco.setBounds(243, 141, 190, 193);
		lblErreserbakIco.setIcon(null);
		lblErreserbakIco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblErreserbakIco);
		lblErreserbakIco.addMouseListener(new MouseAdapter () {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();

				ErreserbakPanela ep = new ErreserbakPanela();
				ep.setVisible(true);
			}

		});

		JLabel lblBodegaIco = new JLabel("");
		lblBodegaIco.setBounds(455, 141, 193, 193);
		lblBodegaIco.setIcon(null);
		lblBodegaIco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblBodegaIco);
		lblBodegaIco.addMouseListener(new MouseAdapter () {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();

				BodegaPanela bop = new BodegaPanela();
				bop.setVisible(true);
			}
		});


		JLabel lblFakturakIco = new JLabel("");
		lblFakturakIco.setBounds(458, 417, 190, 193);
		lblFakturakIco.setIcon(null);
		lblFakturakIco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblFakturakIco);
		lblFakturakIco.addMouseListener(new MouseAdapter () {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();

				FakturakPanela fp = new FakturakPanela();
				fp.setVisible(true);
			}
		});

		JLabel lblItxi = new JLabel("");
		lblItxi.setBounds(1464, 17, 53, 59);
		lblItxi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblItxi);
		lblItxi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                Object[] options = {"Bai", "Ez"}; //Bai edo ez izateko aukerak ematen ditu
                int erantzuna = JOptionPane.showOptionDialog(null, "Programatik atera nahi duzu?", "Konfirmatu atera nahi duzun", JOptionPane.YES_NO_OPTION,
                        + JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if(erantzuna == JOptionPane.YES_OPTION) {
                    dispose();
                    JOptionPane.showMessageDialog(null, "Eskerrik asko aplikazioa erabiltzeagatik.", "ATERA ZARA",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

		JLabel lblSaioaItxi = new JLabel("");
		lblSaioaItxi.setBounds(10, 665, 150, 59);
		lblSaioaItxi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblSaioaItxi);
		lblSaioaItxi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object[] options = {"Bai", "Ez"};
				int erantzuna = JOptionPane.showOptionDialog(null, "Saioa itxi nahi duzu?", "SAIOA ITXI", JOptionPane.YES_NO_OPTION,
						+ JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if(erantzuna == JOptionPane.YES_OPTION) {
					cSharpIreki.ejekutatu();
				}else {}
			}
		});

				JLabel lblFondoa = new JLabel("");
				lblFondoa.setBounds(0, 0, 1538, 735);
				lblFondoa.setIcon(new ImageIcon(Printzipala.class.getResource("/media/FONDO 2.png")));
				contentPane.add(lblFondoa);
	}
}

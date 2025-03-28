package panelak;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import metodoak.LoginaMetodoa;
import objetuak.Bazkidea;

public class LoginPanela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtErabiltzailea;
	private JPasswordField jpassPasahitza;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				LoginPanela frame = new LoginPanela();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public LoginPanela() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 1007, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 978, 434);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblSaioaHasi = new JLabel("Saioa hasi");
		lblSaioaHasi.setFont(new Font("Tahoma", Font.BOLD, 42));
		lblSaioaHasi.setBounds(182, 58, 222, 60);
		panel.add(lblSaioaHasi);

		JLabel lblErabiltzailea = new JLabel("Erabiltzailea:");
		lblErabiltzailea.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblErabiltzailea.setBounds(127, 155, 192, 35);
		panel.add(lblErabiltzailea);

		JLabel lblPasahitza = new JLabel("Pasahitza:");
		lblPasahitza.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblPasahitza.setBounds(127, 243, 161, 35);
		panel.add(lblPasahitza);

		txtErabiltzailea = new JTextField();
		txtErabiltzailea.setFont(new Font("Tahoma", Font.PLAIN, 29));
		txtErabiltzailea.setBounds(127, 201, 358, 35);
		panel.add(txtErabiltzailea);

		jpassPasahitza = new JPasswordField();
		jpassPasahitza.setFont(new Font("Tahoma", Font.PLAIN, 29));
		jpassPasahitza.setBounds(127, 289, 358, 35);
		panel.add(jpassPasahitza);

		JLabel lblSartu = new JLabel("");
		lblSartu.setBounds(227, 360, 150, 57);
		lblSartu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(lblSartu);
		lblSartu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login();
			}
		});

		JLabel lblItxi = new JLabel("");
		lblItxi.setBounds(922, 3, 45, 45);
		lblItxi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(lblItxi);
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

		JLabel lblFondoa = new JLabel(new ImageIcon(LoginPanela.class.getResource("/media/FONDO_LOGIN 1.png")));
		lblFondoa.setBounds(-10, 0, 988, 434);
		panel.add(lblFondoa);
	}

	private void login() {
		String erabiltzailea = txtErabiltzailea.getText().trim();
		String pasahitza = new String(jpassPasahitza.getPassword()).trim();

		if (erabiltzailea.isEmpty() || pasahitza.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Erabiltzailea eta pasahitza bete behar dira.", "ERROREA",
					JOptionPane.ERROR_MESSAGE);
			txtErabiltzailea.setText("");
			jpassPasahitza.setText("");
			txtErabiltzailea.requestFocus();
			return;
		}

		System.out.println("Erabiltzailea bilatzen: " + erabiltzailea);
		System.out.println("Pasahitza bilatzen: " + pasahitza);

		LoginaMetodoa konprobazioa = new LoginaMetodoa();
		Bazkidea bazkidea2 = new Bazkidea();
		bazkidea2.setErabiltzailea(erabiltzailea);
		bazkidea2.setPasahitza(pasahitza);

		Bazkidea baz = konprobazioa.lortuErabiltzailea(bazkidea2);

		if (baz == null) {
			System.out.println("Erabiltzailea ez da aurkitu.");
			JOptionPane.showMessageDialog(this, "Erabiltzailea edo pasahitza okerra da.", "ERROREA",
					JOptionPane.ERROR_MESSAGE);
			txtErabiltzailea.setText("");
			jpassPasahitza.setText("");
			txtErabiltzailea.requestFocus();
			return;
		} else {
			System.out.println("Erabiltzailea aurkitu da: " + baz.getIzena());
		}

		if (baz.isAdmin()) {
			Object[] options = { "Administratzailea", "Bazkidea" };
			int erantzuna = JOptionPane.showOptionDialog(this, "Nola hasi nahi duzu saioa?", "SARTZEKO MOTA",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

			if (erantzuna == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(this, "Ongi etorri administratzaile moduan, " + baz.getIzena() + "!",
						"SARTU ZARA", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				new Printzipala().setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "Ongi etorri bazkide moduan, " + baz.getIzena() + "!", "SARTU ZARA",
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Ez zara administratzailea, " + baz.getIzena() + ".", "EZIN DUZU",
					JOptionPane.INFORMATION_MESSAGE);
			txtErabiltzailea.setText("");
			jpassPasahitza.setText("");
			txtErabiltzailea.requestFocus();
		}
	}
}

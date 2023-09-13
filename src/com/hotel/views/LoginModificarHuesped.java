package com.hotel.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.hotel.jdbc.controller.LoginController;
import com.hotel.jdbc.modelo.Login;

public class LoginModificarHuesped extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtContrasena;
	int xMouse, yMouse;
	private JLabel labelExit;
	private LoginController loginController;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginModificarHuesped frame = new LoginModificarHuesped();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginModificarHuesped() {
		
		this.loginController = new LoginController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginModificarHuesped.class.getResource("/imagenes/aH-40px.png")));
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 453, 527);
		panel.setBackground(new Color(248, 251, 255));
		contentPane.add(panel);
		panel.setLayout(null);	

		
		JPanel btnexit = new JPanel();
		btnexit.setBounds(400, 0, 53, 36);
		panel.add(btnexit);
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Busqueda busqueda = new Busqueda(1);
					busqueda.setVisible(true);
					dispose();	
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnexit.setBackground(new Color(12, 138, 199));
			     labelExit.setForeground(Color.white);
			}
		});
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setLayout(null);
		btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setForeground(SystemColor.text);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);		
		
		
		
		txtUsuario = new JTextField();
		txtUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				 if (txtUsuario.getText().equals("Ingrese su nombre de usuario")) {
					 txtUsuario.setText("");
					 txtUsuario.setForeground(new Color(12, 138, 199));
			        }
			        if (String.valueOf(txtContrasena.getPassword()).isEmpty()) {
			        	txtContrasena.setText("********");
			        	txtContrasena.setForeground(Color.gray);
			        }
			}
		});
		
		txtUsuario.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtUsuario.setText("Ingrese su nombre de usuario");		
		txtUsuario.setBackground(new Color(248, 251, 255));
		txtUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtUsuario.setForeground(Color.gray);
		txtUsuario.setBounds(65, 256, 324, 32);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(12, 138, 199));
		separator.setBounds(65, 292, 324, 2);
		panel.add(separator);
		
		JLabel labelTitulo = new JLabel("CONFIRMAR USUARIO");
		labelTitulo.setForeground(new Color(12, 138, 199));
		labelTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 26));
		labelTitulo.setBounds(90, 149, 300, 26);
		panel.add(labelTitulo);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(12, 138, 199));
		separator_1.setBounds(65, 393, 324, 2);
		panel.add(separator_1);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setText("********");
		txtContrasena.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (String.valueOf(txtContrasena.getPassword()).equals("********")) {
					txtContrasena.setText("");
					txtContrasena.setForeground(new Color(12, 138, 199));
		        }
		        if (txtUsuario.getText().isEmpty()) {
		        	txtUsuario.setText("Ingrese su nombre de usuario");
		        	txtUsuario.setForeground(Color.gray);
		        }
			}
		});
		txtContrasena.setForeground(Color.gray);
		txtContrasena.setFont(new Font("Roboto", Font.PLAIN, 18));		
		txtContrasena.setBackground(new Color(248, 251, 255));
		txtContrasena.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtContrasena.setBounds(65, 353, 324, 32);
		panel.add(txtContrasena);
		
		JLabel LabelUsuario = new JLabel("USUARIO");
		LabelUsuario.setForeground(SystemColor.textInactiveText);
		LabelUsuario.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		LabelUsuario.setBounds(65, 219, 107, 26);
		panel.add(LabelUsuario);
		
		JLabel lblContrasea = new JLabel("CONTRASEÑA");
		lblContrasea.setForeground(SystemColor.textInactiveText);
		lblContrasea.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		lblContrasea.setBounds(65, 316, 140, 26);
		panel.add(lblContrasea);
		
		JPanel btnLogin = new JPanel();
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setBackground(new Color(0, 156, 223));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setBackground(new Color(12, 138, 199));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Login();
			}
		});
		btnLogin.setBackground(new Color(12, 138, 199));
		btnLogin.setBounds(65, 431, 130, 44);
		panel.add(btnLogin);
		btnLogin.setLayout(null);
		btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		JLabel lblNewLabel = new JLabel("CONFIRMAR");
		lblNewLabel.setBounds(0, 0, 130, 44);
		btnLogin.add(lblNewLabel);
		lblNewLabel.setForeground(SystemColor.controlLtHighlight);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Roboto", Font.PLAIN, 18));		

		
		JLabel lblImagen = new JLabel("");
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setIcon(new ImageIcon(LoginModificarHuesped.class.getResource("/imagenes/login.png")));
		lblImagen.setBounds(200, 65, 65, 65);
		panel.add(lblImagen);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setBackground(new Color(248, 251, 255));
		header.setBounds(0, 0, 453, 36);
		panel.add(header);
		header.setLayout(null);
	}
	
	private void Login() {
		String contrase = new String(txtContrasena.getPassword());
		Login nuevoLogin = new Login(txtUsuario.getText(), contrase);
		loginController.login(nuevoLogin);		
		Integer acceso = nuevoLogin.getAcceso();
		if (acceso > 2) {
			EditarHuesped editar = new EditarHuesped();
			editar.setVisible(true);
			dispose();
		} else if (acceso == 2) {
			JOptionPane.showMessageDialog(this, "El usuario " + txtUsuario.getText() + " es solo consulta");
			txtUsuario.setText("Ingrese su nombre de usuario");
			txtUsuario.setForeground(Color.gray);
			txtContrasena.setText("********");
			txtContrasena.setForeground(Color.gray);
		} else {
			JOptionPane.showMessageDialog(this, "Usuario o Contraseña no válidos");
			txtUsuario.setText("Ingrese su nombre de usuario");
			txtUsuario.setForeground(Color.gray);
			txtContrasena.setText("********");
			txtContrasena.setForeground(Color.gray);
		}
	} 
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }//GEN-LAST:event_headerMousePressed

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}

package com.hotel.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.hotel.jdbc.controller.ReservasController;
import com.hotel.jdbc.modelo.Reserva;


@SuppressWarnings("serial")
public class EliminarReservas extends JFrame {

	private JPanel contentPane;
	public static JLabel txtValor;
	private JComboBox<Integer> comboId;
	
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel labelAtras;

			 
	


	
	private ReservasController reservasController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EliminarReservas frame = new EliminarReservas();
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
	public EliminarReservas() {
		super("Eliminar Reserva");
		//Iniciando la clase ReservaController
		this.reservasController = new ReservasController();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(EliminarReservas.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 280);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		

		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(248, 251, 255));
		panel.setBounds(0, 0, 430, 560);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Código que crea los elementos de la interfáz gráfica
		
		JLabel lblTitulo = new JLabel("ELIMINAR RESERVAS");
		lblTitulo.setBounds(68, 40, 290, 42);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
		panel.add(lblTitulo);
		
		JLabel lblId = new JLabel("SELECCIONA EL NÚMERO DE RESERVA");
		lblId.setBounds(50, 90, 320, 20);
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setForeground(SystemColor.textInactiveText);
		lblId.setFont(new Font("Roboto Black", Font.PLAIN, 17));
		panel.add(lblId);
		
		
		comboId = new JComboBox<Integer>();
		comboId.setBounds(68, 120, 289, 20);
		comboId.setBackground(new Color(248, 251, 255));
		comboId.setForeground(new Color(12, 138, 199));
		comboId.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.add(comboId);
		cargarIdReservas();		
	
		JSeparator separatorID = new JSeparator();
		separatorID.setForeground(SystemColor.textHighlight);
		separatorID.setBackground(SystemColor.textHighlight);
		separatorID.setBounds(68, 142, 289, 2);
		panel.add(separatorID);

		
		JLabel lblNota = new JLabel("NOTA: IMPORTANTE esta acción borrará la");
		lblNota.setForeground(Color.red);
		lblNota.setBounds(68, 165, 290, 20);
		lblNota.setHorizontalAlignment(SwingConstants.CENTER);
		lblNota.setFont(new Font("Roboto Black", Font.PLAIN, 15));		
		panel.add(lblNota);
		
		JLabel lblNota1 = new JLabel("reservación relacionada con el huésped.");
		lblNota1.setForeground(Color.red);
		lblNota1.setBounds(68, 185, 290, 20);
		lblNota1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNota1.setFont(new Font("Roboto Black", Font.PLAIN, 15));		
		panel.add(lblNota1);
		
//		JSeparator separator_1_2 = new JSeparator();
//		separator_1_2.setForeground(SystemColor.textHighlight);
//		separator_1_2.setBounds(68, 160, 289, 2);
//		separator_1_2.setBackground(SystemColor.textHighlight);
//		panel.add(separator_1_2);

												
		// Componentes para dejar la interfaz con estilo Material Design
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				Busqueda busqueda = new Busqueda(0);
//				busqueda.setVisible(true);
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
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setBounds(377, 0, 53, 36);
		panel.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setForeground(Color.WHITE);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel header = new JPanel();
		header.setBounds(0, 0, 430, 36);
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
		header.setLayout(null);
		header.setBackground(new Color(248, 251, 255));
		panel.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				Busqueda editarBusqueda = new Busqueda(0);
//				editarBusqueda.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(new Color(248, 251, 255));
			     labelAtras.setForeground(new Color(12, 138, 199));
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(new Color(248, 251, 255));
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));	


		JPanel btnsiguiente = new JPanel();
		btnsiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(comboId.getSelectedItem()== null) {
					JOptionPane.showMessageDialog(contentPane, "No has seleccionado una, reserva");
				} else {
					eliminarReserva();					
				}
				Busqueda busqueda = new Busqueda(0);
				busqueda.setVisible(true);
				dispose();
			}				
		});
		btnsiguiente.setLayout(null);
		btnsiguiente.setBackground(new Color(12, 138, 199));
		btnsiguiente.setBounds(115, 230, 200, 35);
		panel.add(btnsiguiente);
		btnsiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		JLabel labelSiguiente = new JLabel("ELIMINAR");
		labelSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		labelSiguiente.setForeground(Color.WHITE);
		labelSiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelSiguiente.setBounds(0, 0, 200, 35);
		btnsiguiente.add(labelSiguiente);


	}
	
	private void eliminarReserva() {
		Integer id = (Integer) comboId.getSelectedItem(); 
	
		Reserva eliminar = new Reserva(id);
		reservasController.eliminar(eliminar); 		
		JOptionPane.showMessageDialog(contentPane, "Reserva "+ eliminar.getId()+" ELIMINADA con exito");
		Busqueda editarBusqueda = new Busqueda(0);
		editarBusqueda.setVisible(true);
		dispose();
	}
	
 	private void cargarIdReservas() {
	    List<Reserva> reserva = this.reservasController.listar();

	    // Limpia el JComboBox antes de agregar nuevos elementos
	    comboId.removeAllItems();
	    comboId.addItem(null);;
	    // Agrega elementos desde la lista al JComboBox
	    for (Reserva reservaList : reserva) {
	        comboId.addItem(reservaList.getId());
	    }
	}

		
	//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"	
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
	    }
}

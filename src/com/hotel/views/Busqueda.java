package com.hotel.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.hotel.jdbc.controller.HuespedController;
import com.hotel.jdbc.controller.ReservasController;
import com.hotel.jdbc.modelo.Huesped;
import com.hotel.jdbc.modelo.Reserva;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane,btnEditarReserva,btnEditarHuesped,btnBuscarReserva, btnbuscarHuesped,btnEliminarReserva,btnEliminarhuesped;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private JTabbedPane panel;
	private DefaultTableModel modeloReservas;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras,labelExit, lblEditarReserva,lblEditarHuesped;
	private ReservasController reservasController;
	private HuespedController huespedController;
	int xMouse, yMouse;
	private int initialTabIndex;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda(0);
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
	public Busqueda(int initialTabIndex) {
		this.initialTabIndex = initialTabIndex;
		
		this.reservasController = new ReservasController();
		this.huespedController = new HuespedController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 251, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		

		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(311, 62, 300, 42);
		contentPane.add(lblNewLabel_4);
		
		panel = new JTabbedPane(JTabbedPane.TOP);		
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);	
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setBackground(new Color(248, 251, 255));
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloReservas = (DefaultTableModel) tbReservas.getModel();
		modeloReservas.addColumn("Numero de Reserva");
		modeloReservas.addColumn("Fecha Check In");
		modeloReservas.addColumn("Fecha Check Out");
		modeloReservas.addColumn("Total de Noches");		
		modeloReservas.addColumn("Valor en USD.");
		modeloReservas.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		cargarTablaReservas();
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		tbHuespedes.setBackground(new Color(248, 251, 255));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Número de Reserva");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		cargarTablaHuespedes();
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
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
		header.setLayout(null);
		header.setBackground(new Color(248, 251, 255));
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
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
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setForeground(new Color(12, 138, 199));
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(new Color(248, 251, 255));
			     labelExit.setForeground(new Color(12, 138, 199));
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(248, 251, 255));
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(new Color(12, 138, 199));
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBackground(new Color(248, 251, 255));
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		btnBuscarReserva = new JPanel();
		btnBuscarReserva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarReservas();
				cargarBuscarResevas();
				txtBuscar.setText("");
			}
		});
		btnBuscarReserva.setLayout(null);
		btnBuscarReserva.setBackground(new Color(12, 138, 199));
		btnBuscarReserva.setBounds(748, 125, 122, 35);
		btnBuscarReserva.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnBuscarReserva);
		
		JLabel lblBuscarReserva = new JLabel("BUSCAR");
		lblBuscarReserva.setBounds(0, 0, 122, 35);
		btnBuscarReserva.add(lblBuscarReserva);
		lblBuscarReserva.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarReserva.setForeground(Color.WHITE);
		lblBuscarReserva.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		btnbuscarHuesped = new JPanel();
		btnbuscarHuesped.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarHuesped();
				cargarBuscarHuespedes();
				txtBuscar.setText("");
			}
		});
		
		
		btnbuscarHuesped.setLayout(null);
		btnbuscarHuesped.setBackground(new Color(12, 138, 199));
		btnbuscarHuesped.setBounds(748, 125, 122, 35);
		btnbuscarHuesped.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscarHuesped);
		
		JLabel lblBuscarHuesped = new JLabel("BUSCAR");
		lblBuscarHuesped.setBounds(0, 0, 122, 35);
		btnbuscarHuesped.add(lblBuscarHuesped);
		lblBuscarHuesped.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarHuesped.setForeground(Color.WHITE);
		lblBuscarHuesped.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		 //comienza el boton editar reserva
		btnEditarReserva = new JPanel();
		
		btnEditarReserva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginModificarReserva modificar = new LoginModificarReserva();
				modificar.setVisible(true);
				//dispose();
			}	
		});
		  
		
		//btnEditarReserva.setVisible(true);
		btnEditarReserva.setLayout(null);
		btnEditarReserva.setBackground(new Color(12, 138, 199));
		btnEditarReserva.setBounds(635, 508, 122, 35);
		btnEditarReserva.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditarReserva);
		
		lblEditarReserva = new JLabel("EDITAR");
		lblEditarReserva.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditarReserva.setForeground(Color.WHITE);
		lblEditarReserva.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditarReserva.setBounds(0, 0, 122, 35);
		btnEditarReserva.add(lblEditarReserva);
		  //termina el boton editar reserva
		
		  //comienza el boton editar huesped
		btnEditarHuesped = new JPanel();		
		btnEditarHuesped.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginModificarHuesped modificar = new LoginModificarHuesped();
				modificar.setVisible(true);
				//dispose();
			}	
		});

		btnEditarHuesped.setLayout(null);
		btnEditarHuesped.setBackground(new Color(12, 138, 199));
		btnEditarHuesped.setBounds(635, 508, 122, 35);
		btnEditarHuesped.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditarHuesped);
		
		lblEditarHuesped = new JLabel("EDITAR");
		lblEditarHuesped.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditarHuesped.setForeground(Color.WHITE);
		lblEditarHuesped.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditarHuesped.setBounds(0, 0, 122, 35);
		btnEditarHuesped.add(lblEditarHuesped );
		  //termina el boton editar huesped
		
		
		btnEliminarReserva = new JPanel();
		btnEliminarReserva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginEliminarReserva eliminar = new LoginEliminarReserva();
				eliminar.setVisible(true);
				//dispose();
			}	
		});
		btnEliminarReserva.setLayout(null);
		btnEliminarReserva.setBackground(new Color(12, 138, 199));
		btnEliminarReserva.setBounds(767, 508, 122, 35);
		btnEliminarReserva.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminarReserva);
		
		JLabel lblEliminarReseva = new JLabel("ELIMINAR");
		lblEliminarReseva.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminarReseva.setForeground(Color.WHITE);
		lblEliminarReseva.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminarReseva.setBounds(0, 0, 122, 35);
		btnEliminarReserva.add(lblEliminarReseva);
		setResizable(false);
		
		btnEliminarhuesped = new JPanel();
		btnEliminarhuesped.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginEliminarHuesped eliminar = new LoginEliminarHuesped();
				eliminar.setVisible(true);
				//dispose();
			}	
		});
		btnEliminarhuesped.setLayout(null);
		btnEliminarhuesped.setBackground(new Color(12, 138, 199));
		btnEliminarhuesped.setBounds(767, 508, 122, 35);
		btnEliminarhuesped.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminarhuesped);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminarhuesped.add(lblEliminar);
		setResizable(false);
		seleccionarIndex();

		panel.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					// Obtener el índice de la pestaña seleccionada
					int selectedIndex =  panel.getSelectedIndex();

					// Realizar acciones específicas según la pestaña seleccionada
					if (selectedIndex == 0) {
			            btnEditarReserva.setVisible(true);
			            btnBuscarReserva.setVisible(true);
			            btnEliminarReserva.setVisible(true);
			            btnEditarHuesped.setVisible(false);
			            btnbuscarHuesped.setVisible(false);
			            btnEliminarhuesped.setVisible(false);
                                        
					} else if (selectedIndex == 1) {
			            btnEditarReserva.setVisible(false);
			            btnBuscarReserva.setVisible(false);
			            btnEliminarReserva.setVisible(false);
			            btnEditarHuesped.setVisible(true);
			            btnbuscarHuesped.setVisible(true);
			            btnEliminarhuesped.setVisible(true);
					} 
                
				}
        	});
    }
        //Actualiza el index depecdiendo de donde se ejecuto el boton
	private void seleccionarIndex() {
		
		 panel.setSelectedIndex(initialTabIndex);
        if (initialTabIndex == 0) {
            btnEditarReserva.setVisible(true);
            btnBuscarReserva.setVisible(true);
            btnEliminarReserva.setVisible(true);
            btnEditarHuesped.setVisible(false);
            btnbuscarHuesped.setVisible(false);
            btnEliminarhuesped.setVisible(false);
        } else if (initialTabIndex == 1) {
            btnEditarReserva.setVisible(false);
            btnBuscarReserva.setVisible(false);
            btnEliminarReserva.setVisible(false);
            btnEditarHuesped.setVisible(true);
            btnbuscarHuesped.setVisible(true);
            btnEliminarhuesped.setVisible(true);
        }
	}
	
	private void cargarTablaReservas() {
		 List<Reserva> reserva = this.reservasController.listar();
		reserva.forEach(reservas -> modeloReservas.addRow(new Object[] { reservas.getId(), reservas.getFechaEntrada(),
				reservas.getFechaSalida(), reservas.getTotalNoches(), reservas.getValor(), reservas.getFormaDePago() }));
	}
	
	private void cargarBuscarResevas() {
		List<Reserva> buscar = this.reservasController.buscarReserva(txtBuscar.getText());
		buscar.forEach(buscarReserva -> modeloReservas.addRow(new Object[] { buscarReserva.getId(), buscarReserva.getFechaEntrada(), 
				buscarReserva.getFechaSalida(), buscarReserva.getTotalNoches(), buscarReserva.getValor(), buscarReserva.getFormaDePago()}));
	}
	
	private void cargarTablaHuespedes() {
		 List<Huesped> huesped = this.huespedController.listar();
		huesped.forEach(huespedList -> modeloHuesped.addRow(new Object[] { huespedList.getId(), huespedList.getNumeroDeReserva(), huespedList.getNombre(),
				huespedList.getApellido(), huespedList.getFechaN(), huespedList.getNacionalidad(), huespedList.getTelefono()}));
	}
	
	private void cargarBuscarHuespedes() {
		List<Huesped> buscar = this.huespedController.buscarHuesped(txtBuscar.getText());
		buscar.forEach(huespedList -> modeloHuesped.addRow(new Object[] { huespedList.getId(), huespedList.getNumeroDeReserva(), huespedList.getNombre(),
				huespedList.getApellido(), huespedList.getFechaN(), huespedList.getNacionalidad(), huespedList.getTelefono()}));
	}
	
	private void limpiarReservas() {		
		modeloReservas.getDataVector().clear();
	}
	
	private void limpiarHuesped(){
		modeloHuesped.getDataVector().clear();
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

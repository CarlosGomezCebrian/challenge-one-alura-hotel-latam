package com.hotel.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.Format;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.hotel.jdbc.controller.HuespedController;
import com.hotel.jdbc.modelo.Huesped;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class EditarHuesped extends JFrame {
	//Calcula el año actual
	Calendar calendar = Calendar.getInstance();
	int yearActual = calendar.get(Calendar.YEAR);
	// Resta 18 años al año actual para obtener el año máximo permitido
	int yearMaximo = yearActual - 18;
	

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;

	private JDateChooser txtFechaN;
	private JComboBox<Format> txtNacionalidad;
	private JComboBox<Integer>  comboId;
	private JLabel labelExit;
	private JLabel labelAtras;
	int xMouse, yMouse;
	private HuespedController huespedController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarHuesped frame = new EditarHuesped();
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
	public EditarHuesped() {

		this.huespedController = new HuespedController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(EditarHuesped.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 634);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 251, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);
		

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
		header.setOpaque(false);
		header.setBounds(0, 0, 430, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				Busqueda busqueda = new Busqueda(1);
//				busqueda.setVisible(true);
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
		
		comboId = new JComboBox<Integer>();
		comboId.setBounds(68, 130, 289, 36);
		comboId.setBackground(new Color(248, 251, 255));
		comboId.setForeground(new Color(12, 138, 199));
		comboId.setFont(new Font("Roboto", Font.PLAIN, 16));
		contentPane.add(comboId);
		cargarIdHuespedes();
		comboId.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	 cargarDatos();
		    }
		});
//135
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Roboto", Font.PLAIN, 19));
		txtNombre.setForeground(new Color(12, 138, 199));
		txtNombre.setBounds(68, 204, 285, 33);
		txtNombre.setBackground(new Color(248, 251, 255));
		txtNombre.setColumns(10);
		txtNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtNombre);

		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Roboto", Font.PLAIN, 19));
		txtApellido.setForeground(new Color(12, 138, 199));
		txtApellido.setBounds(68, 278, 285, 33);
		txtApellido.setColumns(10);
		txtApellido.setBackground(new Color(248, 251, 255));
		txtApellido.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtApellido);

		txtFechaN = new JDateChooser();
		txtFechaN.setBounds(68, 350, 285, 36);
		txtFechaN.setForeground(new Color(12, 138, 199));
		txtFechaN.getJCalendar().getYearChooser().setEndYear(yearMaximo);
		txtFechaN.setBackground(new Color(248, 251, 255));
		txtFechaN.getCalendarButton().setIcon(new ImageIcon(EditarHuesped.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaN.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaN.setDateFormatString("yyyy-MM-dd");
		contentPane.add(txtFechaN);

		txtNacionalidad = new JComboBox();
		txtNacionalidad.setBounds(68, 424, 289, 36);
		txtNacionalidad.setForeground(new Color(12, 138, 199));
		txtNacionalidad.setBackground(new Color(248, 251, 255));
		txtNacionalidad.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNacionalidad.setModel(new DefaultComboBoxModel(new String[] { "afgano-afgana", "alemán-alemana",
				"árabe-árabe", "argentino-argentina", "australiano-australiana", "belga-belga", "boliviano-boliviana",
				"brasileño-brasileña", "camboyano-camboyana", "canadiense-canadiense", "chileno-chilena", "chino-china",
				"colombiano-colombiana", "coreano-coreana", "costarricense-costarricense", "cubano-cubana",
				"danés-danesa", "ecuatoriano-ecuatoriana", "egipcio-egipcia", "salvadoreño-salvadoreña",
				"escocés-escocesa", "español-española", "estadounidense-estadounidense", "estonio-estonia",
				"etiope-etiope", "filipino-filipina", "finlandés-finlandesa", "francés-francesa", "galés-galesa",
				"griego-griega", "guatemalteco-guatemalteca", "haitiano-haitiana", "holandés-holandesa",
				"hondureño-hondureña", "indonés-indonesa", "inglés-inglesa", "iraquí-iraquí", "iraní-iraní",
				"irlandés-irlandesa", "israelí-israelí", "italiano-italiana", "japonés-japonesa", "jordano-jordana",
				"laosiano-laosiana", "letón-letona", "letonés-letonesa", "malayo-malaya", "marroquí-marroquí",
				"mexicano-mexicana", "nicaragüense-nicaragüense", "noruego-noruega", "neozelandés-neozelandesa",
				"panameño-panameña", "paraguayo-paraguaya", "peruano-peruana", "polaco-polaca", "portugués-portuguesa",
				"puertorriqueño-puertorriqueño", "dominicano-dominicana", "rumano-rumana", "ruso-rusa", "sueco-sueca",
				"suizo-suiza", "tailandés-tailandesa", "taiwanes-taiwanesa", "turco-turca", "ucraniano-ucraniana",
				"uruguayo-uruguaya", "venezolano-venezolana", "vietnamita-vietnamita" }));
		contentPane.add(txtNacionalidad);
		
		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtTelefono.setForeground(new Color(12, 138, 199));
		txtTelefono.setBounds(68, 495, 285, 33);
		txtTelefono.setColumns(10);
		txtTelefono.setBackground(new Color(248, 251, 255));
		txtTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtTelefono);
		
		JLabel lblTitulo = new JLabel("EDITAR HUÉSPED");
		lblTitulo.setBounds(110, 50, 280, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 23));
		contentPane.add(lblTitulo);
		
		JLabel lblNumeroHuesped = new JLabel("NÚMERO DE HUÉSPED");
		lblNumeroHuesped.setBounds(68, 105, 253, 14);
		lblNumeroHuesped.setForeground(SystemColor.textInactiveText);
		lblNumeroHuesped.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNumeroHuesped);	


		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(68, 189, 253, 14);
		lblNombre.setForeground(SystemColor.textInactiveText);
		lblNombre.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNombre);

		JLabel lblApellido = new JLabel("APELLIDO");
		lblApellido.setBounds(68, 256, 255, 14);
		lblApellido.setForeground(SystemColor.textInactiveText);
		lblApellido.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblApellido);

		JLabel lblFechaN = new JLabel("FECHA DE NACIMIENTO");
		lblFechaN.setBounds(68, 326, 255, 14);
		lblFechaN.setForeground(SystemColor.textInactiveText);
		lblFechaN.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblFechaN);

		JLabel lblNacionalidad = new JLabel("NACIONALIDAD");
		lblNacionalidad.setBounds(68, 400, 255, 14);
		lblNacionalidad.setForeground(SystemColor.textInactiveText);
		lblNacionalidad.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNacionalidad);
		
		JLabel lblTelefono = new JLabel("TELEFONO");
		lblTelefono.setBounds(68, 474, 253, 14);
		lblTelefono.setForeground(SystemColor.textInactiveText);
		lblTelefono.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblTelefono);
		
		JSeparator separatorComboId = new JSeparator();
		separatorComboId.setBounds(68, 170, 289, 2);
		separatorComboId.setForeground(new Color(12, 138, 199));
		separatorComboId.setBackground(new Color(12, 138, 199));
		contentPane.add(separatorComboId);

		JSeparator separatorNombre = new JSeparator();
		separatorNombre.setBounds(68, 240, 289, 2);
		separatorNombre.setForeground(new Color(12, 138, 199));
		separatorNombre.setBackground(new Color(12, 138, 199));
		contentPane.add(separatorNombre);

		JSeparator separator_1_2_2 = new JSeparator();
		separator_1_2_2.setBounds(68, 314, 289, 2);
		separator_1_2_2.setForeground(new Color(12, 138, 199));
		separator_1_2_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_2);

		JSeparator separator_1_2_3 = new JSeparator();
		separator_1_2_3.setBounds(68, 386, 289, 2);
		separator_1_2_3.setForeground(new Color(12, 138, 199));
		separator_1_2_3.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_3);

		JSeparator separator_1_2_4 = new JSeparator();
		separator_1_2_4.setBounds(68, 461, 289, 2);
		separator_1_2_4.setForeground(new Color(12, 138, 199));
		separator_1_2_4.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_4);

		JSeparator separatorTelefono = new JSeparator();
		separatorTelefono .setBounds(68, 533, 289, 2);
		separatorTelefono .setForeground(new Color(12, 138, 199));
		separatorTelefono .setBackground(new Color(12, 138, 199));
		contentPane.add(separatorTelefono );

		JPanel btnguardar = new JPanel();
		btnguardar.setBounds(115, 560, 190, 35);
		btnguardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				editarHuesped();

			}
		});
		btnguardar.setLayout(null);
		btnguardar.setBackground(new Color(12, 138, 199));
		contentPane.add(btnguardar);
		btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel labelGuardar = new JLabel("EDITAR HUÉSPED");
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setForeground(Color.WHITE);
		labelGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelGuardar.setBounds(0, 0, 190, 35);
		btnguardar.add(labelGuardar);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				Busqueda buscar = new Busqueda(1);
//				buscar.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(new Color(248, 251, 255));
				labelExit.setForeground(new Color(12, 138, 199));
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(248, 251, 255));
		btnexit.setBounds(377, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setForeground(new Color(12, 138, 199));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
	}

	private void editarHuesped() {

		try {
			
			Integer id = (Integer) comboId.getSelectedItem(); 
			String fechaN = ((JTextField) txtFechaN.getDateEditor().getUiComponent()).getText();
			Huesped editar = new Huesped(id, id, txtNombre.getText(), txtApellido.getText(),
					java.sql.Date.valueOf(fechaN), txtNacionalidad.getSelectedItem().toString(), txtTelefono.getText());
			huespedController.editar(editar);
			dispose();// sirve para cerrar la ventana actual
			JOptionPane.showMessageDialog(contentPane,
					"Huesped en reserva " + editar.getNumeroDeReserva() + " Modificado con exito");
			Busqueda editarBusqueda = new Busqueda(1);
			editarBusqueda.setVisible(true);
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "Algún dato es incorrecto o vacío");
		}
	}

	
	private void cargarIdHuespedes() {
	    List<Huesped> huesped = this.huespedController.listar();

	    // Limpia el JComboBox antes de agregar nuevos elementos
	    comboId.removeAllItems();

	    // Agrega elementos desde la lista al JComboBox
	    for (Huesped huespedList : huesped) {
	        comboId.addItem(huespedList.getId());
	    }
	}
	
	private void cargarDatos() {
		Integer id = (Integer) comboId.getSelectedItem();
		Huesped cargar = new Huesped(id);
		huespedController.cargar(cargar);		

		Date fechaJavaUtil = cargar.getFechaN();
		txtFechaN.setDate(fechaJavaUtil);
		txtNombre.setText(cargar.getNombre());
		txtApellido.setText(cargar.getApellido());
		txtNacionalidad.setSelectedItem(cargar.getNacionalidad());
		txtTelefono.setText(cargar.getTelefono());

	}
	
	// Código que permite mover la ventana por la pantalla según la posición de "x"
	// y "y"
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

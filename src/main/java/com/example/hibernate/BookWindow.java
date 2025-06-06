package com.example.hibernate;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.example.hibernate.model.Author;
import com.example.hibernate.model.Book;
import com.example.hibernate.model.servicio.BookServicio;
import com.example.hibernate.model.servicio.IBookServicio;

public class BookWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextArea mensajes_text_Area;
	private JList<Book> JListAllBooks;


	private IBookServicio bookServicio;

	private CreateAuthorDialog createDialog;
	
	private JTextField JtextFieldEmpno;
	private JLabel lblAuthId;

	

	private Author author = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookWindow frame = new BookWindow();
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
	public BookWindow() {

	
		bookServicio = new BookServicio();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 847, 772);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(8, 8, 821, 707);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 450, 669, 228);
		panel.add(scrollPane);

		mensajes_text_Area = new JTextArea();
		scrollPane.setViewportView(mensajes_text_Area);
		mensajes_text_Area.setEditable(false);
		mensajes_text_Area.setText("Panel de mensajes");
		mensajes_text_Area.setForeground(new Color(255, 0, 0));
		mensajes_text_Area.setFont(new Font("Monospaced", Font.PLAIN, 13));

		JButton btnShowAllBooks = new JButton("Mostrar libros");

		btnShowAllBooks.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnShowAllBooks.setBounds(38, 132, 208, 36);
		panel.add(btnShowAllBooks);

		JScrollPane scrollPanel_in_JlistAllBooks = new JScrollPane();
		panel.add(scrollPanel_in_JlistAllBooks);

	

		DefaultListModel<Book> model = new DefaultListModel<>();

		JListAllBooks = new JList<Book>(model);

		JListAllBooks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		panel.add(JListAllBooks);
		JListAllBooks.setBounds(403, 37, 377, 362);

		JButton btnCrearAutor = new JButton("Añadir autor al libro");

		btnCrearAutor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCrearAutor.setBounds(38, 201, 208, 36);
		btnCrearAutor.setEnabled(false);
		panel.add(btnCrearAutor);

		

		lblAuthId = new JLabel("Introduzca el id del autor y luego presione la tecla ENTER");
		lblAuthId.setFont(new Font("Tahoma", Font.PLAIN, 12));

		lblAuthId.setBounds(35, 37, 500, 31);
		panel.add(lblAuthId);

		panel.add(lblAuthId);
		JtextFieldEmpno = new JTextField();
		JtextFieldEmpno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JtextFieldEmpno.setBounds(38, 77, 136, 36);
		panel.add(JtextFieldEmpno);
		JtextFieldEmpno.setColumns(10);
		JtextFieldEmpno.addKeyListener(null);

		// Eventos
		ActionListener showAllBooksActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getAllBooks();
			}
		};
		btnShowAllBooks.addActionListener(showAllBooksActionListener);

		ActionListener crearListener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int authorId = getAuthorIdFromTextField();
				try {

					if (authorId != -1) {
						

						if ((BookWindow.this.author == null)
								|| (authorId != BookWindow.this.author.getAuthorId())) {

							BookWindow.this.author = bookServicio.findAuthorById(authorId);
						}
					
						JFrame owner = (JFrame) SwingUtilities.getRoot((Component) e.getSource());

						createDialog = new CreateAuthorDialog(owner, "Crear nuevo autor",
								Dialog.ModalityType.DOCUMENT_MODAL);
						showDialog(BigDecimal.ZERO);
					} else {
						addMensaje(true, "El id de autor no es correcto");
					}
				} catch (Exception ex) {

					addMensaje(true, "No se ha podido recuperar el autor  con id " + authorId);
				}
			}
		};
		btnCrearAutor.addActionListener(crearListener);

	

		ListSelectionListener selectionListListener = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					int selectedIx = JListAllBooks.getSelectedIndex();
				
					btnCrearAutor.setEnabled((selectedIx > -1));
					if (selectedIx > -1) {
						Book book = (Book) JListAllBooks.getModel().getElementAt(selectedIx);
						if (book != null) {
							addMensaje(true, "Se ha seleccionado el book: " + book);
							try {
								List<Author> autores = bookServicio
										.getAutoresByBookId(book.getBookId());
								addMensaje(true,
										"Los autores del libro  con id: " + book.getBookId() + " son:\n");
								for (Author author : autores) {
									addMensaje(true, "     " + author + "\n");
								}

							} catch (Exception ex) {
								addMensaje(true, "No se ha podido obtener los autores del libro con id: " +
										book.getBookId());
								System.out.println("Exception: " + ex.getMessage());
								ex.printStackTrace();
							}
						}
					}
				}
			}
		};
		JListAllBooks.addListSelectionListener(selectionListListener);

		
		KeyAdapter enterKeyAdapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String textIntroducido = "";

				int key = e.getKeyCode();
				// Cuando se presiona la tecla enter, intentamos obtener el author
				if ((key == KeyEvent.VK_ENTER)) {

					textIntroducido = ((JTextField) e.getSource()).getText().trim();
					try {
						int bookId = Integer.parseInt(textIntroducido);

						BookWindow.this.author = bookServicio.findAuthorById(bookId);

						if (author != null) {
							addMensaje(true, "Se ha encontrado el author con id: " + bookId);

						}

					} catch (NumberFormatException nfe) {

						addMensaje(true, "Introduzca un número entero");
						BookWindow.this.author = null;

					} catch (Exception ex) {
						System.out.println("Ha ocurrido una excepción: " + ex.getMessage());
						addMensaje(true, "Ha ocurrido un error y no se ha podido recuperar el autor con id: "
								+ textIntroducido);
						BookWindow.this.author = null;

					}
					// Cada vez que se cambia el autor se eliminan los libros
					DefaultListModel<Book> model = (DefaultListModel<Book>) JListAllBooks.getModel();
					model.clear();

					btnCrearAutor.setEnabled(author != null);
				}
			}

		};
		JtextFieldEmpno.addKeyListener(enterKeyAdapter);
	}

	private void addMensaje(boolean keepText, String msg) {
		String oldText = "";
		if (keepText) {
			oldText = mensajes_text_Area.getText();

		}
		oldText = oldText + "\n" + msg;
		mensajes_text_Area.setText(oldText);

	}

	private void showDialog(BigDecimal oldAmount) {
		createDialog.setVisible(true);
		Author author = createDialog.getResult();
		if (author != null) {

			save(author);
		}
	}

	

	private void save(Author author) {
		try {
			int selectedIx = JListAllBooks.getSelectedIndex();

			if (selectedIx > -1) {
				Book book = (Book) JListAllBooks.getModel().getElementAt(selectedIx);
				if (book != null) {

					Author authorCreated = bookServicio.addAuthorToBook(book.getBookId(), author);
					if (authorCreated != null) {
						addMensaje(true, "Se ha añadido un author " + authorCreated.getAuthorId() + " al libro con id: "
								+ book.getBookId());
						getAllBooks();
					} else {
						addMensaje(true, " El author no se ha creado correctamente");
					}
				}
			}

		} catch (Exception ex) {
			addMensaje(true, "Ha ocurrido un error y no se ha podido crear el autor");
		}
	}

	private void getAllBooks() {
		int authorId = getAuthorIdFromTextField();
		if (authorId != -1) {
			List<Book> books = bookServicio.getBooksByAuthorId(authorId);
			addMensaje(true, "Se han recuperado: " + books.size() + " libros");
			DefaultListModel<Book> defModel = new DefaultListModel<>();

			defModel.addAll(books);

			JListAllBooks.setModel(defModel);
		} else {
			addMensaje(true, "El id de autor no es correcto");
		}

	}

	private int getAuthorIdFromTextField() {
		int authorId = -1;
		String textIntroducido = JtextFieldEmpno.getText().trim();
		try {
			authorId = Integer.parseInt(textIntroducido);
		} catch (Exception nfe) {
			authorId = -1;
		}
		return authorId;
	}
}

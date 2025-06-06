package com.example.hibernate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.example.hibernate.model.Author;

public class CreateAuthorDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField firstNameField;
	private JTextField middleNameField;
	private JTextField lastNameField;
	private JLabel lblError;

	

	private Author author = null;

	public Author getResult() {
		return this.author;
	}

	public CreateAuthorDialog(Window owner, String title, ModalityType modalityType) {
		super(owner, title, modalityType);
		initComponents();
		this.setLocationRelativeTo(owner);
	}

	private void initComponents() {
		setBounds(100, 100, 500, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblFirstName = new JLabel("First Name*:");
		lblFirstName.setBounds(20, 20, 100, 25);
		contentPanel.add(lblFirstName);

		firstNameField = new JTextField();
		firstNameField.setBounds(130, 20, 300, 25);
		contentPanel.add(firstNameField);

		JLabel lblMiddleName = new JLabel("Middle Name:");
		lblMiddleName.setBounds(20, 60, 100, 25);
		contentPanel.add(lblMiddleName);

		middleNameField = new JTextField();
		middleNameField.setBounds(130, 60, 300, 25);
		contentPanel.add(middleNameField);

		JLabel lblLastName = new JLabel("Last Name*:");
		lblLastName.setBounds(20, 100, 100, 25);
		contentPanel.add(lblLastName);

		lastNameField = new JTextField();
		lastNameField.setBounds(130, 100, 300, 25);
		contentPanel.add(lastNameField);

		
	
	

		// Mensaje de error
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(20, 250, 410, 25);
		lblError.setVisible(false);
		contentPanel.add(lblError);

		// Panel de botones
		JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

	

		JButton createButton = new JButton("Crear nuevo autor/a");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String first = firstNameField.getText().trim();
				String middle = middleNameField.getText().trim();
				String last = lastNameField.getText().trim();

				if (first.isEmpty() || last.isEmpty()) {
					lblError.setText("El nombre y el apellido son obligatorios.");
					lblError.setVisible(true);
					return;
				}
				author = new Author(first, middle, last);
				dispose();
			}
		});
		buttonPane.add(createButton);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(e -> {
			author = null;
			dispose();
		});
		buttonPane.add(cancelButton);
	}
}

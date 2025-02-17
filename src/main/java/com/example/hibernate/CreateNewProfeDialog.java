package com.example.hibernate;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.example.hibernate.model.Profesor;

public class CreateNewProfeDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldApe1;
    private JTextField textFieldApe2;
    private JTextField textFieldNombreProfe;
    private JButton okButton;
    
    private Profesor profeACrearOActualizar = null;
    
    public Profesor getResult() {
        return this.profeACrearOActualizar;
    }

    public void initComponents() {
        setBounds(100, 100, 598, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre.setBounds(39, 34, 208, 24);
        contentPanel.add(lblNombre);

        textFieldNombreProfe = new JTextField();
        textFieldNombreProfe.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldNombreProfe.setBounds(330, 35, 197, 23);
        contentPanel.add(textFieldNombreProfe);
        textFieldNombreProfe.setColumns(10);

        JLabel lblProfeApe1 = new JLabel("Apellido 1");
        lblProfeApe1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProfeApe1.setBounds(39, 82, 140, 24);
        contentPanel.add(lblProfeApe1);

        textFieldApe1 = new JTextField();
        textFieldApe1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldApe1.setBounds(330, 83, 197, 23);
        contentPanel.add(textFieldApe1);
        textFieldApe1.setColumns(10);
        
        JLabel lblProfeApe2 = new JLabel("Apellido 2");
        lblProfeApe2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProfeApe2.setBounds(39, 130, 140, 24); // Ajustado más abajo
        contentPanel.add(lblProfeApe2);

        textFieldApe2 = new JTextField();
        textFieldApe2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldApe2.setBounds(330, 131, 197, 23); // Ajustado más abajo
        contentPanel.add(textFieldApe2);
        textFieldApe2.setColumns(10);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        okButton = new JButton("Guardar");
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                profeACrearOActualizar = null;
                CreateNewProfeDialog.this.dispose();
            }
        });
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);

        ActionListener crearBtnActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!textFieldApe1.getText().trim().isEmpty()
                        && !textFieldNombreProfe.getText().trim().isEmpty()
                        && !textFieldApe2.getText().trim().isEmpty()) {
                    if (profeACrearOActualizar == null) {
                        profeACrearOActualizar = new Profesor();
                    }
                    profeACrearOActualizar.setNombre(textFieldNombreProfe.getText().trim());
                    profeACrearOActualizar.setApe1(textFieldApe1.getText().trim());
                    profeACrearOActualizar.setApe2(textFieldApe2.getText().trim()); // Corregido
                    CreateNewProfeDialog.this.dispose();
                }
            }
        };

        this.okButton.addActionListener(crearBtnActionListener);
    }

    public CreateNewProfeDialog(Window owner, String title, ModalityType modalityType, Profesor dept) {
        super(owner, title, modalityType);
        initComponents();
        profeACrearOActualizar = dept;
        if (profeACrearOActualizar != null) {
            textFieldNombreProfe.setText(profeACrearOActualizar.getNombre());
            textFieldApe1.setText(profeACrearOActualizar.getApe1());
            textFieldApe2.setText(profeACrearOActualizar.getApe2());
        }
        this.setLocationRelativeTo(owner);
    }
}

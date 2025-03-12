package Controller;

import DAO.ExamenDAO;
import Model.Examen;
import View.frmExamen;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CtrlExamen implements ActionListener {
    private final ExamenDAO consultas;
    private final frmExamen vista;
    private final Examen modelo;

    public CtrlExamen(frmExamen vista, Examen modelo, ExamenDAO consultas) {
        this.vista = vista;
        this.consultas = consultas;
        this.modelo = modelo;
        
        // Agregar el listener al botón de agregar
        this.vista.btnAdd.addActionListener(this);
    }

    public void Listar() {
        List<Examen> examenes = consultas.getAllExamenes();
        DefaultTableModel model = (DefaultTableModel) vista.jtblistar.getModel();
        model.setRowCount(0); // Limpiar filas existentes
        model.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Descripción", "Costo"});

        for (Examen examen : examenes) {
            model.addRow(new Object[]{
                examen.getIdExamen(),
                examen.getNomExamen(),
                examen.getDescExamen(),
                examen.getCosto()
            });
        }
    }

    public void iniciar() { 
        vista.setTitle("Exámenes de Laboratorio");
        vista.setLocationRelativeTo(null);
        Listar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAdd) {
            try {
                modelo.setIdExamen(Integer.parseInt(vista.txtIdExamen.getText()));
                modelo.setNomExamen(vista.txtNomExamen.getText());
                modelo.setDescExamen(vista.txtDescExamen.getText());
                modelo.setCosto(Double.parseDouble(vista.txtCosto.getText()));

                if (consultas.addExamen(modelo)) {
                    JOptionPane.showMessageDialog(null, "Examen registrado exitosamente.");
                    Listar();
                } else {
                    JOptionPane.showMessageDialog(null, "No fue posible registrar el examen.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: Verifica los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
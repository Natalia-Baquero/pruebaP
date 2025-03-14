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
        
        //evento de los botones 
        this.vista.btnAdd.addActionListener(this);
        this.vista.btnEdit.addActionListener(this);
        this.vista.btnSave.addActionListener(this);
        this.vista.btnDelete.addActionListener(this);
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

    // SELECCIONAR DATOS PARA EDITAR
    else if (e.getSource() == vista.btnEdit) {
        int fila = vista.jtblistar.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un examen de la tabla.");
            return;
        }
        //carga la informacion en los campos de texto 
        vista.txtIdExamen.setText(vista.jtblistar.getValueAt(fila, 0).toString());
        vista.txtNomExamen.setText(vista.jtblistar.getValueAt(fila, 1).toString());
        vista.txtDescExamen.setText(vista.jtblistar.getValueAt(fila, 2).toString());
        vista.txtCosto.setText(vista.jtblistar.getValueAt(fila, 3).toString());
    }

    // Boton de guardar 
    else if (e.getSource() == vista.btnSave) {
        try {
            modelo.setIdExamen(Integer.parseInt(vista.txtIdExamen.getText()));
            modelo.setNomExamen(vista.txtNomExamen.getText());
            modelo.setDescExamen(vista.txtDescExamen.getText());
            modelo.setCosto(Double.parseDouble(vista.txtCosto.getText()));

            //actualiza los datos en la bd
            if (consultas.updateExamen(modelo)) {
                JOptionPane.showMessageDialog(null, "Examen actualizado correctamente.");
                Listar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar examen.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error: Verifica los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Elimina datos 
    else if (e.getSource() == vista.btnDelete) {
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Seguro que deseas eliminar este examen?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            int idExamen = Integer.parseInt(vista.txtIdExamen.getText());
            if (consultas.deleteExamen(idExamen)) {
                JOptionPane.showMessageDialog(null, "Examen eliminado correctamente.");
                Listar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar examen.");
            }
        }
    }
}
}
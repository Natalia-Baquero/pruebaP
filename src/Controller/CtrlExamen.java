package Controller;

import DAO.ExamenDAO;
import Model.Examen;
import View.frmExamen;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class CtrlExamen {
    private final ExamenDAO consultas;
    private final frmExamen vista;
    private final Examen modelo;

    public CtrlExamen(frmExamen vista, Examen modelo, ExamenDAO consultas) {
        this.vista = vista;
        this.consultas = consultas;
        this.modelo = modelo;
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
        vista.setTitle("Examenes de Laboratorio");
        vista.setLocationRelativeTo(null);
        Listar();
    }
}

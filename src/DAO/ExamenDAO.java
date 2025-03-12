package DAO;

import Connection.MySQLConnection;
import Model.Examen;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamenDAO extends MySQLConnection {
    
    public List<Examen> getAllExamenes() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Examen> examenes = new ArrayList<>();
        Connection con = conectar();
        String sql = "SELECT * FROM examen"; 

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Examen examen = new Examen();
                examen.setIdExamen(rs.getInt("id")); 
                examen.setNomExamen(rs.getString("nombre"));
                examen.setDescExamen(rs.getString("descripcion"));
                examen.setCosto(rs.getDouble("costo"));
                examenes.add(examen);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener exámenes: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar conexión: " + ex.getMessage());
            }
        }
        return examenes;
    }
    
    public boolean addExamen(Examen examen) {
        String sql = "INSERT INTO examen (id, nombre, descripcion, costo) VALUES (?, ?, ?, ?)";

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, examen.getIdExamen());
            ps.setString(2, examen.getNomExamen());
            ps.setString(3, examen.getDescExamen());
            ps.setDouble(4, examen.getCosto());
            ps.execute();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al agregar examen: " + e.getMessage());
            return false;
        }
    }
    
}
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventarioDAO {

    private Connection conexion;

    public InventarioDAO() {
        try {
            conexion = new ConexionDAO().conexionMysql();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ingresarInventario(Long id_producto, String precio_coste, String precio_publico, String cantidad) throws SQLException {
        String query = "INSERT INTO inventario (id_producto, precio_coste, precio_publico, cantidad) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setLong(1, id_producto);
            stmt.setString(2, precio_coste);
            stmt.setString(3, precio_publico);
            stmt.setString(4, cantidad);
            stmt.executeUpdate();
        }

    }
}

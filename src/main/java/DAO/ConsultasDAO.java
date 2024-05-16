/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CompuFire
 */
public class ConsultasDAO {

    ConexionDAO con = new ConexionDAO();

    public List<Usuario> consultarUsuarios() throws Exception {
        List<Usuario> Usuarios = new ArrayList<Usuario>();

        try {
            String query = "SELECT id, nombre, puesto, usuario, contraseña FROM usuarios";
            Statement s = con.conexionMysql().createStatement();
            ResultSet r = s.executeQuery(query);

            while (r.next()) {
                Usuario usuario = new Usuario();
                
                usuario.setId(r.getLong("id"));
                usuario.setNombre(r.getString("nombre"));
                usuario.setPuesto(r.getString("puesto"));
                usuario.setUsuario(r.getString("usuario"));
                usuario.setContraseña(r.getString("contraseña"));
                Usuarios.add(usuario);
            }
        } catch (Exception e) {
            System.out.println("Error al consultar usuarios");
        } finally {
            if (con != null) {
                try {
                    con.conexionMysql().close();
                    System.out.println("Cierre de conexion exitosa");
                } catch (SQLException ex) {
                    System.out.println("Error al cerrar conexion");
                }
            }
        }

        return Usuarios;
    }

}

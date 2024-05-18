/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    private Connection conexion;

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

    public void insertarUsuario(String nombre, String puesto, String usuario, String contraseña) throws SQLException {

        String query = "INSERT INTO usuarios (nombre, puesto, usuario, contraseña) VALUES ('" + nombre + "','" + puesto + "','" + usuario + "','" + contraseña + "')";
        try {

            //String query="INSERT INTO clientes VALUES ('"+cliente.getNombre()+"','"+cliente.getCorreo()+"','"+cliente.getDireccion()+"','"+cliente.getTelefono()+"')";
            //String query = "INSERT INTO clientes VALUES (6, 'isai', 'isaimixia18@gmail.com','Santa Luxia', '48407205')";  
            Statement s = con.conexionMysql().createStatement();
            s.executeUpdate(query);

            System.out.println("-------------------Datos Insertados--------------------------------");
            System.out.println("Nombre: " + nombre + "Puesto: " + puesto + "Usuario: " + usuario + "Contraseña: " + contraseña);
            System.out.println("---------------------------------------------------");

        } catch (Exception e) {
            System.out.println("Error al realizar la insercion");
        }

        System.out.println("-------------------Datos--------------------------------");
        System.out.println("QUERY: " + query);
        System.out.println("Nombre: " + nombre + " Puesto: " + puesto + " Usuario: " + usuario + " Contraseña: " + contraseña);
        System.out.println("---------------------------------------------------");
    }

}

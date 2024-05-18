package DTO;

import DAO.ConsultasDAO;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import DAO.InventarioDAO;

@ManagedBean(name = "bkn_InventarioDTO")
public class InventarioDTO implements Serializable {

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the puesto
     */
    public String getPuesto() {
        return puesto;
    }

    /**
     * @param puesto the puesto to set
     */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    /**
     * @return the contraseña
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * @param contraseña the contraseña to set
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * @return the listaUsuarios
     */
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * @param listaUsuarios the listaUsuarios to set
     */
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    //usuarios
    private String nombre;
    private String usuario;
    private String puesto;
    private String contraseña;
    //producto
    private Long id;
    private Long id_producto;
    private String precio_coste;
    private String precio_publico;
    private String cantidad;
    private List<InventarioDTO> lista;
    private List<Usuario> listaUsuarios;
    InventarioDAO consulta = new InventarioDAO();

    public InventarioDTO() {
    }

    @PostConstruct
    public void init() {
        // Puedes inicializar algún dato aquí si es necesario

        listarUsuarios();
        System.out.println("-------------------------------------------------PRUEBA DE IMPRESION----------------------------------------------------------------");
    }

    public void listarUsuarios() {

        ConsultasDAO consulta = new ConsultasDAO();

        try {
            setListaUsuarios(consulta.consultarUsuarios());
            System.out.println("Usuarios: " + consulta.consultarUsuarios());
        } catch (Exception e) {
            System.out.println("Error al listar usuarios");
        }

    }

    public void agregarInventario() {
        System.out.println("Código Producto: " + getId_producto());
        System.out.println("Precio Coste: " + getPrecio_coste());
        System.out.println("Precio Público: " + getPrecio_publico());
        System.out.println("Cantidad: " + getCantidad());
        try {
            consulta.ingresarInventario(getId_producto(), getPrecio_coste(), getPrecio_publico(), getCantidad());
        } catch (Exception ex) {
            Logger.getLogger(InventarioDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void agregarUsuario() {

        ConsultasDAO consulta = new ConsultasDAO();
        
        try {
            //consulta.ingresarInventario(getId_producto(), getPrecio_coste(), getPrecio_publico(), getCantidad());
            consulta.insertarUsuario(getNombre(), getPuesto(), getUsuario(), getContraseña());
            System.out.println("DATOS USUARIO: "+getNombre()+":"+getPuesto()+":"+getUsuario()+":"+getContraseña());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al agregar usuario");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

    public String getPrecio_coste() {
        return precio_coste;
    }

    public void setPrecio_coste(String precio_coste) {
        this.precio_coste = precio_coste;
    }

    public String getPrecio_publico() {
        return precio_publico;
    }

    public void setPrecio_publico(String precio_publico) {
        this.precio_publico = precio_publico;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public List<InventarioDTO> getLista() {
        return lista;
    }

    public void setLista(List<InventarioDTO> lista) {
        this.lista = lista;
    }
}

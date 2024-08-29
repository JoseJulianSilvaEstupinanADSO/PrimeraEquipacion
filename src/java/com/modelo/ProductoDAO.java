/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelo;

// Importación de la clase Conexion para gestionar la conexión a la base de datos
import com.conexion.Conexion;
// Importación de clases necesarias para la gestión de SQL y colecciones
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

/**
 * Clase que gestiona operaciones CRUD para la entidad Producto.
 * Esta clase extiende de la clase Conexion y maneja la comunicación
 * con la base de datos para realizar operaciones relacionadas con productos.
 * 
 * @author Propietario
 */
public class ProductoDAO extends Conexion {

    /**
     * Lista todas las tallas disponibles en la base de datos.
     * 
     * @return List<Producto> - Lista de objetos Producto con la información de las tallas.
     */
    public List<Producto> ListarTallas() {
        // Lista para almacenar las tallas obtenidas de la base de datos
        List<Producto> tallas = new ArrayList<>();
        
        try {
            // Establece la conexión con la base de datos
            this.conectar();
            
            // Consulta SQL para seleccionar todas las tallas disponibles
            String sql = "SELECT * FROM talla";
            // Preparación de la sentencia SQL para su ejecución
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            // Ejecución de la consulta y obtención del resultado
            ResultSet rs = pre.executeQuery();
            
            // Iteración sobre los resultados de la consulta
            while (rs.next()) {                
                // Creación de un nuevo objeto Producto para almacenar la talla
                Producto p = new Producto();
                p.setTalla(rs.getString("talla"));
                
                // Adición del objeto Producto a la lista de tallas
                tallas.add(p);
            }
            
        } catch (SQLException e) {
            // Manejo de excepciones relacionadas con SQL
            e.printStackTrace();
        } finally {
            // Desconexión de la base de datos para liberar recursos
            this.desconectar();
        }
        
        // Retorno de la lista de tallas
        return tallas;
    }
    
    /**
     * Agrega un nuevo producto a la base de datos.
     * 
     * @param p - Objeto Producto que contiene la información del producto a agregar.
     * @return boolean - Retorna true si la operación fue exitosa, false en caso contrario.
     */
    public boolean AgregarProducto(Producto p) {
        try {
            // Establece la conexión con la base de datos
            this.conectar();
            
            // Consulta SQL para insertar un nuevo producto en la tabla 'producto'
            String sql = "INSERT INTO producto(nombre, precio, estado) VALUES (?,?,?)";
            // Preparación de la sentencia SQL para su ejecución, con retorno de claves generadas
            PreparedStatement pre = this.getCon().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Asignación de valores a la sentencia SQL
            pre.setString(1, p.getNombre());
            pre.setInt(2, Integer.parseInt(p.getPrecio()));
            pre.setInt(3, Integer.parseInt(p.getEstado()));
            
            // Ejecución de la inserción y obtención del número de filas afectadas
            int exito = pre.executeUpdate();
            
            // Verificación de si la inserción fue exitosa
            if (exito > 0) {
                // Obtención de la clave generada para el nuevo producto
                ResultSet rs = pre.getGeneratedKeys();
                if (rs.next()) {
                    int id_producto = rs.getInt(1);
                    
                    System.out.println(id_producto); // Imprime el ID del producto
                    
                    // Consulta SQL para insertar los detalles del producto en la tabla 'producto_desc'
                    String sql2 = "INSERT INTO producto_desc(id_producto, talla, stock, tela) VALUES (?,?,?,?)";
                    // Preparación de la sentencia SQL para la inserción de detalles
                    PreparedStatement pre2 = this.getCon().prepareStatement(sql2);
                    // Asignación de valores a la sentencia SQL
                    pre2.setInt(1, id_producto);
                    pre2.setString(2, p.getTalla());
                    pre2.setInt(3, Integer.parseInt(p.getStock()));
                    pre2.setString(4, p.getTela());
                    
                    // Ejecución de la inserción de detalles del producto
                    pre2.executeUpdate();
                    
                    // Retorna true si la inserción fue exitosa
                    return true;
                }
            }
         
        } catch (SQLException e) {
            // Manejo de excepciones relacionadas con SQL
            e.printStackTrace();
            return false;
            
        } finally {
            // Desconexión de la base de datos para liberar recursos
            this.desconectar();
        }
        
        // Retorno de false si la operación no fue exitosa
        return false;
    }
    
    /**
     * Lista todos los productos con sus detalles asociados.
     * 
     * @return List<Producto> - Lista de objetos Producto con la información completa de cada producto.
     */
    public List<Producto> ListarProductos() {
        // Lista para almacenar los productos obtenidos de la base de datos
        List<Producto> productos = new ArrayList<>();
        
        try {
            // Establece la conexión con la base de datos
            this.conectar();
            
            // Consulta SQL para seleccionar todos los productos y sus detalles
            String sql = "SELECT producto.id_producto, nombre, precio, estado, talla, stock, tela FROM producto JOIN producto_desc ON producto.id_producto = producto_desc.id_producto";
            // Preparación de la sentencia SQL para su ejecución
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            // Ejecución de la consulta y obtención del resultado
            ResultSet rs = pre.executeQuery();
            
            // Iteración sobre los resultados de la consulta
            while (rs.next()) {                
                // Creación de un nuevo objeto Producto para almacenar los detalles del producto
                Producto p = new Producto();
                p.setId_producto(rs.getString("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getString("precio"));
                p.setTalla(rs.getString("talla"));
                p.setStock(rs.getString("stock"));
                p.setTela(rs.getString("tela"));
                p.setEstado(rs.getString("estado"));
                
                // Adición del objeto Producto a la lista de productos
                productos.add(p);
            }
            
        } catch (SQLException e) {
            // Manejo de excepciones relacionadas con SQL
            e.printStackTrace();
        } finally {
            // Desconexión de la base de datos para liberar recursos
            this.desconectar();
        }
        
        // Retorno de la lista de productos
        return productos;
    }
    
    /**
     * Modifica un producto existente en la base de datos.
     * 
     * @param p - Objeto Producto que contiene la información actualizada del producto.
     * @return boolean - Retorna true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean ModificarProducto(Producto p) {
        try {
            // Establece la conexión con la base de datos
            this.conectar();
            
            // Consulta SQL para actualizar los detalles del producto en las tablas correspondientes
            String sql = "UPDATE producto p JOIN producto_desc pd ON p.id_producto = pd.id_producto JOIN talla t ON pd.talla = t.talla SET nombre = ?, precio = ?, stock = ?, estado = ? WHERE p.id_producto = ? AND t.talla=?";
            // Preparación de la sentencia SQL para la actualización
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            
            System.out.println(p.getId_producto()); // Imprime el ID del producto
            
            // Asignación de valores a la sentencia SQL
            pre.setString(1, p.getNombre());
            pre.setInt(2, Integer.parseInt(p.getPrecio()));
            pre.setInt(3, Integer.parseInt(p.getStock()));
            pre.setInt(4, Integer.parseInt(p.getEstado()));
            pre.setInt(5, Integer.parseInt(p.getId_producto()));
            pre.setString(6, p.getTalla());

            // Ejecución de la actualización y retorno true si la operación fue exitosa
            return pre.executeUpdate() > 0;
            
        } catch (SQLException e) {
            // Manejo de excepciones relacionadas con SQL
            e.printStackTrace();
            return false;
            
        } finally {
            // Desconexión de la base de datos para liberar recursos
            this.desconectar();
        }
    }
    
    /**
     * Busca un producto específico por ID y talla.
     * 
     * @param id_producto - Identificador del producto a buscar.
     * @param talla - Talla del producto a buscar.
     * @return Producto - Objeto Producto con la información del producto encontrado, o null si no se encuentra.
     */
    public Producto BuscarProducto(String id_producto, String talla) {
        Producto p = null;
        
        try {
            // Establece la conexión con la base de datos
            this.conectar();
            
            // Consulta SQL para seleccionar un producto específico por ID y talla
            String sql = "SELECT p.id_producto, p.nombre, p.precio, p.estado, pd.stock, pd.talla, pd.tela FROM producto p JOIN producto_desc pd ON p.id_producto = pd.id_producto JOIN talla t ON pd.talla = t.talla WHERE p.id_producto = ? AND t.talla = ? AND estado = 1";
            // Preparación de la sentencia SQL para su ejecución
            PreparedStatement pre = this.getCon().prepareStatement(sql);
                    
            // Asignación de valores a la sentencia SQL
            pre.setInt(1, Integer.parseInt(id_producto));
            pre.setString(2, talla);
            
            // Ejecución de la consulta y obtención del resultado
            ResultSet rs = pre.executeQuery();
            
            // Verificación de si se obtuvo un resultado
            if (rs.next()) {
                // Creación del objeto Producto con la información obtenida
                p = new Producto(
                        rs.getString("id_producto"),
                        rs.getString("talla"),
                        rs.getString("nombre"),
                        rs.getString("precio"),
                        rs.getString("stock"),
                        rs.getString("tela"),
                        rs.getString("estado")
                );
            }
            System.out.println(p); // Imprime el producto encontrado
            
        } catch (SQLException e) {
            // Manejo de excepciones relacionadas con SQL
            e.printStackTrace();
        } finally {
            // Desconexión de la base de datos para liberar recursos
            this.desconectar();
        }
        
        // Retorno del objeto Producto encontrado, o null si no se encontró
        return p;
    }
}

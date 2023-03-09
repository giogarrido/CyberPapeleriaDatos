package implementaciones;

import entidades.Inventario;
import interfaces.IConexionBD;
import interfaces.IInventariosDAO;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author giova
 */
public class InventariosDAO implements IInventariosDAO {

    private final IConexionBD conexion;

    public InventariosDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean agregar(Inventario inventario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizar(Inventario inventario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean quitarStock(Inventario inventario, int stock) {
        try {
            EntityManager em = this.conexion.crearConexion();
            Inventario inventarioBD = em.find(Inventario.class, inventario.getId());
            em.getTransaction().begin();
            inventarioBD.setCantidadExistencia(inventarioBD.getCantidadExistencia() - stock);
            em.getTransaction().commit();
            return true;

        } catch (IllegalStateException ise) {
            System.err.println("No fue posible eliminar el producto");
            ise.printStackTrace();
            return false;
        }
    }

    @Override
    public Inventario consultar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Inventario> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

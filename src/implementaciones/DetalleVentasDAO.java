package implementaciones;

import entidades.DetalleVenta;
import entidades.Inventario;
import entidades.Venta;
import interfaces.IConexionBD;
import interfaces.IDetalleVentasDAO;
import interfaces.IInventariosDAO;
import javax.persistence.EntityManager;

/**
 *
 * @author Giovanni Garrido
 */
public class DetalleVentasDAO implements IDetalleVentasDAO {

   private final IConexionBD conexion;
    
    private IInventariosDAO inventariosDAO;
    
    public DetalleVentasDAO(IConexionBD conexion, IInventariosDAO inventariosDAO){
        this.conexion = conexion;
        this.inventariosDAO = inventariosDAO;
    }
    
    @Override
    public boolean agregar(DetalleVenta venta){
        
       try{
           EntityManager em = this.conexion.crearConexion();
           em.getTransaction().begin();
           
           Inventario inventarioBD = em.find(Inventario.class, venta.getInventario().getId());
           
           venta.setInventario(inventarioBD);
           
           Venta ventaBD = em.find(Venta.class, venta.getVenta().getId());
           
           venta.setVenta(ventaBD);
           
           em.persist(venta);
           
           inventariosDAO.quitarStock(inventarioBD, venta.getCantidad());
           
           
           em.getTransaction().commit();
           return true;
       }catch (IllegalStateException ise){
           System.err.println("No fue posible guardar la venta");
           ise.printStackTrace();
           return false;
       }
    }


}

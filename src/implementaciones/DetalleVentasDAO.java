package implementaciones;

import entidades.DetalleVenta;
import entidades.Producto;
import entidades.Venta;
import interfaces.IConexionBD;
import interfaces.IDetalleVentasDAO;
import interfaces.IProductosDAO;
import javax.persistence.EntityManager;

/**
 *
 * @author Giovanni Garrido
 */
public class DetalleVentasDAO implements IDetalleVentasDAO {

   private final IConexionBD conexion;
    
    private IProductosDAO productosDAO;
    
    public DetalleVentasDAO(IConexionBD conexion, IProductosDAO productosDAO){
        this.conexion = conexion;
        this.productosDAO = productosDAO;
    }
    
    @Override
    public boolean agregar(DetalleVenta venta){
        
       try{
           EntityManager em = this.conexion.crearConexion();
           em.getTransaction().begin();
           
           Producto productoBD = em.find(Producto.class, venta.getProducto().getId());
           
           venta.setProducto(productoBD);
           
           Venta ventaBD = em.find(Venta.class, venta.getVenta().getId());
           
           venta.setVenta(ventaBD);
           
           em.persist(venta);
           
           productosDAO.quitarStock(productoBD, venta.getCantidad());
           
           
           em.getTransaction().commit();
           return true;
       }catch (IllegalStateException ise){
           System.err.println("No fue posible guardar la venta");
           ise.printStackTrace();
           return false;
       }
    }


}

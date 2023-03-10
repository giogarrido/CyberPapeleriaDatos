 
package implementaciones;

import entidades.Producto;
import interfaces.IConexionBD;
import interfaces.IProductosDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author giova
 */
public class ProductosDAO implements IProductosDAO{

    private final IConexionBD conexion;
    
    public ProductosDAO(IConexionBD conexion){
        this.conexion = conexion;
    }
    
    @Override
    public boolean agregar(Producto producto) {

       try{
           EntityManager em = this.conexion.crearConexion();
           em.getTransaction().begin();
           em.persist(producto);
           em.getTransaction().commit();
           return true;
       }catch (IllegalStateException ise){
           System.err.println("No fue posible agregar el producto");
           ise.printStackTrace();
           return false;
       }
        
    }

    @Override
    public boolean actualizar(Producto producto) {

        try{
            
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            
            Producto productoBD = em.find(Producto.class, producto.getId());
            
            productoBD.setNombre(producto.getNombre());
            productoBD.setDescripcion(productoBD.getDescripcion());
            productoBD.setPrecioCompra(productoBD.getPrecioCompra());
            productoBD.setStock(productoBD.getStock());
            productoBD.setPrecioVenta(productoBD.getPrecioVenta());
           
            em.getTransaction().commit();
            return true;
        }catch (IllegalStateException ise){
            System.err.println("No fue posible actualizar el producto");
            ise.printStackTrace();
            return false;
        }
    
    }

    @Override
    public boolean eliminar(int id) {

        try{
        EntityManager em = this.conexion.crearConexion();
        Producto productoBD = em.find(Producto.class, id);
        em.getTransaction().begin();
        em.remove(productoBD);
        em.getTransaction().commit();
        return true;
        
        }catch (IllegalStateException ise){
            System.err.println("No fue posible eliminar el producto");
            ise.printStackTrace();
            return false;
        }
    
    }

    @Override
    public boolean quitarStock(Producto producto, int stock) {
        try{
        EntityManager em = this.conexion.crearConexion();
        Producto productoBD = em.find(Producto.class, producto.getId());
        em.getTransaction().begin();
        productoBD.setStock(productoBD.getStock() - stock);
        em.getTransaction().commit();
        return true;
        
        }catch (IllegalStateException ise){
            System.err.println("No fue posible eliminar el producto");
            ise.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean agregarStock(Producto producto, int stock) {
        try{
        EntityManager em = this.conexion.crearConexion();
        Producto productoBD = em.find(Producto.class, producto.getId());
        em.getTransaction().begin();
        productoBD.setStock(productoBD.getStock() + stock);
        em.getTransaction().commit();
        return true;
        
        }catch (IllegalStateException ise){
            System.err.println("No fue posible agregar el producto");
            ise.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Producto consultar(int id) {
        
        try{
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            
            Producto productoBD = em.find(Producto.class, id);
            
            em.getTransaction().commit();
            return productoBD;
        }catch (IllegalStateException ise){
            System.err.println("No se pudo consultar el producto");
            ise.printStackTrace();
            return null;
        }
        
    }
    
    @Override
    public List<Producto> consultarPorNombre(String nombre){
        
        try{
            EntityManager em = this.conexion.crearConexion();
            
            CriteriaBuilder builder = em.getCriteriaBuilder();
            
            CriteriaQuery<Producto> criteria = builder.createQuery(Producto.class);
            
            Root<Producto> root = criteria.from(Producto.class);
            
            criteria = criteria.select(root).where(builder.like(root.get("nombre"), "%"+ nombre +"%"));
            
            TypedQuery<Producto> query = em.createQuery(criteria);
            
            return query.getResultList();
            
        }catch(IllegalStateException ise){
            System.err.println("No se pudo consultar los productos por nombre");
            ise.printStackTrace();
            return null;
        }
                
    }

    @Override
    public List<Producto> consultarTodos() {

        try{
            EntityManager em = this.conexion.crearConexion();
            
            CriteriaBuilder builder = em.getCriteriaBuilder();
            
            CriteriaQuery<Producto> criteria = builder.createQuery(Producto.class);
            
            TypedQuery<Producto>query = em.createQuery(criteria);
            
            return query.getResultList();
        }catch(IllegalStateException ise){
            System.err.println("No se pudierón consultar los Productos");
            ise.printStackTrace();
            return null;
        }

    
    } 
}

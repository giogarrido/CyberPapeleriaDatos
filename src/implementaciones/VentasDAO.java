
package implementaciones;

import entidades.Caja;
import entidades.Cliente;
import entidades.Venta;
import interfaces.ICajasDAO;
import interfaces.IClientesDAO;
import interfaces.IConexionBD;
import interfaces.IVentasDAO;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Giovanni Garrido
 */
public class VentasDAO implements IVentasDAO{
    
    private final IConexionBD conexion;
     
    
    public VentasDAO(IConexionBD conexion){
        this.conexion = conexion;

    }
    
    @Override
    public int agregar(Venta venta){
        
        int idVenta = -1;
        
       try{
           EntityManager em = this.conexion.crearConexion();
           em.getTransaction().begin();
           
           Cliente clienteBD = em.find(Cliente.class, venta.getCliente().getId());
           
           Caja cajaBD = em.find(Caja.class, venta.getCaja().getId());
           
           venta.setCliente(clienteBD);
           venta.setCaja(cajaBD);
           
           em.persist(venta);
           em.flush();
           
           idVenta = venta.getId();

           em.getTransaction().commit();
           return idVenta;
       }catch (IllegalStateException ise){
           System.err.println("No fue posible guardar la venta");
           ise.printStackTrace();
           return idVenta;
       }
    }

    @Override
    public Venta consultar(int id) {
        
        try {
            EntityManager em = this.conexion.crearConexion();
                        
            Venta venta =  em.find(Venta.class, id);
            
            return venta;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible consultar la venta");
            ise.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Venta> consultarTodas() {
        List<Venta> ventas = null;

        try {

            EntityManager em = conexion.crearConexion();

            em.getTransaction().begin();

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Venta> criteria = builder.createQuery(Venta.class);
            TypedQuery<Venta> query = em.createQuery(criteria);

            ventas = query.getResultList();

            em.getTransaction().commit();
        } catch (IllegalStateException ex) {
            System.err.println("No se pudieron consultar todas las ventas");
            ex.printStackTrace();
            return null;
        }

        return ventas;
    }

    @Override
    public List<Venta> buscarEntre(Calendar inicio, Calendar fin) {
        List<Venta> ventas = null;

        try {

            EntityManager em = conexion.crearConexion();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            String fechaInicio = dateFormat.format(inicio.getTimeInMillis());
            
            String fechaFin = dateFormat.format(fin.getTimeInMillis());
            
            return em.createQuery(String.format("SELECT v FROM Venta v WHERE v.fecha >= '%s' AND v.fecha <= '%s'", fechaInicio , fechaFin)).getResultList();

        } catch (IllegalStateException ex) {
            System.err.println("No se pudieron consultar las ventas entre las fechas dadas");
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Venta> buscarEntreCliente(Calendar inicio, Calendar fin, Cliente cliente) {
        List<Venta> ventas = null;

        try {

            EntityManager em = conexion.crearConexion();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            String fechaInicio = dateFormat.format(inicio.getTimeInMillis());
            
            String fechaFin = dateFormat.format(fin.getTimeInMillis());
            
            return em.createQuery(String.format("SELECT v FROM Venta v WHERE v.fecha >= '%s' AND v.fecha <= '%s' AND v.cliente.id = %d", fechaInicio , fechaFin, cliente.getId())).getResultList();

        } catch (IllegalStateException ex) {
            System.err.println("No se pudieron consultar las ventas del cliente en la fecha dada");
            ex.printStackTrace();
            return null;
        }
    }
}

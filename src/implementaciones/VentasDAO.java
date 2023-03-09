
package implementaciones;

import entidades.Venta;
import interfaces.IConexionBD;
import interfaces.IVentasDAO;
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

    public VentasDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean agregar(Venta venta) {

        EntityManager em = conexion.crearConexion();

        try {
            em.getTransaction().begin();
            em.persist(venta);
            em.getTransaction().commit();
            return true;

        } catch (IllegalStateException ex) {
            System.err.println("No fue posible agregar la venta");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(Venta venta) {

        EntityManager em = conexion.crearConexion();

        try {
            em.getTransaction().begin();

            Venta ventaBD = em.find(Venta.class, venta.getId());

            ventaBD.setFecha(venta.getFecha());
            ventaBD.setNumTicket(venta.getNumTicket());
            ventaBD.setTotalventa(venta.getTotalventa());
            ventaBD.setCliente(venta.getCliente());

            em.getTransaction().commit();
            return true;

        } catch (IllegalStateException ex) {
            System.err.println("No fue posible agregar la venta");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {

        EntityManager em = conexion.crearConexion();

        try {
            em.getTransaction().begin();

            Venta ventaBD = em.find(Venta.class, id);

            em.remove(ventaBD);

            em.getTransaction().commit();
            return true;

        } catch (IllegalStateException ex) {
            System.err.println("No fue posible eliminar la venta");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Venta consultar(int id) {

        EntityManager em = conexion.crearConexion();

        try {

            em.getTransaction().begin();

            Venta ventaBD = em.find(Venta.class, id);

            em.getTransaction().commit();

            return ventaBD;

        } catch (IllegalStateException ex) {
            System.err.println("No fue posible consultar la venta");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Venta> consultarTodos() {
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
            System.err.println("No fue posible consultar todas las ventas");
            ex.printStackTrace();
            return null;
        }

        return ventas;

    }
}

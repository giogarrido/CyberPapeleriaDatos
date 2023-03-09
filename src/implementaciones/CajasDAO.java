package implementaciones;

import entidades.Caja;
import interfaces.IConexionBD;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import interfaces.ICajasDAO;

/**
 *
 * @author Giovanni Garrido
 */
public class CajasDAO implements ICajasDAO {

    private final IConexionBD conexion;

    public CajasDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean agregar(Caja caja) {
        EntityManager em = conexion.crearConexion();

        try {
            em.getTransaction().begin();
            em.persist(caja);
            em.getTransaction().commit();
            return true;

        } catch (IllegalStateException ex) {
            System.err.println("No fe posible agregar la caja");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(Caja caja) {
        EntityManager em = conexion.crearConexion();

        try {
            em.getTransaction().begin();

            Caja cajaBD = em.find(Caja.class, caja.getId());

            cajaBD.setEstado(caja.getEstado());
            cajaBD.setFechaApertura(caja.getFechaApertura());
            cajaBD.setFechaCierre(caja.getFechaCierre());
            cajaBD.setSaldoCierre(caja.getSaldoCierre());
            cajaBD.setSaldoInicial(caja.getSaldoInicial());
            cajaBD.setTotalGastos(caja.getTotalGastos());
            cajaBD.setTotalIngresos(caja.getTotalIngresos());

            em.getTransaction().commit();
            return true;

        } catch (IllegalStateException ex) {
            System.err.println("No fue posible actualizar la caja");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        EntityManager em = conexion.crearConexion();

        try {
            em.getTransaction().begin();

            Caja cajaBD = em.find(Caja.class, id);

            em.remove(cajaBD);

            em.getTransaction().commit();
            return true;

        } catch (IllegalStateException ex) {
            System.err.println("No fue posible eliminar la caja");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Caja consultar(int id) {
        EntityManager em = conexion.crearConexion();

        try {

            em.getTransaction().begin();

            Caja cajaBD = em.find(Caja.class, id);

            em.getTransaction().commit();

            return cajaBD;

        } catch (IllegalStateException ex) {
            System.err.println("No fue posible consultar la caja");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Caja> consultarTodos() {
        List<Caja> cajas = null;
        try {
            EntityManager em = conexion.crearConexion();

            em.getTransaction().begin();

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Caja> criteria = builder.createQuery(Caja.class);
            TypedQuery<Caja> query = em.createQuery(criteria);

            cajas = query.getResultList();

            em.getTransaction().commit();
        } catch (IllegalStateException ex) {
            System.err.println("No fue posible consultar todas las cajas");
            ex.printStackTrace();
            return null;
        }

        return cajas;

    }
}

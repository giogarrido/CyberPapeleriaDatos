package implementaciones;

import entidades.Caja;
import entidades.Cliente;
import entidades.Gasto;
import interfaces.IConexionBD;
import interfaces.IGastosDAO;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Giovani Garrido
 */
public class GastosDAO implements IGastosDAO {

    private final IConexionBD conexion;

    public GastosDAO(IConexionBD conexion) {
        this.conexion = conexion;

    }

    @Override
    public int agregar(Gasto gasto) {
        int idGasto = -1;

        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();

            Caja cajaBD = em.find(Caja.class, gasto.getCaja().getId());

            gasto.setCaja(cajaBD);

            em.persist(gasto);
            em.flush();

            idGasto = gasto.getId();

            em.getTransaction().commit();
            return idGasto;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible guardar el gasto");
            ise.printStackTrace();
            return idGasto;
        }
    }

    @Override
    public Gasto consultar(int id) {
        try {
            EntityManager em = this.conexion.crearConexion();

            Gasto gasto = em.find(Gasto.class, id);

            return gasto;
        } catch (IllegalStateException ise) {
            System.err.println("No fue posible consultar el gasto");
            ise.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Gasto> consultarTodas() {
        List<Gasto> ventas = null;

        try {

            EntityManager em = conexion.crearConexion();

            em.getTransaction().begin();

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Gasto> criteria = builder.createQuery(Gasto.class);
            TypedQuery<Gasto> query = em.createQuery(criteria);

            ventas = query.getResultList();

            em.getTransaction().commit();
        } catch (IllegalStateException ex) {
            System.err.println("No se pudieron consultar todos los gastos");
            ex.printStackTrace();
            return null;
        }

        return ventas;
    }

    @Override
    public List<Gasto> buscarEntre(Calendar inicio, Calendar fin) {
        List<Gasto> gastos = null;

        try {

            EntityManager em = conexion.crearConexion();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String fechaInicio = dateFormat.format(inicio.getTimeInMillis());

            String fechaFin = dateFormat.format(fin.getTimeInMillis());

            return em.createQuery(String.format("SELECT v FROM Gasto v WHERE v.fecha >= '%s' AND v.fecha <= '%s'", fechaInicio, fechaFin)).getResultList();

        } catch (IllegalStateException ex) {
            System.err.println("No se pudieron consultar los gastos entre las fechas dadas");
            ex.printStackTrace();
            return null;
        }
    }

}

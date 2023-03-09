package implementaciones;

import entidades.Usuario;
import interfaces.IConexionBD;
import interfaces.IUsuariosDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Giovanni Garrido
 */
public class UsuariosDAO implements IUsuariosDAO{

    private final IConexionBD conexion;

    public UsuariosDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean agregar(Usuario usuario) {

        EntityManager em = conexion.crearConexion();

        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
            return true;

        } catch (IllegalStateException ex) {
            System.err.println("No se pudo agregar al usuario");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(Usuario usuario) {

        EntityManager em = conexion.crearConexion();

        try {
            em.getTransaction().begin();

            Usuario usuarioBD = em.find(Usuario.class, usuario.getId());

            usuarioBD.setNombre(usuario.getNombre());
            usuarioBD.setPassword(usuario.getPassword());
            usuarioBD.setRol(usuario.getRol());

            em.getTransaction().commit();
            return true;

        } catch (IllegalStateException ex) {
            System.err.println("No se pudo actualizar al usuario");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {

        EntityManager em = conexion.crearConexion();

        try {
            em.getTransaction().begin();

            Usuario usuarioBD = em.find(Usuario.class, id);

            em.remove(usuarioBD);

            em.getTransaction().commit();
            return true;

        } catch (IllegalStateException ex) {
            System.err.println("No se pudo eliminar al usuario");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Usuario consultar(int id) {

        EntityManager em = conexion.crearConexion();

        try {

            em.getTransaction().begin();
            
            Usuario usuarioBD = em.find(Usuario.class, id);
            
            em.getTransaction().commit();

            return usuarioBD;
            
        } catch (IllegalStateException ex) {
            System.err.println("No se pudo consultar al cliente");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Usuario> consultarTodos() {
        List<Usuario> usuarios = null;

        try {

            EntityManager em = conexion.crearConexion();

            em.getTransaction().begin();

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
            TypedQuery<Usuario> query = em.createQuery(criteria);

            usuarios = query.getResultList();

            em.getTransaction().commit();
        } catch (IllegalStateException ex) {
            System.err.println("No se pudieron consultar todos los usuarios");
            ex.printStackTrace();
            return null;
        }

        return usuarios;

    }
}

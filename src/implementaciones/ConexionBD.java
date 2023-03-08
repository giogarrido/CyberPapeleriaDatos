package implementaciones;

import interfaces.IConexionBD;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Giovanni Garrido
 */
public class ConexionBD implements IConexionBD {

    @Override
    public EntityManager crearConexion() {
        try {
            EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("CyberPapeleriaDominioPU2");
            EntityManager em = emFactory.createEntityManager();
            return em;
        } catch (Exception e) {
            System.out.println("");
        }
        return null;
    }

}

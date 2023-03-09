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
    public EntityManager crearConexion() throws IllegalStateException {

        //EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("PapeleriaPU");
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("PapeleriaPU");
        EntityManager em = emFactory.createEntityManager();
        
        return em;
    }

}


package interfaces;

import javax.persistence.EntityManager;



/**
 *
 * @author Giovanni Garrido
 */
public interface IConexionBD {
        
    EntityManager crearConexion() throws IllegalStateException;
}

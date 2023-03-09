
package interfaces;

import entidades.Caja;
import java.util.List;

/**
 *
 * @author Giovanni Garrido
 */
public interface ICajasDAO {
    
    boolean agregar(Caja caja);
    
    boolean actualizar(Caja caja);
    
    boolean eliminar(int id);
    
    Caja consultar(int id);
    
    List<Caja> consultarTodos();
}

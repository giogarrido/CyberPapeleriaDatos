
package interfaces;

import entidades.Cliente;
import java.util.List;

/**
 *
 * @author Giovanni Garrido
 */
public interface IClientesDAO {
    
    boolean agregar(Cliente cliente);
    
    boolean actualizar(Cliente cliente);
    
    boolean eliminar(int id);
    
    Cliente consultar(int id);
    
    List<Cliente> consultarTodos();
}

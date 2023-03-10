
package interfaces;

import entidades.Proveedor;
import java.util.List;

/**
 *
 * @author Giovanni Garrido
 */
public interface IProveedoresDAO {
    
    boolean agregar(Proveedor proveedor);
    
    boolean actualizar(Proveedor proveedor);
    
    boolean eliminar(int id);
    
    Proveedor consultar(int id);
    
    List<Proveedor> consultarTodos();

}

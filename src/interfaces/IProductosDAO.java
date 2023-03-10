
package interfaces;

import entidades.EntradaAlmacen;
import entidades.Producto;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

/**
 *
 * @author Giovanni Garrido
 */
public interface IProductosDAO {

    boolean agregar(Producto producto);
    
    boolean actualizar(Producto producto);
    
    boolean eliminar(int id);
    
    boolean quitarStock(Producto producto , int stock);
    
    boolean agregarStock(Producto producto , int stock);
    
    Producto consultar(int id);
    
    List<Producto> consultarPorNombre(String nombre);
    
    List<Producto> consultarTodos();
}

package interfaces;

import entidades.EntradaAlmacen;
import entidades.Producto;
import entidades.Proveedor;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Giovanni Garrido
 */
public interface IEntradasAlmacenDAO {

    boolean agregar(EntradaAlmacen entradaAlmacen);

    EntradaAlmacen consultar(int id);

    List<EntradaAlmacen> consultarTodos();
    
    List<EntradaAlmacen> buscarEntre (Calendar inicio, Calendar fin);
    
    List<EntradaAlmacen> buscarEntreProveedores (Calendar inicio, Calendar fin, Proveedor proveedor);
    
    List<EntradaAlmacen> buscarEntreProductos (Calendar inicio, Calendar fin, Producto producto);
}

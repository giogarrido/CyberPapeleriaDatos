package interfaces;

import entidades.Venta;
import java.util.List;

/**
 *
 * @author giova
 */
public interface IVentasDAO {

    boolean agregar(Venta venta);

    boolean actualizar(Venta venta);

    boolean eliminar(int id);

    Venta consultar(int id);

    List<Venta> consultarTodos();
}

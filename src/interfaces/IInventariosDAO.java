package interfaces;

import entidades.Inventario;
import java.util.List;

/**
 *
 * @author giova
 */
public interface IInventariosDAO {

    boolean agregar(Inventario inventario);

    boolean actualizar(Inventario inventario);

    boolean eliminar(int id);

    boolean quitarStock(Inventario inventario, int stock);

    Inventario consultar(int id);

    List<Inventario> consultarTodos();
}

package interfaces;

import entidades.Categoria;
import entidades.Cliente;
import java.util.List;

/**
 *
 * @author Giovanni Garrido
 */
public interface ICategoriasDAO {

    boolean agregar(Categoria categoria);

    boolean actualizar(Categoria categoria);

    boolean eliminar(int id);

    Categoria consultar(int id);

    List<Categoria> consultarTodos();
}

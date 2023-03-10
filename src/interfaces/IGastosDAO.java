
package interfaces;

import entidades.Caja;
import entidades.Gasto;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Giovanni Garrido
 */
public interface IGastosDAO {
    
    int agregar(Gasto gasto);
    
    Gasto consultar(int id);
    
    List<Gasto> consultarTodas();
    
    List<Gasto> buscarEntre(Calendar inicio , Calendar fin);   
    
}

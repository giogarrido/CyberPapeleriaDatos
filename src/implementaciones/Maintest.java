package implementaciones;

import entidades.Categoria;
import entidades.Cliente;
import interfaces.IClientesDAO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Giovanni Garrido
 */
public class Maintest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IClientesDAO clientesDAO;//        
//    EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("CyberPapeleriaDominioPU");
//        EntityManager em = emFactory.createEntityManager();
//        em.getTransaction().begin();
//
//       Cliente cliente = new Cliente("Pedro", "pegsd2354", "pedro@gonzalez", "59868547");
////        cliente = em.find(Cliente.class, 1);
//        Categoria categoria = new Categoria("Papeleria");
//        //categoria = em.find(Categoria.class, 1);
//        em.persist(cliente);
//        
//        em.getTransaction().commit();

        clientesDAO = new ClientesDAO(new ConexionBD());
//        System.out.println(clientesDAO.consultar(1).getEmail());

        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes = clientesDAO.consultarTodos();

        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(clientes.get(i));
        }

    }

}

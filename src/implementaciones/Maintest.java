
package implementaciones;

import entidades.Cliente;
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
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("CyberPapeleriaDominioPU");
        EntityManager em = emFactory.createEntityManager();  
        
        Cliente cliente = new Cliente("Pedro", "pegsd2354", "pedro@gonzalez", "59868547");
        cliente = em.find(Cliente.class, 1);
        
        em.getTransaction().begin();
        em.getTransaction().commit();
    }
    
}

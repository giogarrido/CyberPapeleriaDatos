package implementaciones;

import entidades.Caja;
import entidades.Categoria;
import entidades.Cliente;
import entidades.DetalleVenta;
import entidades.Usuario;
import entidades.Venta;
import enumeradores.Estado;
import static enumeradores.Estado.ABIERTA;
import enumeradores.Rol;
import interfaces.ICajasDAO;
import interfaces.IClientesDAO;
import interfaces.IInventariosDAO;
import interfaces.IUsuariosDAO;
import interfaces.IVentasDAO;
import java.util.ArrayList;
import java.util.Calendar;
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
        IVentasDAO ventasDAO;// 
        ICajasDAO cajasDAO;
        IUsuariosDAO usuariosDAO;
        IInventariosDAO inventariosDAO;
        IClientesDAO clientesDAO;
//        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("CyberPapeleriaDominioPU");
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

//                clientesDAO = new ClientesDAO(new ConexionBD());
//        ////        System.out.println(clientesDAO.consultar(1).getEmail());
//        //
//                List<Cliente> clientes = new ArrayList<Cliente>();
//                clientes = clientesDAO.consultarTodos();
//        
//                for (int i = 0; i < clientes.size(); i++) {
//                    System.out.println(clientes.get(i));
//                }
        cajasDAO = new CajasDAO(new ConexionBD());
        ventasDAO = new VentasDAO(new ConexionBD());
        usuariosDAO = new UsuariosDAO(new ConexionBD());
        inventariosDAO = new InventariosDAO(new ConexionBD());
        clientesDAO = new ClientesDAO(new ConexionBD());
        //Cliente cliente = new Cliente(1);
        DetalleVenta detalleVenta = new DetalleVenta(1);

        //usuariosDAO.agregar(new Usuario("Adrian", "adrian", Rol.VENDEDOR));
        //System.out.println(usuariosDAO.consultar(1));
        
//
        Caja caja = new Caja(Calendar.getInstance(), 0, 0, 0, 0, ABIERTA, usuariosDAO.consultar(1));
        
//        cajasDAO.agregar(caja);


        Venta venta = new Venta(1, Calendar.getInstance(), 100, clientesDAO.consultar(1), cajasDAO.consultar(1));

        ventasDAO.agregar(venta);
        

    }

}

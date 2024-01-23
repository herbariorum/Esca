import org.esca.app.auth.dao.impl.RoleDAOImpl;
import org.esca.app.auth.dominio.Roles;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class RoleDAOImplTest {

    @Test
    public void tesAddRole(){
        Roles roles = new Roles();
        RoleDAOImpl roleDAO = new RoleDAOImpl();
//        roles.setId(1L);
        roles.setRole("TESTE");
        roleDAO.addRole(roles);
        assertNotNull(roles.getId());
    }

    @Test
    public void uptadeRole(){
        Roles roles = new Roles();
        RoleDAOImpl roleDAO = new RoleDAOImpl();
        roles.setId(1L);
        roles.setRole("ADIMIN");
        roleDAO.updateRole(roles);
        assertNotNull(roles.getId());
    }

    @Test
    public void deleteRole(){
        Roles roles = new Roles();
        RoleDAOImpl roleDAO = new RoleDAOImpl();
        roles.setId(4L);
        roleDAO.deleteRole(roles);

    }

    @Test
    public void getAll(){
        RoleDAOImpl roleDAO = new RoleDAOImpl();
        List<Roles> roles = roleDAO.selectRole();
        for (Roles r : roles){
            System.out.println("Roles: "+r.getRole());
        }
    }
}

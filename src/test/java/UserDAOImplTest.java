import org.esca.app.auth.dao.impl.UsuarioDAOImpl;
import org.esca.app.auth.dominio.Roles;
import org.esca.app.auth.dominio.Usuarios;
import org.esca.app.util.Util;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class UserDAOImplTest {

    public void save(Usuarios user){
        UsuarioDAOImpl dao = new UsuarioDAOImpl();
        if (user.getId() == null){
            dao.addUser(user);
            assertNotNull(user.getId());
        }else {
            dao.updateUser(user);
            assertNotNull(user.getId());
        }
    }
    @Test
    public void adicionaUsuario(){
        Usuarios user = new Usuarios();
        user.setId(null);
        user.setNome("JULIANA GOMES DE LIMA");
        user.setEmail("eliaspbareia@gmail.com");
        user.setPassword(new Util().gerarPassword("20mP'd!`46zz"));
        user.setCargo("Diretor");
        user.setPhone("63991111196");
        user.setCreated_at(LocalDate.now());
        Roles role = new Roles();
        role.setId(2L);
        user.setRole(role);
        save(user);
    }

    @Test
    public void atualizaUsuario(){
        Usuarios user = new Usuarios();
        user.setId(1L);
        user.setNome("JOSÉ ELIAS GOMES DE LIMA");
        user.setEmail("eliaspbareia@gmail.com");
        user.setPassword(new Util().gerarPassword("20mP'd!`46zz"));
        user.setCargo("Coordenador");
        user.setPhone("63991111196");
        user.setCreated_at(LocalDate.now());
        Roles role = new Roles();
        role.setId(1L);
        user.setRole(role);
        save(user);
    }

    @Test
    public void deleteUsuario(){
        UsuarioDAOImpl dao = new UsuarioDAOImpl();
        Usuarios user = new Usuarios();
        user.setId(2L);
        dao.deleteUser(user);
    }

    @Test
    public void getAll(){
        UsuarioDAOImpl dao = new UsuarioDAOImpl();
        List<Usuarios> useres = dao.selectUser();
        for (Usuarios u : useres){
            System.out.println("Usuários "+u.getNome());
        }
    }

    @Test
    public void getByEmail(){
        UsuarioDAOImpl dao = new UsuarioDAOImpl();
        Usuarios user = new Usuarios();
        user.setEmail("eliaspbareia@gmaill.com");
        Usuarios u = dao.selectEmailUser(user);
        if (u != null){
            System.out.println("Usuário por Email " + u.getNome());
        }else{
            System.out.println("Usuário não encontrado");
        }
    }

    @Test
    public void selectByValue(){
        UsuarioDAOImpl dao = new UsuarioDAOImpl();

        List<Usuarios> u = dao.selectByValue("adriana");
        if (u.isEmpty()){
            System.out.println("Nulo");
        }else{
            for (Usuarios user : u) {
                System.out.println("Usuarios " + user.getNome());
            }
        }
    }
}

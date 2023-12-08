import exemplo.java.spark.dto.input.UsuarioDTOInput;
import exemplo.java.spark.dto.output.UsuarioDTOOutput;
import exemplo.java.spark.model.Usuario;

import java.util.Collections;
import java.util.List;
import exemplo.java.spark.services.UsuarioService;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ServiceTest {
    @Test
    public void testeInsercao() {
        UsuarioService usuarioService = new UsuarioService();
        UsuarioDTOInput usuarioDTOInput = new UsuarioDTOInput(3,"user", "senha123");

        usuarioService.adicionarUsuario(usuarioDTOInput);

        assertEquals(3,usuarioService.listarUsuarios().size());
    }

    @Test
    public void testeBuscarPorId() {
        UsuarioService usuarioService = new UsuarioService();

        List<UsuarioDTOOutput> num = Collections.singletonList(usuarioService.buscarUsuario(1));
        assertEquals(1,num.size());
    }

    @Test
    public void testeEditarUsuario() {
        UsuarioService usuarioService = new UsuarioService();
        UsuarioDTOInput usuario = new UsuarioDTOInput(2, "Editado", "12345");

        usuarioService.alterarUsuario(usuario);
        String nome = "Editado";

        assertEquals(nome, usuarioService.buscarUsuario(2).getNome());
    }

    @Test
    public void testeExclusaoUsuario() {
        UsuarioService usuarioService = new UsuarioService();

        usuarioService.removerUsuario(1);

        assertEquals(1,usuarioService.listarUsuarios().size());
    }
}

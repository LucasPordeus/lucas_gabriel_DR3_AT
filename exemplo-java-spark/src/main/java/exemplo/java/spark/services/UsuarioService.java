
package exemplo.java.spark.services;

import exemplo.java.spark.model.Usuario;
import exemplo.java.spark.dto.input.UsuarioDTOInput;
import exemplo.java.spark.dto.output.UsuarioDTOOutput;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;

public class UsuarioService {

    private final List<Usuario> listaUsuarios = new ArrayList<>();
    private final ModelMapper modelMapper = new ModelMapper();

    public UsuarioService() {
        listaUsuarios.add(new Usuario(1, "Usuario1", "senha1"));
        listaUsuarios.add(new Usuario(2, "Usuario2", "senha2"));
    }

    public List<UsuarioDTOOutput> listarUsuarios() {
        List<UsuarioDTOOutput> listaUsuarioDTOOutputs = new ArrayList<>();
        for (Usuario usuario : listaUsuarios) {
            listaUsuarioDTOOutputs.add(modelMapper.map(usuario, UsuarioDTOOutput.class));
        }
        return listaUsuarioDTOOutputs;
    }

    public void adicionarUsuario(UsuarioDTOInput usuarioDTOInput) {
        Usuario usuario = modelMapper.map(usuarioDTOInput, Usuario.class);
        listaUsuarios.add(usuario);
    }

    public void alterarUsuario(UsuarioDTOInput usuarioDTOInput) {
        Usuario usuarioAtualizado = modelMapper.map(usuarioDTOInput, Usuario.class);
        for (int i = 0; i < listaUsuarios.size(); i++) {
            Usuario usuarioExistente = listaUsuarios.get(i);
            if (usuarioExistente.getId() == usuarioAtualizado.getId()) {
                listaUsuarios.set(i, usuarioAtualizado);
                break;
            }
        }
    }

    public UsuarioDTOOutput buscarUsuario(long id) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId() == id) {
                return modelMapper.map(usuario, UsuarioDTOOutput.class);
            }
        }
        return null;
    }

    public void removerUsuario(long id) {
        listaUsuarios.removeIf(usuario -> usuario.getId() == id);
    }
}

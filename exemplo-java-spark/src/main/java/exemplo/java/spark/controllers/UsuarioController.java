package exemplo.java.spark.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import exemplo.java.spark.dto.input.UsuarioDTOInput;
import exemplo.java.spark.services.UsuarioService;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.delete;
import static spark.Spark.put;

public class UsuarioController {

    private final UsuarioService usuarioService = new UsuarioService();
    private final ObjectMapper objMapper = new ObjectMapper();

    public void respostasRequisicoes() {
        get("/usuarios", (request, response) -> {
            response.type("application/json");
            response.status(200);
            String json = objMapper.writeValueAsString(usuarioService.listarUsuarios());
            return json;
        });

        get("/usuarios/:id", (request, response) -> {
            response.type("application/json");
            String idParam = request.params("id");
            Integer id = Integer.valueOf(idParam);
            String json = objMapper.writeValueAsString(usuarioService.buscarUsuario(id));
            response.status(200);
            return json;
        });

        post("/usuarios", (request, response) -> {
            UsuarioDTOInput usuarioDTOInput = objMapper.readValue(request.body(), UsuarioDTOInput.class);
            usuarioService.adicionarUsuario(usuarioDTOInput);
            response.type("application/json");
            response.status(201);
            return "Usuário inserido com sucesso.";
        });

        put("/usuarios", (request, response) -> {
            UsuarioDTOInput usuarioDTOInput = objMapper.readValue(request.body(), UsuarioDTOInput.class);
            usuarioService.alterarUsuario(usuarioDTOInput);
            response.type("application/json");
            response.status(200);
            return "Usuário alterado com sucesso.";
        });

        delete("/usuarios/:id", (request, response) -> {
            response.type("application/json");
            String idParam = request.params("id");
            Integer id = Integer.valueOf(idParam);
            usuarioService.removerUsuario(id);
            response.status(200);
            return "Usuário excluido com sucesso.";
        });
    }
    public UsuarioController() {
        this.respostasRequisicoes();
    }



}

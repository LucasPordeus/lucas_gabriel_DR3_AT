
package exemplo.java.spark.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTOInput {
    private Integer id;
    private String nome;
    private String senha;

    public UsuarioDTOInput(Integer id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }

}

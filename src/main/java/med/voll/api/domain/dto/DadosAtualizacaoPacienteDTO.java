package med.voll.api.domain.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoPacienteDTO(

        @NotNull
        Long id,

        String nome,

        @Pattern(regexp = "\\d{11}", message = "Formato do telefone é inválido")
        String telefone,

        EnderecoCompletoDTO endereco) {
}

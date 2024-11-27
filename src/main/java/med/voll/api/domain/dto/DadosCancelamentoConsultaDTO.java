package med.voll.api.domain.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.enums.MotivoCancelamento;

public record DadosCancelamentoConsultaDTO(

        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivo) {
}

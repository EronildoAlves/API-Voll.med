package med.voll.api.domain.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.enums.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsultaDTO(

        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade) {
}

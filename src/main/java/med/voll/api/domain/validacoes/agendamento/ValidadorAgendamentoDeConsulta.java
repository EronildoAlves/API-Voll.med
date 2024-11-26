package med.voll.api.domain.validacoes.agendamento;

import med.voll.api.domain.dto.DadosAgendamentoConsultaDTO;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsultaDTO dados);
}

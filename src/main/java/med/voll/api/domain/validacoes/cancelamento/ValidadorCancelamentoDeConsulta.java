package med.voll.api.domain.validacoes.cancelamento;

import med.voll.api.domain.dto.DadosCancelamentoConsultaDTO;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsultaDTO dados);
}

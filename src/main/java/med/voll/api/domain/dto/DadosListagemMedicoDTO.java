package med.voll.api.domain.dto;

import med.voll.api.domain.entities.Medico;
import med.voll.api.domain.enums.Especialidade;

public record DadosListagemMedicoDTO(
        Long id,

        String nome,

        String email,

        String telefone,

        String crm,

        Especialidade especialidade) {

    public DadosListagemMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade());
    }
}

package med.voll.api.domain.dto;

import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.entities.Medico;
import med.voll.api.domain.enums.Especialidade;

public record DadosDetalhamentoMedicoDTO(
        String nome,

        String email,

        String telefone,

        String crm,

        Especialidade especialidade,

        Endereco endereco) {

    public DadosDetalhamentoMedicoDTO(Medico medico){
        this(medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}

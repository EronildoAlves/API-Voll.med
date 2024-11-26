package med.voll.api.domain.dto;

import med.voll.api.domain.entities.Paciente;

public record DadosListagemPacienteDTO(
        Long id,

        String nome,

        String email,

        String telefone) {

    public DadosListagemPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone());
    }
}

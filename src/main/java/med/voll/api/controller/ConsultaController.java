package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.dto.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.dto.DadosCancelamentoConsultaDTO;
import med.voll.api.domain.dto.DadosDetalhamentoConsulta;
import med.voll.api.domain.dto.DadosDetalhamentoConsultaDTO;
import med.voll.api.service.AgendaConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> agenda(@RequestBody @Valid DadosAgendamentoConsultaDTO dados) {
        var dto = consultaService.agendar(dados);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancelar(@RequestBody @Valid DadosCancelamentoConsultaDTO dados) {
        consultaService.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}

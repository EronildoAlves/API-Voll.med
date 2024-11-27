package med.voll.api.controller;

import med.voll.api.domain.dto.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.dto.DadosDetalhamentoConsulta;
import med.voll.api.domain.enums.Especialidade;
import med.voll.api.service.AgendaConsultaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    //objeto utilitário para converter o json
    //simula o json de entrada
    private JacksonTester<DadosAgendamentoConsultaDTO> dadosAgendamentoConsultaDTOJson;

    @Autowired
    //simula o json de saída
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson;

    @MockBean
    private AgendaConsultaService agendaConsultaService;

    @Test
    @DisplayName("Deveria devolver código http 400 quando informações estão invalidas")
    void agendar_cenario1() throws Exception {
       var response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("Deveria devolver código http 200 quando informações estão validas")
    void agendar_cenario2() throws Exception {
        var data = LocalDate.now().plusYears(1);
        var especialidade = Especialidade.CARDIOLOGIA;

        var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 5l, 4l, data.atStartOfDay());

        when(agendaConsultaService.agendar(any())).thenReturn(dadosDetalhamento);

        var response = mvc
                .perform(
                        post("/consultas")
                                //envia dados no formato json para o servidor
                                .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAgendamentoConsultaDTOJson.write(
                                new DadosAgendamentoConsultaDTO(5l, 4l, data.atStartOfDay(), especialidade)
                        ).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoConsultaJson.write(
                dadosDetalhamento
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

}
package br.com.martins.valdelar.service;

import br.com.martins.valdelar.dto.CotacaoDTO;
import br.com.martins.valdelar.model.Cotacao;
import br.com.martins.valdelar.repository.CotacaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.zip.DataFormatException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CotacaoServiceTest {

    @Autowired
    private CotacaoService service;

    @MockBean
    private CotacaoRepository repository;

    @MockBean
    ApiCotacao api;

    @Test
    @DisplayName("buscaCotacaoPorDia NoResultException")
    public void buscaCotacaoPorDiaException() {
        String response = "";

        Mockito.doReturn(Optional.of(response)).when(api).getCotacao(Mockito.anyString());

        Exception exception =Assertions.assertThrows( NoResultException.class, () -> service.buscaCotacaoPorDia(LocalDate.now()));
        Assertions.assertTrue(exception.getMessage().equals("Nao houve resultado na busca"));
    }

    @Test
    @DisplayName("buscaCotacaoPorDia Entidade nao salva")
    public void buscaCotacaoPorDiaNaoSalva() throws DataFormatException {
        String response = "{\n" +
                "    \"@odata.context\": \"https://was-p.bcnet.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata$metadata#_CotacaoDolarDia\",\n" +
                "    \"value\": [\n" +
                "        {\n" +
                "            \"cotacaoCompra\": 5.9366,\n" +
                "            \"cotacaoVenda\": 5.9372,\n" +
                "            \"dataHoraCotacao\": \"2020-05-14 13:04:37.655\"\n" +
                "        }\n" +
                "    ]\n" +
                "} ";

        Mockito.doReturn(Optional.of(response)).when(api).getCotacao(Mockito.anyString());
        Mockito.doReturn(new Cotacao()).when(repository).save(Mockito.any());

        Optional<CotacaoDTO> cotacaoDTO = service.buscaCotacaoPorDia(LocalDate.now());

        Assertions.assertEquals(cotacaoDTO, Optional.ofNullable(new CotacaoDTO()));
    }


    @Test
    @DisplayName("buscaCotacaoPorDia Entidade sucesso")
    public void buscaCotacaoPorDiaSucesso() throws DataFormatException {
        LocalDateTime agora = LocalDateTime.now();
        Cotacao cotacao = new Cotacao();
        cotacao.setDataHoraCotacao(agora);
        cotacao.setCotacaoVenda(new BigDecimal("5.9372"));
        cotacao.setCotacaoCompra(new BigDecimal("5.9366"));

        String response = "{\n" +
                "    \"@odata.context\": \"https://was-p.bcnet.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata$metadata#_CotacaoDolarDia\",\n" +
                "    \"value\": [\n" +
                "        {\n" +
                "            \"cotacaoCompra\": 5.9366,\n" +
                "            \"cotacaoVenda\": 5.9372,\n" +
                "            \"dataHoraCotacao\": \"2020-05-14 13:04:37.655\"\n" +
                "        }\n" +
                "    ]\n" +
                "} ";

        Mockito.doReturn(Optional.of(response)).when(api).getCotacao(Mockito.anyString());
        Mockito.doReturn(cotacao).when(repository).save(Mockito.any());

        Optional<CotacaoDTO> cotacaoDTO = service.buscaCotacaoPorDia(LocalDate.now());

        Assertions.assertNotNull(cotacaoDTO);
        Assertions.assertEquals(cotacaoDTO.get().getCotacaoCompra(), new BigDecimal("5.9366"));
        Assertions.assertEquals(cotacaoDTO.get().getCotacaoVenda(), new BigDecimal("5.9372"));
        Assertions.assertEquals(cotacaoDTO.get().getDataHoraCotacao(),agora);
    }

}

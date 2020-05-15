package br.com.martins.valdelar.service;

import br.com.martins.valdelar.dto.CotacaoDTO;
import br.com.martins.valdelar.model.Cotacao;
import br.com.martins.valdelar.repository.CotacaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@Slf4j
public class CotacaoServiceImpl implements CotacaoService {

    @Autowired
    private ApiCotacao apiCotacao;

    @Autowired
    private CotacaoRepository cotacaoRepository;

    @Override
    public Optional<CotacaoDTO> buscaCotacaoPorDia(LocalDate data) throws DataFormatException {
        final String dataFormatada = data.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        Optional<String> cotacao = apiCotacao.getCotacao(dataFormatada);

        cotacao.ifPresent(this::salvaCotacao);

        log.info("cotacao do dia " + dataFormatada + " eh " + cotacao);
        return Optional.empty();
    }

    private void salvaCotacao(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
          //  mapper.registerModule(new JavaTimeModule());
            //mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            //DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

            Cotacao cotacao = new Cotacao();

            JsonNode json = mapper.readTree(response).get("value");

            if (json == null)
                throw new NoResultException();

            cotacao.setCotacaoCompra(json.get(0).get("cotacaoCompra").decimalValue());
            cotacao.setCotacaoVenda(json.get(0).get("cotacaoVenda").decimalValue());
            cotacao.setDataHoraCotacao(LocalDateTime.parse(json.get(0).get("dataHoraCotacao").asText().replace(' ', 'T')));


            if (cotacao != null)
                log.info(cotacao.getCotacaoCompra().toString());

            cotacaoRepository.save(cotacao);
        } catch (JsonProcessingException e) {
            log.error("Erro ao tentar converter resposta", e);
        }
    }
}

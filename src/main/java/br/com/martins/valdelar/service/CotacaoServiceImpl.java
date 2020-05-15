package br.com.martins.valdelar.service;

import br.com.martins.valdelar.dto.CotacaoDTO;
import br.com.martins.valdelar.model.Cotacao;
import br.com.martins.valdelar.repository.CotacaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        if (cotacao.isPresent()) {
            Cotacao entidadeSalva = this.salvaCotacao(cotacao.get());
            return Optional.ofNullable(CotacaoDTO.entityToDto(entidadeSalva));
        }

        return Optional.empty();
    }

    private Cotacao salvaCotacao(String response) {

        Cotacao cotacao = convertJsonToEntity(response);

        if (cotacao != null) {
            log.info("Salvando a cotacao " + cotacao);
            try {
                return cotacaoRepository.save(cotacao);
            } catch (Exception e) {
                log.error("Erro ao salvar no banco de dados", e);
            }

        }
        return null;
    }

    private Cotacao convertJsonToEntity(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Cotacao cotacao = new Cotacao();

            JsonNode jsonNode = mapper.readTree(response).get("value");

            if (jsonNode == null || jsonNode.get(0) == null)
                throw new NoResultException("Nao houve resultado na busca");

            cotacao.setCotacaoCompra(jsonNode.get(0).get("cotacaoCompra").decimalValue());
            cotacao.setCotacaoVenda(jsonNode.get(0).get("cotacaoVenda").decimalValue());
            cotacao.setDataHoraCotacao(LocalDateTime.parse(jsonNode.get(0).get("dataHoraCotacao").asText().replace(' ', 'T')));
            return cotacao;
        } catch (JsonProcessingException e) {
            log.error("Erro ao tentar converter resposta", e);
        }

        return null;
    }
}

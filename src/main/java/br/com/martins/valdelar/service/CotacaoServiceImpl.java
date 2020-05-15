package br.com.martins.valdelar.service;

import br.com.martins.valdelar.dto.CotacaoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@Slf4j
public class CotacaoServiceImpl implements CotacaoService {

    @Autowired
    private ApiCotacao apiCotacao;

    @Override
    public Optional<CotacaoDTO> buscaCotacaoPorDia(LocalDate data) throws DataFormatException {
        final String dataFormatada = data.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        Optional<String> cotacao = apiCotacao.getCotacao(dataFormatada);
        log.info("cotacao do dia " + dataFormatada + " eh " + cotacao);
        return Optional.empty();
    }
}

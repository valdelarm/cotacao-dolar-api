package br.com.martins.valdelar.service;

import br.com.martins.valdelar.dto.CotacaoDTO;

import java.time.LocalDate;
import java.util.Optional;
import java.util.zip.DataFormatException;

public interface ApiCotacao {

    /**
     *
     * @param data
     * @return a cotacao do dia de acordo com a API externa.
     */
    Optional<String> getCotacao(String data);
}

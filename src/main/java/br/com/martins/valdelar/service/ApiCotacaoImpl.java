package br.com.martins.valdelar.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;

@Service
@Slf4j
public class ApiCotacaoImpl implements ApiCotacao {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.servico.cotacao}")
    private String urlServico;

    @Override
    public Optional<String> getCotacao(String data) {
        try {
             return Optional.of(restTemplate.getForEntity(buildUrl(data), String.class).getBody());
        } catch (RestClientException e) {
           log.error("Erro ao chamar o servico", e);
        }
        return Optional.empty();
    }


    private String buildUrl(final String data) {
        String formatedUrl = String.format(urlServico, data);
        log.info("Get cotacao " + formatedUrl);

        return formatedUrl;

    }
}

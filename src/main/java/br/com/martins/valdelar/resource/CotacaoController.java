package br.com.martins.valdelar.resource;

import br.com.martins.valdelar.dto.CotacaoDTO;
import br.com.martins.valdelar.service.CotacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping("api/v1/cotacao")
@Api("API de consulta a cotação do dólar comercial")
@Slf4j
public class CotacaoController {

    @Autowired
    private CotacaoService cotacaoService;

    @GetMapping("/{data}")
    @ApiOperation(value = "Busca a cotação de um dia específico a ser informado", notes = "formato dd-mm-aaaa",
            response = CotacaoDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cotação encontrada"),
            @ApiResponse(code = 404, message = "Cotação não encontrada"),
            @ApiResponse(code = 400, message = "Data ou formato inválido. Tente dd-mm-aaaa"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    public ResponseEntity<?> getCotacaoDeDeterminadoDia(@PathVariable LocalDate data) {
        log.info("Consulta de cotacao do dia " + data);

        try {
            return cotacaoService.buscaCotacaoPorDia(data)
                    .map(cotacao -> {
                        try {
                            return ResponseEntity
                                    .ok()
                                    .location(new URI("cotacao/" + data))
                                    .body(cotacao);
                        } catch (URISyntaxException e) {
                            log.error("Erro " + e);
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                        }
                    }).orElse(ResponseEntity.notFound().build());
        } catch (DataFormatException e) {
            log.error("Data com formato invalido");
            return ResponseEntity.badRequest().build();
        }
    }
}

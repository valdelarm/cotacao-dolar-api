package br.com.martins.valdelar.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/cotacao")
@Api("API de consulta a cotação do dólar comercial")
@Slf4j
public class DolarController {

    @GetMapping
    @ApiOperation("Busca a cotação do dia")
    public ResponseEntity getCotacao() {

        log.info("cotacao");
        return new ResponseEntity(HttpStatus.OK).ok().body("");
    }
}

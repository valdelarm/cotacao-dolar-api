package br.com.martins.valdelar.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "cotacao")
@Getter
@Setter
public class Cotacao {

    private String id;

    private LocalDateTime tempoRequisicao;

    LocalDate dataCotacao;

    private BigDecimal cotacaoCompra;

    private BigDecimal cotacaoVenda;

    private LocalDateTime dataHoraCotacao;

    @PrePersist
    private void prePersist() {
        this.tempoRequisicao = LocalDateTime.now();
        this.dataCotacao = LocalDate.now();
    }
}

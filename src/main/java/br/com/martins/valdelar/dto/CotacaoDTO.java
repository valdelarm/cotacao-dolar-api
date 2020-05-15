package br.com.martins.valdelar.dto;

import br.com.martins.valdelar.model.Cotacao;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
public class CotacaoDTO {
    private BigDecimal cotacaoCompra;

    private BigDecimal cotacaoVenda;

    private LocalDateTime dataHoraCotacao;

    public static CotacaoDTO entityToDto(Cotacao entidade) {
        CotacaoDTO dto = new CotacaoDTO();
        if (entidade == null)
            return null;

        dto.cotacaoCompra = entidade.getCotacaoCompra();
        dto.cotacaoVenda = entidade.getCotacaoVenda();
        dto.dataHoraCotacao = entidade.getDataHoraCotacao();

        return dto;
    }
}

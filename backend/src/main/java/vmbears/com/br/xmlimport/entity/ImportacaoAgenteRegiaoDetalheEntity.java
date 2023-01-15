package vmbears.com.br.xmlimport.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "importacao_agente_regiao_detalhe")
public class ImportacaoAgenteRegiaoDetalheEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "importacao_agente_regiao_id", nullable = false)
    private ImportacaoAgenteRegiaoEntity importacaoAgenteRegiao;

    private BigDecimal compra;
    private BigDecimal geracao;

    public ImportacaoAgenteRegiaoDetalheEntity(ImportacaoAgenteRegiaoEntity importacaoAgenteRegiaoEntity,
            Double compra, Double geracao) {
        this.importacaoAgenteRegiao = importacaoAgenteRegiaoEntity;
        this.compra = new BigDecimal(compra);
        this.geracao = new BigDecimal(geracao);
    }
}

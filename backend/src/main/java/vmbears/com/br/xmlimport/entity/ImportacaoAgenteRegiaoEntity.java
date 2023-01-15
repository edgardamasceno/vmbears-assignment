package vmbears.com.br.xmlimport.entity;

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
@Table(name = "importacao_agente_regiao")
public class ImportacaoAgenteRegiaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "importacao_agente_id", nullable = false)
    private ImportacaoAgenteEntity importacaoAgente;

    @ManyToOne
    @JoinColumn(name = "regiao_sigla", nullable = false)
    private RegiaoEntity regiao;

    public ImportacaoAgenteRegiaoEntity(ImportacaoAgenteEntity importacaoAgente, RegiaoEntity regiao) {
        this.importacaoAgente = importacaoAgente;
        this.regiao = regiao;
    }

}

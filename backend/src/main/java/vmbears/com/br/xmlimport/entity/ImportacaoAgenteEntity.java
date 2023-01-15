package vmbears.com.br.xmlimport.entity;

import java.time.LocalDateTime;

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
@Table(name = "importacao_agente")
public class ImportacaoAgenteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "importacao_id", nullable = false)
    private ImportacaoEntity importacao;

    @ManyToOne
    @JoinColumn(name = "agente_codigo", nullable = false)
    private AgenteEntity agente;

    private LocalDateTime data;

    public ImportacaoAgenteEntity(ImportacaoEntity importacaoEntity, AgenteEntity agenteEntity, LocalDateTime data) {
        this.importacao = importacaoEntity;
        this.agente = agenteEntity;
        this.data = data;
    }

}

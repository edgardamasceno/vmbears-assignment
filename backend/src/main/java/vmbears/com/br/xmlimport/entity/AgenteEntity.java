package vmbears.com.br.xmlimport.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import vmbears.com.br.xmlimport.model.Agente;

@Data
@Entity
@Table(name = "agente")
public class AgenteEntity {

    @Id
    private int codigo;

    public AgenteEntity(Agente agente) {
        this.codigo = agente.getCodigo();
    }

    public AgenteEntity() {
    }

}

package vmbears.com.br.xmlimport.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import vmbears.com.br.xmlimport.model.Regiao;

@Data
@Entity
@Table(name = "regiao")
public class RegiaoEntity {

    @Id
    private String sigla;

    public RegiaoEntity(Regiao regiao) {
        this.sigla = regiao.getSigla();
    }

    public RegiaoEntity() {
    }
}

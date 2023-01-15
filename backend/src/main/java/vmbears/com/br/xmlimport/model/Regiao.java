package vmbears.com.br.xmlimport.model;

import lombok.Data;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@Data
@XStreamAlias("regiao")
public class Regiao {

    @XStreamAsAttribute
    private String sigla;

    @XStreamImplicit(itemFieldName = "geracao")
    private List<Geracao> geracoes;

    @XStreamImplicit(itemFieldName = "compra")
    private List<Compra> compras;
    // getters and setters
}

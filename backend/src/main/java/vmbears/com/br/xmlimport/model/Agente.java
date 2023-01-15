package vmbears.com.br.xmlimport.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import lombok.Data;

import java.util.List;

@Data
@XStreamAlias("agente")
public class Agente {
    @XStreamAlias("codigo")
    private int codigo;
    @XStreamAlias("data")
    private String data;

    @XStreamImplicit
    private List<Regiao> regiao;
}

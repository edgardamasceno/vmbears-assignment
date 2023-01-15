package vmbears.com.br.xmlimport.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import lombok.Data;

import java.util.List;

@Data
@XStreamAlias("agentes")
public class Agentes {
    @XStreamImplicit(itemFieldName = "agente")
    private List<Agente> agentes;
}
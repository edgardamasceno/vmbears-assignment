package vmbears.com.br.xmlimport.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import lombok.Data;

import java.util.List;

@Data
@XStreamAlias("geracao")
public class Geracao {
    @XStreamImplicit(itemFieldName = "valor")
    private List<Double> valores;
    // getters and setters
}
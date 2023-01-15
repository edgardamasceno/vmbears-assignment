package vmbears.com.br.xmlimport.model;

import lombok.Data;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@Data
@XStreamAlias("compra")
public class Compra {
    @XStreamImplicit(itemFieldName = "valor")
    private List<Double> valores;
}

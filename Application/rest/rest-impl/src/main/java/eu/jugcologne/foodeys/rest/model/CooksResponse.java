package eu.jugcologne.foodeys.rest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * @author Daniel Sachse
 * @date 04.09.13 13:14
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CooksResponse implements Serializable {
    private List<ExtendedCookResponse> cooks;

    public CooksResponse() {
    }

    public CooksResponse(List<ExtendedCookResponse> cooks) {
        this.cooks = cooks;
    }

    public List<ExtendedCookResponse> getCooks() {
        return cooks;
    }

    public void setCooks(List<ExtendedCookResponse> cooks) {
        this.cooks = cooks;
    }
}
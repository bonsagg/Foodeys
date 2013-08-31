package eu.jugcologne.foodeys.rest.api.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AddCookRequest extends AbstractCookRequest {
    public AddCookRequest() {
    }

    public AddCookRequest(String name) {
        setName(name);
    }
}
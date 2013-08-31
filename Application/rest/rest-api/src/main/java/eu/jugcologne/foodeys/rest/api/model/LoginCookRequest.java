package eu.jugcologne.foodeys.rest.api.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginCookRequest extends AbstractCookRequest {
    public LoginCookRequest() {
    }

    public LoginCookRequest(String name) {
        setName(name);
    }
}
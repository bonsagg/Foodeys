package eu.jugcologne.foodeys.rest;

import eu.jugcologne.foodeys.rest.model.CookResponse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Daniel Sachse
 * @date 04.09.13 13:17
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExtendedCookResponse extends CookResponse {
    private String url;

    public ExtendedCookResponse(long id, String name, String url) {
        super(id, name);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
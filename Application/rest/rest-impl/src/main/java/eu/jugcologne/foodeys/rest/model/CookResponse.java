package eu.jugcologne.foodeys.rest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Daniel Sachse
 * @date 24.08.13 22:28
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CookResponse implements Serializable {
    private long id;
    private String name;

    public CookResponse() {
    }

    public CookResponse(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
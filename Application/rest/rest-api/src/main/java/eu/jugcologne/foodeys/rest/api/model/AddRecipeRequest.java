package eu.jugcologne.foodeys.rest.api.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Daniel Sachse
 * @date 02.09.13 17:50
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AddRecipeRequest implements Serializable {
    private String name;

    public AddRecipeRequest() {
    }

    public AddRecipeRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
package eu.jugcologne.foodeys.rest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Daniel Sachse
 * @date 24.08.13 22:28
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FoodResponse {
    private String name;

    public FoodResponse() {
    }

    public FoodResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
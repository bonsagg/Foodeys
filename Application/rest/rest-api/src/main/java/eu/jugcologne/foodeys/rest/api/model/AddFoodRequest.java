package eu.jugcologne.foodeys.rest.api.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class AddFoodRequest implements Serializable {
    private String name;

    public AddFoodRequest() {
    }

    public AddFoodRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
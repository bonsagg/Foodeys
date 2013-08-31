package eu.jugcologne.foodeys.rest.api.model;

import java.io.Serializable;

public abstract class AbstractCookRequest implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
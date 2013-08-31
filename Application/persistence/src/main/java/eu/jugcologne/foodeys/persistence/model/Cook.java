package eu.jugcologne.foodeys.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Daniel Sachse
 * @date 29.08.13 22:22
 */

@Entity
public class Cook extends AbstractEntity {
    public Cook() {
    }

    public Cook(String name) {
        this.name = name;
    }

    @Column(name = "name", updatable = false, nullable = false, length = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
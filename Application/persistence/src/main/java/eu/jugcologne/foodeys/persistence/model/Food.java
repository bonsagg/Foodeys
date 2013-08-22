package eu.jugcologne.foodeys.persistence.model;

import eu.jugcologne.foodeys.persistence.util.DbConst;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Food stores all properties related to a food object used in recipes
 *
 * @author Daniel Sachse <mail@wombatsoftware.de>
 */

@Entity(name = DbConst.Food)
public class Food extends AbstractEntity {
    private static final long serialVersionUID = 1879457945957630137L;

    @Column(name = "name", updatable = false, nullable = false, length = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
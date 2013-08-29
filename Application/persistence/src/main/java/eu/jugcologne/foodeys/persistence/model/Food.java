package eu.jugcologne.foodeys.persistence.model;

import eu.jugcologne.foodeys.persistence.util.DbConst;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Food stores all properties related to a food object used in recipes
 *
 * @author Daniel Sachse <mail@wombatsoftware.de>
 */

@NamedQueries({
        @NamedQuery(name = Food.findAllStartingWith, query = "SELECT f.name FROM Food f WHERE f.name LIKE :query ORDER BY f.name asc"),
        @NamedQuery(name = Food.findFoodByName, query = "SELECT f FROM Food f WHERE f.name = :name")
})
@Entity(name = DbConst.Food)
public class Food extends AbstractEntity {
    public static final String findAllStartingWith = "Food.findAllStartingWith";
    public static final String findFoodByName = "Food.findFoodByName";

    private static final long serialVersionUID = 1879457945957630137L;

    public Food() {
    }

    public Food(String name) {
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
package eu.jugcologne.foodeys.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author Daniel Sachse
 * @date 29.08.13 22:22
 */

@Entity
@NamedQueries({
        @NamedQuery(name = Cook.findCookByEmail, query = "SELECT c FROM Cook c WHERE c.email = :email"),
})
public class Cook extends AbstractEntity {
    public static final String findCookByEmail = "Cook.findCookByEmail";

    @Column(name = "name", updatable = false, nullable = false, length = 50)
    private String name;

    @Column(name = "email", updatable = false, nullable = false, length = 50)
    private String email;

    public Cook() {
    }

    public Cook(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
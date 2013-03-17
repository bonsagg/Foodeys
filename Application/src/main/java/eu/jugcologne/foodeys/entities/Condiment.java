package eu.jugcologne.foodeys.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author mmueller
 * Every condiment for a recipe has an amount, a unit and a name.
 * For example, a recipe needs 1 (piece of) tomato or 3 tomatoes.
 * A name must be provided in a singular as well as in plural form.
 * The unit might be a different one, e.g. 500 g tomatoes.
 * The author a a recipe may use a unit of her choice,
 * out of a list of possible units. 
 * For expample "liter" milk is permited, alike "fl. Oz.",
 * whilst "piece" does not make any sence here.
 * Some units may be converted exactly by a mathematics formula,
 * whilst others may be converted approximately only,
 * e.g. 2 eggs, 150 ml egg.
 */
@Entity
public class Condiment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Condiment)) {
            return false;
        }
        Condiment other = (Condiment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.jugcologne.foodeys.entities.Condiment[ id=" + id + " ]";
    }
    
}

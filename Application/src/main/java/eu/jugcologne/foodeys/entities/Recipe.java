package eu.jugcologne.foodeys.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author mmueller
 * This class represents a recipe.
 * A recipe may simply consists of a couple of condiments and processing instructions.
 * But, taking a closer look, such an aproach is too simple.
 * Maybe a cake consists of a flan cake and a topping. Both have to created separate 
 * and then merged together. Or a complete meal consists of parts (meat, vegetables, ...), 
 * which might consists of sub-parts. Every part might be created following it's own recipe.
 * Thus, a recipe migt be a simple recipe or a composition of recipes.
 * For general, any recipe is build upon a list of parts. 
 * A simple recipe contains just one part.
 * Other attributes: portions count, nutrition facts
 */
@Entity
public class Recipe implements Serializable {
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
        if (!(object instanceof Recipe)) {
            return false;
        }
        Recipe other = (Recipe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.jugcologne.foodeys.entities.Recipe[ id=" + id + " ]";
    }
    
}

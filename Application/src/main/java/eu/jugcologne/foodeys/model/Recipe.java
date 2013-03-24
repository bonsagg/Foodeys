package eu.jugcologne.foodeys.model;

import javax.persistence.Entity;

/**
 * @author mmueller
 * 
 * This class represents a recipe.
 * A recipe may simply consists of a couple of ingredients and processing instructions.
 * But, taking a closer look, such an approach is too simple.
 * Maybe a cake consists of a flan cake and a topping. Both have to created separate 
 * and then merged together. Or a complete meal consists of parts (meat, vegetables, ...), 
 * which might consists of sub-parts. Every part might be created following it's own recipe.
 * Thus, a recipe migt be a simple recipe or a composition of recipes.
 * For general, any recipe is build upon a list of parts. 
 * A simple recipe contains just one part.
 * Other attributes: portions count, nutrition facts, picture(s)
 */
@Entity
public class Recipe extends AbstractEntity {
    private static final long serialVersionUID = 2476888974204976237L;
}
package eu.jugcologne.foodeys.model;

import javax.persistence.Entity;

/**
 * @author mmueller
 * 
 * Every ingredient for a recipe has an amount, a unit and a name.
 * For example, a recipe needs 1 (piece of) tomato or 3 tomatoes.
 * A name must be provided in a singular as well as in plural form.
 * The unit might be a different one, e.g. 500 g tomatoes.
 * The author of a recipe may use a unit of her choice,
 * out of a list of possible units. 
 * For example "liter" milk is permmited, alike "fl. Oz.",
 * whilst "piece" does not make any sence here.
 * Some units may be converted exactly by a mathematics formula,
 * whilst others may be converted approximately only,
 * e.g. 2 eggs, 150 ml egg.
 */
@Entity
public class Ingredient extends AbstractEntity {
    private static final long serialVersionUID = -8705211020401289741L;
}
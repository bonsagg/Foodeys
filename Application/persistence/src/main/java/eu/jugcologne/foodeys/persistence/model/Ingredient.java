package eu.jugcologne.foodeys.persistence.model;

import eu.jugcologne.foodeys.persistence.util.DbConst;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author mmueller
 *         Every ingredient for a recipe has an amount, a unit and a name.
 *         For example, a recipe needs 1 (piece of) tomato or 3 tomatoes.
 *         A name must be provided in a singular as well as in plural form.
 *         The unit might be a different one, e.g. 500 g tomatoes.
 *         The author of a recipe may use a unit of her choice,
 *         out of a list of possible units.
 *         For example "liter" milk is permitted, alike "fl. Oz.",
 *         whilst "piece" does not make any sense here.
 *         Some units may be converted exactly by a mathematics formula,
 *         whilst others may be converted approximately only,
 *         e.g. 2 eggs, 150 ml egg.
 */

@Entity(name = DbConst.Ingredient)
public class Ingredient extends AbstractEntity {
    private static final long serialVersionUID = -8705211020401289741L;

    @Column(name = "amount", nullable = false, precision = 8, scale = 2)
    private BigDecimal amount;

    @Column(name = "unit", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Unit unit;

    @ManyToOne(optional = false)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(BigDecimal amount, Recipe recipe, Food food, Unit unit) {
        this.amount = amount;
        this.recipe = recipe;
        this.food = food;
        this.unit = unit;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
package eu.jugcologne.foodeys.persistence.model;

import eu.jugcologne.foodeys.persistence.util.DbConst;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

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
 *
 * Sample:
 *
 * Michael's pan cake (2 pieces)
 *
 * Preparation time: 5 min
 * Cooking time: 5 min
 *
 * Ingredients
 *
 * 2 tablespoon (heapened) of flour
 * 150 ml (apx.) milk
 * 1 egg
 * Toppings of choice
 *
 * Directions
 * 1. Mix flour, milk and egg, giving a fluid paste
 * 2. Flow half into a big pan (28-30 cm)
 * 3. When the upper side gets tight, turn it arround
 * 4. For a tasty pan cake, add topping like cheese and / or ham
 * 5. Continue baking until both sides get golden-brown
 * 6. Prepare second pan cake
 * 7. For a sweet version, serve with marmelade, otherwise with warm tasty topping
 *
 * This is not a real good recipe, but it shows some aspects of a simple recipe.
 *
 * A more complex recipe may be composed of more parts or other recipes.
 */
@Entity(name = DbConst.Recipe)
@NamedQueries({
        @NamedQuery(name = Recipe.findRecipeByFood, query = "SELECT r FROM Recipe r JOIN r.ingredients i WHERE i.food = :food")
})
public class Recipe extends AbstractEntity {
    private static final long serialVersionUID = 2476888974204976237L;

    public static final String findRecipeByFood = "Recipe.findRecipeByFood";

    @Column(name = "name", nullable = false, length = 50)
    @NotNull
    @Size(max = 50)
    private String name;

    @Column(name = "instructions")
    private String instructions;

    @OneToMany(orphanRemoval = true, mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients;

    @ManyToOne(optional = false)
    private Cook cook;

    public Recipe() {
    }

    public Recipe(String name, Cook cook) {
        this.name = name;
        this.cook = cook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Cook getCook() {
        return cook;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
    }
}
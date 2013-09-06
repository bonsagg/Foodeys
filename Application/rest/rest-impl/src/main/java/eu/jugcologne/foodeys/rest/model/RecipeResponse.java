package eu.jugcologne.foodeys.rest.model;

import eu.jugcologne.foodeys.rest.api.model.IngredientResponse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * @author Daniel Sachse
 * @date 24.08.13 22:28
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RecipeResponse implements Serializable {
    private String name;
    private String instructions;
    private String recipe_url;
    private List<IngredientResponse> ingredients;

    public RecipeResponse() {
    }

    public RecipeResponse(String name, String instructions, String recipe_url, List<IngredientResponse> ingredients) {
        this.name = name;
        this.instructions = instructions;
        this.recipe_url = recipe_url;
        this.ingredients = ingredients;
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

    public List<IngredientResponse> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientResponse> ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecipe_url() {
        return recipe_url;
    }

    public void setRecipe_url(String recipe_url) {
        this.recipe_url = recipe_url;
    }
}
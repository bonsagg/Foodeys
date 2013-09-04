package eu.jugcologne.foodeys.rest.model;

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
    private List<IngredientResponse> ingredients;

    public RecipeResponse() {
    }

    public RecipeResponse(String name, String instructions, List<IngredientResponse> ingredients) {
        this.name = name;
        this.instructions = instructions;
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
}
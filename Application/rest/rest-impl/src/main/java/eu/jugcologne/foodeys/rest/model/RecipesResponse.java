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
public class RecipesResponse implements Serializable {
    private List<RecipeResponse> recipes;

    public RecipesResponse() {
    }

    public RecipesResponse(List<RecipeResponse> recipes) {
        this.recipes = recipes;
    }

    public List<RecipeResponse> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeResponse> recipes) {
        this.recipes = recipes;
    }
}
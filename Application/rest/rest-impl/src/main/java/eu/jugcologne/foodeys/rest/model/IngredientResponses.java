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
public class IngredientResponses implements Serializable {
    private List<IngredientResponse> ingredients;

    public IngredientResponses() {
    }

    public IngredientResponses(List<IngredientResponse> ingredients) {
        this.ingredients = ingredients;
    }

    public List<IngredientResponse> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientResponse> ingredients) {
        this.ingredients = ingredients;
    }
}
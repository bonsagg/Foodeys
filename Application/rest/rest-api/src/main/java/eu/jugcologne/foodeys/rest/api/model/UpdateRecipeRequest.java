package eu.jugcologne.foodeys.rest.api.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Daniel Sachse
 * @date 02.09.13 18:03
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UpdateRecipeRequest extends AddRecipeRequest {
    private String instructions;

    public UpdateRecipeRequest(String instructions) {
        this.instructions = instructions;
    }

    public UpdateRecipeRequest(String name, String instructions) {
        super(name);
        this.instructions = instructions;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
package eu.jugcologne.foodeys.rest.model;

import eu.jugcologne.foodeys.persistence.model.Unit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Daniel Sachse
 * @date 04.09.13 16:41
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class IngredientResponse implements Serializable {
    private String food;
    private BigDecimal amount;
    private Unit unit;
    private String ingredient_url;

    public IngredientResponse() {
    }

    public IngredientResponse(String food, BigDecimal amount, Unit unit, String ingredient_url) {
        this.food = food;
        this.amount = amount;
        this.unit = unit;
        this.ingredient_url = ingredient_url;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
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

    public String getIngredient_url() {
        return ingredient_url;
    }

    public void setIngredient_url(String ingredient_url) {
        this.ingredient_url = ingredient_url;
    }
}
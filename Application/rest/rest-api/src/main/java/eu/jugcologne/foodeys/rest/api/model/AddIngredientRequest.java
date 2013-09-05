package eu.jugcologne.foodeys.rest.api.model;

import eu.jugcologne.foodeys.persistence.model.Unit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Daniel Sachse
 * @date 02.09.13 17:51
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AddIngredientRequest implements Serializable {
    private long foodID;

    private BigDecimal amount;

    private Unit unit;

    public AddIngredientRequest() {
    }

    public AddIngredientRequest(long foodID, BigDecimal amount, Unit unit) {
        this.foodID = foodID;
        this.amount = amount;
        this.unit = unit;
    }

    public long getFoodID() {
        return foodID;
    }

    public void setFoodID(long foodID) {
        this.foodID = foodID;
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
}

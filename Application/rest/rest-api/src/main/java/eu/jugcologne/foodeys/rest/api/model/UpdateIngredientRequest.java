package eu.jugcologne.foodeys.rest.api.model;

import eu.jugcologne.foodeys.persistence.model.Unit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Daniel Sachse
 * @date 02.09.13 17:58
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UpdateIngredientRequest implements Serializable {
    private BigDecimal amount;
    private Unit unit;

    public UpdateIngredientRequest() {
    }

    public UpdateIngredientRequest(BigDecimal amount, Unit unit) {
        this.amount = amount;
        this.unit = unit;
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
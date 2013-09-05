package eu.jugcologne.foodeys.rest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * @author Daniel Sachse
 * @date 04.09.13 13:14
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FoodsResponse implements Serializable {
    private List<FoodResponse> foods;

    public FoodsResponse() {
    }

    public FoodsResponse(List<FoodResponse> foods) {
        this.foods = foods;
    }

    public List<FoodResponse> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodResponse> foods) {
        this.foods = foods;
    }
}
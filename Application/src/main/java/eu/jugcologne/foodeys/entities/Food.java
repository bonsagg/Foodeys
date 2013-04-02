package eu.jugcologne.foodeys.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import eu.jugcologne.foodeys.util.DbConst;

@Entity(name=DbConst.Food)
public class Food extends AbstractEntity {
	private static final long serialVersionUID = 1879457945957630137L;
	
    @Column(name = "name", updatable = false, nullable = false, length=50)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

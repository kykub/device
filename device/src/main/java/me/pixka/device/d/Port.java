package me.pixka.device.d;

import javax.persistence.Entity;

import me.pixka.data.Data;

@Entity
public class Port extends Data {

	private String name;
	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

}

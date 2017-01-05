package me.pixka.device.d;

import javax.persistence.Entity;

import me.pixka.data.Data;

/**
 * ใช้สำหรับ บอกว่า Device config เป็น type อะไร
 * 
 * @author kykub
 *
 */
@Entity
public class Devicetype extends Data {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

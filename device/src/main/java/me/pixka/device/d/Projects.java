package me.pixka.device.d;

import java.util.EnumSet;


public enum Projects {

	Water(0, "Water"), Update(1, "Update"), Het(2, "Het");

	private int id;
	private String name;

	private Projects(int i, String n) {

		this.setId(i);
		this.setName(n);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static EnumSet<Projects> all() {
		return EnumSet.of(Projects.Water, Projects.Update, Projects.Het);
	}

}

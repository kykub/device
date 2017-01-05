package me.pixka.device.d;

import javax.persistence.Entity;

import me.pixka.data.Data;

@Entity
public class Fw extends Data {

	private String version;
	private String description;
	private Boolean enable = false;

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

package me.pixka.device.d;

import java.math.BigDecimal;

import javax.persistence.Entity;

import me.pixka.data.Data;

@Entity
public class Device extends Data {

	private String mac;
	private String name;
	private String description;
	private BigDecimal ttune = BigDecimal.ZERO; // สำหรับ ปรับ จุน Sensor
	private BigDecimal htune = BigDecimal.ZERO; // สำหรับ ปรับ จุน Sensor
	private Long refid;

	public BigDecimal getTtune() {
		if (ttune == null)
			return BigDecimal.ZERO;
		return ttune;
	}

	public void setTtune(BigDecimal ttune) {
		this.ttune = ttune;
	}

	public BigDecimal getHtune() {
		if (htune == null)
			return BigDecimal.ZERO;
		return htune;
	}

	public void setHtune(BigDecimal htune) {
		this.htune = htune;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Device [id=" + this.getId() + ", mac=" + mac + ", name=" + name + ", vers=" + this.getVers() + "]";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getRefid() {
		return refid;
	}

	public void setRefid(Long refid) {
		this.refid = refid;
	}

}

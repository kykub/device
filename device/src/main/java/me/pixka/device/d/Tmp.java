package me.pixka.device.d;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import me.pixka.data.Data;
import me.pixka.device.d.Device;

@Entity
public class Tmp extends Data {

	private BigDecimal t, h;
	private String name;
	private String mac;
	private String ip;

	@ManyToOne
	private Device device;
	@Column(insertable = false, updatable = false)
	private Long device_id;

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Long getDevice_id() {
		return device_id;
	}

	public void setDevice_id(Long device_id) {
		this.device_id = device_id;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public BigDecimal getT() {
		return t;
	}

	public void setT(BigDecimal t) {
		this.t = t;
	}

	public BigDecimal getH() {
		return h;
	}

	public void setH(BigDecimal h) {
		this.h = h;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return device.getMac() + " t:" + t + " h:" + h + " adddate:" + adddate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}

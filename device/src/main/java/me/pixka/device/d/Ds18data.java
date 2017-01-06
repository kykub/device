package me.pixka.device.d;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import me.pixka.data.Data;
import me.pixka.device.d.Device;

@Entity
public class Ds18data extends Data {

	private BigDecimal tmp;

	@Column(insertable = false, updatable = false)
	private Long device_id;

	@ManyToOne
	private Device device;

	public BigDecimal getTmp() {
		return tmp;
	}

	public void setTmp(BigDecimal tmp) {
		this.tmp = tmp;
	}

	public Long getDevice_id() {
		return device_id;
	}

	public void setDevice_id(Long device_id) {
		this.device_id = device_id;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public String toString() {
		return this.device.getName() + " tmp:" + this.tmp + " adddate:" + this.adddate;
	}
}

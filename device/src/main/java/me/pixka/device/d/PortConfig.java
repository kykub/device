package me.pixka.device.d;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import me.pixka.data.Data;

@Entity
public class PortConfig extends Data {

	private String name;
	private Boolean open = false;

	@ManyToOne
	private Deviceconfig deviceconfig;
	@Column(insertable = false, updatable = false)
	private Long deviceconfig_id;

	public Deviceconfig getDeviceconfig() {
		return deviceconfig;
	}

	public void setDeviceconfig(Deviceconfig deviceconfig) {
		this.deviceconfig = deviceconfig;
	}

	public Long getDeviceconfig_id() {
		return deviceconfig_id;
	}

	public void setDeviceconfig_id(Long deviceconfig_id) {
		this.deviceconfig_id = deviceconfig_id;
	}

	@Column(insertable = false, updatable = false)
	private Long port_id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPort_id() {
		return port_id;
	}

	public void setPort_id(Long port_id) {
		this.port_id = port_id;
	}

	public Port getPort() {
		return port;
	}

	public void setPort(Port port) {
		this.port = port;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	@ManyToOne
	private Port port;
}

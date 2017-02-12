package me.pixka.device.d;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinState;

import me.pixka.data.Data;

@Entity
public class PortConfig extends Data {

	private String name;

	private String pin;

	@Enumerated
	private Projects projects;

	@Enumerated
	private PinMode pinmode;

	@Enumerated
	private PinState startstate;

	@Enumerated
	private PinState shutdownstate;

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Projects getProjects() {
		return projects;
	}

	public void setProjects(Projects projects) {
		this.projects = projects;
	}

	public PinMode getPinmode() {
		return pinmode;
	}

	public void setPinmode(PinMode pinmode) {
		this.pinmode = pinmode;
	}

	public PinState getStartstate() {
		return startstate;
	}

	public void setStartstate(PinState startstate) {
		this.startstate = startstate;
	}

	public PinState getShutdownstate() {
		return shutdownstate;
	}

	public void setShutdownstate(PinState shutdownstate) {
		this.shutdownstate = shutdownstate;
	}

	@Override
	public String toString() {
		return "PortConfig [name=" + name + ", pin=" + pin + ", projects=" + projects + ", pinmode=" + pinmode
				+ ", startstate=" + startstate + ", shutdownstate=" + shutdownstate + "]";
	}

}

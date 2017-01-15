package me.pixka.device.d;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import me.pixka.data.Data;

@Entity
public class Watertiming extends Data {

	@Column(insertable = false, updatable = false)
	private Long device_id;
	@ManyToOne
	private Device device;
	private Boolean enable = true;
	private Long waittime; // เวลาที่รอ default 5 นาที
	private Long openwatertime; // ช่วงเวลาเปิดน้ำ
	private BigDecimal tmplow;//ช่วงอณหภูที่ run
	private BigDecimal tmphigh;
	private Long refid; // id ของ server 
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

	public Long getWaittime() {
		return waittime;
	}

	public void setWaittime(Long waittime) {
		this.waittime = waittime;
	}

	public Long getOpenwatertime() {
		return openwatertime;
	}

	public void setOpenwatertime(Long openwatertime) {
		this.openwatertime = openwatertime;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	

	public Long getRefid() {
		return refid;
	}

	public void setRefid(Long refid) {
		this.refid = refid;
	}

}

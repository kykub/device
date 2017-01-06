package me.pixka.device.d;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import me.pixka.data.Data;

@Entity
public class Deviceconfig extends Data{


	private Long refid;
	private Boolean read = false;
	@ManyToOne
	private Device device;
	private BigDecimal h, t; // ความชื่นขันตำ�?ละอุณหภูมิ
	private BigDecimal hh, ht;// ความร้อนสูงสุด
	private Date sdate, edate, stime, etime; // ช่วงเวลา
	private Boolean enable = true;
	private Boolean blacklight = true;
	private Boolean lcdon = true;
	private Long runtime = 10000l; // runtime สำหรับ เวลาใน�?าร run
	@Column(insertable = false, updatable = false)
	private Long device_id;
	
	@OneToMany(mappedBy = "deviceconfig", cascade = CascadeType.ALL)
	private List<PortConfig> ports;

	private Boolean port1open = false;
	private Boolean port2open = false;

	private Boolean port3open = false;
	private Boolean port4open = false;
	private Boolean port5open = false;
	private Boolean port6open = false;
	private Boolean port7open = false;
	private Boolean port8open = false;

	private Long readdsfrom_id; // ใช้สำหรับบอกว่าอ่าน ค่า DS18B20 จาก Device_id ไหน   rest = /readds18b20/{id} 
	private Long readdhtfrom_id; //ใช้สำหรับบอก Pi ว่าจะต้องอ่าน ค่า ของ DHT จากที่ไหน หรือ ID อะไร rest = /readdht/{id}

	public Long getReaddsfrom_id() {
		return readdsfrom_id;
	}

	public void setReaddsfrom_id(Long readdsfrom_id) {
		this.readdsfrom_id = readdsfrom_id;
	}

	public Long getReaddhtfrom_id() {
		return readdhtfrom_id;
	}

	public void setReaddhtfrom_id(Long readdhtfrom_id) {
		this.readdhtfrom_id = readdhtfrom_id;
	}

	@ManyToOne
	private Devicetype devicetype;

	@Column(insertable = false, updatable = false)
	private Long devicetype_id;

	public List<PortConfig> getPorts() {
		return ports;
	}

	public void setPorts(List<PortConfig> ports) {
		this.ports = ports;
	}

	public Boolean getPort1open() {
		return port1open;
	}

	public void setPort1open(Boolean port1open) {
		this.port1open = port1open;
	}

	public Boolean getPort2open() {
		return port2open;
	}

	public void setPort2open(Boolean port2open) {
		this.port2open = port2open;
	}

	public Boolean getBlacklight() {
		return blacklight;
	}

	public void setBlacklight(Boolean blacklight) {
		this.blacklight = blacklight;
	}

	public Boolean getLcdon() {
		return lcdon;
	}

	public void setLcdon(Boolean lcdon) {
		this.lcdon = lcdon;
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

	public BigDecimal getH() {
		return h;
	}

	public void setH(BigDecimal h) {
		this.h = h;
	}

	public BigDecimal getT() {
		return t;
	}

	public void setT(BigDecimal t) {
		this.t = t;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public Date getStime() {
		return stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

	public Date getEtime() {
		return etime;
	}

	public void setEtime(Date etime) {
		this.etime = etime;
	}

	@Override
	public String toString() {
		return "Deviceconfig [refid="+refid+",id=" + this.getId() + ", device_id=" + device_id + ", ports=" + ports + ", device=" + device
				+ ", h=" + h + ", t=" + t + ", ht="+ht+" , hh=,"+hh+" sdate=" + sdate + ", edate=" + edate + ", stime=" + stime + ", etime="
				+ etime + ", enable=" + enable + ", port1open=" + port1open + ", port2open=" + port2open + ", adddate="
				+ this.getAdddate() + "]";
	}

	public Long getRuntime() {
		return runtime;
	}

	public void setRuntime(Long runtime) {
		this.runtime = runtime;
	}

	public Boolean getPort3open() {
		return port3open;
	}

	public void setPort3open(Boolean port3open) {
		this.port3open = port3open;
	}

	public Boolean getPort4open() {
		return port4open;
	}

	public void setPort4open(Boolean port4open) {
		this.port4open = port4open;
	}

	public Boolean getPort5open() {
		return port5open;
	}

	public void setPort5open(Boolean port5open) {
		this.port5open = port5open;
	}

	public Boolean getPort6open() {
		return port6open;
	}

	public void setPort6open(Boolean port6open) {
		this.port6open = port6open;
	}

	public Boolean getPort7open() {
		return port7open;
	}

	public void setPort7open(Boolean port7open) {
		this.port7open = port7open;
	}

	public Boolean getPort8open() {
		return port8open;
	}

	public void setPort8open(Boolean port8open) {
		this.port8open = port8open;
	}

	public Boolean getRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public BigDecimal getHh() {
		return hh;
	}

	public void setHh(BigDecimal hh) {
		this.hh = hh;
	}

	public BigDecimal getHt() {
		return ht;
	}

	public void setHt(BigDecimal ht) {
		this.ht = ht;
	}

	public Devicetype getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(Devicetype devicetype) {
		this.devicetype = devicetype;
	}

	public Long getDevicetype_id() {
		return devicetype_id;
	}

	public void setDevicetype_id(Long devicetype_id) {
		this.devicetype_id = devicetype_id;
	}

	public Long getRefid() {
		return refid;
	}

	public void setRefid(Long refid) {
		this.refid = refid;
	}

}

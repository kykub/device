package me.pixka.device.d;

import javax.persistence.Entity;

import me.pixka.data.Data;

@Entity
public class Pifw extends Data {

	private String verno;
	private String checksum;
	private String pathtofile;
	private String Filename;
	private String appname; // สำหรับบอกว่า FW ของอะไรเพระามีหลายอันมาก ทั้งคุ่ม
							// เตา คุ่มน้ำ คุ่มความชื้น

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	private Long fwsize;

	public Long getFwsize() {
		return fwsize;
	}

	public void setFwsize(Long fwsize) {
		this.fwsize = fwsize;
	}

	public String getVerno() {
		return verno;
	}

	public void setVerno(String verno) {
		this.verno = verno;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getPathtofile() {
		return pathtofile;
	}

	public void setPathtofile(String pathtofile) {
		this.pathtofile = pathtofile;
	}

	public String getFilename() {
		return Filename;
	}

	public void setFilename(String filename) {
		Filename = filename;
	}

}

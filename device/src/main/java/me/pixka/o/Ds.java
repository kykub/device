package me.pixka.o;

import java.math.BigDecimal;

public class Ds {
	private BigDecimal tmp;
	private String ip;
	private String adddate;

	public BigDecimal getTmp() {
		return tmp;
	}

	public void setTmp(BigDecimal tmp) {
		this.tmp = tmp;
	}

	public String getAdddate() {
		return adddate;
	}

	public void setAdddate(String adddate) {
		this.adddate = adddate;
	}

	@Override
	public String toString() {
		return "Ds [tmp=" + tmp + ", ip=" + ip + ", adddate=" + adddate + "]";
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
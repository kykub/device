package me.pixka.device.s;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import me.pixka.c.Ds18b20;
import me.pixka.c.Font;
import me.pixka.c.Led;
import me.pixka.c.PiDevice;
import me.pixka.device.c.DeviceconfigControl;
import me.pixka.device.c.DeviceconfigUtil;
import me.pixka.device.d.Deviceconfig;
import me.pixka.o.Ds;

@Service
public class DisplayService {

	@Autowired
	private Led led;
	@Autowired
	private Ds18b20 ds;
	@Autowired
	private DeviceconfigUtil dcfu;
	@Autowired
	private PiDevice pi;

	@Autowired
	private DeviceconfigControl du;
	private boolean display = true;

	@Async
	public void printRunstatus() {

		while (true) {

			if (led.isRun()) {
				System.out.println("LED busy ");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			}
			// System.out.println("print status ");

			pd();
			led.letter((short) 3, (short) '.', Font.CP437_FONT, true);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			pd();
			led.letter((short) 3, (short) ' ', Font.CP437_FONT, true);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private BigDecimal data = BigDecimal.ZERO;
	private BigDecimal last = BigDecimal.ZERO;
	private Deviceconfig deviceconfig;
	private String baseUrl = "http://61.19.255.23:3333/";

	@Async
	public void printDsvalue() {
		while (true) {
			pd();
		}

	}

	public BigDecimal readtmp() {
		BigDecimal d = BigDecimal.ZERO;
		try {
			if (deviceconfig != null && deviceconfig.getReaddsfrom_id() != null) {
				Long id = deviceconfig.getReaddsfrom_id();
				Ds dsvalue = dcfu.read18Ds(baseUrl + "readds18b20/", id);
				if (dsvalue != null)
					d = dsvalue.getTmp();
				else
					d = null;
			} else
				d = ds.read();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return d;
	}

	private void pd() {

		if (led.isRun()) {
			System.out.println("Display Value Busy");
			return;
		}
		// System.out.println("Display :" + display);

		if (!display) {
			led.print("   ");

		} else {

			if (data == null) {
				led.print("***");

			} else {
				// System.out.println("print Value");
				if (data.compareTo(BigDecimal.ZERO) != 0) {// have data
					String value = data.setScale(0, RoundingMode.HALF_UP) + "";
					String f = "=";
					int cp = data.compareTo(last);
					if (cp > 0)
						f = "^";
					else if (cp < 0)
						f = "v";
					
					
					if (!led.isRun())
						led.print(f + value);
					
					//save value
					last = data;
				} else {
					if (!led.isRun())
						led.print("---");
				}

			}
		}

	}

	@Async
	public void checkLCDon() {
		while (true) {

			deviceconfig = load();
			if (deviceconfig != null) {// ปิดจอ
				if (!deviceconfig.getLcdon()) {
					System.out.println("Off display");
					display = false;
				} else {
					display = true;
					System.out.println("On display");
				}
			}

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	private String url = "http://61.19.255.23:3333/getdeviceconfigmac/";

	/**
	 * เปลียน มา load จาก Local ตลอดเลย เพราะ LoadDeviceconfigTask จะทำการ load
	 * มาให้เองจาก Server อยู่แล้ว
	 * 
	 * @return
	 */
	public Deviceconfig load() {
		Deviceconfig deviceconfig = null;
		deviceconfig = du.last();
		return deviceconfig;
	}

	private long nextread = 5*1000;
	/**
	 * ใช้อ่านค่า ทุกสองนาที
	 */
	@Async
	public void readDs() {
		while (true) {
			try {
			BigDecimal d = readtmp();
			if(d!=null)
				data = d;
				Thread.sleep(nextread);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Led getLed() {
		return led;
	}

	public void setLed(Led led) {
		this.led = led;
	}

	public Ds18b20 getDs() {
		return ds;
	}

	public void setDs(Ds18b20 ds) {
		this.ds = ds;
	}

	public DeviceconfigUtil getDcfu() {
		return dcfu;
	}

	public void setDcfu(DeviceconfigUtil dcfu) {
		this.dcfu = dcfu;
	}

	public PiDevice getPi() {
		return pi;
	}

	public void setPi(PiDevice pi) {
		this.pi = pi;
	}

	public DeviceconfigControl getDu() {
		return du;
	}

	public void setDu(DeviceconfigControl du) {
		this.du = du;
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	public BigDecimal getData() {
		return data;
	}

	public void setData(BigDecimal data) {
		this.data = data;
	}

	public BigDecimal getLast() {
		return last;
	}

	public void setLast(BigDecimal last) {
		this.last = last;
	}

	public Deviceconfig getDeviceconfig() {
		return deviceconfig;
	}

	public void setDeviceconfig(Deviceconfig deviceconfig) {
		this.deviceconfig = deviceconfig;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getNextread() {
		return nextread;
	}

	public void setNextread(long nextread) {
		this.nextread = nextread;
	}
}

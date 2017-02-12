package me.pixka.device.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import me.pixka.c.PiDevice;
import me.pixka.device.c.DeviceconfigUtil;
import me.pixka.device.d.Deviceconfig;
import me.pixka.device.s.DeviceconfigService;

/**
 * ใช้สำหรับ LOAD Config จาก Server แล้วเอามาเก็บไว้ใน Device
 * 
 * @author kykub
 *
 */
@Component
public class LoadDeviceConfigTask {

	private String loadurl = "http://pixka.me:3333";
	private long nextload = 10 * 1000; // 10วิ

	@Autowired
	private DeviceconfigService service;
	@Autowired
	private DeviceconfigUtil du;
	@Autowired
	private PiDevice pi;
	private String url = "http://pixka.me:3333/deviceconfig/load/";
	private String mac;

	@Async
	public void run() {

		while (true) {
			try {

				// load ล่าสุดของ device
				Deviceconfig df = loadlast();
				long refid = 0;
				if (df != null && df.getRefid() != null)
					refid = df.getRefid();

				List newconfig = loadsfromhttp(refid);

				if (newconfig != null) {
					saveDeviceconfig(newconfig);
				}

				// จบการทำงานของ Save Load config
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(nextload);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public DeviceconfigService getService() {
		return service;
	}

	public void setService(DeviceconfigService service) {
		this.service = service;
	}

	public DeviceconfigUtil getDu() {
		return du;
	}

	public void setDu(DeviceconfigUtil du) {
		this.du = du;
	}

	public PiDevice getPi() {
		return pi;
	}

	public void setPi(PiDevice pi) {
		this.pi = pi;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private void saveDeviceconfig(List newconfig) {

		System.out.println("Save Device config: ");
		List<Deviceconfig> l = newconfig;
		int count = 0;
		for (Deviceconfig i : l) {
			i.setRefid(i.getId());
			i.setDevice(null);//ไม่ต้องใส่ก็ได้เป็นข้อมูลของเครื่อง
			i.setDevice_id(null);
			service.save(i);
			count++;
		}

		System.out.println("Save config " + count);
		// จบการทำงาน
	}

	private List loadsfromhttp(long refid) {

		if (mac == null)
			mac = pi.wifi();
		return du.loadsfromhttp(url, mac, refid);
	}

	/**
	 * ใช้สำหรับ Load Device config อันล่าสุดที่ได้รับจาก Server จาก Device
	 * 
	 * @return อันล่าสุดใน Device ของ pi
	 */
	private Deviceconfig loadlast() {

		return service.last();
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getLoadurl() {
		return loadurl;
	}

	public void setLoadurl(String loadurl) {
		this.loadurl = loadurl;
	}

	public long getNextload() {
		return nextload;
	}

	public void setNextload(long nextload) {
		this.nextload = nextload;
	}

}

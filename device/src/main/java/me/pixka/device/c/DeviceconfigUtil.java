package me.pixka.device.c;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import me.pixka.c.HttpControl;
import me.pixka.data.ISODateAdapter;
import me.pixka.device.d.Device;
import me.pixka.device.d.Deviceconfig;
import me.pixka.device.d.Ds18data;
import me.pixka.device.d.Watertiming;
import me.pixka.o.Ds;

@Component
public class DeviceconfigUtil {
	private static Gson g = new GsonBuilder().registerTypeAdapter(Date.class, new ISODateAdapter()).create();
	@Autowired
	private HttpControl http;
	@Autowired
	private DeviceconfigControl dcf;
	@Autowired
	private DeviceControl dc;

	public Deviceconfig loadfromhttp(String url, String mac) {
		String re = null;
		try {
			// String get = url + mac;
			String get = url + mac;
			System.out.println("URL:" + get);
			re = http.get(get);
			// System.out.println("Return value:" + re);
			Deviceconfig dfs = g.fromJson(re, Deviceconfig.class);
			dfs.setRefid(dfs.getId());
			return dfs;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * ใช้สำหรับอ่านค่าของ DS18B20 จากตัวอื่น
	 * 
	 * @param url
	 * @param id
	 * @return
	 */
	public Ds read18Ds(String url, Long id) {
		String re = null;
		try {
			String get = url + id;
			System.out.println("Read ds18b20 URL:" + get);
			re = http.get(get);
			System.out.println("Return value:" + re);
			Ds dfs = g.fromJson(re, Ds.class);
			System.out.println("DS18B20 DATA: =====================>" + dfs);
			return dfs;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * ใช้สำหรับอ่านค่าของ DS18B20 จากตัวอื่น ตามที่กำหนดด้วย
	 * 
	 * @param url
	 *            ที่หมาย
	 * @param id
	 *            device id
	 * @param currentmp
	 *            อุณหภูมิ
	 * @return
	 */
	public Ds18data read18Ds(String url, Long id, BigDecimal currenttmp) {
		String re = null;
		try {

			String get = url + id + "/" + currenttmp.toString();
			System.out.println("Read ds18b20 URL:" + get);
			re = http.get(get);
			System.out.println("Return value:========>" + re);
			Ds18data dfs = g.fromJson(re, Ds18data.class);
			return dfs;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void saveConfigtoDevice(Deviceconfig deviceconfig) {

		Deviceconfig d = dcf.findRefId(deviceconfig.getId());
		if (d == null) {// ถ้าไม่เจอให้เก็บเข้าระบบ
			try {
				Device device = dc.findByRefid(deviceconfig.getDevice_id());

				if (device == null) {
					System.out.println("not found device:" + device);
					device = deviceconfig.getDevice();
					device.setRefid(device.getId());
					device = (Device) dc.save(device);
					deviceconfig.setDevice(device);
					System.out.println("New Device :" + device);
				} else {
					deviceconfig.setDevice(device);
				}

				deviceconfig.setRefid(deviceconfig.getId());
				deviceconfig = dcf.save(deviceconfig);
				System.out.println("New Deviceconfig");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("End of save");
	}

	public Deviceconfig loadfromdevice() {
		return dcf.last();
	}

	public Watertiming loadWatertiming(String url, Long id) {
		System.out.println("========== Load water config  =============");
		String re = null;
		try {
			String get = url + id;
			// System.out.println("Read Watertiming URL:" + get);
			re = http.get(get);
			// System.out.println("Return water value:" + re);
			Watertiming dfs = g.fromJson(re, Watertiming.class);
			System.out.println("load local ok");
			return dfs;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * หา config ที่อยู่ใน ช่วงนี้ ที่มีค่าตรงกับ tmp
	 * 
	 * @param url
	 * @param id
	 * @param tmp
	 * @return
	 */
	public Watertiming loadWatertiming(String url, Long id, BigDecimal tmp) {
		System.out.println("========== Load water config  =============");
		String re = null;
		try {
			String get = url + id + "/" + tmp;
			System.out.println("GETURL:" + get);
			// System.out.println("Read Watertiming URL:" + get);
			re = http.get(get);
			// System.out.println("Return water value:" + re);
			Watertiming dfs = g.fromJson(re, Watertiming.class);
			System.out.println("load http ok");
			return dfs;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}

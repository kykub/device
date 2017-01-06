package me.pixka.device.c;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import me.pixka.c.HttpControl;
import me.pixka.data.ISODateAdapter;
import me.pixka.device.d.Device;
import me.pixka.device.d.Deviceconfig;

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
			String get = url + mac;
			System.out.println("URL:" + get);
			re = http.get(get);
			System.out.println("Return value:" + re);
			Deviceconfig dfs = g.fromJson(re, Deviceconfig.class);
			dfs.setRefid(dfs.getId());
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

}

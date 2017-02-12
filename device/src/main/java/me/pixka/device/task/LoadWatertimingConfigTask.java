package me.pixka.device.task;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import me.pixka.c.HttpControl;
import me.pixka.c.PiDevice;
import me.pixka.data.ISODateAdapter;
import me.pixka.device.d.Watertiming;
import me.pixka.device.s.WatertimingService;

/**
 * ใช้สำหรับ Load Water config มาไว้ในเครือง
 * 
 * @author kykub
 *
 */
@Component
public class LoadWatertimingConfigTask {
	private static Gson g = new GsonBuilder().registerTypeAdapter(Date.class, new ISODateAdapter()).create();

	@Autowired
	private WatertimingService service;
	@Autowired
	private HttpControl http;
	@Autowired
	private PiDevice pi;
	private String mac;

	public static Gson getG() {
		return g;
	}

	public static void setG(Gson g) {
		LoadWatertimingConfigTask.g = g;
	}

	public WatertimingService getService() {
		return service;
	}

	public void setService(WatertimingService service) {
		this.service = service;
	}

	public HttpControl getHttp() {
		return http;
	}

	public void setHttp(HttpControl http) {
		this.http = http;
	}

	public PiDevice getPi() {
		return pi;
	}

	public void setPi(PiDevice pi) {
		this.pi = pi;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public long getNextrun() {
		return nextrun;
	}

	public void setNextrun(long nextrun) {
		this.nextrun = nextrun;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private long nextrun = 10 * 1000;
	private String url = "http://pixka.me:3333/water/load/";

	@Async
	public void run() {

		while (true) {
			try {
				Watertiming l = last();
				long lastid = 0;
				if (l != null && l.getRefid() != null)
					lastid = l.getRefid();
				List wconfiglist = load(lastid);
				if (wconfiglist != null) {
					save(wconfiglist);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(nextrun);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void save(List wconfiglist) {

		List<Watertiming> items = wconfiglist;
		System.out.println("Save Water config");
		int count = 0;
		for (Watertiming i : items) {
			
			i.setRefid(i.getId());
			i.setDevice(null);
			i.setDevice_id(null);
			service.save(i);
			count++;
		}

		System.out.println("Save waterconfig :" + count);

	}

	public List load(long lastid) {

		try {
			if (mac == null)
				mac = pi.wifi();
			//// watertiming/loads/{mac}/{id}

			String u = url + mac + "/" + lastid;
			System.out.println("URL:" + u);
			String re = http.get(u);
			Watertiming[] dfs = g.fromJson(re, Watertiming[].class);
			return (List<Watertiming>) Arrays.asList(dfs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private Watertiming last() {
		return service.last();
	}
}

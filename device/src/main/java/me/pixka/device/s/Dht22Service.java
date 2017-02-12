package me.pixka.device.s;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import me.pixka.c.DHT22;
import me.pixka.c.HttpControl;
import me.pixka.c.PiDevice;

@Service
public class Dht22Service {

	@Autowired
	private DHT22 dht22;
	@Autowired
	private HttpControl http;

	@Autowired
	private PiDevice device;

	@Async
	public void read() {

		while (true) {
			try {
				dht22.read();
				BigDecimal t = dht22.getT();
				BigDecimal h = dht22.getH();
				if (t != null && h != null) {
					String m = device.wifi();
					String url = "http://61.19.255.23:3333/add?t=" + t + "&h=" + h + "&m=" + m + "&ip="
							+ device.wifiip();
					http.get(url);
				}

			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			

			try {
				Thread.sleep(60 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void run() {
		read();
	}
}

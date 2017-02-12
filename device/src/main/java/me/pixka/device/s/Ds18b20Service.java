package me.pixka.device.s;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import me.pixka.c.Ds18b20;
import me.pixka.c.HttpControl;
import me.pixka.c.PiDevice;

@Service
public class Ds18b20Service {

	@Autowired
	private PiDevice pi;
	@Autowired
	private Ds18b20 ds;
	@Autowired
	private HttpControl http;

	@Async
	public void send() {

		while (true) {
			BigDecimal data = null;
			try {
				data = ds.read();
				send("61.19.255.23", "3333", data, pi.wifi());
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

	public void send(String host, String port, BigDecimal tmp, String mac) {
		String s = "http://" + host + ":" + port + "/addds?t=" + tmp + "&m=" + mac + "&ip=" + pi.wifiip();
		try {
			http.get(s);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

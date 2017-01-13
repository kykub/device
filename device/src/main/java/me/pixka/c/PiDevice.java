package me.pixka.c;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;

@Controller
public class PiDevice {

	public String wifiAddress() throws IOException {
		String contents = new String(Files.readAllBytes(Paths.get("/sys/class/net/wlan0/address")));
		return contents;
		// Files.lines(Paths.get("manifest.mf"),
		// StandardCharsets.UTF_8).forEach(System.out::println);

	}
	
	public String wifiIpAddress() throws IOException {
		String contents = new String(Files.readAllBytes(Paths.get("/sensor/wlan")));
		return contents;
		// Files.lines(Paths.get("manifest.mf"),
		// StandardCharsets.UTF_8).forEach(System.out::println);

	}

	public String eth0Address() throws IOException {
		/// sys/class/net/eth0/address
		String contents = new String(Files.readAllBytes(Paths.get("/sys/class/net/eth0/address")));
		return contents;

	}

	public String wifi() {

		String mc = null;
		try {
			mc = this.wifiAddress().replaceAll(":", "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (mc == null)
			return null;
		mc = mc.replaceAll(" ", "");
		mc = mc.replaceAll("(\\r|\\n)", "");
		return mc;
	}
	public String wifiip() {

		String mc = null;
		try {
			mc = this.wifiIpAddress().replaceAll(":", "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (mc == null)
			return null;
		mc = mc.replaceAll(" ", "");
		mc = mc.replaceAll("(\\r|\\n)", "");
		return mc;
	}
}

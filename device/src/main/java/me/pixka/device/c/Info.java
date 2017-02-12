package me.pixka.device.c;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.pixka.c.PiDevice;
import me.pixka.device.d.Deviceconfig;

@RestController
public class Info {

	@Autowired
	private PiDevice pi;
	@Autowired
	private DeviceconfigControl dcf;

	@CrossOrigin
	@RequestMapping(value = "/piinfo", method = RequestMethod.GET)
	@ResponseBody
	public String info() {
		String version = null;
		try {
			version = new String(Files.readAllBytes(Paths.get("version")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String r = pi.toString();
		Deviceconfig last = dcf.last();
		r += " " + last + " ############# Version : " + version + " #####################";
		return r;
	}
}

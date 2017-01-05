package me.pixka.device.c;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.pixka.device.d.Device;
import me.pixka.device.d.Deviceconfig;
import me.pixka.device.s.DeviceService;
import me.pixka.device.s.DeviceconfigService;

@RestController
public class Readconfig {

	@Autowired
	private DeviceconfigService devicecfservice;
	@Autowired
	private DeviceService deviceservice;

	@CrossOrigin
	// produces = "application/json", consumes = "application/json"
	@RequestMapping(value = "/readconfig/{mac}", method = RequestMethod.GET)
	@ResponseBody
	/*
	 * public ResponseEntity<Deviceconfig> readdeviceconfig(@PathVariable("mac")
	 * String mac) throws Exception {
	 * 
	 * Device device = deviceservice.findBymac(mac);
	 * 
	 * if (device == null) { System.out.println("Device not found"); return new
	 * ResponseEntity<Deviceconfig>(HttpStatus.NOT_FOUND);
	 * 
	 * }
	 * 
	 * Deviceconfig deviceconfig = devicecfservice.findJob(device.getId());
	 * 
	 * if (deviceconfig == null) return new
	 * ResponseEntity<Deviceconfig>(HttpStatus.NOT_FOUND);
	 * 
	 * return new ResponseEntity<Deviceconfig>(deviceconfig, HttpStatus.OK);
	 * 
	 * }
	 */
	public Deviceconfig readdeviceconfig(@PathVariable("mac") String mac) throws Exception {

		Device device = deviceservice.findBymac(mac);

		if (device == null) {
			System.out.println("Device not found");
			return null;
		}

		Deviceconfig deviceconfig = devicecfservice.findJob(device.getId());

		if (deviceconfig == null)
			return null;
		return deviceconfig;

	}
}

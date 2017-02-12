package me.pixka.device.c;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.RaspiPin;

import me.pixka.data.obj.Bag;
import me.pixka.device.s.PifwService;

@RestController
public class PiControl {

	@Autowired
	private PifwService service;
	@RequestMapping(value = "/allpipins", method = RequestMethod.GET)
	@ResponseBody
	public List pins() {

		List buf = new ArrayList();

		Pin[] pins = RaspiPin.allPins();
		for (int i = 0; i < pins.length; i++) {
			buf.add(pins[i].getName());
		}
		return buf;
	}

	@RequestMapping(value = "/allpimodes", method = RequestMethod.GET)
	@ResponseBody
	public List modes() {
		List buf = new ArrayList();
		EnumSet<PinMode> modes = PinMode.all();
		for (PinMode pm : modes) {
			buf.add(pm.getName());
		}
		return buf;
	}

	@RequestMapping(value = "/allpiports", method = RequestMethod.GET)
	@ResponseBody
	public List allpiports() {
		List buf = new ArrayList();
		Pin[] pins = RaspiPin.allPins();
		for (int i = 0; i < pins.length; i++) {
			buf.add(pins[i].getName());
		}

		return buf;
	}
	
	@RequestMapping(value = "/allpifw", method = RequestMethod.GET)
	@ResponseBody
	public List allpifw() {

			return service.all();
			
	}
	
}

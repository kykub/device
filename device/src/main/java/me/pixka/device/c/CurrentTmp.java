package me.pixka.device.c;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import me.pixka.device.d.Device;
import me.pixka.device.d.Tmp;
import me.pixka.device.s.DeviceService;

@RestController
public class CurrentTmp {

	@Autowired
	private DeviceService deviceservice;

	@CrossOrigin
	@RequestMapping(value = "/rest/getone/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Device getone(@PathVariable("id") Long id) throws JsonProcessingException, IOException {
		return deviceservice.findById(id);
	}

	@CrossOrigin
	@RequestMapping(value = "/rest/getstatus/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Tmp getstatus(@PathVariable("id") Long id) throws JsonProcessingException, IOException {
		Tmp tmp = deviceservice.lastTmpById(id);
		return tmp;
	}
}

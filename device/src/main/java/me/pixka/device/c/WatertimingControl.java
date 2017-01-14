package me.pixka.device.c;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.pixka.device.d.Device;
import me.pixka.device.d.Watertiming;
import me.pixka.device.s.DeviceService;
import me.pixka.device.s.WatertimingService;

@RestController
public class WatertimingControl {

	@Autowired
	private WatertimingService service;
	@Autowired
	private DeviceService deviceservice;

	@CrossOrigin
	@RequestMapping(value = "/water/readtiming/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Watertiming get(@PathVariable("id") Long id) {
		Watertiming data = service.findlast(id);
		if (data != null) {
			System.out.println("Found water config :" + data);
			return data;
		}

		return null;

	}
	
	@CrossOrigin
	@RequestMapping(value = "/water/readtiming/{id}/{tmp}", method = RequestMethod.GET)
	@ResponseBody
	public Watertiming get(@PathVariable("id") Long id,@PathVariable("tmp") BigDecimal tmp) {
		Watertiming data = service.findlast(id,tmp);
		if (data != null) {
			System.out.println("Found water at:"+tmp+" config :" + data);
			return data;
		}

		return null;

	}

	@CrossOrigin
	@RequestMapping(value = "/rest/watertiming/add", method = RequestMethod.POST)
	@ResponseBody
	public Watertiming add(@RequestBody Watertiming d) {
		System.out.println("New Watertiming" + d);
		Device device = deviceservice.findById(d.getDevice_id());
		d.setAdddate(new Date());
		d.setDevice(device);
		d = service.add(d);
		System.out.println(d);
		return d;
	}

	@CrossOrigin
	@RequestMapping(value = "/rest/watertiming/delete", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public void delete(@RequestBody Watertiming d) {
		System.out.println("delete timing" + d);
		service.delete(d);
	}

}

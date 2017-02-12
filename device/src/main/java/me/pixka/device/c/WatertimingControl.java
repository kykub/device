package me.pixka.device.c;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.pixka.data.obj.P;
import me.pixka.device.d.Device;
import me.pixka.device.d.Searchtmp;
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
	@RequestMapping(value = "/water/readtiming/{id}/{tmp:.+}", method = RequestMethod.GET)
	@ResponseBody
	public Watertiming get(@PathVariable("id") Long id, @PathVariable("tmp") BigDecimal tmp) {
		System.out.println("GET By TMP id:+" + id + " tmp:" + tmp);
		Watertiming data = service.findlast(id, tmp);
		if (data != null) {
			System.out.println("Found water at:" + tmp + " config :" + data);
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

	@RequestMapping(value = "/rest/getwatertimingbypage", method = RequestMethod.POST)
	@ResponseBody
	public P gettmps(@RequestBody Searchtmp js) {
		P p = new P();
		Long cs = service.searchcount(js.getSearch());
		p.setCount(cs.intValue());
		List list = service.search(js.getSearch(), js.getPage(), js.getPp());
		p.setItems(list);
		return p;
	}

	@CrossOrigin
	@RequestMapping(value = "/rest/watertiming/list/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List add(@PathVariable("id") Long id) {

		return service.findByDeviceId(id);
	}

	@CrossOrigin
	@RequestMapping(value = "/water/load/{mac}/{refid}", method = RequestMethod.GET)
	@ResponseBody
	public List loads(@PathVariable("mac") String mac, @PathVariable("refid") Long refid) {

		Device device = deviceservice.findBymac(mac);
		if (device != null) {
			return service.last(device.getId(), refid);
		}

		return null;
	}

	@CrossOrigin
	@RequestMapping(value = "/rest/water/resetconfig/{mac}", method = RequestMethod.GET)
	@ResponseBody
	public List reset(@PathVariable("mac") String mac) {

		Device device = deviceservice.findBymac(mac);
		if (device != null) {
			service.reset(device.getId());
		}

		return null;
	}

}

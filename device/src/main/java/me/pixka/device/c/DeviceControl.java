package me.pixka.device.c;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import me.pixka.device.d.Device;
import me.pixka.device.d.Tmp;
import me.pixka.device.s.DeviceService;

@RestController
public class DeviceControl {
	public class Bag {
		private Device device;

		public Device getDevice() {
			return device;
		}

		public void setDevice(Device device) {
			this.device = device;
		}

		public BigDecimal getH() {
			return h;
		}

		public void setH(BigDecimal h) {
			this.h = h;
		}

		public BigDecimal getT() {
			return t;
		}

		public void setT(BigDecimal t) {
			this.t = t;
		}

		private BigDecimal h, t;
		private Date stime;

		public Date getStime() {
			return stime;
		}

		public void setStime(Date stime) {
			this.stime = stime;
		}
	}

	@Autowired
	private DeviceService deviceservice;

	@CrossOrigin
	// produces = "application/json", consumes = "application/json"
	@RequestMapping(value = "/rest/listdevices", method = RequestMethod.GET)
	@ResponseBody
	public List get(String text) throws JsonProcessingException, IOException {

		List buf = new ArrayList();
		List<Device> devices = deviceservice.list();

		for (Device item : devices) {

			Bag b = new Bag();
			b.setDevice(item);

			Tmp t = deviceservice.lastTmpById(item.getId());
			if (t != null) {
				b.setH(t.getH());
				b.setT(t.getT());
				b.setStime(t.getAdddate());
			} else {
				b.setT(BigDecimal.ZERO);
				b.setH(BigDecimal.ZERO);
				b.setStime(new Date());
			}

			buf.add(b);

		}

		return buf;
	}

	@CrossOrigin
	@RequestMapping(value = "/rest/device/lds", method = RequestMethod.GET)
	@ResponseBody
	public List gets() throws JsonProcessingException, IOException {

		List<Device> devices = deviceservice.list();
		return devices;
	}

	/*
	 * @CrossOrigin
	 * 
	 * @RequestMapping(value = "/adddevice", method =
	 * RequestMethod.POST,produces = "application/json", consumes =
	 * "application/json")
	 * 
	 * @ResponseBody public Device add(@RequestBody Device device) throws
	 * JsonProcessingException, IOException { System.out.println(device); return
	 * deviceservice.add(device);
	 * 
	 * }
	 */
	@CrossOrigin
	@RequestMapping(value = "/rest/adddevice", method = RequestMethod.POST)
	@ResponseBody
	public Device add(@RequestBody Device device) throws JsonProcessingException, IOException {
		System.out.println(device);
		return deviceservice.add(device);

	}

	@CrossOrigin
	//
	@RequestMapping(value = "/rest/adddevicebyget", method = RequestMethod.GET)
	@ResponseBody
	public Device addgetmethod(@RequestBody Device device) throws JsonProcessingException, IOException {
		System.out.println(device);
		return deviceservice.add(device);

	}

	@CrossOrigin
	@RequestMapping(value = "/rest/editdevice", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public Device editdevice(@RequestBody Device device) throws JsonProcessingException, IOException {
		System.out.println(device);

		Device d = deviceservice.findById(device.getId());
		d.setTtune(device.getTtune());
		d.setName(device.getName());
		d.setHtune(device.getHtune());
		return deviceservice.save(d);

	}

	public Device findByRefid(Long refid) {
		return deviceservice.findByRefid(refid);
	}

	public Device save(Device device) {
		return deviceservice.save(device);
	}

	public List<Device> findAll() {
		return deviceservice.list();
	}

}

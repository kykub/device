package me.pixka.device.c;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import me.pixka.device.d.Device;
import me.pixka.device.d.Deviceconfig;
import me.pixka.device.r.DeviceRepo;
import me.pixka.device.s.DeviceconfigService;

@RestController
public class DeviceconfigControl {
	@Autowired
	private DeviceconfigService devicecfservice;
	@Autowired
	private DeviceRepo ds;

	// userBy angular2
	@CrossOrigin
	// produces = "application/json", consumes = "application/json"
	@RequestMapping(value = "/rest/getdeviceconfig/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Deviceconfig get(@PathVariable("id") Long id) throws JsonProcessingException, IOException {

		// หาว่าช่วงนี้มีการกำหนดหรือเปล่า
		Deviceconfig deviceconfig = devicecfservice.findJob(id, new Date());

		if (deviceconfig != null) {
			/**
			 * ไม่ต้อง set read
			 */
			return deviceconfig;
		}
		// หาข้อมูลที่ยังไม่ได้อ่าน
		deviceconfig = devicecfservice.findLast(id);
		if (deviceconfig != null) {
			deviceconfig.setRead(true);
			devicecfservice.save(deviceconfig); // ต้องบอกว่า อ่านแล้ว
												// หรือใช้แล้วจะกลับมาใช้อีกไม่ได้
			return deviceconfig;
		}
		// หาอันที่อ่านหลังสุด
		deviceconfig = devicecfservice.findLastRead(id);

		if (deviceconfig != null)
			return deviceconfig;

		return null;

	}

	// /rest/getdeviceconfigbyid/

	@CrossOrigin
	// produces = "application/json", consumes = "application/json"
	@RequestMapping(value = "/rest/getdeviceconfigbyid/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Deviceconfig getbyid(@PathVariable("id") String id) throws JsonProcessingException, IOException {

		// หาว่าช่วงนี้มีการกำหนดหรือเปล่า
		Deviceconfig deviceconfig = devicecfservice.findById(Long.valueOf(id));

		if (deviceconfig != null) {
			return deviceconfig;
		}

		return null;

	}

	@CrossOrigin
	@RequestMapping(value = "/getdeviceconfigmac/{mac}", method = RequestMethod.GET)
	@ResponseBody
	public Deviceconfig get(@PathVariable("mac") String mac) throws JsonProcessingException, IOException {

		// หาว่าช่วงนี้มีการกำหนดหรือเปล่า
		Deviceconfig deviceconfig = devicecfservice.findJob(mac, new Date());

		if (deviceconfig != null)
			return deviceconfig;

		deviceconfig = devicecfservice.findLast(mac);

		if (deviceconfig != null) {
			if (deviceconfig.getRuntime() > 0) // ถ้าไม่ใข่ -1 เป็นแบบ run ตลอด
												// แต่ถ้า >0 run ครั้งเดียว
				deviceconfig.setRead(true);
			devicecfservice.save(deviceconfig);
			return deviceconfig;
		}

		Device dv = ds.findByMac(mac);
		deviceconfig = devicecfservice.findLastRead(dv.getId());

		if (deviceconfig != null)
			return deviceconfig;

		return null;

	}


	@CrossOrigin
	@RequestMapping(value = "/getdeviceconfigmac/{mac}/{refid}", method = RequestMethod.GET)
	@ResponseBody
	public List get(@PathVariable("mac") String mac,@PathVariable("refid") Long refid) throws JsonProcessingException, IOException {

		// หาว่าช่วงนี้มีการกำหนดหรือเปล่า
		return devicecfservice.findRefid(mac, refid);

		

	}

	@CrossOrigin
	// produces = "application/json", consumes = "application/json"
	@RequestMapping(value = "/endjob/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Deviceconfig end(@PathVariable("id") String id) throws JsonProcessingException, IOException {

		// หาว่าช่วงนี้มีการกำหนดหรือเปล่า
		Deviceconfig deviceconfig = devicecfservice.findById(Long.valueOf(id));

		if (deviceconfig != null) {
			deviceconfig.setEnable(false); // Run รัน เสร็จแล้ว
			deviceconfig = devicecfservice.save(deviceconfig);
			return deviceconfig;
		}
		return null;

	}

	public Deviceconfig findRefId(Long id) {
		return devicecfservice.findByRefId(id);
	}

	public Deviceconfig last() {

		return devicecfservice.last();
	}

	public Deviceconfig save(Deviceconfig deviceconfig) {
		return devicecfservice.save(deviceconfig);
	}

	@CrossOrigin
	@RequestMapping(value = "/finddeviceconfigbyh/{id}/{h}", method = RequestMethod.GET)
	@ResponseBody
	public Deviceconfig findbyh(@PathVariable("id") Long id, @PathVariable("h") BigDecimal h)
			throws JsonProcessingException, IOException {
		// หาว่าช่วงนี้มีการกำหนดหรือเปล่า
		Deviceconfig deviceconfig = devicecfservice.findByH(id, h);
		return deviceconfig;

	}

	@CrossOrigin
	@RequestMapping(value = "/finddeviceconfigbymach/{mac}/{h}", method = RequestMethod.GET)
	@ResponseBody
	public Deviceconfig findbymach(@PathVariable("mac") String mac, @PathVariable("h") BigDecimal h)
			throws JsonProcessingException, IOException {
		// หาว่าช่วงนี้มีการกำหนดหรือเปล่า
		Device device = ds.findByMac(mac);
		if (device != null) {
			Deviceconfig deviceconfig = devicecfservice.findByH(device.getId(), h);
			return deviceconfig;
		}

		return null;
	}

	@CrossOrigin
	@RequestMapping(value = "/finddeviceconfigbyt/{id}/{t}", method = RequestMethod.GET)
	@ResponseBody
	public Deviceconfig findbyt(@PathVariable("id") Long id, @PathVariable("t") BigDecimal t)
			throws JsonProcessingException, IOException {
		// หาว่าช่วงนี้มีการกำหนดหรือเปล่า
		Deviceconfig deviceconfig = devicecfservice.findByT(id, t);
		return deviceconfig;

	}

	@CrossOrigin
	@RequestMapping(value = "/finddeviceconfigbymact/{mac}/{t}", method = RequestMethod.GET)
	@ResponseBody
	public Deviceconfig findbyt(@PathVariable("mac") String mac, @PathVariable("t") BigDecimal t)
			throws JsonProcessingException, IOException {
		// หาว่าช่วงนี้มีการกำหนดหรือเปล่า
		Device device = ds.findByMac(mac);
		if (device != null) {

			Deviceconfig deviceconfig = devicecfservice.findByT(device.getId(), t);
			return deviceconfig;
		}
		return null;

	}

	@CrossOrigin
	@RequestMapping(value = "/loadconfigs/{mac}", method = RequestMethod.GET)
	@ResponseBody
	public List<Deviceconfig> loadconfigs(@PathVariable("mac") String mac) throws JsonProcessingException, IOException {
		// หาว่าช่วงนี้มีการกำหนดหรือเปล่า
		Device device = ds.findByMac(mac);
		if (device != null) {
			return devicecfservice.finds(device.getId(), true);
		}
		return null;

	}
	
	
	@CrossOrigin
	@RequestMapping(value = "/deviceconfig/load/{mac}/{lastid}", method = RequestMethod.GET)
	@ResponseBody
	public List<Deviceconfig> loadconfigs(@PathVariable("mac") String mac,@PathVariable("lastid") Long lastid) throws JsonProcessingException, IOException {
		// หาว่าช่วงนี้มีการกำหนดหรือเปล่า
		Device device = ds.findByMac(mac);
		
		if (device != null) {
		//	return devicecfservice.finds(device.getId(), true);
			return devicecfservice.findByLast(device.getId(),lastid);
		}
		return null;

	}

	@CrossOrigin
	@RequestMapping(value = "/loadonetimedeviceconfig/{mac}", method = RequestMethod.GET)
	@ResponseBody
	public Deviceconfig loadonetimeconfig(@PathVariable("mac") String mac) throws JsonProcessingException, IOException {
		// หาว่าช่วงนี้มีการกำหนดหรือเปล่า
		Device device = ds.findByMac(mac);
		if (device != null) {

			Deviceconfig task = devicecfservice.findonetimeconfig(device.getId());
			// devicecfservice.delete(task);

			return task;
		}
		return null;

	}

	/**
	 * ใช้สำหรับ บอก Server ว่า Deviceconfig นี้ run เสร็จแล้วจะ ลบออกจากระบบ
	 * @param id
	 */
	@CrossOrigin
	@RequestMapping(value = "/finishedonetime/{id}", method = RequestMethod.GET)
	// @ResponseBody
	public void runOnetimeCompelete(@PathVariable("id") Long id) {

		devicecfservice.delete(id);

	}
}

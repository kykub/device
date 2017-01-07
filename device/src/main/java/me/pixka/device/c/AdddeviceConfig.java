package me.pixka.device.c;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.pixka.device.d.Device;
import me.pixka.device.d.Deviceconfig;
import me.pixka.device.s.DeviceService;
import me.pixka.device.s.DeviceconfigService;


@RestController
public class AdddeviceConfig {

	@Autowired
	private DeviceconfigService devicecfservice;
	@Autowired
	private DeviceService deviceservice;

//	@CrossOrigin
//	// produces = "application/json", consumes = "application/json"
//	@RequestMapping(value = "/rest/adddeviceconfig", method = RequestMethod.GET)
//	@ResponseBody
//	public String add(String text) throws JsonProcessingException, IOException {
//		ObjectMapper mapper = new ObjectMapper();
//		System.out.println(text);
//		JsonNode root = mapper.readTree(text);
//		System.out.println(root.get("email"));
//		return "{ok}";
//	}

	@CrossOrigin
	// produces = "application/json", consumes = "application/json"
	@RequestMapping(value = "/rest/adddeviceconfig", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public Deviceconfig addpost(@RequestBody Deviceconfig d) {
		System.out.println(d);
		/*
		 * Long rt = d.getRuntime(); rt = rt * 60 * 1000;// หน่วยเป็นนาที
		 * ต้องเอามา X ก่อนเข้า d.setRuntime(rt);
		 */
		Device device = deviceservice.findById(d.getDevice_id());
		d.setAdddate(new Date());
		d.setDevice(device);
		d = devicecfservice.add(d);
		System.out.println(d);
		return d;

	}

	@CrossOrigin
	@RequestMapping(value = "/rest/editdeviceconfig", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public Deviceconfig editpost(@RequestBody Deviceconfig d) {
		System.out.println(d);
		Deviceconfig dd = null;
		if (d.getId() == null)
			dd = devicecfservice.add(d);
		else
			dd = devicecfservice.findById(d.getId());

		dd.setPort1open(d.getPort1open());
		dd.setPort2open(d.getPort2open());

		dd.setSdate(d.getSdate());
		dd.setStime(d.getStime());
		dd.setEdate(d.getEdate());
		dd.setEtime(d.getEtime());
		dd.setH(d.getH());
		dd.setT(d.getT());

		System.out.println(d);
		dd = devicecfservice.save(dd);
		return dd;

	}
}

package me.pixka.device.c;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.pixka.device.d.Device;
import me.pixka.device.d.Tmp;
import me.pixka.device.r.DeviceRepo;
import me.pixka.device.r.TmpRepo;

@Controller
public class AddTmp {
	@Autowired
	private TmpRepo dao;
	@Autowired
	private DeviceRepo ddao;

	@RequestMapping("/add")
	@ResponseBody
	public String add(@RequestParam(value = "t", required = false, defaultValue = "0") String t,
			@RequestParam(value = "h", required = true, defaultValue = "0") String h,
			@RequestParam(value = "m", required = true, defaultValue = "0") String m) {

		Device device = ddao.findByMac(m);
		if (device == null) {
			device = new Device();
			device.setMac(m);
			device = ddao.save(device);
		}
		Tmp tmp = new Tmp();
		tmp.setH(new BigDecimal(h).add(device.getHtune()));
		tmp.setT(new BigDecimal(t).add(device.getTtune()));
		tmp.setDevice(device);
		tmp.setAdddate(new Date());
		tmp = dao.save(tmp);
		return "ok";

	}

}

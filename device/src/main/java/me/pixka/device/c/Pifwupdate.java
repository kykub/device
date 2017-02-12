package me.pixka.device.c;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.pixka.device.d.Pifw;
import me.pixka.device.s.PifwService;

@RestController
public class Pifwupdate {

	@Autowired
	private PifwService service;

	@RequestMapping(value = "/pifwinfo/{ver}", method = RequestMethod.GET)
	@ResponseBody
	public Pifw update(@PathVariable("ver") String ver) {
		Pifw last = service.findlast();
		if (!last.getVerno().equals(ver)) {
			return last;
		}
		return null;

	}
	@RequestMapping(value = "/pifwinfo/{ver}/{appname}", method = RequestMethod.GET)
	@ResponseBody
	public Pifw update(@PathVariable("ver") String ver,@PathVariable("appname") String appname) {
		Pifw last = service.findlast(appname);
		if (!last.getVerno().equals(ver)) {
			return last;
		}
		return null;

	}
}

package me.pixka.device.c;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.pixka.device.d.Device;
import me.pixka.device.d.Ds18data;
import me.pixka.device.d.Gettmp;
import me.pixka.device.r.DeviceRepo;
import me.pixka.device.s.Ds18Service;

@RestController
public class Ds18control {

	@Autowired
	private Ds18Service service;
	@Autowired
	private DeviceRepo ddao;

	DateFormat df = new SimpleDateFormat("ddMMyyyy");
	DateFormat tdf = new SimpleDateFormat("ddMMyyyy HH:mm:ss");
	DateFormat ef = new SimpleDateFormat("HH:mm:ss");

	@RequestMapping(value = "/addds", method = RequestMethod.GET)
	@ResponseBody
	public String add(@RequestParam(value = "t", required = false, defaultValue = "0") String t,
			@RequestParam(value = "m", required = true, defaultValue = "0") String m) {

		Device device = ddao.findByMac(m);
		if (device == null) {
			device = new Device();
			device.setMac(m);
			device = ddao.save(device);
		}
		Ds18data tmp = new Ds18data();

		tmp.setTmp(new BigDecimal(t).add(device.getTtune()));
		tmp.setDevice(device);
		tmp.setAdddate(new Date());
		tmp = service.save(tmp);

		System.out.println("Tmp:" + tmp);
		return "ok";

	}

	// produces = "application/json", consumes = "application/json"
	@CrossOrigin
	@RequestMapping(value = "/rest/ds18b20/getgraph", method = RequestMethod.POST)
	@ResponseBody
	public List process(@RequestBody Gettmp p) throws Exception {

		System.out.println(p.getId() + " " + p.getS() + " " + p.getE());
		String sd = df.format(p.getS());
		String ed = df.format(p.getE());
		Date sds = tdf.parse(sd + " " + p.getSt() + ":00:00");
		Date eds = tdf.parse(ed + " " + p.getEt() + ":59:00");

		List<Ds18data> list = service.findGraph(Long.valueOf(p.getId()), sds, eds);
		List buf = new ArrayList();
		System.out.println("Found ds18data-------------------->" + list.size());
		for (Ds18data i : list) {
			Ds d = new Ds();
			d.setAdddate(ef.format(i.getAdddate()));
			d.setTmp(i.getTmp());
			buf.add(d);
		}
		// System.out.println("Found:" + list.size() + "buf " + buf);
		return buf;
	}

	// produces = "application/json", consumes = "application/json"
	@CrossOrigin
	@RequestMapping(value = "/rest/ds18b20/getlas" + "t", method = RequestMethod.POST)
	@ResponseBody
	public Ds getlast(@RequestBody Gettmp p) throws Exception {

		System.out.println(p.getId() + " " + p.getS() + " " + p.getE());
		String sd = df.format(p.getS());
		String ed = df.format(p.getE());
		Date sds = tdf.parse(sd + " " + p.getSt() + ":00:00");
		Date eds = tdf.parse(ed + " " + p.getEt() + ":59:00");

		Ds18data data = service.findlast(Long.valueOf(p.getId()), sds, eds);
		if (data != null) {
			Ds d = new Ds();
			d.setAdddate(tdf.format(data.getAdddate()));
			d.setTmp(data.getTmp());

			System.out.println("Found last item :" + d);
			return d;
		}

		Ds d = new Ds();
		d.setAdddate(ef.format(new Date()));
		d.setTmp(new BigDecimal("0.00"));

		System.out.println("New  last item :" + d);
		return d;

	}

	@CrossOrigin
	@RequestMapping(value = "/readds18b20/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Ds get(@PathVariable("id") Long id) throws Exception {
		Ds18data data = service.findlast(id);
		if (data != null) {
			Ds d = new Ds();
			d.setAdddate(ef.format(data.getAdddate()));
			d.setTmp(data.getTmp());

			System.out.println("Found last item :" + d);
			return d;
		}

		Ds d = new Ds();
		d.setAdddate(ef.format(new Date()));
		d.setTmp(new BigDecimal("0.00"));

		System.out.println("New  last item :" + d);
		return d;

	}

	class Ds {
		private BigDecimal tmp;
		private String adddate;

		public BigDecimal getTmp() {
			return tmp;
		}

		public void setTmp(BigDecimal tmp) {
			this.tmp = tmp;
		}

		public String getAdddate() {
			return adddate;
		}

		public void setAdddate(String adddate) {
			this.adddate = adddate;
		}

		@Override
		public String toString() {
			return "Ds [tmp=" + tmp + ", adddate=" + adddate + "]";
		}

	}
}

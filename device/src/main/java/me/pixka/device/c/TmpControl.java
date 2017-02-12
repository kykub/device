package me.pixka.device.c;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.pixka.data.obj.P;
import me.pixka.device.d.Forgraph;
import me.pixka.device.d.Gettmp;
import me.pixka.device.d.Searchtmp;
import me.pixka.device.d.Tmp;
import me.pixka.device.s.Tmpimpl;

@RestController
public class TmpControl {

	@Autowired
	private Tmpimpl tmp;

	@RequestMapping(value = "/tmp/", method = RequestMethod.GET)
	public ResponseEntity<List<Tmp>> listAllUsers() {

		List tmps = tmp.findAll();
		if (tmps.isEmpty()) {
			return new ResponseEntity<List<Tmp>>(HttpStatus.NO_CONTENT);// You
																		// many
																		// decide
																		// to
																		// return
																		// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Tmp>>(tmps, HttpStatus.OK);
	}

	DateFormat df = new SimpleDateFormat("ddMMyyyy");
	DateFormat tdf = new SimpleDateFormat("ddMMyyyy HH:mm:ss");

	// produces = "application/json", consumes = "application/json"
	@CrossOrigin
	@RequestMapping(value = "/rest/getgraph", method = RequestMethod.POST)
	@ResponseBody
	public List process(@RequestBody Gettmp p) throws Exception {

		System.out.println(p.getId() + " " + p.getS() + " " + p.getE());
		String sd = df.format(p.getS());
		String ed = df.format(p.getE());
		Date sds = tdf.parse(sd + " 00:00:00");
		Date eds = tdf.parse(ed + " 23:59:00");

		List<Object[]> list = tmp.findGraph(Long.valueOf(p.getId()), sds, eds);
		List buf = new ArrayList();
		for (Object[] o : list) {
			Forgraph fg = new Forgraph();
			fg.setHour(Long.parseLong(o[0].toString()));
			fg.setDay(Long.parseLong(o[1].toString()));
			fg.setMonth(Long.parseLong(o[2].toString()));
			fg.setYear(Long.parseLong(o[3].toString()));
			fg.setH(new BigDecimal(o[4].toString()).setScale(2, RoundingMode.HALF_UP));
			fg.setT(new BigDecimal(o[5].toString()).setScale(2, RoundingMode.HALF_UP));
			buf.add(fg);

		}
		System.out.println("Found:" + list.size());
		return buf;
	}

	@RequestMapping(value = "/rest/gettmps", method = RequestMethod.POST)
	public P gettmps(@RequestBody Searchtmp js) {
		P p = new P();
		Long cs = tmp.searchcount(js.getSearch());
		p.setCount(cs.intValue());
		List list = tmp.search(js.getSearch(), js.getPage(), js.getPp());
		p.setItems(list);
		return p;
	}

}

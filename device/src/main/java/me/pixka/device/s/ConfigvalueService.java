package me.pixka.device.s;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.pixka.data.s.DefaultService;
import me.pixka.device.d.ConfigValue;
import me.pixka.device.r.ConfigValueRepo;

@Service
@Transactional
public class ConfigvalueService extends DefaultService {

	@Autowired
	private ConfigValueRepo dao;

	public String get(String name, String defaultvalue) {
		ConfigValue cf = dao.findTop1ByNameOrderByIdDesc(name);
		if (cf != null)
			return cf.getValue();

		cf = new ConfigValue();
		cf.setName(name);
		cf.setValue(defaultvalue);
		cf = dao.save(cf);
		return cf.getValue();
	}
}

package me.pixka.device.s;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.pixka.device.d.PortConfig;
import me.pixka.device.r.PortconfigRepo;

@Service
@Transactional
public class PortConfigService {
	@Autowired
	private PortconfigRepo dao;

	public PortConfig save(PortConfig p) {

		return dao.save(p);
	}

	public List all() {
		return dao.findAll();
	}

}

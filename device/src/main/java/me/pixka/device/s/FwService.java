package me.pixka.device.s;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.pixka.device.d.Fw;
import me.pixka.device.r.FwRepo;

@Service
@Transactional
public class FwService {

	@Autowired
	private FwRepo dao;

	public Fw save(Fw fw) {
		return dao.save(fw);
	}

	public Fw getLast() {
		return dao.findTop1ByOrderByIdDesc();
	}

	public Iterable<Fw> list() {
		return dao.findAll();
	}
}

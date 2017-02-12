package me.pixka.device.s;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.pixka.data.s.DefaultService;
import me.pixka.device.d.Pifw;
import me.pixka.device.r.PifwRepo;

@Service
@Transactional
public class PifwService extends DefaultService {

	@Autowired
	private PifwRepo dao;

	public Pifw save(Pifw fw) {
		return dao.save(fw);
	}

	public Pifw findlast() {

		return dao.findTop1ByOrderByIdDesc();
	}

	public Pifw findlast(String app) {

		return dao.findTop1ByAppnameOrderByIdDesc(app);
	}

	public Pifw findByVersion(String ver) {
		return dao.findByVerno(ver);
	}

	public List all() {
		return (List) dao.findAll();
	}

}

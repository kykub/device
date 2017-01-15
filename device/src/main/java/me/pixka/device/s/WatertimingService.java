package me.pixka.device.s;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.pixka.device.d.Watertiming;
import me.pixka.device.r.WatertimingRepo;

@Service
@Transactional
public class WatertimingService {
	@Autowired
	private WatertimingRepo dao;

	public Watertiming findlast(Long id) {
		return dao.findTop1ByDevice_idAndEnableOrderByIdDesc(id, true);
	}

	public Watertiming add(Watertiming d) {
		return dao.save(d);
	}

	public void delete(Watertiming d) {
		dao.delete(d);
	}

	public Watertiming findlast(Long id, BigDecimal tmp) {

		return dao.findTop1ByDevice_idAndRunatGreaterThanEqualAndRunmaxLessThanEqualAndEnableOrderByIdDesc(id, tmp, tmp,
				true);
	}

	public Watertiming findByRefid(Long id) {
		return dao.findByRefid(id);
	}

	public Watertiming save(Watertiming wwwww) {
		return dao.save(wwwww);
	}

}

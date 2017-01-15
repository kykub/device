package me.pixka.device.s;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.pixka.data.s.DefaultService;
import me.pixka.device.d.Watertiming;
import me.pixka.device.r.WatertimingRepo;


@Service
@Transactional
public class WatertimingService extends DefaultService {
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

		/*
		 * return dao.
		 * findTop1ByDevice_idAndRunatGreaterThanEqualAndRunmaxLessThanEqualAndEnableOrderByIdDesc
		 * (id, tmp, tmp, true);
		 */

		Pageable p = this.getPage(0L, 1L);
		List list = dao.findBytmp(id, tmp, p);
		
		System.out.println("---------------------> Water item id:"+id+" tmp:"+tmp+" size:"+list.size());
		if(list.isEmpty())
			return null;
		return (Watertiming) list.get(0);

	}

	public Watertiming findByRefid(Long id) {
		return dao.findByRefid(id);
	}

	public Watertiming save(Watertiming wwwww) {
		return dao.save(wwwww);
	}

}

package me.pixka.device.s;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

		Watertiming obj = dao.findTop1ByDevice_idAndTmplowLessThanEqualAndTmphighGreaterThanEqualOrderByIdDesc(id, tmp,
				tmp);

		return obj;
	}

	public Watertiming findByRefid(Long id) {
		return dao.findByRefid(id);
	}

	public Watertiming save(Watertiming wwwww) {
		return dao.save(wwwww);
	}

	public List search(String n, int p, int pp) {
		if (p > 0)
			p--;
		PageRequest request = new PageRequest(p, pp, Sort.Direction.ASC, "id");
		return dao.search(n, request);
	}

	public Long searchcount(String search) {
		return dao.searchcount(search);
	}

	/**
	 * ใช้สำหรับ local device หา ค่าของตัวเอง
	 * 
	 * @param tmp
	 * @return
	 */
	public Watertiming findlast(BigDecimal tmp) {
		return dao.findTop1ByTmplowLessThanEqualAndTmphighGreaterThanEqualOrderByIdDesc(tmp, tmp);
	}

	public List findByDeviceId(Long id) {
		return dao.findByDevice_id(id);
	}

	/**
	 * ใช้สำหรับหาค่า สุดท้ายในเครื่อง
	 * 
	 * @return
	 */
	public Watertiming last() {
		return dao.findTop1ByOrderByIdDesc();
	}

	public Long lastrefid() {
		Watertiming wt = last();

		if (wt != null && wt.getRefid() != null)
			return wt.getRefid();

		return 0L;
	}

	public List last(Long id, Long refid) {
		return dao.findByDevice_idAndIdGreaterThan(id, refid);
	}

	public void reset(Long id) {
		dao.deleteAll();
	}
}

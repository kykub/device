package me.pixka.device.s;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.pixka.device.d.Deviceconfig;
import me.pixka.device.r.DeviceconfigRepo;

@Service
@Transactional
public class DeviceconfigService {
	@Autowired
	private DeviceconfigRepo dao;

	public Deviceconfig findJob(Long id) {
		return dao.findOne(id);
	}

	public Deviceconfig findLast(Long id) {
		return dao.findTop1ByDevice_IdAndReadOrderByIdDesc(id, false);
	}

	public Deviceconfig add(Deviceconfig d) {
		return dao.save(d);
	}

	/**
	 * หาในช่วงเวลาที่กำหนด
	 * 
	 * @param id
	 * @param date
	 * @return
	 */
	public Deviceconfig findJob(Long id, Date date) {

		List list = dao.findByDate(id, date);

		if (list != null && list.size() > 0) {
			return (Deviceconfig) list.get(0);
		}

		return null;
	}

	public Deviceconfig findJob(String mac, Date date) {
		return dao.findJob(mac, date);
	}

	public Deviceconfig findLast(String mac) {

		List list = dao.findLastBymac(mac);

		if (list != null && list.size() > 0) {
			return (Deviceconfig) list.get(0);
		}

		return null;
	}

	public Deviceconfig findById(Long id) {

		return dao.findOne(id);
	}

	public Deviceconfig save(Deviceconfig dd) {
		return dao.save(dd);
	}

	public Deviceconfig findLastRead(Long id) {
		return dao.findTop1ByDevice_IdAndReadOrderByIdDesc(id, true);
	}

	public Deviceconfig findByRefId(Long id) {
		return dao.findByRefid(id);
	}

	/**
	 * หา deviceconfig ล่าสุดของ Device
	 * 
	 * @return
	 */
	public Deviceconfig last() {
		return dao.findTop1ByOrderByIdDesc();
	}

	public Deviceconfig findByH(Long deviceid, BigDecimal h) {
		return dao.findTop1ByDevice_idAndLowhLessThanEqualAndHighhGreaterThanEqualOrderByIdDesc(deviceid, h, h);
	}

	public Deviceconfig findByT(Long id, BigDecimal t) {
		return dao.findTop1ByDevice_idAndLowtLessThanEqualAndHightGreaterThanEqualOrderByIdDesc(id, t, t);
	}

	public List<Deviceconfig> finds(Long id, boolean b) {
		return dao.findByDevice_idAndEnable(id, b);
	}

	public Deviceconfig findonetimeconfig(Long id) {
		return dao.findTop1ByDevice_idAndOnetime(id, true);
	}

	public void delete(Deviceconfig dvf) {
		dao.delete(dvf);
	}

	public void delete(Long id) {
		dao.delete(id);
	}

	/**
	 * ใช้สำหรับ การหา device config ที่เริ่มจาก refid จะดึงมาเป็นชุด
	 * 
	 * @param mac
	 * @param refid
	 * @return
	 */
	public List findRefid(String mac, Long refid) {

		return null;
	}

	public List<Deviceconfig> findByLast(Long id, Long lastid) {
		return dao.findByDevice_idAndIdGreaterThan(id, lastid);
	}

}

package me.pixka.device.s;

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

}

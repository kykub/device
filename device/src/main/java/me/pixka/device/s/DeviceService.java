package me.pixka.device.s;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.pixka.device.d.Device;
import me.pixka.device.d.Tmp;
import me.pixka.device.r.DeviceRepo;
import me.pixka.device.r.TmpRepo;

@Service
@Transactional
public class DeviceService {

	@Autowired
	private DeviceRepo dao;
	@Autowired
	private TmpRepo tdao;

	/**
	 * ใช้สำหรับค้นหาว่า มี device อันไหนมี mac address ตรงกับตัวนี้บ้าง
	 * 
	 * @param mac
	 * @return
	 */
	public Device findBymac(String mac) {
		return dao.findByMac(mac);
	}

	public List list() {
		return dao.all();
	}

	public Device add(Device device) {
		return dao.save(device);
	}

	public Device findById(Long id) {
		return dao.findOne(id);
	}

	/**
	 * ค่าล่าสุดของ device
	 * 
	 * @param id
	 * @return
	 */
	public Tmp lastTmpById(Long id) {
		return tdao.findTop1ByDevice_idOrderByAdddateDesc(id);
	}

	public Device save(Device device) {
		return dao.save(device);

	}

	public Device findByRefid(Long refid) {
		return dao.findByRefid(refid);
	}
}

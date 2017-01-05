package me.pixka.device.r;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import me.pixka.device.d.Device;

@Repository
public interface DeviceRepo extends CrudRepository<Device, Long> {

	Device findByMac(String mac);

	@Query("from Device d order by d.id")
	List all();

	Device findByRefid(Long refid);
}

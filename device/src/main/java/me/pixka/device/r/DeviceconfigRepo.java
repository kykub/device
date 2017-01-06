package me.pixka.device.r;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import me.pixka.device.d.Deviceconfig;

@Repository
public interface DeviceconfigRepo extends CrudRepository<Deviceconfig, Long> {

	Deviceconfig findByIdOrderByAdddate(Long id);

	Deviceconfig findTop1ByDevice_IdAndReadOrderByIdDesc(Long id, Boolean read);

	@Query("from Deviceconfig dcf where dcf.device_id = ?1 and (dcf.sdate >= ?2 and dcf.edate <= ?2 )")
	List findByDate(Long id, Date date);

	@Query("from Deviceconfig dcf where dcf.device.mac = ?1 and (dcf.sdate >= ?2 and dcf.edate <= ?2 ) and dcf.enable = true")
	Deviceconfig findJob(String mac, Date date);

	@Query("from Deviceconfig dcf where dcf.device.mac = ?1  and dcf.read = false order by dcf.adddate desc ")
	List findLastBymac(String mac);

	Deviceconfig findTopByOrderByIdDesc();

	Deviceconfig findByRefid(Long id);

	Deviceconfig findById(Long id);

	Deviceconfig findTop1ByOrderByIdDesc();

}

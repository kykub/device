package me.pixka.device.r;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import me.pixka.device.d.Ds18data;

@Repository
public interface Ds18Repo extends CrudRepository<Ds18data, Long> {

	@Query(" from  Ds18data  t where t.device_id = ?1 and t.adddate >= ?2 and adddate <= ?3 order by t.adddate ")
	List findgraph(Long device_id, Date s, Date e);

	@Query(" from  Ds18data  t where t.device_id = ?1 and t.adddate >= ?2 and adddate <= ?3 order  by t.adddate DESC")
	List findlast(Long id, Date s, Date e, Pageable p);

	Ds18data findTop1ByDevice_idAndAdddateBetweenOrderByIdDesc(Long id,Date s,Date e);
}

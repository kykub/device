package me.pixka.device.r;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import me.pixka.device.d.Tmp;


@Repository
public interface TmpRepo extends CrudRepository<Tmp, Long>, PagingAndSortingRepository<Tmp, Long> {

	@Query("from Tmp")
	List all();

	Tmp findTop1ByDevice_idOrderByAdddateDesc(Long id);

	@Query("select hour(t.adddate),day(t.adddate),month(t.adddate),year(t.adddate),avg(t.h),avg(t.t) from Tmp  t where t.device_id = ?1 and t.adddate >= ?2 and adddate <= ?3 group by hour(t.adddate),day(t.adddate),month(t.adddate),year(t.adddate) order by year(t.adddate),month(t.adddate),day(t.adddate),hour(t.adddate) ")
	List findgraph(Long device_id, Date s, Date e);

	@Query("from Tmp t where t.device.name like %?1% ")
	List search(String s, Pageable p);

	@Query("select count(*) from Tmp t where t.device.name like %?1% ")
	Long countsearch(String s);

}

package me.pixka.device.r;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import me.pixka.device.d.Watertiming;

@Repository
public interface WatertimingRepo extends CrudRepository<Watertiming, Long> {

	Watertiming findTop1ByDevice_idOrderByIdDesc(Long id);

	Watertiming findTop1ByDevice_idAndEnableOrderByIdDesc(Long id, boolean b);

	Watertiming findTop1ByDevice_idAndTmplowLessThanEqualAndTmphighGreaterThanEqual(Long id, BigDecimal t1,
			BigDecimal t2);

	Watertiming findTop1ByDevice_idAndTmplowLessThanEqualAndTmphighGreaterThanEqualOrderByIdDesc(Long id, BigDecimal t1,
			BigDecimal t2);

	Watertiming findByRefid(Long id);

	List findTop1ByDevice_id(Long id);

	@Query("select count(*) from Watertiming t where t.device.name like %?1% ")
	Long countsearch(String s);

	@Query("from Watertiming t where t.device.name like %?1% ")
	List search(String s, Pageable p);

	@Query("select count(*) from Watertiming t where t.device.name like %?1% ")
	Long searchcount(String search);

	// ใช้สำหรับหา Local host หรือ local device
	Watertiming findTop1ByTmplowLessThanEqualAndTmphighGreaterThanEqualOrderByIdDesc(BigDecimal tmp, BigDecimal tmp2);

	List findByDevice_id(Long id);

	Watertiming findTop1ByOrderByIdDesc();

	List findByDevice_idAndIdGreaterThan(Long id, Long refid);

}

package me.pixka.device.r;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import me.pixka.device.d.Watertiming;

@Repository
public interface WatertimingRepo extends JpaRepository<Watertiming, Long>
// ,CrudRepository<Watertiming, Long>,
{

	Watertiming findTop1ByDevice_idOrderByIdDesc(Long id);

	Watertiming findTop1ByDevice_idAndEnableOrderByIdDesc(Long id, boolean b);

	// Watertiming
	// findTop1ByDevice_idAndRunatGreaterThanEqualAndRunmaxLessThanEqualAndEnableOrderByIdDesc(Long
	// id,
	// BigDecimal tmp, BigDecimal tmp1, boolean b);

	// Watertiming findTop1ByDevice_idAndRunatGreaterThanEqual(Long
	// did,BigDecimal v);

	Watertiming findTop1ByDevice_idAndTmplowLessThanEqualAndTmphighGreaterThanEqual(Long id, BigDecimal t1,
			BigDecimal t2);

	Watertiming findTop1ByDevice_idAndTmplowLessThanEqualAndTmphighGreaterThanEqualOrderByIdDesc(Long id, BigDecimal t1,
			BigDecimal t2);

	Watertiming findByRefid(Long id);

	@Query("from Watertiming w where w.device_id = ?1 and w.tmplow <= ?2 and  w.tmphigh >= ?2")
	List findBytmp(Long id, BigDecimal v);

	@Query("from Watertiming w where w.device_id = 1 and  w.tmplow <= 90 and w.tmphigh >= 96.5")
	List x();

	@Query("select w from Watertiming w where  w.tmplow <= ?1")
	List x(BigDecimal t1);
	// Watertiming findTop1ByDevice_idAndByRunatGreaterThanEqual(Long id,
	// BigDecimal tmp);

	// List findTop1ByDevice_idAndRunatGreaterThan(Long id, BigDecimal tmp);

	List findTop1ByDevice_id(Long id);
}

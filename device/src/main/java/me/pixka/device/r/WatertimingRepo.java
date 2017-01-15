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

	Watertiming findTop1ByDevice_idAndRunatGreaterThanEqualAndRunmaxLessThanEqualAndEnableOrderByIdDesc(Long id,
			BigDecimal tmp, BigDecimal tmp1, boolean b);

	Watertiming findByRefid(Long id);

	@Query("from Watertiming w where w.device_id = ?1 and (w.runat >= ?2 and w.runmax <= ?2)")
	List findBytmp(Long id, BigDecimal v, Pageable p);
}

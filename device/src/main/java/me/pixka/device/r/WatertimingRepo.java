package me.pixka.device.r;

import java.math.BigDecimal;

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
}

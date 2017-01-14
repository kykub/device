package me.pixka.device.r;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import me.pixka.device.d.Watertiming;

@Repository
public interface WatertimingRepo extends CrudRepository<Watertiming, Long> {

	Watertiming findTop1ByDevice_idOrderByIdDesc(Long id);

	Watertiming findTop1ByDevice_idAndEnableOrderByIdDesc(Long id, boolean b);

	Watertiming findTop1ByDevice_idAndRunAtAndEnableOrderByIdDesc(Long id, Long tmp, boolean b);

}

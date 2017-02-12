package me.pixka.device.r;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.pixka.device.d.PortConfig;
@Repository
public interface PortconfigRepo extends JpaRepository<PortConfig, Long> {
	

}

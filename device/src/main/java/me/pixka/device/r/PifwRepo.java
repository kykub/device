package me.pixka.device.r;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import me.pixka.device.d.Pifw;

@Repository
public interface PifwRepo extends CrudRepository<Pifw, Long> {

	Pifw findTop1ByOrderByIdDesc();

	Pifw findByVerno(String ver);

	Pifw findTop1ByAppnameOrderByIdDesc(String app);

}

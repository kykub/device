package me.pixka.device.r;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import me.pixka.device.d.ConfigValue;

@Repository
public interface ConfigValueRepo extends CrudRepository<ConfigValue, Long> {

	ConfigValue findTop1ByNameOrderByIdDesc(String name);

}

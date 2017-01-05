package me.pixka.device.r;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import me.pixka.device.d.Fw;


@Repository
public interface FwRepo extends CrudRepository<Fw, Long>, PagingAndSortingRepository<Fw, Long> {
	Fw findTop1ByOrderByIdDesc();
}

package me.pixka.device.s;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.pixka.device.d.Ds18data;
import me.pixka.device.r.Ds18Repo;

@Service
@Transactional
public class Ds18Service {
	@Autowired
	private Ds18Repo dao;

	public Ds18data save(Ds18data o) {

		return dao.save(o);
	}

	public List findGraph(Long id, Date s, Date e) {
		return dao.findgraph(id, s, e);
	}

	public Ds18data findlast(Long id, Date s, Date e) {
		/*
		PageRequest request = new PageRequest(0, 1, Sort.Direction.DESC, "adddate");
		List list = dao.findlast(id, s, e, request);
		System.out.println("Last object :" + list.get(0));
		return (Ds18data) list.get(0);
		*/
		
		Ds18data o = dao.findTop1ByDevice_idAndAdddateBetweenOrderByIdDesc(id, s, e);
		
		return o;
	}
	
}

package me.pixka.device.s;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import me.pixka.device.r.TmpRepo;

@Service("tmpService")
public class Tmpimpl implements TmpService {
	@Autowired
	private TmpRepo dao;

	@Override
	public List findAll() {
		return dao.all();
	}

	@Override
	public List findGraph(Long id, Date s, Date e) {
		return dao.findgraph(id, s, e);
	}

	public List search(String n, int p, int pp) {
		if (p > 0)
			p--;
		PageRequest request = new PageRequest(p, pp, Sort.Direction.ASC, "id");
		return dao.search(n, request);
	}

	public Long searchcount(String search) {

		return dao.countsearch(search);
	}

}

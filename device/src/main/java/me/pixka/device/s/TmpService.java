package me.pixka.device.s;

import java.util.Date;
import java.util.List;

public interface TmpService {

	
	List findAll();
	List findGraph(Long id,Date s,Date e);
}

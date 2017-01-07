package me.pixka.device.c;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import me.pixka.device.s.DeviceService;

/**
 * ใช้สำหรับอ่านค่าจาก Sensor อื่น
 * 
 * @author kykub
 *
 */

@RestController
public class ReadOthervalueControl {
	@Autowired
	private DeviceService deviceservice;
}

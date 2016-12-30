package me.pixka.c;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;


@Controller
public class DHT22 {
	static String DHT22DirPath = "/sensor/dht22";

	private BigDecimal t, h;

	public void read() {
		String content;

		try{
		content = new String(Files.readAllBytes(Paths.get(DHT22DirPath)));
		System.out.println("DHT22 Raw value :"+content);
		content = content.replaceAll(" ", "").replaceAll("(\\r|\\n)", "");
		String value[] = content.split("\\|");
		System.out.println("["+value[0]+"]----["+value[1]+"]");
		if (value.length > 0) {
			this.t = new BigDecimal(value[0]);
			this.h = new BigDecimal(value[1]);
		}
		}catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("Canot read DHT22");
		}
	}

	public BigDecimal getH() {
		return this.h;
	}

	public BigDecimal getT() {
		return this.t;
	}
}

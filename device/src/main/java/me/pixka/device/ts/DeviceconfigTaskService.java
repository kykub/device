package me.pixka.device.ts;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import me.pixka.c.HttpControl;
import me.pixka.c.PiDevice;
import me.pixka.data.ISODateAdapter;
import me.pixka.device.d.Deviceconfig;
import me.pixka.device.s.ConfigvalueService;

/**
 * ใช้สำหรับ Run Device config ใน PI
 * 
 * @author kykub
 *
 */
@Service
public class DeviceconfigTaskService {

	private static Gson g = new GsonBuilder().registerTypeAdapter(Date.class, new ISODateAdapter()).create();

	/**
	 * ใช้สำหรับ ดึง Task แบบ Onetime มาทำงานจาก Server จะ RUN ทุก 1 วินาที
	 * 
	 * @param id
	 * @return
	 */
	@Autowired
	private ConfigvalueService cs;
	@Autowired
	private HttpControl http;
	@Autowired
	private PiDevice pi;

	private GpioController gpio = null;

	private GpioPinDigitalOutput pin0;

	private GpioPinDigitalOutput pin1;

	private GpioPinDigitalOutput pin3;

	private GpioPinDigitalOutput pin2;

	private GpioPinDigitalOutput pin4;

	private GpioPinDigitalOutput pin5;

	private GpioPinDigitalInput pin7;

	private GpioPinDigitalInput pin6;

	public Deviceconfig fetchOnetime(Long id) throws IOException {
		String baseurl = cs.get("baseurl", "http://locahost:3333");

		String re = http.get(baseurl + "/loadonetimedeviceconfig/" + pi.wifi());
		Deviceconfig d = g.fromJson(re, Deviceconfig.class);
		return d;
	}

	public void setup() {
		try {
			gpio = GpioFactory.getInstance();
			
		
			pin0 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "Port0", PinState.HIGH);
			pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Port1", PinState.HIGH);
			pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Port2", PinState.HIGH);
			pin3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Port3", PinState.HIGH);
			pin4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Port4", PinState.HIGH);
			pin5 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Port5", PinState.HIGH);
			pin6 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_06);
			pin7 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_07);

			pin0.setShutdownOptions(true, PinState.HIGH);
			pin1.setShutdownOptions(true, PinState.HIGH);
			pin2.setShutdownOptions(true, PinState.HIGH);
			pin3.setShutdownOptions(true, PinState.HIGH);
			pin4.setShutdownOptions(true, PinState.HIGH);
			pin5.setShutdownOptions(true, PinState.HIGH);
			pin6.setShutdownOptions(true, PinState.HIGH);
			pin7.setShutdownOptions(true, PinState.HIGH);
			System.out.println("Setup device done.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Runconfig(Deviceconfig d) {

	}

	public int BooleantoPin(Boolean b) {
		if (b == null || b == false)
			return 0;

		return 1;

	}

	public void Finished(Long id) throws IOException {
		String baseurl = cs.get("baseurl", "http://locahost:3333");
		String re = http.get(baseurl + "/finishedonetime/" + id);
		System.out.println("################################################");
		System.out.println("Finished One time: " + id);
		System.out.println("################################################");
	}

}

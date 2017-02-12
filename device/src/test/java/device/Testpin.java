package device;

import java.util.EnumSet;

import org.junit.Test;

import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import me.pixka.device.d.Projects;

public class Testpin {

	@Test
	public void test() {
		PinMode e = PinMode.valueOf("DIGITAL_INPUT");
		EnumSet<PinMode> pins = PinMode.all();
		RaspiPin.allPins();

		Projects p = Projects.valueOf("Het");
	}
}

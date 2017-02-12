package me.pixka.c;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Controller;

@Controller
public class Ds18b20 {

	static String w1DirPath = "/sys/bus/w1/devices";
	private BigDecimal t;

	/**
	 * Local read DS18B20 
	 * 
	 * @return
	 * @throws Exception
	 */
	public BigDecimal read() throws Exception {
		// System.out.println("DS18b20 read");
		File dir = new File(w1DirPath);
		File[] files = dir.listFiles(new DirectoryFileFilter());
		if (files != null) {
			// while (true) {
			for (File file : files) {
				// System.out.print(file.getName() + ": ");
				// Device data in w1_slave file
				String filePath = w1DirPath + File.separatorChar + file.getName() + File.separatorChar + "w1_slave";
				File f = new File(filePath);
				try (BufferedReader br = new BufferedReader(new FileReader(f))) {
					String output;
					while ((output = br.readLine()) != null) {
						int idx = output.indexOf("t=");
						if (idx > -1) {
							// Temp data (multiplied by 1000) in 5 chars
							// after t=
							float tempC = Float.parseFloat(output.substring(output.indexOf("t=") + 2));
							// Divide by 1000 to get degrees Celsius
							BigDecimal t = new BigDecimal(tempC /= 1000);

							// System.out.print(String.format("%.1f ", tempC));
							float tempF = tempC * 9 / 5 + 32;
							// System.out.println(String.format("%.1f", tempF));
							return t.setScale(1, RoundingMode.HALF_UP);
						}
					}
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		}
		// }

		throw new Exception("Canot read ds18b20");
	}

	public BigDecimal readDir(String dir) throws IOException {
		File dirfile = new File(dir);
		File[] files = dirfile.listFiles(new DirectoryFileFilter());

		for (File file : files) {
			String filePath = file.getAbsolutePath() + File.separatorChar + File.separatorChar + "w1_slave";
			return read(filePath);
		}

		return null;

	}

	public BigDecimal read(String file) throws IOException {

		Stream<String> stream = Files.lines(Paths.get(file));

		// 1. filter line 3
		// 2. convert all content to upper case
		// 3. convert it into a List
		List list = stream.filter(line -> line.indexOf("t=") != -1).collect(Collectors.toList());

		System.out.println(list.size());

		if (list.size() == 1) {
			String output = (String) list.get(0);
			int idx = output.indexOf("t=");
			if (idx != -1) {
				float tempC = Float.parseFloat(output.substring(output.indexOf("t=") + 2));
				// Divide by 1000 to get degrees Celsius
				t = new BigDecimal(tempC /= 1000);

				System.out.print(String.format("%.1f ", tempC));
				float tempF = tempC * 9 / 5 + 32;
				System.out.println(String.format("%.1f", tempF));
				return t;
			}
		}

		return null;
	}

}

class DirectoryFileFilter implements FileFilter {
	public boolean accept(File file) {
		String dirName = file.getName();
		String startOfName = dirName.substring(0, 3);
		return (file.isDirectory() && startOfName.equals("28-"));
	}
}

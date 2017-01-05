package me.pixka.device.c;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import me.pixka.device.d.Fw;
import me.pixka.device.s.FwService;

@Controller
public class Updatefw {
	@Autowired
	private FwService service;

	public static final String path = "./";

	@RequestMapping(value = "/updatefw")
	// @RequestBody String body,
	public ResponseEntity get(@RequestHeader HttpHeaders headers) throws IOException {

		// System.out.println("Body "+body);

		List<String> version = headers.get("x-esp8266-version");
		Fw last = service.getLast();

		if (last == null) {
			System.out.println("no update for " + headers.get("x-esp8266-sta-mac") + " version :" + version);
			return new ResponseEntity(HttpStatus.FORBIDDEN);
		}
		String boardver = version.get(0);
		int lastver = Integer.parseInt(last.getVersion());
		int bv = Integer.parseInt(boardver);

		if (bv < lastver) {
			System.out.println("update for " + headers.get("x-esp8266-sta-mac") + " version :" + version
					+ " new version " + lastver);
			byte[] array = Files.readAllBytes(new File(path + "" + last.getId() + ".bin").toPath());
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(array);
		}

		return new ResponseEntity(HttpStatus.FORBIDDEN);
	}

	@RequestMapping(value = "/rest/addfw", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> uploadFile(@RequestParam("xxx") MultipartFile file, @RequestParam("ver") String n) {

		try {

			Fw fw = new Fw();
			fw.setVersion(n);
			fw.setDescription(file.getName());
			fw = service.save(fw);

			FileOutputStream fo = new FileOutputStream(new File(path + fw.getId() + ".bin"));
			InputStream input = file.getInputStream();
			int read = 0;
			byte buf[] = new byte[1024];

			while ((read = input.read(buf)) != -1) {
				fo.write(buf, 0, read);
			}

			fo.close();
			input.close();
			System.out.println("test upload :" + file + " version " + n);

		} catch (Exception e) {
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("ok", HttpStatus.OK);
	} // method uploadFile

	@RequestMapping(value = "/lastfw", method = RequestMethod.GET)
	@ResponseBody
	public String lastfw() {
		Fw fw = service.getLast();
		if (fw != null) {
			return fw.getVersion();
		}

		return "no have fw";
	}
}

package me.pixka.device.c;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import me.pixka.device.d.Pifw;
import me.pixka.device.s.ConfigvalueService;
import me.pixka.device.s.PifwService;

@Controller
public class UploadpifwControl {

	@Autowired
	private PifwService service;
	@Autowired
	private ConfigvalueService configservice;

	private String path = "/home/pi/pifw";

	@RequestMapping(value = "/pifw/{ver}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> findfw(@PathVariable("ver") String ver) throws IOException {
		String otherpath = configservice.get("pifwpath", "/home/pi/pifw");
		if (otherpath != null)
			path = otherpath;

		checkDir();
		Pifw fw = service.findByVersion(ver);
		String filename = path + File.separatorChar + ver;
		return getfw(filename, fw);
	}

	@RequestMapping(value = "/pifw/{ver}/{appname}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> findfw(@PathVariable("ver") String ver,@PathVariable("appname") String appname) throws IOException {
		String otherpath = configservice.get("pifwpath", "/home/pi/pifw");
		if (otherpath != null)
			path = otherpath;

		checkDir();
		Pifw fw = service.findByVersion(ver);
		String filename = path + File.separatorChar + ver;
		return getfw(filename, fw);
	}
	public void checkDir() {
		File f = new File(path);

		if (!f.exists())
			f.mkdir();

	}

	public ResponseEntity<InputStreamResource> getfw(String fileName, Pifw fw) throws IOException {
		File file = new File(fileName);
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		respHeaders.setContentLength(fw.getFwsize());
		respHeaders.setContentDispositionFormData("attachment", "fw.jar");
		InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
		return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
	}

	public ResponseEntity<InputStreamResource> o() {
		return null;
	}

	/**
	 * ใช้สำหรับ upload Pi fw
	 * 
	 * @param file
	 * @param n
	 * @return
	 */
	@RequestMapping(value = "/uploadpifw", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> uploadFile(@RequestParam("afile") MultipartFile file,
			@RequestParam("ver") String ver,@RequestParam("appname") String appname) {

		String checksum = savefw(ver, file);
		if (checksum == null)
			return new ResponseEntity<String>("{return:'error'}", HttpStatus.BAD_REQUEST);

		Pifw fw = new Pifw();
		fw.setVerno(ver);
		fw.setFwsize(file.getSize());
		fw.setChecksum(checksum);
		fw.setFilename(file.getName());
		fw.setAppname(appname);
		fw = service.save(fw);
		return new ResponseEntity<String>("{return:'ok'}", HttpStatus.OK);
	}

	public String md5(String file) throws NoSuchAlgorithmException, IOException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(Files.readAllBytes(Paths.get(file)));
		byte[] digest = md.digest();
		// String s = Arrays.toString(digest);
		String s = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return s;
	}

	private String savefw(String ver, MultipartFile file) {
		System.out.println("Save PI fw ");
		try {
			String otherpath = configservice.get("pifwpath", "/home/pi/pifw");
			if (otherpath != null)
				path = otherpath;
			String fs = path + File.separatorChar + ver + "";
			File f = new File(fs);
			FileOutputStream fo = new FileOutputStream(f);
			InputStream input = file.getInputStream();
			int read = 0;
			byte buf[] = new byte[1024];
			while ((read = input.read(buf)) != -1) {
				fo.write(buf, 0, read);
			}
			fo.close();
			input.close();

			String m = md5(fs);

			return m;

		} catch (Exception e) {
			return null;
		}

	}

}

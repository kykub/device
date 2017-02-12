package me.pixka.device.c;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.pixka.device.d.Projects;

@RestController
public class ProjectControl {

	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	@ResponseBody
	public List all() {

		EnumSet<Projects> projects = Projects.all();
		List buf = new ArrayList();

		for (Projects pj : projects) {
			buf.add(pj.getName());
		}

		return buf;
	}
}

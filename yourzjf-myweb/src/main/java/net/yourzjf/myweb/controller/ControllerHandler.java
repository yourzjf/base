package net.yourzjf.myweb.controller;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ControllerHandler {

	@ModelAttribute
	protected void initRequestParam(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String[]> map = request.getParameterMap();
		Iterator<Map.Entry<String, String[]>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			// System.out.println(it.next());
		}

	}
}

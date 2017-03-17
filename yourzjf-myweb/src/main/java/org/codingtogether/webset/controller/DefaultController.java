package org.codingtogether.webset.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController extends ControllerHandler {

	@RequestMapping
	public String indexPage() {
		return "redirect:index.jsp";
	}
}

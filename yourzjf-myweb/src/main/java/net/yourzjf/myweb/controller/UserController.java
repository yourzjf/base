package net.yourzjf.myweb.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.yourzjf.myweb.UserException;
import net.yourzjf.myweb.bean.User;

/**
 * Bean类上的RequestMapping("/user")是Controller的命名空间，访问该Bean的Mapping时需要加上/user
 * 如：http://localhost:8060/AppName/user/xxx
 * 
 * @author zhangjianfeng
 */
@Controller
@RequestMapping("/user")
// @Scope("prototype") //Spring 管理的 Controller 默认是单例的，如不加Scope，成员变量的值会受到其他请求的污染
public class UserController extends ControllerHandler {

	private Map<String, User> users = null;

	{
		users = new HashMap<String, User>();
		users.put("aa", new User("aa", "111111", "aa@aa.aa"));
		users.put("bb", new User("bb", "222222", "bb@bb.bb"));
		users.put("cc", new User("cc", "333333", "cc@cc.cc"));
	}

	/**
	 * 方法名上的RequestMapping(value = "/users")是具体的访问路径，method可以指定响应何种请求，如get
	 * 访问方式：http://localhost:8060/AppName/user/users
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("users", users);
		return "user/list";
	}

	/**
	 * mapping所对应的/add.jsp页面上的user对象不能为空
	 * 可以在方法参数列表中使用如：add(org.springframework.ui.Model model)
	 * 也可以使用如：add(@ModelAttribute("user") User user)
	 * 
	 * 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@ModelAttribute("user") User user) {
		return "user/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Validated User user, BindingResult br,
			@RequestParam("attachs") MultipartFile[] files,
			HttpServletRequest hsr) {
		if (br.hasErrors()) {
			return "user/add";
		}
		users.put(user.getUsername(), user);

		// 此处为文件上传
		String realpath = hsr.getSession().getServletContext()
				.getRealPath("/resources/upload");
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				try {
					File rFile = new File(realpath + File.separator
							+ file.getOriginalFilename());
					System.out.println(rFile.getPath());
					FileUtils.copyInputStreamToFile(file.getInputStream(),
							rFile);
				} catch (IOException e) {
					// 处理错误
				}
			}
		}
		return "redirect:/user/users";
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String view(@PathVariable String username, Model model) {
		model.addAttribute("user", users.get(username));
		return "user/view";
	}

	/**
	 * 以JSON的形式返回， params={"json"} 指明如果有参数名为json则调用此重载方法，否则调用原有方法
	 */
	@RequestMapping(value = "/{username}", method = RequestMethod.GET, params = { "json" })
	@ResponseBody
	public User view(@PathVariable String username) {
		return users.get(username);
	}

	@RequestMapping(value = "/{username}/update", method = RequestMethod.GET)
	public String update(@PathVariable String username, Model model) {
		model.addAttribute("user", users.get(username));
		return "user/update";
	}

	@RequestMapping(value = "/{username}/update", method = RequestMethod.POST)
	public String update(@PathVariable String username, User user,
			BindingResult br) {
		if (br.hasErrors()) {
			return "user/update";
		}
		users.put(username, user);
		return "redirect:/user/users";
	}

	@RequestMapping(value = "/{username}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable String username) {
		users.remove(username);
		return "redirect:/user/users";
	}

	// ------------ SpringMVC003_Login -----------------

	/**
	 * 使用如：HttpSession session 或 HttpServletRequest httpServletRequest
	 * 直接获取容器中的对象
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password, HttpSession session) {
		User user;
		if (!users.containsKey(username)) {
			throw new UserException("用户不存在");
		}
		user = users.get(username);
		if (password == null || !password.equals(user.getPassword())) {
			throw new UserException("密码不正确");
		}
		session.setAttribute("loginUser", user);
		return "redirect:/user/users";
	}

	/**
	 * 局部异常，仅仅只能处理这个控制器中的异常
	 */
	// @ExceptionHandler(value={UserException.class})
	// public String handlerException(UserException ex, HttpServletRequest hsr)
	// {
	// hsr.setAttribute("ex", ex);
	// //此处的error对应的是视图
	// return "error";
	// }

}

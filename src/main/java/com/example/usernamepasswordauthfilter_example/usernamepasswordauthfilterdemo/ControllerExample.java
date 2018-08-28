package com.example.usernamepasswordauthfilter_example.usernamepasswordauthfilterdemo;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on August, 2018
 *
 * @author yagiz
 */
@Controller
public class ControllerExample {

	@GetMapping({"/index", "/"})
	@ResponseBody
	public String index(@AuthenticationPrincipal User user) {
		return "Merhaba " + user.getUsername();
	}

}

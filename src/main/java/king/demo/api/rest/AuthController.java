package king.demo.api.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import king.demo.api.domain.AuthUser;
import king.demo.api.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	//returns bearer token for provided username and password
	@PostMapping(value = "/login", consumes = "application/json")
	public AuthUser loginUser(@RequestBody Map<String, String> userData) {
		return authService.loginUser(userData.get("username"), userData.get("password"));
	}
}


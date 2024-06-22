package king.demo.api.service;

import king.demo.api.domain.AuthUser;

public interface AuthService {
	AuthUser loginUser(String username, String password);
	String getCurrentAuthUser(String bearerToken);
}

package com.example.istg;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.istg.commons.User;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

//	@Autowired
//	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getPrincipal().toString();
		String password = null;
		Object cre = authentication.getCredentials();
		if (cre != null) {
			password = cre.toString();
		}
//		User u = userService.findByUsernameAndPassword(username, password); 
		User u = new User();
		// TODO test
		u.setUsername("mathen");
		u.setPassword("12345678");
		if (!u.getUsername().equals(username) || !u.getPassword().equals(password)) {
			return null;
		}
		Collection<? extends GrantedAuthority> authorities = Collections
				.singleton(new SimpleGrantedAuthority("ROLE_USER"));
		return new UsernamePasswordAuthenticationToken(username, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}

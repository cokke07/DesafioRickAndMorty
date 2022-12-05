package cl.cokke.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.cokke.exception.RestServiceException;
import cl.cokke.model.User;
import cl.cokke.repository.UserRepository;
import cl.cokke.security.JwtTokenProvider;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public String login(String username, String password) {
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			//System.out.println(username);
			//System.out.println(password);
			return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
		} catch (AuthenticationException e) {
			throw new RestServiceException("username o password invalido", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@Override
	@Transactional
	public User crearUser(User User) {
		
		if (!userRepository.existsByUsername(User.getUsername())) {
			User.setPassword(passwordEncoder.encode(User.getPassword()));
			userRepository.save(User);
			
			jwtTokenProvider.createToken(User.getUsername(), User.getRoles());
			return User;
		} else {
			throw new RestServiceException("Username ya esta en uso", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		final User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Usuario '" + username + "' no encontrado");
		}
		return org.springframework.security.core.userdetails.User
				.withUsername(username)
				.password(user.getPassword())
				.authorities(user.getRoles())
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();
	}
}

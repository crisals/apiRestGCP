package core.ewig.apiRestMySQL.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import core.ewig.apiRestMySQL.models.UserModel;
import core.ewig.apiRestMySQL.repositories.UserRepository;

/**
 * @author Cristian Alarcón
 *
 *         <p>
 *         Implementation of the methods for the custom users details service.
 *         </p>
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * <p>
	 * Method to validate that the username and password entered correspond to existing credentials in the database.
	 * </p>
	 * 
	 * @param
	 * @return UserDetails.
	 * @since 1.0
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserModel user = userRepository.findByUserName(userName);
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				new ArrayList<>());
	}
}

package core.ewig.apiRestMySQL.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import core.ewig.apiRestMySQL.models.UserModel;

/**
 * @author Cristian Alarcón
 *
 *<p>
 *   Repository for system user model.      
 *</p>
 */
public interface UserRepository extends JpaRepository<UserModel, Integer>{

	/**
	 * <p>
	 * Method to get a user by your username.
	 * </p>
	 * 
	 * @param
	 * @return user object.
	 * @since 1.0
	 */
	UserModel findByUserName(String userName);

}

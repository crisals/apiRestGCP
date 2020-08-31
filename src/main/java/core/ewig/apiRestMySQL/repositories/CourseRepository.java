package core.ewig.apiRestMySQL.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.ewig.apiRestMySQL.models.CourseModel;

/**
 * @author Cristian Alarcón
 *
 *<p>
 *   Repository for Course model.      
 *</p>
 */
@Repository
public interface CourseRepository extends JpaRepository<CourseModel, String> {

	/**
	 * <p>
	 * Method to get a course by your code.
	 * </p>
	 * 
	 * @param code of the course.
	 * @return course object.
	 * @since 1.0
	 */
	Optional<CourseModel> findByCode(String code);
}

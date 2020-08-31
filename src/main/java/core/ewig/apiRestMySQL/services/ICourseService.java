package core.ewig.apiRestMySQL.services;

import org.springframework.http.ResponseEntity;

import core.ewig.apiRestMySQL.models.CourseModel;

/**
 * @author Cristian Alarcón
 *
 *         <p>
 *         Statement of methods for course services.
 *         </p>
 */
public interface ICourseService {

	/**
	 * @see CourseServiceImpl#getAllCourses()
	 */
	public ResponseEntity<Object> getAllCourses();

	/**
	 * @see CourseServiceImpl#createCourse(CourseModel)
	 */
	public ResponseEntity<?> createCourse(CourseModel course);

	/**
	 * @see CourseServiceImpl#getByCode(String)
	 */
	public ResponseEntity<?> getByCode(String code);

	/**
	 * @see CourseServiceImpl#UpdateCourse(CourseModel)
	 */
	public ResponseEntity<?> updateCourse(CourseModel course);;

	/**
	 * @see CourseServiceImpl#deleteCourse(String)
	 */
	public ResponseEntity<?> deleteCourse(String code);
}

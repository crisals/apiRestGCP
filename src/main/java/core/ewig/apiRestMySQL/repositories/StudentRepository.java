package core.ewig.apiRestMySQL.repositories;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.ewig.apiRestMySQL.models.StudentModel;

/**
 * @author Cristian Alarcón
 *
 *<p>
 *   Repository for student model.      
 *</p>
 */
@Repository
public interface StudentRepository extends JpaRepository<StudentModel, String>{

	/**
	 * <p>
	 * Method to get a student by your rut and course code.
	 * </p>
	 * 
	 * @param
	 * @return student object.
	 * @since 1.0
	 */
	Optional<StudentModel> findByRutAndCourseCode(String rut, String courseCode);

	/**
	 * <p>
	 * Method to delete a student by your rut and course code.
	 * </p>
	 * 
	 * @param
	 * @since 1.0
	 */
	void deleteByRutAndCourseCode(String rut, String courseCode);

	/**
	 * <p>
	 * Method to obtain an array of students by your rut.
	 * </p>
	 * 
	 * @param
	 * @return student arrayList.
	 * @since 1.0
	 */
	ArrayList<StudentModel> findByRut(String rut);
	
	/**
	 * <p>
	 * Method to obtain an array of students by your course code.
	 * </p>
	 * 
	 * @param
	 * @return student arrayList.
	 * @since 1.0
	 */
	ArrayList<StudentModel> findStudentByCourseCode(String courseCode);

}

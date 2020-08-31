package core.ewig.apiRestMySQL.services;

import org.springframework.http.ResponseEntity;

import core.ewig.apiRestMySQL.models.StudentModel;

/**
 * @author Cristian Alarcón
 *
 *         <p>
 *         Statement of methods for student services.
 *         </p>
 */
public interface IStudentService {
	
	/**
	 * @see StudentServiceImpl#rutValidation(String)
	 */
	public boolean rutValidation(String rut);
	
	/**
	 * @see StudentServiceImpl#getAllStudents()
	 */
	public ResponseEntity<Object> getAllStudents();
	
	/**
	 * @see StudentServiceImpl#createStudent(StudentModel)
	 */
	public ResponseEntity<String> createStudent(StudentModel student);
	
	/**
	 * @see StudentServiceImpl#getByRut(String, String)
	 */
	public ResponseEntity<?> getByRutAndCourseCode(String rut, String courseCode);
	
	/**
	 * @see StudentServiceImpl#updateStudent(StudentModel)
	 */
	public ResponseEntity<?> updateStudent(StudentModel student);
	
	/**
	 * @see StudentServiceImpl#deleteStudent(String, String)
	 */
	public ResponseEntity<?> deleteStudent(String rut, String courseCode);
}

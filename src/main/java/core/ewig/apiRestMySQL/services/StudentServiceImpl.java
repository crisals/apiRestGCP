package core.ewig.apiRestMySQL.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import core.ewig.apiRestMySQL.models.CourseModel;
import core.ewig.apiRestMySQL.models.StudentModel;
import core.ewig.apiRestMySQL.repositories.CourseRepository;
import core.ewig.apiRestMySQL.repositories.StudentRepository;

/**
 * @author Cristian Alarcón
 *
 *         <p>
 *         Implementation of the methods for the student model.
 *         </p>
 */
@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	CourseRepository courseRepository;

	/**
	 * <p>
	 * Method that validates if the entered rout corresponds to its verification
	 * digit.
	 * </p>
	 * 
	 * @param
	 * @return boolean.
	 * @since 1.0
	 */
	@Override
	public boolean rutValidation(String rut) {
		boolean validacion = false;
		try {
			rut = rut.toUpperCase();
			rut = rut.replace(".", "");
			rut = rut.replace("-", "");
			int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

			char dv = rut.charAt(rut.length() - 1);

			int m = 0, s = 1;
			for (; rutAux != 0; rutAux /= 10) {
				s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
			}
			if (dv == (char) (s != 0 ? s + 47 : 75)) {
				validacion = true;
			}

		} catch (java.lang.NumberFormatException e) {
			return validacion = false;
		} catch (Exception e) {
			return validacion = false;
		}
		return validacion;
	}

	/**
	 * <p>
	 * Method to get an object with all students.
	 * </p>
	 * 
	 * @param
	 * @return ResponseEntity.
	 * @since 1.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<Object> getAllStudents() {
		List<StudentModel> students = studentRepository.findAll();
		List<JSONObject> entities = new ArrayList<JSONObject>();

		if (students.size() > 0) {
			for (StudentModel n : students) {
				JSONObject entity = new JSONObject();
				entity.put("rut", n.getRut());
				entity.put("name", n.getName());
				entity.put("lastName", n.getLastName());
				entity.put("age", n.getAge());
				entity.put("course", n.getCourseCode());

				entities.add(entity);
			}

			return new ResponseEntity<Object>(entities, HttpStatus.OK);
		}

		return new ResponseEntity<Object>(entities, HttpStatus.NOT_FOUND);
	}

	/**
	 * <p>
	 * Method to create a new student, it is validated that it does not exist
	 * previously and that the associated course code exists as a course within the
	 * system.
	 * </p>
	 * 
	 * @param
	 * @return ResponseEntity.
	 * @since 1.0
	 */
	@Override
	public ResponseEntity<String> createStudent(StudentModel student) {
		if (rutValidation(student.getRut())) {
			try {
				if (student.getAge() > 18) {

					Optional<StudentModel> existingStudent = studentRepository.findByRutAndCourseCode(student.getRut(),
							student.getCourseCode());
					Optional<CourseModel> existingCourse = courseRepository.findByCode(student.getCourseCode());

					if (existingStudent.isEmpty()) {
						if (existingCourse.isPresent()) {
							studentRepository.save(student);
							return new ResponseEntity<>("Student successfully registered", HttpStatus.OK);
						} else {
							return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
						}

					} else {
						return new ResponseEntity<>("The student is already registered in this course", HttpStatus.OK);
					}

				} else {
					return new ResponseEntity<>("The student's age must be over 18 years", HttpStatus.BAD_REQUEST);
				}

			} catch (Exception ex) {
				return new ResponseEntity<>("General Error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>("The entered Rut is incorrect", HttpStatus.BAD_REQUEST);
	}

	/**
	 * <p>
	 * Method to obtain a student filtering by rut and course code.
	 * </p>
	 * 
	 * @param
	 * @return ResponseEntity.
	 * @since 1.0
	 */
	@Override
	public ResponseEntity<?> getByRutAndCourseCode(String rut, String courseCode) {
		Optional<StudentModel> student = studentRepository.findByRutAndCourseCode(rut, courseCode);

		if (student.isPresent()) {
			return new ResponseEntity<>(student.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("Student not Found", HttpStatus.NOT_FOUND);
	}

	/**
	 * <p>
	 * Method to delete a course by its rut and course code.
	 * </p>
	 * 
	 * @param
	 * @return ResponseEntity.
	 * @since 1.0
	 */
	@Override
	public ResponseEntity<?> deleteStudent(String rut, String courseCode) {
		try {
			StudentModel student = (StudentModel) studentRepository.findByRutAndCourseCode(rut, courseCode).get();

			studentRepository.delete(student);
			return new ResponseEntity<>("Student Delete", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>("Failed to delete student", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * <p>
	 * Method that updates a student, filters by course routing and code, in case of
	 * finding more than one student, it updates their personal data.
	 * </p>
	 * 
	 * @param
	 * @return ResponseEntity.
	 * @since 1.0
	 */
	@Override
	public ResponseEntity<?> updateStudent(StudentModel student) {
		Optional<StudentModel> existingStudent = studentRepository.findByRutAndCourseCode(student.getRut(),
				student.getCourseCode());
		ArrayList<StudentModel> existingStudents = studentRepository.findByRut(student.getRut());

		if (existingStudent.isEmpty()) {
			if (existingStudents.isEmpty()) {
				return new ResponseEntity<>("Rut is not registered", HttpStatus.NOT_FOUND);
			} else {
				for (StudentModel n : existingStudents) {
					n.setName(student.getName());
					n.setLastName(student.getLastName());
					n.setAge(student.getAge());
					studentRepository.save(n);
				}

				return new ResponseEntity<>("Student successfully updated", HttpStatus.OK);
			}
		} else {
			if (student.getAge() > 18) {
				
				for (StudentModel n : existingStudents) {
					n.setName(student.getName());
					n.setLastName(student.getLastName());
					n.setAge(student.getAge());
					studentRepository.save(n);
				}
				
				studentRepository.save(student);
				return new ResponseEntity<>("Student successfully updated", HttpStatus.OK);

			} else {
				return new ResponseEntity<>("The student's age must be over 18 years", HttpStatus.BAD_REQUEST);
			}
		}
	}
}

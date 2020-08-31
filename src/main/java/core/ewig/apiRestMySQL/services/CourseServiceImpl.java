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
 *         Implementation of the methods for the courses.
 *         </p>
 */
@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	StudentRepository studentRepository;

	/**
	 * <p>
	 * Method to get an object with all courses.
	 * </p>
	 * 
	 * @param
	 * @return ResponseEntity.
	 * @since 1.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<Object> getAllCourses() {
		List<CourseModel> courses = courseRepository.findAll();
		List<JSONObject> entities = new ArrayList<JSONObject>();

		if (courses.size() > 0) {
			for (CourseModel n : courses) {
				JSONObject entity = new JSONObject();
				entity.put("code", n.getCode());
				entity.put("name", n.getName());
				entities.add(entity);
			}

			return new ResponseEntity<Object>(entities, HttpStatus.OK);
		}

		return new ResponseEntity<Object>(entities, HttpStatus.NOT_FOUND);
	}

	/**
	 * <p>
	 * Method to create a new course.
	 * </p>
	 * 
	 * @param
	 * @return ResponseEntity.
	 * @since 1.0
	 */
	@Override
	public ResponseEntity<?> createCourse(CourseModel course) {
		if (course.getCode().length() < 5 && course.getCode().length() > 0) {
			Optional<CourseModel> existingCourse = courseRepository.findByCode(course.getCode());

			if (!existingCourse.isEmpty()) {
				return new ResponseEntity<>("The course is already registered", HttpStatus.OK);
			}

			courseRepository.save(course);
			return new ResponseEntity<>("Course successfully registered", HttpStatus.OK);
		}
		return new ResponseEntity<>("Course not registered", HttpStatus.NOT_FOUND);
	}

	/**
	 * <p>
	 * Method to obtain a course filtering by its code.
	 * </p>
	 * 
	 * @param
	 * @return ResponseEntity.
	 * @since 1.0
	 */
	@Override
	public ResponseEntity<?> getByCode(String code) {
		Optional<CourseModel> course = courseRepository.findByCode(code);

		if (course.isPresent()) {
			return new ResponseEntity<>(course.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("Course not Found", HttpStatus.NOT_FOUND);
	}

	/**
	 * <p>
	 * Method to delete a course by its code, filtering by its code and validating
	 * that there are no students associated with the course to delete.
	 * </p>
	 * 
	 * @param
	 * @return ResponseEntity.
	 * @since 1.0
	 */
	@Override
	public ResponseEntity<?> deleteCourse(String code) {
		try {
			Optional<CourseModel> existingCourse = courseRepository.findByCode(code);
			ArrayList<StudentModel> courseWithStudents = studentRepository.findStudentByCourseCode(code);

			if (courseWithStudents.isEmpty()) {
				if (existingCourse.isPresent()) {
					courseRepository.deleteById(code);
					return new ResponseEntity<>("Course Delete Successfull", HttpStatus.OK);
				}
				return new ResponseEntity<>("Course is not registered", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>("There are students registered in this course", HttpStatus.BAD_REQUEST);

		} catch (Exception ex) {
			return new ResponseEntity<>("Failed to delete course", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * <p>
	 * Method to update a course by its code.
	 * </p>
	 * 
	 * @param
	 * @return ResponseEntity.
	 * @since 1.0
	 */
	@Override
	public ResponseEntity<?> updateCourse(CourseModel course) {
		Optional<CourseModel> existingCourse = courseRepository.findByCode(course.getCode());

		if (!existingCourse.isEmpty()) {
			courseRepository.save(course);
			return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("The course is not registered", HttpStatus.NOT_FOUND);
	}
}

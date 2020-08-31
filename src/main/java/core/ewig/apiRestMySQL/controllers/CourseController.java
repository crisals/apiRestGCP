package core.ewig.apiRestMySQL.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.ewig.apiRestMySQL.models.CourseModel;
import core.ewig.apiRestMySQL.services.ICourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	ICourseService courseService;

	@GetMapping()
	public ResponseEntity<?> getAllCourses() {
		ResponseEntity<?> response = this.courseService.getAllCourses();
		return (ResponseEntity<?>) ResponseEntity.status(response.getStatusCode()).body(response);
	}

	@GetMapping(path = "/{code}")
	public ResponseEntity<?> getCourseByCode(@PathVariable("code") String code) {
		ResponseEntity<?> response = this.courseService.getByCode(code);
		return (ResponseEntity<?>) ResponseEntity.status(response.getStatusCode()).body(response);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createCourse(@RequestBody CourseModel course) {
		ResponseEntity<?> response = this.courseService.createCourse(course);
		return (ResponseEntity<?>) ResponseEntity.status(response.getStatusCode()).body(response);
	}

	@PutMapping()
	public ResponseEntity<?> updateStudent(@RequestBody CourseModel course) {
		ResponseEntity<?> response = this.courseService.updateCourse(course);
		return (ResponseEntity<?>) ResponseEntity.status(response.getStatusCode()).body(response);
	}

	@DeleteMapping(path = "/{code}")
	public ResponseEntity<?> deleteById(@PathVariable("code") String code) {
		ResponseEntity<?> response = this.courseService.deleteCourse(code);
		return (ResponseEntity<?>) ResponseEntity.status(response.getStatusCode()).body(response);
	}
}

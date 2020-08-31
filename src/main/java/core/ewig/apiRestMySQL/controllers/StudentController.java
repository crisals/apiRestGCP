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

import core.ewig.apiRestMySQL.models.StudentModel;
import core.ewig.apiRestMySQL.services.IStudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	@Autowired
	IStudentService studentService;

	@GetMapping()
	public ResponseEntity<?> getAllStudents() {
		ResponseEntity<?> response = this.studentService.getAllStudents();
		return (ResponseEntity<?>) ResponseEntity.status(response.getStatusCode()).body(response);
	}

	@PostMapping()
	public ResponseEntity<?> createStudent(@RequestBody StudentModel student) {
		ResponseEntity<?> response = this.studentService.createStudent(student);
		return (ResponseEntity<?>) ResponseEntity.status(response.getStatusCode()).body(response);
	}

	@GetMapping(path = "/{rut}/{courseCode}")
	public ResponseEntity<?> getStudentById(@PathVariable("rut") String rut,
			@PathVariable("courseCode") String courseCode) {
		ResponseEntity<?> response = this.studentService.getByRutAndCourseCode(rut, courseCode);
		return (ResponseEntity<?>) ResponseEntity.status(response.getStatusCode()).body(response);
	}

	@PutMapping()
	public ResponseEntity<?> updateStudent(@RequestBody StudentModel student) {
		ResponseEntity<?> response = this.studentService.updateStudent(student);
		return (ResponseEntity<?>) ResponseEntity.status(response.getStatusCode()).body(response);
	}

	@DeleteMapping(path = "/{rut}/{courseCode}")
	public ResponseEntity<?> deleteById(@PathVariable("rut") String rut,
			@PathVariable("courseCode") String courseCode) {
		ResponseEntity<?> response = this.studentService.deleteStudent(rut, courseCode);
		return (ResponseEntity<?>) ResponseEntity.status(response.getStatusCode()).body(response);
	}
}

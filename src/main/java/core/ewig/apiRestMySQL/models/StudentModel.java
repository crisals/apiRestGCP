package core.ewig.apiRestMySQL.models;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Cristian Alarcón
 *
 */
@Entity
@Table(name = "student")
@IdClass(StudentModelId.class)
public class StudentModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * String with student rut.
	 */
	@Id
	private String rut;

	/**
	 * String with student first name.
	 */
	private String name;

	/**
	 * String with student last name.
	 */
	private String lastName;

	/**
	 * String with student age.
	 */
	private int age;

	@Id
	@Column(nullable = false, columnDefinition = "char(4)")
	private String courseCode;

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

}

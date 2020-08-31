package core.ewig.apiRestMySQL.models;

import java.io.Serializable;

import javax.persistence.*;

/**
 * @author Cristian Alarcón
 *
 */
@Entity
@Table(name = "course")
public class CourseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * String with course code
	 */
	@Id
	@Column(unique = true, nullable = false, columnDefinition = "char(4)")
	private String code;

	/**
	 * String with course name.
	 */
	private String name;

	public CourseModel() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

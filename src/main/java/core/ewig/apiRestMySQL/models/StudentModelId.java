package core.ewig.apiRestMySQL.models;

import java.io.Serializable;

/**
 * @author Cristian Alarcón
 *
 */
public class StudentModelId implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * String with student rut for generate compoused primary key.
	 */
	private String rut;

	/**
	 * String with course code for generate compoused primary key.
	 */
	private String courseCode;

	public StudentModelId() {
	}

	public StudentModelId(String rut, String courseCode) {
		super();
		this.rut = rut;
		this.courseCode = courseCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseCode == null) ? 0 : courseCode.hashCode());
		result = prime * result + ((rut == null) ? 0 : rut.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentModelId other = (StudentModelId) obj;
		if (courseCode == null) {
			if (other.courseCode != null)
				return false;
		} else if (!courseCode.equals(other.courseCode))
			return false;
		if (rut == null) {
			if (other.rut != null)
				return false;
		} else if (!rut.equals(other.rut))
			return false;
		return true;
	}
}

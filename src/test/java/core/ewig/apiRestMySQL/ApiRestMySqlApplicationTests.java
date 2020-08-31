package core.ewig.apiRestMySQL;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import core.ewig.apiRestMySQL.services.StudentServiceImpl;

@SpringBootTest
class ApiRestMySqlApplicationTests {

	@Test
	void testRutValidation() {
		StudentServiceImpl service = new StudentServiceImpl();

		String rut = "9666516-5";

		assertTrue(service.rutValidation(rut));
	}
}

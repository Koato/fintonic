package com.erureka.fintonic;

import com.erureka.fintonic.domain.exception.TaskException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class FintonicApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testException() {
		assertThrows(TaskException.class, () -> {
			throw new TaskException();
		});
	}

}

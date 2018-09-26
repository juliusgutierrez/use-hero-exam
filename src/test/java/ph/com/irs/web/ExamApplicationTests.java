package ph.com.irs.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ph.com.irs.web.config.H2Config;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ExamApplication.class, H2Config.class})
@ActiveProfiles("test")
public class ExamApplicationTests {

	@Test
	public void contextLoads() {
	}

}

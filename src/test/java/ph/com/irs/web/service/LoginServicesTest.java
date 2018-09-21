package ph.com.irs.web.service;

import com.querydsl.core.types.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hamcrest.CoreMatchers;
import org.hamcrest.collection.IsMapContaining;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ph.com.irs.web.dao.LoginRepository;
import ph.com.irs.web.service.impl.LoginServiceImpl;

import static org.hamcrest.CoreMatchers.*;

/**
 * Created by julius on 15/09/2018.
 */
@RunWith(SpringRunner.class)
public class LoginServicesTest {

  @Mock
  private LoginRepository loginRepository;

  @InjectMocks
  private LoginServiceImpl loginService;

  @Before
  public void setUp() {
    List<Date> dateList = new ArrayList<>();
    dateList.add(new Date());
    Mockito.when(loginRepository.findAllUniqueDates())
        .thenReturn(dateList);

    List<String> users = new ArrayList<>();
    users.add("Juan");
    Mockito.when(loginRepository.findUsersBy(Mockito.any(Predicate.class)))
        .thenReturn(users);

    Map<String, Long> logs = new HashMap<>();
    logs.put("Juan", 33L);
    Mockito.when(loginRepository.findUserAndLoginCountBy(Mockito.any(Predicate.class)))
        .thenReturn(logs);
  }

  @Test
  public void testGetAllUniqueLoginDate() {
    List<Date> dateList = loginService.getAllUniqueLoginDate();
    Assert.assertNotNull(dateList.get(0));
  }


  @Test
  public void testGetAllUniqueUsersBy() {
    List<String> users = loginService.getAllUniqueUsersBy("20180101", "20180102");
    Assert.assertThat(users, hasItem("Juan"));
  }

  @Test
  public void testGetAllLoginsBy() {
    Map<String, Long> logs = loginService.getAllLoginsBy("20180101", "20180102",
        null, null, null, null);
    Assert.assertThat(logs, IsMapContaining.hasEntry("Juan", 33L));

  }

}

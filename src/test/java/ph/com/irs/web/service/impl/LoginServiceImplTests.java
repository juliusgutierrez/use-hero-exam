package ph.com.irs.web.service.impl;


import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hamcrest.collection.IsMapContaining;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ph.com.irs.web.dao.LoginRepository;
import ph.com.irs.web.model.Login;
import ph.com.irs.web.service.LoginService;

/**
 * Created by julius on 21/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LoginServiceImplTests {

  @Autowired
  private LoginRepository loginRepository;

  @Autowired
  private LoginService loginService;

  @Before
  public void setUp() {
    Login login = new Login();
    login.setUser("Juan");
    login.setAttribute1("AAA");
    login.setAttribute2("BAA");
    login.setAttribute3("CAA");
    login.setAttribute4("DAA");
    login.setLoginTime(LocalDateTime.now());
    loginRepository.save(login);

  }

  //TODO :MySql Function "DATE_FORMAT" is not available in H2 yet
  @Test(expected = JpaSystemException.class)
  public void testGetAllUniqueLoginDate() {
    List<Date> dateList = loginService.getAllUniqueLoginDate();
    Assert.assertNotNull(dateList.get(0));
  }


  @Test
  public void testGetAllUniqueUsersByShouldReturnNotNull() {
    List<String> users = loginService.getAllUniqueUsersBy(null, null);
    Assert.assertNotNull(users);
  }

  @Test
  public void testStartDateOnlyOfGetAllUniqueUsers() {
    List<String> users = loginService.getAllUniqueUsersBy("20180901", null);
    Assert.assertNotNull(users);
  }

  @Test
  public void testEndDateOnlyOfGetAllUniqueUsers() {
    List<String> users = loginService.getAllUniqueUsersBy(null, "20181231");
    Assert.assertNotNull(users);
  }

  @Test
  public void testStartAndEndDateOfGetAllUniqueUsers() {
    List<String> users = loginService.getAllUniqueUsersBy("20180901", "20181231");
    Assert.assertThat(users, hasItem("Juan"));
  }

  @Test
  public void testGetAllLoginsBy() {
    Map<String, Long> logs = loginService.getAllLoginsBy("20180101", "20181231",
        null, null, null, null);
    Assert.assertThat(logs.get("Juan"), greaterThan(0L));
  }

  @Test
  public void testAttr1OfGetAllLoginsBy() {
    List<String> attr1 = new ArrayList<>();
    attr1.add("AAA");

    Map<String, Long> logs = loginService.getAllLoginsBy("20180101", "20181231",
        attr1, null, null, null);
    Assert.assertThat(logs, IsMapContaining.hasKey("Juan"));
  }

  @Test
  public void testAttr2OfGetAllLoginsByShouldReturnNon() {
    List<String> attr2 = new ArrayList<>();
    attr2.add("CAB");

    Map<String, Long> logs = loginService.getAllLoginsBy("20180101", "20181231",
        null, attr2, null, null);
    Assert.assertThat(logs, not(IsMapContaining.hasKey("Juan")));
  }

}

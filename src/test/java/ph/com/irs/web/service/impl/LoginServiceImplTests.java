package ph.com.irs.web.service.impl;


import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
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
    Login juanLogin = new Login();
    juanLogin.setUser("Juan");
    juanLogin.setAttribute1("AAA");
    juanLogin.setAttribute2("BAA");
    juanLogin.setAttribute3("CAA");
    juanLogin.setAttribute4("DAA");

    LocalDate firstDaySept2018 = LocalDate.of(2018, Month.SEPTEMBER, 1);
    LocalDateTime septDateTime = LocalDateTime.of(firstDaySept2018, LocalTime.now());
    juanLogin.setLoginTime(septDateTime);
    loginRepository.save(juanLogin);

    Login pedroLogin = new Login();
    pedroLogin.setUser("Pedro");
    pedroLogin.setAttribute1("ABC");
    pedroLogin.setAttribute2("BAA");
    pedroLogin.setAttribute3("CBB");
    pedroLogin.setAttribute4("DAA");

    LocalDate firstDayAug2018 = LocalDate.of(2018, Month.AUGUST, 1);
    LocalDateTime augDateTime = LocalDateTime.of(firstDayAug2018, LocalTime.now());
    pedroLogin.setLoginTime(augDateTime);
    loginRepository.save(pedroLogin);

  }

  //TODO :MySql Function "DATE_FORMAT" is not available in H2 yet
  @Test(expected = JpaSystemException.class)
  public void when_getAllUniqueLoginDate_expect_hasTwo() {
    List<Date> dateList = loginService.getAllUniqueLoginDate();
    Assert.assertThat(dateList, hasSize(2));
  }


  @Test
  public void when_getAllUniqueUsersBy_expect_hasTwo() {
    List<String> users = loginService.getAllUniqueUsersBy(null, null);
    Assert.assertThat(users, hasSize(2));
  }

  @Test
  public void when_getAllUniqueUsersStartDateHasVal_expect_notNull() {
    List<String> users = loginService.getAllUniqueUsersBy("20180901", null);
    Assert.assertNotNull(users);
  }

  @Test
  public void when_getAllUniqueUsersEndDateHasVal_expect_notNull() {
    List<String> users = loginService.getAllUniqueUsersBy(null, "20181231");
    Assert.assertNotNull(users);
  }

  @Test
  public void when_getAllUniqueUsersDatesHasVal_expect_hasItem() {
    List<String> users = loginService.getAllUniqueUsersBy("20180901", "20181231");
    Assert.assertThat(users, hasItem("Juan"));
  }

  @Test
  public void when_getAllLoginsBy_expect_loginCountIsGreaterThanZero() {
    Map<String, Long> logs = loginService.getAllLoginsBy("20180101", "20181231",
        null, null, null, null);
    Assert.assertThat(logs.get("Juan"), greaterThan(0L));
  }

  @Test
  public void when_getAllLoginsByAttr1HasVal_expect_hasKey() {
    List<String> attr1 = new ArrayList<>();
    attr1.add("AAA");

    Map<String, Long> logs = loginService.getAllLoginsBy("20180101", "20181231",
        attr1, null, null, null);
    Assert.assertThat(logs, IsMapContaining.hasKey("Juan"));
  }

  @Test
  public void when_getAllLoginsByAttr2HasWrongVal_expect_notExist() {
    List<String> attr2 = new ArrayList<>();
    attr2.add("CAB");

    Map<String, Long> logs = loginService.getAllLoginsBy("20180101", "20181231",
        null, attr2, null, null);
    Assert.assertThat(logs, not(IsMapContaining.hasKey("Juan")));
  }

}

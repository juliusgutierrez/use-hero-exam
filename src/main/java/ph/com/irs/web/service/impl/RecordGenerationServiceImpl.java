package ph.com.irs.web.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ph.com.irs.web.dao.LoginRepository;
import ph.com.irs.web.model.Login;
import ph.com.irs.web.service.RecordGenerationService;

/**
 * Created by julius on 11/09/2018.
 */
@Service
public class RecordGenerationServiceImpl implements RecordGenerationService {

  private static final Long DEFAULT_COUNT = 100000L;

  private static String[] USERS = {
      "Janette", "Everette", "Angelica", "Zechariah", "Anya", "Precious", "Makenzie", "Mohammad",
      "Kristofer", "Catherine", "Darby", "Haskell"
  };

  @Autowired
  private LoginRepository loginRepository;

  @PersistenceContext
  private EntityManager em;

  @Transactional
  @Override
  public void doGenerate(Long count) {

    if (count == null) {
      count = DEFAULT_COUNT;
    }

    for (int index = 0; index < count; index++) {
      Login login = new Login();
      login.setLoginTime(randomDate());
      String userName = USERS[(int) (Math.random() * USERS.length)];
      login.setUser(userName);
      login.setAttribute1(createAttr("A"));
      login.setAttribute2(createAttr("B"));
      login.setAttribute3(createAttr("C"));
      login.setAttribute4(createAttr("D"));
      loginRepository.save(login);
      if ((index % 1000) == 0) {
        em.flush();
        em.clear();
      }
    }
  }

  /**
   * generate a random date
   */
  public static LocalDateTime randomDate() {
    int randomDateToMinus = (int) (Math.random() * 30 + 1);
    int randomTimeToMinus = (int) (Math.random() * 23 + 1);
    int randomMonth = (int) (Math.random() * 12 + 1);
    LocalDate randomDate = LocalDate.now().minusDays(randomDateToMinus);
    LocalTime randomTime = LocalTime.now().minusHours(randomTimeToMinus);
    LocalDateTime localDateTime = LocalDateTime.of(randomDate, randomTime)
        .withMonth(randomMonth);
    return localDateTime;
  }

  /**
   * create a random attributes based on prefix
   */
  public static String createAttr(String c) {
    int length = 2;
    boolean useLetters = true;
    boolean useNumbers = true;
    return new StringBuilder(c)
        .append(RandomStringUtils
            .random(length, 97, 100, useLetters, useNumbers)
            .toUpperCase()).toString();
  }
}

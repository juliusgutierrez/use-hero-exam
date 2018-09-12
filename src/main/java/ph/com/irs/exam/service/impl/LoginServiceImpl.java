package ph.com.irs.exam.service.impl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ph.com.irs.exam.dao.LoginRepository;
import ph.com.irs.exam.dao.filter.LoginFilterPredicateBuilder;
import ph.com.irs.exam.model.Login;
import ph.com.irs.exam.model.QLogin;
import ph.com.irs.exam.service.LoginService;

/**
 * Created by julius on 10/09/2018.
 */
@Service
public class LoginServiceImpl implements LoginService {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private LoginRepository loginRepository;

  @Override
  public List<Date> getAllUniqueLoginDate() {
    return loginRepository.findAllUniqueDates();
  }

  @Override
  public List<Login> getAllLoginsBy(String startDate, String endDate) {
    LOGGER.debug("start-date : [{}], end-date : [{}]", startDate, endDate);

    Predicate predicate = new LoginFilterPredicateBuilder()
        .startDate(startDate)
        .endDate(endDate)
        .build();
    List<Login> logins = (List<Login>) loginRepository.findAll(predicate, orderByLoginTimeAsc());
    LOGGER.debug("count result [{}]", logins.size());
    return logins;
  }

  private OrderSpecifier<LocalDateTime> orderByLoginTimeAsc() {
    return QLogin.login.loginTime.asc();
  }

}

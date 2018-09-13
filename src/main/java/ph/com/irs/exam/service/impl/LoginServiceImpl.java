package ph.com.irs.exam.service.impl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QPageRequest;
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
    return (List<Login>) loginRepository.findAll(predicate, orderByLoginTimeAsc());
  }

  private QPageRequest gotoPage(int page) {
    return new QPageRequest(page, 500, orderByLoginTimeAsc());
  }

  private OrderSpecifier<LocalDateTime> orderByLoginTimeAsc() {
    return QLogin.login.loginTime.asc();
  }

}

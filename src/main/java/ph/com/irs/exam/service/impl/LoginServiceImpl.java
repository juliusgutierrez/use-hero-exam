package ph.com.irs.exam.service.impl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
  public List<Login> getAllUsersBy(String startDate, String endDate) {
    LOGGER.debug("start-date : [{}], end-date : [{}]", startDate, endDate);

    Predicate predicate = new LoginFilterPredicateBuilder()
        .startDate(startDate)
        .endDate(endDate)
        .build();
    return (List<Login>) loginRepository.findAll(predicate, orderByLoginTimeAsc());
  }

  @Override
  public Map<String, Long> getAllLoginsBy(String startDate, String endDate, List<String> attr1,
      List<String> attr2,
      List<String> attr3, List<String> attr4) {

    Predicate predicate = new LoginFilterPredicateBuilder()
        .attr1(attr1).attr2(attr2).attr3(attr3).attr4(attr4)
        .startDate(startDate)
        .endDate(endDate)
        .build();

    return loginRepository.doItRight(predicate);
  }

  private QPageRequest gotoPage(int page) {
    return new QPageRequest(page, 500, orderByLoginTimeAsc());
  }

  private OrderSpecifier<LocalDateTime> orderByLoginTimeAsc() {
    return QLogin.login.loginTime.asc();
  }

}

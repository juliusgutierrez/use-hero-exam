package ph.com.irs.exam.dao.filter;

import com.querydsl.core.types.Predicate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.StringUtils;
import ph.com.irs.exam.commons.OptionalBooleanBuilder;
import ph.com.irs.exam.model.QLogin;

/**
 * Created by julius on 12/09/2018.
 */
public class LoginFilterPredicateBuilder {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
  private final QLogin LOGIN = QLogin.login;
  private LocalDateTime start;
  private LocalDateTime end;

  public LoginFilterPredicateBuilder startDate(String startDate) {
    if (StringUtils.isNotEmpty(startDate)) {
      this.start = LocalDate.parse(startDate, FORMATTER).atStartOfDay();
    }
    return this;
  }

  public LoginFilterPredicateBuilder endDate(String endDate) {
    if (StringUtils.isNotEmpty(endDate)) {
      this.end = LocalDate.parse(endDate, FORMATTER).atStartOfDay();
    }
    return this;
  }

  public Predicate build() {
    return new OptionalBooleanBuilder()
        .notNullAnd(LOGIN.loginTime::after, this.start)
        .notNullAnd(LOGIN.loginTime::before, this.end)
        .build();
  }

}

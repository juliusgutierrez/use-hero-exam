package ph.com.irs.web.dao.filter;

import com.querydsl.core.types.Predicate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import ph.com.irs.web.commons.OptionalBooleanBuilder;
import ph.com.irs.web.model.QLogin;

/**
 * Created by julius on 12/09/2018.
 */
public class LoginFilterPredicateBuilder {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
  private final QLogin login = QLogin.login;
  private LocalDateTime start;
  private LocalDateTime end;
  private List<String> attributes1;
  private List<String> attributes2;
  private List<String> attributes3;
  private List<String> attributes4;

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

  public LoginFilterPredicateBuilder attr1(List<String> attr1) {
    if (!CollectionUtils.isEmpty(attr1)) {
      this.attributes1 = attr1;
    }
    return this;
  }

  public LoginFilterPredicateBuilder attr2(List<String> attr2) {
    if (!CollectionUtils.isEmpty(attr2)) {
      this.attributes2 = attr2;
    }
    return this;
  }

  public LoginFilterPredicateBuilder attr3(List<String> attr3) {
    if (!CollectionUtils.isEmpty(attr3)) {
      this.attributes3 = attr3;
    }
    return this;
  }

  public LoginFilterPredicateBuilder attr4(List<String> attr4) {
    if (!CollectionUtils.isEmpty(attr4)) {
      this.attributes4 = attr4;
    }
    return this;
  }

  public Predicate build() {
    return new OptionalBooleanBuilder()
        .notNullAnd(login.loginTime::after, this.start)
        .notNullAnd(login.loginTime::before, this.end)
        .notEmptyAnd(login.attribute1::in, this.attributes1)
        .notEmptyAnd(login.attribute2::in, this.attributes2)
        .notEmptyAnd(login.attribute3::in, this.attributes3)
        .notEmptyAnd(login.attribute4::in, this.attributes4)
        .build();
  }

}

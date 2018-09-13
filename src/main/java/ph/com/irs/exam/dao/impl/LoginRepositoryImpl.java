package ph.com.irs.exam.dao.impl;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ph.com.irs.exam.dao.LoginRepositoryCustom;
import ph.com.irs.exam.model.QLogin;

/**
 * Created by julius on 13/09/2018.
 */
@Repository
public class LoginRepositoryImpl implements LoginRepositoryCustom {

  private final QLogin LOGIN = QLogin.login;

  @Autowired
  private JPAQueryFactory queryFactory;

  @Override
  public Map<String, Long> doItRight(Predicate predicate) {

    NumberPath<Long> count = Expressions.numberPath(Long.class, "c");
    Map<String, Long> logs = queryFactory.from(LOGIN)
        .select(LOGIN.user, LOGIN.loginTime.count().as(count))
        .where(predicate)
        .groupBy(LOGIN.loginTime.dayOfYear(), LOGIN.user)
        .transform(GroupBy.groupBy(LOGIN.user).as(LOGIN.loginTime.count().as(count)));

    return logs;
  }
}

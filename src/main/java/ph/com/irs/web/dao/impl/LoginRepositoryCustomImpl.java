package ph.com.irs.web.dao.impl;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ph.com.irs.web.dao.LoginRepositoryCustom;
import ph.com.irs.web.model.QLogin;

/**
 * Created by julius on 13/09/2018.
 */
@Repository
public class LoginRepositoryCustomImpl implements LoginRepositoryCustom {

  private final QLogin login = QLogin.login;

  @Autowired
  private JPAQueryFactory queryFactory;

  @Override
  public List<String> findUsersBy(Predicate predicate) {
    List<String> users = queryFactory
        .select(login.user)
        .from(login)
        .where(predicate)
        .groupBy(login.user)
        .orderBy(login.user.asc())
        .fetch();
    return users;
  }

  @Override
  public Map<String, Long> findUserAndLoginCountBy(Predicate predicate) {

    NumberPath<Long> count = Expressions.numberPath(Long.class, "c");
    Map<String, Long> logs = queryFactory
        .select(login.user, login.loginTime.count().as(count))
        .from(login)
        .where(predicate)
        .groupBy(login.user)
        .transform(GroupBy.groupBy(login.user).as(login.loginTime.count().as(count)));

    return logs;
  }
}

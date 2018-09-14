package ph.com.irs.web.dao;

import com.querydsl.core.types.Predicate;
import java.util.List;
import java.util.Map;

/**
 * Created by julius on 13/09/2018.
 */
public interface LoginRepositoryCustom {

  List<String> findUsersBy(Predicate predicate);

  /**
   * Retrieve user and number of login
   * @param predicate
   * @return
   */
  Map<String, Long> findUserAndLoginCountBy(Predicate predicate);

}

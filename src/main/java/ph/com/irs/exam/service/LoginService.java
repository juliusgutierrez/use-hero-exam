package ph.com.irs.exam.service;

import com.querydsl.core.Tuple;
import java.util.Date;
import java.util.List;
import java.util.Map;
import ph.com.irs.exam.model.Login;

/**
 * Created by julius on 10/09/2018.
 */
public interface LoginService {

  List<Date> getAllUniqueLoginDate();

  List<Login> getAllUsersBy(String startDate, String endDate);

  Map<String, Long> getAllLoginsBy(String startDate, String endDate,
      List<String> attr1, List<String> attr2, List<String> attr3, List<String> attr4);

}

package ph.com.irs.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import ph.com.irs.web.model.Login;

/**
 * Created by julius on 10/09/2018.
 */
public interface LoginService {

  /**
   * Retrieves all unique login dates
   * @return
   */
  List<Date> getAllUniqueLoginDate();

  /**
   * Retrieves Logins based on parameter
   * @param startDate
   * @param endDate
   * @return
   */
  List<String> getAllUniqueUsersBy(String startDate, String endDate);


  /**
   * Retrieves users and count of logins
   * @param startDate
   * @param endDate
   * @param attr1
   * @param attr2
   * @param attr3
   * @param attr4
   * @return
   */
  Map<String, Long> getAllLoginsBy(String startDate, String endDate,
      List<String> attr1, List<String> attr2, List<String> attr3, List<String> attr4);

}

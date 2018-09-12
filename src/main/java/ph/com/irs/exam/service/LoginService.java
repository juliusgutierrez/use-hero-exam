package ph.com.irs.exam.service;

import java.util.Date;
import java.util.List;
import ph.com.irs.exam.model.Login;

/**
 * Created by julius on 10/09/2018.
 */
public interface LoginService {

  List<Date> getAllUniqueLoginDate();

  List<Login> getAllLoginsBy(String startDate, String endDate);

}

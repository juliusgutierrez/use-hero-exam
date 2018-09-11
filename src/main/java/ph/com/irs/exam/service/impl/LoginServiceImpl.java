package ph.com.irs.exam.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ph.com.irs.exam.dao.LoginRepository;
import ph.com.irs.exam.model.Login;
import ph.com.irs.exam.service.LoginService;

/**
 * Created by julius on 10/09/2018.
 */
@Service
public class LoginServiceImpl implements LoginService {

  @Autowired
  private LoginRepository loginRepository;

  @Override
  public List<Login> getAllLogins() {
    return (List<Login>) loginRepository.findAll();
  }

  @Override
  public List<Date> getAllUniqueLoginDate() {
    return (List<Date>) loginRepository.findDistinctByLoginTime();
  }
}

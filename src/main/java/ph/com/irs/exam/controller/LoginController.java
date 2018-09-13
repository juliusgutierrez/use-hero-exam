package ph.com.irs.exam.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ph.com.irs.exam.dto.BaseResponseDTO;
import ph.com.irs.exam.model.Login;
import ph.com.irs.exam.service.LoginService;
import ph.com.irs.exam.service.impl.LoginServiceImpl;

/**
 * Created by julius on 10/09/2018.
 */
@RestController
@RequestMapping("/test")
public class LoginController {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

  @Autowired
  private LoginService loginService;

  @GetMapping("/dates")
  public ResponseEntity getAllUniqueDates() {
    return ResponseEntity.ok(loginService.getAllUniqueLoginDate());
  }

  @GetMapping("/users")
  @ResponseBody
  public ResponseEntity<BaseResponseDTO> getAllLogins(
      @RequestParam(value = "start", required = false) String startDate,
      @RequestParam(value = "end", required = false) String endDate) {

    long startTime = System.currentTimeMillis();
    List<Login> loginList = loginService.getAllLoginsBy(startDate, endDate);
    BaseResponseDTO<Login> responseDTO = new BaseResponseDTO<>();
    responseDTO.setData(loginList);
    long stopTime = System.currentTimeMillis();
    LOGGER.debug("total time spent [{}]", (stopTime - startTime));
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping("/dates1")
  public ResponseEntity da() {
    Date s = new Date();
    System.out.println(s);
    return ResponseEntity.ok(LocalDate.now());
  }


}

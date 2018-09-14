package ph.com.irs.web.controller;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ph.com.irs.web.dto.BaseResponseDTO;
import ph.com.irs.web.model.Login;
import ph.com.irs.web.service.LoginService;

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
  public ResponseEntity<BaseResponseDTO> getAllUsersBy(
      @RequestParam(value = "start", required = false) String startDate,
      @RequestParam(value = "end", required = false) String endDate) {

    long startTime = System.currentTimeMillis();
    List<String> loginList = loginService.getAllUniqueUsersBy(startDate, endDate);
    BaseResponseDTO<String> responseDTO = new BaseResponseDTO<>();
    responseDTO.setData(loginList);
    long stopTime = System.currentTimeMillis();
    LOGGER.debug("total time spent [{}]", (stopTime - startTime));
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping("/logins")
  public ResponseEntity getAllLoginsBy(
      @RequestParam(value = "start", required = false) String startDate,
      @RequestParam(value = "end", required = false) String endDate,
      @RequestParam(value = "attribute1", required = false) List<String> attr1,
      @RequestParam(value = "attribute2", required = false) List<String> attr2,
      @RequestParam(value = "attribute3", required = false) List<String> attr3,
      @RequestParam(value = "attribute4", required = false) List<String> attr4) {

    long startTime = System.currentTimeMillis();
    Map<String, Long> loginList = loginService
        .getAllLoginsBy(startDate, endDate, attr1, attr2, attr3, attr4);
    BaseResponseDTO<Map<String, Integer>> responseDTO = new BaseResponseDTO<>();
    responseDTO.setData(null);
    long stopTime = System.currentTimeMillis();
    LOGGER.debug("total time spent [{}]", (stopTime - startTime));
    return ResponseEntity.ok(loginList);
  }


}

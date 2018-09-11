package ph.com.irs.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ph.com.irs.exam.service.LoginService;

/**
 * Created by julius on 10/09/2018.
 */
@RestController
@RequestMapping("/test")
public class LoginController {

  @Autowired
  private LoginService loginService;

  @GetMapping("/hello")
  public String getString() {
    return "hello world";
  }

  @GetMapping("/dates")
  public ResponseEntity getAllUniqueDates() {
    return ResponseEntity.ok(loginService.getAllUniqueLoginDate());
  }

  @GetMapping("/users")
  public ResponseEntity getAllLogins() {
    return ResponseEntity.ok(loginService.getAllLogins());
  }

}

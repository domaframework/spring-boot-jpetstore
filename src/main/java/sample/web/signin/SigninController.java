package sample.web.signin;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signin")
@Transactional
public class SigninController {

  @GetMapping
  public String signin() {
    return "signin/signin";
  }
}

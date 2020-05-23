package sample.web.account;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

public class AddAccountForm extends AbstractAccountForm {

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(min = 8)
  private String password;

  @NotBlank private String repeatedPassword;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRepeatedPassword() {
    return repeatedPassword;
  }

  public void setRepeatedPassword(String repeatedPassword) {
    this.repeatedPassword = repeatedPassword;
  }
}

package sample.entity;

import java.io.Serializable;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;

@Entity(metamodel = @Metamodel)
public class Signon implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id private String username;

  private String password;

  @Override
  public String toString() {
    return "Signon [username=" + username + ", password=" + password + "]";
  }

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
}

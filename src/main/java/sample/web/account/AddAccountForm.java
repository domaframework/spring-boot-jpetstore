package sample.web.account;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddAccountForm extends AbstractAccountForm {

    @NotNull
    @Size(max = 5)
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String repeatedPassword;

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

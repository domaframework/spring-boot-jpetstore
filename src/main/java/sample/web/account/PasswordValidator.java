package sample.web.account;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return AddAccountForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AddAccountForm form = (AddAccountForm) target;
        String password = form.getPassword();
        String repeatedPassword = form.getRepeatedPassword();
        if (password == null) {
            return;
        }
        if (!password.equals(repeatedPassword)) {
            errors.rejectValue("password",
                    "PasswordValidator.addAccountForm.password",
                    "password and repeated password must be same.");
        }
    }
}

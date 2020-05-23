package sample.web.account;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.entity.Account;
import sample.service.AccountService;
import sample.web.Constants;

@Controller
@RequestMapping("/account")
@Transactional
public class AccountController {

  private final AccountService accountService;
  private final PasswordEncoder passwordEncoder;
  private final PasswordValidator passwordValidator;

  public AccountController(
      AccountService accountService,
      PasswordEncoder passwordEncoder,
      PasswordValidator passwordValidator) {
    this.accountService = accountService;
    this.passwordEncoder = passwordEncoder;
    this.passwordValidator = passwordValidator;
  }

  @InitBinder("addAccountForm")
  public void initBinder(WebDataBinder binder) {
    binder.addValidators(passwordValidator);
  }

  @GetMapping("add")
  public String add(Model model) {
    return modelAndViewForAdd(model, new AddAccountForm());
  }

  @PostMapping("add")
  public String add(
      @Validated AddAccountForm accountForm, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      return modelAndViewForAdd(model, accountForm);
    }
    var account = new Account();
    BeanUtils.copyProperties(accountForm, account);
    var rawPassword = accountForm.getPassword();
    var encodedPassword = passwordEncoder.encode(rawPassword);
    account.setPassword(encodedPassword);
    accountService.insertAccount(account);
    return "redirect:/signin";
  }

  private String modelAndViewForAdd(Model model, AddAccountForm accountForm) {
    model.addAttribute(accountForm);
    model.addAttribute("languageList", Constants.LANGUAGE_LIST);
    model.addAttribute("categoryList", Constants.CATEGORY_LIST);
    return "account/add";
  }

  @GetMapping("/edit")
  public String edit(Model model, @AuthenticationPrincipal User user) {
    var account = accountService.getAccount(user.getUsername());
    var accountForm = new EditAccountForm();
    BeanUtils.copyProperties(account, accountForm, "password");
    return modelAndViewForEdit(model, accountForm, user);
  }

  @PostMapping("/edit")
  public String edit(
      @Validated EditAccountForm accountForm,
      BindingResult bindingResult,
      Model model,
      @AuthenticationPrincipal User user) {
    if (bindingResult.hasErrors()) {
      return modelAndViewForEdit(model, accountForm, user);
    }
    var account = accountService.getAccount(user.getUsername());
    BeanUtils.copyProperties(accountForm, account);
    account.setUsername(user.getUsername());
    accountService.updateAccount(account);
    return "redirect:/";
  }

  private String modelAndViewForEdit(Model model, EditAccountForm accountForm, User user) {
    model.addAttribute(accountForm);
    model.addAttribute("username", user.getUsername());
    model.addAttribute("languageList", Constants.LANGUAGE_LIST);
    model.addAttribute("categoryList", Constants.CATEGORY_LIST);
    return "account/edit";
  }
}

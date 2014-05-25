/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package sample.web.account;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sample.entity.Account;
import sample.service.AccountService;
import sample.web.Constants;

@Controller
@RequestMapping("/account")
@Transactional
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        return modelAndViewForAdd(model, new AddAccountForm());
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@Validated AddAccountForm accountForm,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return modelAndViewForAdd(model, accountForm);
        }
        Account account = new Account();
        BeanUtils.copyProperties(accountForm, account);
        accountService.insertAccount(account);
        return "redirect:/signin";
    }

    private String modelAndViewForAdd(Model model, AddAccountForm accountForm) {
        model.addAttribute("accountForm", accountForm);
        model.addAttribute("languageList", Constants.LANGUAGE_LIST);
        model.addAttribute("categoryList", Constants.CATEGORY_LIST);
        return "account/add";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model, @AuthenticationPrincipal User user) {
        Account account = accountService.getAccount(user.getUsername());
        EditAccountForm accountForm = new EditAccountForm();
        BeanUtils.copyProperties(account, accountForm, "password");
        return modelAndViewForEdit(model, accountForm, user);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Validated EditAccountForm accountForm,
            BindingResult bindingResult, Model model,
            @AuthenticationPrincipal User user) {
        if (bindingResult.hasErrors()) {
            return modelAndViewForEdit(model, accountForm, user);
        }
        Account account = accountService.getAccount(user.getUsername());
        BeanUtils.copyProperties(accountForm, account);
        account.setUsername(user.getUsername());
        accountService.updateAccount(account);
        return "redirect:/";
    }

    private String modelAndViewForEdit(Model model,
            EditAccountForm accountForm, User user) {
        model.addAttribute("accountForm", accountForm);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("languageList", Constants.LANGUAGE_LIST);
        model.addAttribute("categoryList", Constants.CATEGORY_LIST);
        return "account/edit";
    }

}

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
package sample.service;

import java.util.Collections;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sample.dao.AccountDao;
import sample.dao.ProfileDao;
import sample.dao.SignonDao;
import sample.entity.Account;
import sample.entity.Profile;
import sample.entity.Signon;

@Service
public class AccountService implements UserDetailsService {

    private final AccountDao accountDao;

    private final ProfileDao profileDao;

    private final SignonDao signonDao;

    @Autowired
    public AccountService(AccountDao accountDao, ProfileDao profileDao,
            SignonDao signonDao) {
        this.accountDao = accountDao;
        this.profileDao = profileDao;
        this.signonDao = signonDao;
    }

    public Account getAccount(String username) {
        return accountDao.selectAccountByUsername(username);
    }

    public void insertAccount(Account account) {
        accountDao.insertAccount(account);

        Profile profile = new Profile();
        BeanUtils.copyProperties(account, profile);
        profileDao.insertProfile(profile);

        Signon signon = new Signon();
        BeanUtils.copyProperties(account, signon);
        signonDao.insertSignon(signon);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);

        Profile profile = new Profile();
        BeanUtils.copyProperties(account, profile);
        profileDao.updateProfile(profile);

        Signon signon = new Signon();
        BeanUtils.copyProperties(account, signon);
        if (signon.getPassword() != null && signon.getPassword().length() > 0) {
            signonDao.updateSignon(signon);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Account account = accountDao.selectAccountByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username + " not found");
        }
        return new User(account.getUsername(), account.getPassword(),
                Collections.emptyList());
    }

}

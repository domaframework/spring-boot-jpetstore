package sample.service;

import java.util.Collections;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sample.entity.Account;
import sample.repository.AccountRepository;

@Service
public class AccountService implements UserDetailsService {

  private final AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public Account getAccount(String username) {
    return accountRepository.selectAccountByUsername(username);
  }

  public void insertAccount(Account account) {
    accountRepository.insertAccount(account);
  }

  public void updateAccount(Account account) {
    accountRepository.updateAccount(account);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var account = accountRepository.selectAccountByUsername(username);
    if (account == null) {
      throw new UsernameNotFoundException(username + " not found");
    }
    return new User(account.getUsername(), account.getPassword(), Collections.emptyList());
  }
}

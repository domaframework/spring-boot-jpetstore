package sample.repository;

import org.seasar.doma.jdbc.SqlLogType;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.springframework.stereotype.Repository;
import sample.entity.Account;
import sample.entity.Account_;
import sample.entity.BannerData_;
import sample.entity.Profile_;
import sample.entity.Signon_;

@Repository
public class AccountRepository {

  private final Entityql entityql;

  public AccountRepository(Entityql entityql) {
    this.entityql = entityql;
  }

  public Account selectAccountByUsername(String username) {
    var a = new Account_();
    var p = new Profile_();
    var s = new Signon_();
    var b = new BannerData_();

    return entityql
        .from(a)
        .innerJoin(p, on -> on.eq(a.username, p.username))
        .innerJoin(s, on -> on.eq(a.username, s.username))
        .innerJoin(b, on -> on.eq(p.favouriteCategoryId, b.favouriteCategoryId))
        .where(c -> c.eq(a.username, username))
        .associate(a, p, Account::setProfile)
        .associate(a, s, Account::setSignon)
        .associate(a, b, Account::setBannerData)
        .fetchOne();
  }

  public void insertAccount(Account account) {
    var a = new Account_();
    var p = new Profile_();
    var s = new Signon_();

    entityql.insert(a, account).execute();

    entityql.insert(p, account.getProfile()).execute();

    entityql
        .insert(s, account.getSignon(), settings -> settings.setSqlLogType(SqlLogType.RAW))
        .execute();
  }

  public void updateAccount(Account account) {
    var a = new Account_();
    var p = new Profile_();
    var s = new Signon_();

    entityql.update(a, account).execute();

    entityql.update(p, account.getProfile()).execute();

    var signon = account.getSignon();
    if (signon.getPassword() != null && signon.getPassword().length() > 0) {
      entityql.update(s, signon, settings -> settings.setSqlLogType(SqlLogType.RAW)).execute();
    }
  }
}

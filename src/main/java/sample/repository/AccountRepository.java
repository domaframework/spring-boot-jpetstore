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
package sample.repository;

import org.seasar.doma.jdbc.SqlLogType;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.springframework.stereotype.Repository;
import sample.entity.Account;
import sample.entity.Account_;
import sample.entity.BannerData_;
import sample.entity.Profile_;
import sample.entity.Signon;
import sample.entity.Signon_;

@Repository
public class AccountRepository {

    private final Entityql entityql;

    public AccountRepository(Entityql entityql) {
        this.entityql = entityql;
    }

    public Account selectAccountByUsername(String username) {
        Account_ a = new Account_();
        Profile_ p = new Profile_();
        Signon_ s = new Signon_();
        BannerData_ b = new BannerData_();

        return entityql.from(a)
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
        Account_ a = new Account_();
        Profile_ p = new Profile_();
        Signon_ s = new Signon_();

        entityql.insert(a, account).execute();

        entityql.insert(p, account.getProfile()).execute();

        entityql.insert(s, account.getSignon(),
                settings -> settings.setSqlLogType(SqlLogType.RAW)
        ).execute();
    }

    public void updateAccount(Account account) {
        Account_ a = new Account_();
        Profile_ p = new Profile_();
        Signon_ s = new Signon_();

        entityql.update(a, account).execute();

        entityql.update(p, account.getProfile()).execute();

        Signon signon = account.getSignon();
        if (signon.getPassword() != null && signon.getPassword().length() > 0) {
            entityql.update(s, signon,
                    settings -> settings.setSqlLogType(SqlLogType.RAW)
            ).execute();
        }
    }

}

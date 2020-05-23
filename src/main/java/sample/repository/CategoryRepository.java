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

import org.seasar.doma.jdbc.criteria.Entityql;
import org.springframework.stereotype.Repository;
import sample.entity.Category;
import sample.entity.Category_;

@Repository
public class CategoryRepository {

    private final Entityql entityql;

    public CategoryRepository(Entityql entityql) {
        this.entityql = entityql;
    }

    public Category selectCategory(String categoryId) {
        Category_ ca = new Category_();

        return entityql.from(ca).where(c -> c.eq(ca.categoryId, categoryId)).fetchOne();
    }
}

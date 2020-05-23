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
import org.seasar.doma.jdbc.criteria.option.LikeOption;
import org.springframework.stereotype.Repository;
import sample.entity.Product;
import sample.entity.Product_;

import java.util.List;

import static org.seasar.doma.jdbc.criteria.expression.Expressions.lower;

@Repository
public class ProductRepository {

    private final Entityql entityql;

    public ProductRepository(Entityql entityql) {
        this.entityql = entityql;
    }

    public Product selectProduct(String productId) {
        Product_ p = new Product_();

        return entityql.from(p).where(c -> c.eq(p.productId, productId)).fetchOne();
    }

    public List<Product> selectProductsByCategory(String categoryId) {
        Product_ p = new Product_();

        return entityql.from(p).where(c -> c.eq(p.categoryId, categoryId)).fetch();
    }

    public List<Product> selectProductList(List<String> keywords) {
        Product_ p = new Product_();
        return entityql.from(p).where(c ->
        {
            for (String keyword : keywords) {
                c.or(() -> {
                    c.like(lower(p.name), keyword.toLowerCase(), LikeOption.infix());
                });
                c.or(() -> {
                    c.like(lower(p.categoryId), keyword.toLowerCase(), LikeOption.infix());
                });
                c.or(() -> {
                    c.like(lower(p.description), keyword.toLowerCase(), LikeOption.infix());
                });
            }
        }).fetch();
    }

}

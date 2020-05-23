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
import org.seasar.doma.jdbc.criteria.NativeSql;
import org.springframework.stereotype.Repository;
import sample.entity.Inventory_;
import sample.entity.Item;
import sample.entity.Item_;
import sample.entity.Product_;

import java.util.List;

import static org.seasar.doma.jdbc.criteria.expression.Expressions.sub;

@Repository
public class ItemRepository {

    private final Entityql entityql;
    private final NativeSql nativeSql;

    public ItemRepository(Entityql entityql, NativeSql nativeSql) {
        this.entityql = entityql;
        this.nativeSql = nativeSql;
    }

    public int updateInventoryQuantity(String itemId, Integer increment) {
        Inventory_ i = new Inventory_();

        return nativeSql
                .update(i)
                .set(c -> c.value(i.quantity, sub(i.quantity, increment)))
                .where(c -> c.eq(i.itemId, itemId))
                .execute();
    }

    public Integer selectInventoryQuantity(String itemId) {
        Inventory_ i = new Inventory_();

        return nativeSql.from(i).where($ -> $.eq(i.itemId, itemId)).select(i.quantity).fetchOne();
    }

    public Item selectItem(String itemId) {
        Item_ it = new Item_();
        Inventory_ in = new Inventory_();
        Product_ p = new Product_();

        return entityql.from(it)
                .innerJoin(in, on -> on.eq(it.itemId, in.itemId))
                .innerJoin(p, on -> on.eq(it.productId, p.productId))
                .where(c -> c.eq(it.itemId, itemId))
                .associate(it, in, Item::setInventory)
                .associate(it, p, Item::setProduct)
                .fetchOne();
    }

    public List<Item> selectItemsByProduct(String productId) {
        Item_ i = new Item_();
        Inventory_ in = new Inventory_();
        Product_ p = new Product_();

        return entityql.from(i)
                .innerJoin(in, on -> on.eq(i.itemId, in.itemId))
                .innerJoin(p, on -> on.eq(i.productId, p.productId))
                .where(c -> c.eq(i.productId, productId))
                .associate(i, in, Item::setInventory)
                .associate(i, p, Item::setProduct)
                .fetch();
    }

}

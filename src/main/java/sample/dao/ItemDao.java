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
package sample.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

import sample.ConfigAutowireable;
import sample.entity.Item;

@Dao
@ConfigAutowireable
public interface ItemDao {

    @Update(sqlFile = true)
    int updateInventoryQuantity(String itemId, Integer increment);

    @Select
    Integer selectInventoryQuantity(String itemId);

    @Select
    Item selectItem(String itemId);

    @Select
    List<Item> selectItemsByProduct(String productId);

}

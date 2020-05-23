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
import sample.entity.Inventory_;
import sample.entity.Item;
import sample.entity.Item_;
import sample.entity.Order;
import sample.entity.OrderLineItem;
import sample.entity.OrderLineItem_;
import sample.entity.OrderStatus_;
import sample.entity.Order_;
import sample.entity.Product_;

import java.util.List;

@Repository
public class OrderRepository {

    private final Entityql entityql;

    public OrderRepository(Entityql entityql) {
        this.entityql = entityql;
    }

    public Order selectOrder(int orderId) {
        Order_ order_ = new Order_();
        OrderStatus_ orderStatus_ = new OrderStatus_();
        OrderLineItem_ orderLineItem_ = new OrderLineItem_();
        Item_ item_ = new Item_();
        Inventory_ inventory_ = new Inventory_();
        Product_ product_ = new Product_();

        return entityql.from(order_)
                .innerJoin(orderStatus_, on -> on.eq(order_.orderId, orderStatus_.orderId))
                .leftJoin(orderLineItem_, on -> on.eq(order_.orderId, orderLineItem_.orderId))
                .leftJoin(item_, on -> on.eq(orderLineItem_.itemId, item_.itemId))
                .innerJoin(inventory_, on -> on.eq(item_.itemId, inventory_.itemId))
                .innerJoin(product_, on -> on.eq(item_.productId, product_.productId))
                .where(c -> c.eq(order_.orderId, orderId))
                .associate(order_, orderStatus_, Order::setOrderStatus)
                .associate(order_, orderLineItem_, Order::addLineItem)
                .associate(orderLineItem_, item_, OrderLineItem::setItem)
                .associate(item_, inventory_, Item::setInventory)
                .associate(item_, product_, Item::setProduct)
                .fetchOne();
    }

    public List<Order> selectOrdersByUsername(String username) {
        Order_ o = new Order_();
        OrderStatus_ os = new OrderStatus_();

        return entityql.from(o)
                .innerJoin(os, on -> on.eq(o.orderId, os.orderId))
                .where(c -> c.eq(o.username, username))
                .orderBy(c -> c.asc(o.orderDate))
                .associate(o, os, Order::setOrderStatus)
                .fetch();
    }

    public void insertOrder(Order order) {
        Order_ o = new Order_();
        OrderStatus_ os = new OrderStatus_();
        OrderLineItem_ ol = new OrderLineItem_();

        entityql.insert(o, order).execute();

        entityql.insert(os, order.getOrderStatus()).execute();

        entityql.insert(ol, order.getLineItemList()).execute();
    }

}

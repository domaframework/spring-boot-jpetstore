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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.dao.AccountDao;
import sample.dao.ItemDao;
import sample.dao.OrderDao;
import sample.dao.OrderLineItemDao;
import sample.entity.Account;
import sample.entity.Order;
import sample.entity.OrderLineItem;
import sample.model.Cart;
import sample.model.CartItem;

@Service
public class OrderService {

    private final ItemDao itemDao;

    private final OrderDao orderDao;

    private final OrderLineItemDao orderLineItemDao;

    private final AccountDao accountDao;

    @Autowired
    public OrderService(ItemDao itemDao, OrderDao orderDao,
            OrderLineItemDao orderLineItemDao, AccountDao accountDao) {
        this.itemDao = itemDao;
        this.orderDao = orderDao;
        this.orderLineItemDao = orderLineItemDao;
        this.accountDao = accountDao;
    }

    public void insertOrder(Order order, List<OrderLineItem> lineItems) {
        for (OrderLineItem lineItem : lineItems) {
            itemDao.updateInventoryQuantity(lineItem.getItemId(),
                    lineItem.getQuantity());
        }

        orderDao.insertOrder(order);
        orderDao.insertOrderStatus(order);
        for (OrderLineItem lineItem : lineItems) {
            lineItem.setOrderId(order.getOrderId());
        }
        orderLineItemDao.insertLineItem(lineItems);
    }

    public Order getOrder(int orderId) {
        return orderDao.selectOrder(orderId);
    }

    public List<OrderLineItem> getOrderLineItems(int orderId) {
        return orderLineItemDao.selectLineItemsByOrderId(orderId);
    }

    public List<Order> getOrdersByUsername(String username) {
        return orderDao.selectOrdersByUsername(username);
    }

    public Order createNewOrder(String username, Cart cart) {
        Account account = accountDao.selectAccountByUsername(username);

        Order order = new Order();
        order.setUsername(account.getUsername());
        order.setOrderDate(new Date(System.currentTimeMillis()));
        order.setShipToFirstName(account.getFirstName());
        order.setShipToLastName(account.getLastName());
        order.setShipAddress1(account.getAddress1());
        order.setShipAddress2(account.getAddress2());
        order.setShipCity(account.getCity());
        order.setShipState(account.getState());
        order.setShipZip(account.getZip());
        order.setShipCountry(account.getCountry());
        order.setBillToFirstName(account.getFirstName());
        order.setBillToLastName(account.getLastName());
        order.setBillAddress1(account.getAddress1());
        order.setBillAddress2(account.getAddress2());
        order.setBillCity(account.getCity());
        order.setBillState(account.getState());
        order.setBillZip(account.getZip());
        order.setBillCountry(account.getCountry());
        order.setTotalPrice(cart.getSubTotal());
        order.setCreditCard("999 9999 9999 9999");
        order.setExpiryDate("12/03");
        order.setCardType("Visa");
        order.setCourier("UPS");
        order.setLocale("CA");
        order.setStatus("P");
        return order;
    }

    public List<OrderLineItem> createNewLineItems(Cart cart) {
        ArrayList<OrderLineItem> lineItems = new ArrayList<OrderLineItem>();
        for (CartItem cartItem : cart.getCartItemList()) {
            OrderLineItem lineItem = new OrderLineItem();
            lineItem.setLineNumber(lineItems.size() + 1);
            lineItem.setQuantity(cartItem.getQuantity());
            lineItem.setItemId(cartItem.getItem().getItemId());
            lineItem.setUnitPrice(cartItem.getItem().getListPrice());
            lineItems.add(lineItem);
        }
        return lineItems;
    }

}

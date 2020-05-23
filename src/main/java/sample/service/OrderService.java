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

import org.springframework.stereotype.Service;
import sample.entity.Account;
import sample.entity.Order;
import sample.entity.OrderLineItem;
import sample.model.Cart;
import sample.model.CartItem;
import sample.repository.AccountRepository;
import sample.repository.ItemRepository;
import sample.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final AccountRepository accountRepository;

    public OrderService(ItemRepository itemRepository,
                        OrderRepository orderRepository,
                        AccountRepository accountRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.accountRepository = accountRepository;
    }

    public void insertOrder(Order order) {
        for (OrderLineItem lineItem : order.getLineItemList()) {
            itemRepository.updateInventoryQuantity(lineItem.getItemId(),
                    lineItem.getQuantity());
        }
        orderRepository.insertOrder(order);
    }

    public Order getOrder(int orderId) {
        return orderRepository.selectOrder(orderId);
    }

    public List<Order> getOrdersByUsername(String username) {
        return orderRepository.selectOrdersByUsername(username);
    }

    public Order createNewOrder(String username, Cart cart) {
        Account account = accountRepository.selectAccountByUsername(username);

        Order order = new Order();
        order.setUsername(account.getUsername());
        order.setOrderDate(LocalDateTime.now());
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

        int i = 0;
        for (CartItem cartItem : cart.getCartItemList()) {
            OrderLineItem lineItem = new OrderLineItem();
            lineItem.setLineNumber(++i);
            lineItem.setQuantity(cartItem.getQuantity());
            lineItem.setItemId(cartItem.getItem().getItemId());
            lineItem.setUnitPrice(cartItem.getItem().getListPrice());
            order.addLineItem(lineItem);
        }

        return order;
    }
}

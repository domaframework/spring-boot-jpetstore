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
package sample.web.history;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sample.entity.Order;
import sample.entity.OrderLineItem;
import sample.service.OrderService;

@Controller
@RequestMapping("/history")
@Transactional
public class HistoryController {

    private final OrderService orderService;

    @Autowired
    public HistoryController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String viewList(Model model, @AuthenticationPrincipal User user) {
        List<Order> orderList = orderService.getOrdersByUsername(user
                .getUsername());
        model.addAttribute("orderList", orderList);
        return "history/list";
    }

    @RequestMapping(value = "{orderId}", method = RequestMethod.GET)
    public String viewDetail(@PathVariable("orderId") int orderId, Model model) {
        Order order = orderService.getOrder(orderId);
        List<OrderLineItem> lineItems = orderService.getOrderLineItems(orderId);
        model.addAttribute("order", order);
        model.addAttribute("lineItems", lineItems);
        return "history/detail";
    }
}

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
package sample.web.order;

import java.util.List;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sample.entity.Order;
import sample.entity.OrderLineItem;
import sample.model.Cart;
import sample.service.OrderService;

@Controller
@RequestMapping("/order")
@Transactional
public class OrderController {

    private final OrderService orderService;
    private final Cart cart;

    public OrderController(OrderService orderService, Cart cart) {
        this.orderService = orderService;
        this.cart = cart;
    }

    @GetMapping
    public String confirm(Model model, @AuthenticationPrincipal User user,
            RedirectAttributes redirectAttributes) {
        if (cart.isEmpty()) {
            return fillMessageAndredirectToIndex(redirectAttributes);
        }
        Order order = orderService.createNewOrder(user.getUsername(), cart);
        model.addAttribute("order", order);
        return "order/confirm";
    }

    @PostMapping
    public String confirm(@AuthenticationPrincipal User user,
            RedirectAttributes redirectAttributes) {
        if (cart.isEmpty()) {
            return fillMessageAndredirectToIndex(redirectAttributes);
        }
        Order order = orderService.createNewOrder(user.getUsername(), cart);
        orderService.insertOrder(order);
        cart.clear();
        redirectAttributes.addFlashAttribute("message", "Thank you!");
        return "redirect:/";
    }

    protected String fillMessageAndredirectToIndex(
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Your Cart is Empty.");
        return "redirect:/";
    }
}
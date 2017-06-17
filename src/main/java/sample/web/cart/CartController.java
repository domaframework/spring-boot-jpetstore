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
package sample.web.cart;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.entity.Item;
import sample.model.Cart;
import sample.model.CartItem;
import sample.service.ItemService;

@Controller
@RequestMapping("/cart")
@Transactional
public class CartController {

    private final ItemService itemService;

    private final Cart cart;

    public CartController(ItemService itemService, Cart cart) {
        this.itemService = itemService;
        this.cart = cart;
    }

    @GetMapping
    public String view(Model model) {
        CartForm cartForm = new CartForm();
        for (CartItem item : cart.getCartItemList()) {
            CartItemForm cartItemForm = new CartItemForm();
            cartItemForm.setQuantity(item.getQuantity());
            cartForm.getItems().put(item.getItem().getItemId(), cartItemForm);
        }
        model.addAttribute("cart", cart);
        model.addAttribute("cartForm", cartForm);
        return "cart/list";
    }

    @PostMapping
    public String updateAll(@Validated CartForm cartForm, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("cart", cart);
            model.addAttribute("cartForm", cartForm);
            return "cart/list";
        }
        for (Map.Entry<String, CartItemForm> entry : cartForm.getItems()
                .entrySet()) {
            String itemId = entry.getKey();
            CartItem cartItem = cart.getCartItem(itemId);
            if (cartItem == null) {
                continue;
            }
            CartItemForm cartItemForm = entry.getValue();
            if (cartItemForm.getQuantity() < 1) {
                cart.removeItemById(itemId);
            } else {
                cart.setQuantityByItemId(itemId, cartItemForm.getQuantity());
            }
        }
        return "redirect:/cart";
    }

    @PostMapping("/item/{itemId}")
    public String addItem(@PathVariable String itemId, Model model) {
        if (cart.containsItemId(itemId)) {
            cart.incrementQuantityByItemId(itemId);
        } else {
            boolean isInStock = itemService.isItemInStock(itemId);
            Item item = itemService.getItem(itemId);
            cart.addItem(item, isInStock);
        }
        model.addAttribute(cart);
        return "redirect:/cart";
    }

    @DeleteMapping("/item/{itemId}")
    public String removeItem(@PathVariable String itemId) {
        cart.removeItemById(itemId);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("cart", cart);
        return "cart/checkout";
    }

    @PostMapping("/checkout")
    public String checkout() {
        return "redirect:/order";
    }

}

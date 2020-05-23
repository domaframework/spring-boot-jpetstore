package sample.web.cart;

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
import sample.model.Cart;
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
    var cartForm = new CartForm();
    for (var item : cart.getCartItemList()) {
      var cartItemForm = new CartItemForm();
      cartItemForm.setQuantity(item.getQuantity());
      cartForm.getItems().put(item.getItem().getItemId(), cartItemForm);
    }
    model.addAttribute("cart", cart);
    model.addAttribute("cartForm", cartForm);
    return "cart/list";
  }

  @PostMapping
  public String updateAll(@Validated CartForm cartForm, BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("cart", cart);
      model.addAttribute("cartForm", cartForm);
      return "cart/list";
    }
    for (var entry : cartForm.getItems().entrySet()) {
      var itemId = entry.getKey();
      var cartItem = cart.getCartItem(itemId);
      if (cartItem == null) {
        continue;
      }
      var cartItemForm = entry.getValue();
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
      var isInStock = itemService.isItemInStock(itemId);
      var item = itemService.getItem(itemId);
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

package sample.web.order;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
  public String confirm(
      Model model, @AuthenticationPrincipal User user, RedirectAttributes redirectAttributes) {
    if (cart.isEmpty()) {
      return fillMessageAndredirectToIndex(redirectAttributes);
    }
    var order = orderService.createNewOrder(user.getUsername(), cart);
    model.addAttribute("order", order);
    return "order/confirm";
  }

  @PostMapping
  public String confirm(@AuthenticationPrincipal User user, RedirectAttributes redirectAttributes) {
    if (cart.isEmpty()) {
      return fillMessageAndredirectToIndex(redirectAttributes);
    }
    var order = orderService.createNewOrder(user.getUsername(), cart);
    orderService.insertOrder(order);
    cart.clear();
    redirectAttributes.addFlashAttribute("message", "Thank you!");
    return "redirect:/";
  }

  protected String fillMessageAndredirectToIndex(RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("message", "Your Cart is Empty.");
    return "redirect:/";
  }
}

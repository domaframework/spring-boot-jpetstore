package sample.web.history;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.service.OrderService;

@Controller
@RequestMapping("/history")
@Transactional
public class HistoryController {

  private final OrderService orderService;

  public HistoryController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  public String viewList(Model model, @AuthenticationPrincipal User user) {
    var orderList = orderService.getOrdersByUsername(user.getUsername());
    model.addAttribute("orderList", orderList);
    return "history/list";
  }

  @GetMapping("{orderId}")
  public String viewDetail(@PathVariable int orderId, Model model) {
    var order = orderService.getOrder(orderId);
    model.addAttribute("order", order);
    return "history/detail";
  }
}

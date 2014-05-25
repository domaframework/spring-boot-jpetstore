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

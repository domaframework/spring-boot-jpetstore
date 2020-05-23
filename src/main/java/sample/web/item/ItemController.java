package sample.web.item;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.service.ItemService;

@Controller
@RequestMapping("/item")
@Transactional
public class ItemController {

  private final ItemService itemService;

  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @GetMapping("/{itemId}")
  public String viewDetail(@PathVariable String itemId, Model model) {
    model.addAttribute("item", itemService.getItem(itemId));
    return "item/detail";
  }
}

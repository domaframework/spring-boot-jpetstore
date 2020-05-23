package sample.web.product;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.service.ItemService;
import sample.service.ProductService;

@Controller
@RequestMapping("/product")
@Transactional
public class ProductController {

  private final ProductService productService;
  private final ItemService itemService;

  public ProductController(ProductService productService, ItemService itemService) {
    this.productService = productService;
    this.itemService = itemService;
  }

  @GetMapping("/{productId}")
  public String product(@PathVariable String productId, Model model) {
    model.addAttribute("product", productService.getProduct(productId));
    model.addAttribute("itemList", itemService.getItemsByProduct(productId));
    return "product/list";
  }
}

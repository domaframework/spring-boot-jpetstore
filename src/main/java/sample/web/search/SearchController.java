package sample.web.search;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sample.service.ProductService;

@Controller
@RequestMapping("/search")
@Transactional
public class SearchController {

  private final ProductService productService;

  public SearchController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public String search(
      @RequestParam(required = false, defaultValue = "") String keyword, Model model) {
    var productList = productService.searchProductList(keyword);
    model.addAttribute("productList", productList);
    return "search/list";
  }
}

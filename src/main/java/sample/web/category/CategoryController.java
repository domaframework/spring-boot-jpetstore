package sample.web.category;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.service.CategoryService;
import sample.service.ProductService;

@Controller
@RequestMapping("/category")
@Transactional
public class CategoryController {

  private final CategoryService categoryService;
  private final ProductService productService;

  public CategoryController(CategoryService categoryService, ProductService productService) {
    this.categoryService = categoryService;
    this.productService = productService;
  }

  @GetMapping("/{id}")
  public String category(@PathVariable String id, Model model) {
    model.addAttribute("category", categoryService.getCategory(id));
    model.addAttribute("productList", productService.getProductListByCategory(id));
    return "category/list";
  }
}

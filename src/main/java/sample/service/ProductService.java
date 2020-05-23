package sample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.springframework.stereotype.Service;
import sample.entity.Product;
import sample.repository.ProductRepository;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product getProduct(String productId) {
    return productRepository.selectProduct(productId);
  }

  public List<Product> getProductListByCategory(String categoryId) {
    return productRepository.selectProductsByCategory(categoryId);
  }

  public List<Product> searchProductList(String keywords) {
    List<String> keywordList = new ArrayList<>();
    for (var tokenizer = new StringTokenizer(keywords.toLowerCase(), " ", false);
        tokenizer.hasMoreTokens(); ) {
      keywordList.add(tokenizer.nextToken());
    }
    return productRepository.selectProductList(keywordList);
  }
}

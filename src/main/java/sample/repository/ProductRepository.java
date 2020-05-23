package sample.repository;

import static org.seasar.doma.jdbc.criteria.expression.Expressions.lower;

import java.util.List;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.seasar.doma.jdbc.criteria.option.LikeOption;
import org.springframework.stereotype.Repository;
import sample.entity.Product;
import sample.entity.Product_;

@Repository
public class ProductRepository {

  private final Entityql entityql;

  public ProductRepository(Entityql entityql) {
    this.entityql = entityql;
  }

  public Product selectProduct(String productId) {
    var p = new Product_();

    return entityql.from(p).where(c -> c.eq(p.productId, productId)).fetchOne();
  }

  public List<Product> selectProductsByCategory(String categoryId) {
    var p = new Product_();

    return entityql.from(p).where(c -> c.eq(p.categoryId, categoryId)).fetch();
  }

  public List<Product> selectProductList(List<String> keywords) {
    var p = new Product_();
    return entityql
        .from(p)
        .where(
            c -> {
              for (var keyword : keywords) {
                c.or(() -> c.like(lower(p.name), keyword.toLowerCase(), LikeOption.infix()));
                c.or(() -> c.like(lower(p.categoryId), keyword.toLowerCase(), LikeOption.infix()));
                c.or(() -> c.like(lower(p.description), keyword.toLowerCase(), LikeOption.infix()));
              }
            })
        .fetch();
  }
}

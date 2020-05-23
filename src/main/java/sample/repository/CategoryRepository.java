package sample.repository;

import org.seasar.doma.jdbc.criteria.Entityql;
import org.springframework.stereotype.Repository;
import sample.entity.Category;
import sample.entity.Category_;

@Repository
public class CategoryRepository {

  private final Entityql entityql;

  public CategoryRepository(Entityql entityql) {
    this.entityql = entityql;
  }

  public Category selectCategory(String categoryId) {
    var ca = new Category_();

    return entityql.from(ca).where(c -> c.eq(ca.categoryId, categoryId)).fetchOne();
  }
}

package sample.repository;

import static org.seasar.doma.jdbc.criteria.expression.Expressions.sub;

import java.util.List;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.seasar.doma.jdbc.criteria.NativeSql;
import org.springframework.stereotype.Repository;
import sample.entity.Inventory_;
import sample.entity.Item;
import sample.entity.Item_;
import sample.entity.Product_;

@Repository
public class ItemRepository {

  private final Entityql entityql;
  private final NativeSql nativeSql;

  public ItemRepository(Entityql entityql, NativeSql nativeSql) {
    this.entityql = entityql;
    this.nativeSql = nativeSql;
  }

  public void updateInventoryQuantity(String itemId, Integer increment) {
    var i = new Inventory_();

    nativeSql
        .update(i)
        .set(c -> c.value(i.quantity, sub(i.quantity, increment)))
        .where(c -> c.eq(i.itemId, itemId))
        .execute();
  }

  public Integer selectInventoryQuantity(String itemId) {
    var i = new Inventory_();

    return nativeSql.from(i).where($ -> $.eq(i.itemId, itemId)).select(i.quantity).fetchOne();
  }

  public Item selectItem(String itemId) {
    var it = new Item_();
    var in = new Inventory_();
    var p = new Product_();

    return entityql
        .from(it)
        .innerJoin(in, on -> on.eq(it.itemId, in.itemId))
        .innerJoin(p, on -> on.eq(it.productId, p.productId))
        .where(c -> c.eq(it.itemId, itemId))
        .associate(it, in, Item::setInventory)
        .associate(it, p, Item::setProduct)
        .fetchOne();
  }

  public List<Item> selectItemsByProduct(String productId) {
    var i = new Item_();
    var in = new Inventory_();
    var p = new Product_();

    return entityql
        .from(i)
        .innerJoin(in, on -> on.eq(i.itemId, in.itemId))
        .innerJoin(p, on -> on.eq(i.productId, p.productId))
        .where(c -> c.eq(i.productId, productId))
        .associate(i, in, Item::setInventory)
        .associate(i, p, Item::setProduct)
        .fetch();
  }
}

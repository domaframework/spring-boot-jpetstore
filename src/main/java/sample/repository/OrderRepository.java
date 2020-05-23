package sample.repository;

import java.util.List;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.springframework.stereotype.Repository;
import sample.entity.Inventory_;
import sample.entity.Item;
import sample.entity.Item_;
import sample.entity.Order;
import sample.entity.OrderLineItem;
import sample.entity.OrderLineItem_;
import sample.entity.OrderStatus_;
import sample.entity.Order_;
import sample.entity.Product_;

@Repository
public class OrderRepository {

  private final Entityql entityql;

  public OrderRepository(Entityql entityql) {
    this.entityql = entityql;
  }

  public Order selectOrder(int orderId) {
    var order_ = new Order_();
    var orderStatus_ = new OrderStatus_();
    var orderLineItem_ = new OrderLineItem_();
    var item_ = new Item_();
    var inventory_ = new Inventory_();
    var product_ = new Product_();

    return entityql
        .from(order_)
        .innerJoin(orderStatus_, on -> on.eq(order_.orderId, orderStatus_.orderId))
        .leftJoin(orderLineItem_, on -> on.eq(order_.orderId, orderLineItem_.orderId))
        .leftJoin(item_, on -> on.eq(orderLineItem_.itemId, item_.itemId))
        .innerJoin(inventory_, on -> on.eq(item_.itemId, inventory_.itemId))
        .innerJoin(product_, on -> on.eq(item_.productId, product_.productId))
        .where(c -> c.eq(order_.orderId, orderId))
        .associate(order_, orderStatus_, Order::setOrderStatus)
        .associate(order_, orderLineItem_, Order::addLineItem)
        .associate(orderLineItem_, item_, OrderLineItem::setItem)
        .associate(item_, inventory_, Item::setInventory)
        .associate(item_, product_, Item::setProduct)
        .fetchOne();
  }

  public List<Order> selectOrdersByUsername(String username) {
    var o = new Order_();
    var os = new OrderStatus_();

    return entityql
        .from(o)
        .innerJoin(os, on -> on.eq(o.orderId, os.orderId))
        .where(c -> c.eq(o.username, username))
        .orderBy(c -> c.asc(o.orderDate))
        .associate(o, os, Order::setOrderStatus)
        .fetch();
  }

  public void insertOrder(Order order) {
    var o = new Order_();
    var os = new OrderStatus_();
    var ol = new OrderLineItem_();

    entityql.insert(o, order).execute();

    entityql.insert(os, order.getOrderStatus()).execute();

    entityql.insert(ol, order.getLineItemList()).execute();
  }
}

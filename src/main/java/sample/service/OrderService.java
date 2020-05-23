package sample.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import sample.entity.Order;
import sample.entity.OrderLineItem;
import sample.model.Cart;
import sample.repository.AccountRepository;
import sample.repository.ItemRepository;
import sample.repository.OrderRepository;

@Service
public class OrderService {

  private final ItemRepository itemRepository;
  private final OrderRepository orderRepository;
  private final AccountRepository accountRepository;

  public OrderService(
      ItemRepository itemRepository,
      OrderRepository orderRepository,
      AccountRepository accountRepository) {
    this.itemRepository = itemRepository;
    this.orderRepository = orderRepository;
    this.accountRepository = accountRepository;
  }

  public void insertOrder(Order order) {
    for (var lineItem : order.getLineItemList()) {
      itemRepository.updateInventoryQuantity(lineItem.getItemId(), lineItem.getQuantity());
    }
    orderRepository.insertOrder(order);
  }

  public Order getOrder(int orderId) {
    return orderRepository.selectOrder(orderId);
  }

  public List<Order> getOrdersByUsername(String username) {
    return orderRepository.selectOrdersByUsername(username);
  }

  public Order createNewOrder(String username, Cart cart) {
    var account = accountRepository.selectAccountByUsername(username);

    var order = new Order();
    order.setUsername(account.getUsername());
    order.setOrderDate(LocalDateTime.now());
    order.setShipToFirstName(account.getFirstName());
    order.setShipToLastName(account.getLastName());
    order.setShipAddress1(account.getAddress1());
    order.setShipAddress2(account.getAddress2());
    order.setShipCity(account.getCity());
    order.setShipState(account.getState());
    order.setShipZip(account.getZip());
    order.setShipCountry(account.getCountry());
    order.setBillToFirstName(account.getFirstName());
    order.setBillToLastName(account.getLastName());
    order.setBillAddress1(account.getAddress1());
    order.setBillAddress2(account.getAddress2());
    order.setBillCity(account.getCity());
    order.setBillState(account.getState());
    order.setBillZip(account.getZip());
    order.setBillCountry(account.getCountry());
    order.setTotalPrice(cart.getSubTotal());
    order.setCreditCard("999 9999 9999 9999");
    order.setExpiryDate("12/03");
    order.setCardType("Visa");
    order.setCourier("UPS");
    order.setLocale("CA");
    order.setStatus("P");

    var i = 0;
    for (var cartItem : cart.getCartItemList()) {
      var lineItem = new OrderLineItem();
      lineItem.setLineNumber(++i);
      lineItem.setQuantity(cartItem.getQuantity());
      lineItem.setItemId(cartItem.getItem().getItemId());
      lineItem.setUnitPrice(cartItem.getItem().getListPrice());
      order.addLineItem(lineItem);
    }

    return order;
  }
}

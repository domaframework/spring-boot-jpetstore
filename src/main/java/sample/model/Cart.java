package sample.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import sample.entity.Amount;
import sample.entity.Item;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart implements Serializable {

  private static final long serialVersionUID = 1L;

  private final Map<String, CartItem> itemMap = new HashMap<>();

  private final List<CartItem> itemList = new ArrayList<>();

  public List<CartItem> getCartItemList() {
    return itemList;
  }

  public CartItem getCartItem(String itemId) {
    return itemMap.get(itemId);
  }

  public int getNumberOfItems() {
    return itemList.size();
  }

  public boolean containsItemId(String itemId) {
    return itemMap.containsKey(itemId);
  }

  public void addItem(Item item, boolean isInStock) {
    var cartItem = itemMap.get(item.getItemId());
    if (cartItem == null) {
      cartItem = new CartItem();
      cartItem.setItem(item);
      cartItem.setQuantity(0);
      cartItem.setInStock(isInStock);
      itemMap.put(item.getItemId(), cartItem);
      itemList.add(cartItem);
    }
    cartItem.incrementQuantity();
  }

  public void removeItemById(String itemId) {
    var cartItem = itemMap.remove(itemId);
    if (cartItem != null) {
      itemList.remove(cartItem);
    }
  }

  public void incrementQuantityByItemId(String itemId) {
    var cartItem = itemMap.get(itemId);
    cartItem.incrementQuantity();
  }

  public void setQuantityByItemId(String itemId, int quantity) {
    var cartItem = itemMap.get(itemId);
    cartItem.setQuantity(quantity);
  }

  public Amount getSubTotal() {
    var subTotal = Amount.ZERO;
    for (var cartItem : itemList) {
      var item = cartItem.getItem();
      var listPrice = item.getListPrice();
      subTotal = subTotal.add(listPrice.multiply(cartItem.getQuantity()));
    }
    return subTotal;
  }

  public boolean isEmpty() {
    return itemList.isEmpty();
  }

  public void clear() {
    itemMap.clear();
    itemList.clear();
  }
}
